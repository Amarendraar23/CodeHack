package com.nokia.uni.sri.controller;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.uni.sri.domain.Device;
import com.nokia.uni.sri.domain.DeviceData;
import com.nokia.uni.sri.services.DeviceService;

@RestController
public class DeviceController {

	private final DeviceService deviceService;

	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@PostMapping("/registerDevice")
	public Device registerDevice(@RequestBody Device device) {
		return deviceService.registerDevice(device);
	}
	
	@PostMapping("/devices")
	public String devices(@RequestBody DeviceData deviceData) {
		System.out.println("==================="+deviceData);
		String response = "{\r\n" + 
				"	\"correlatorId\": \""+deviceData.getCorrelatorId()+"\",\r\n" + 
				"	\"requestStatus\": 0,\r\n" + 
				"	\"responseCode\": 0,\r\n" + 
				"	\"protocol\": \"http\",\r\n" + 
				"	\"response\": [{\r\n" + 
				"		\"device/0/endPointClientName\": \""+deviceData.getRead()[0].getEndPointClientNames()[0]+"\",\r\n" + 
				"		\"device/0/location\": \"bangalore\"\r\n" + 
				"	}]\r\n" + 
				"}";
		doCurlResponse(response);
		return response;
	}
	
	@GetMapping("/updateDevice/{name}")
	public String updateDevice(@PathVariable("name") String deviceData) {
		System.out.println("==================="+deviceData);
		String response = "{\r\n" + 
				"	\"notify2\": [{\r\n" + 
				"		\"device/0/endPointClientName\": \"RFID-1\",\r\n" + 
				"		\"resource\": \"device/0/location\",\r\n" + 
				"		\"value\": \""+deviceData+"\"\r\n" + 
				"	}]\r\n" + 
				"}" ;
		doUpdateResponse(response);
		return "";
	}
	
	private void doUpdateResponse(String deviceData) {
		try {
			String url = "http://10.58.127.156:31443/m2m/device";
			URL obj = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			String userpass = "geo" + ":" + "geo123";
			String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
			conn.setRequestProperty ("Authorization", basicAuth);
			conn.setRequestProperty("x-adaptation_layer_id", "impact_geo");


			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(deviceData);
			out.close();
			new InputStreamReader(conn.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doCurlResponse(String response) {
		try {
			String url = "http://10.58.127.156:31443/m2m/device";
			URL obj = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			String userpass = "geo" + ":" + "V4wqO+XE72MWoDa+NPSReHk/6e9udTfVjEkd3Fd8Z6w=";
			String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
			conn.setRequestProperty ("Authorization", basicAuth);
			conn.setRequestProperty("x-adaptation_layer_id", "impact_geo");


			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(response);
			out.close();
			new InputStreamReader(conn.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
