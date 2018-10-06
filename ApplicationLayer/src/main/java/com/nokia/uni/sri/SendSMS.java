package com.nokia.uni.sri;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendSMS {
	public static void main(String args[]) {
		sendSms("");
	}
	public static String sendSms(String msg) {
		try {
			// Construct data
			String apiKey = "apikey=" + "q7CsyyTPJhA-tAEqecuqSgA9McJJfUozuBy75Xuqp1";
			String message = "&message=" + msg;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "9972393595";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}