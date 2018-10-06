package com.nokia.uni.sri.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.uni.sri.services.HackService;


@RestController
@RequestMapping("/m2m/impact")
public class HackController {

	private final HackService hackService;

	public HackController(HackService hackService) {
		this.hackService = hackService;
	}

    @PostMapping("/callback")
	public void createDevice() {

		System.out.println("====================================");
		System.out.println("====================================");
	}

	@PutMapping("/registerWithImpact")
	public void registerWithImpact() {
		doCurlRegister();
	}

	@GetMapping("/getTokenFromImpact")
	public void getTokenFromImpact() {
		doGetTokenFromImpact();
	}

	private void doCurlRegister() {
		try {
			String url = "http://10.58.127.155:31090/m2m/applications/registration";
			URL obj = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");

			String userpass = "geo" + ":" + "geo123";
			String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
			conn.setRequestProperty ("Authorization", basicAuth);

			String data =  "{\"format\":\"json\",\"pattern\":\"#\",\"headers\": {\"authorization\":\"Basic dWF0YWRlcDpBc2RmMSM=\"}, \"url\":\"http://10.58.127.22:8080/m2m/impact/callback\"}";

			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(data);
			out.close();
			new InputStreamReader(conn.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doGetTokenFromImpact() {
		try {
			String url = "http://10.58.127.155:31090/m2m/token?groupName=impact_geo";
			URL obj = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");

			String userpass = "geo" + ":" + "geo123";
			String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
			conn.setRequestProperty ("Authorization", basicAuth);

			BufferedReader br =  new BufferedReader( new InputStreamReader(conn.getInputStream()) );
			String tmp;
			while((tmp=br.readLine()) != null) {
				System.out.println(tmp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
