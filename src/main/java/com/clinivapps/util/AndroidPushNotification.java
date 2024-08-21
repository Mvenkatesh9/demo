package com.clinivapps.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;


public class AndroidPushNotification {

	private static final String ANDROID_DEVICE_TEST="ANDROID_DEVICE_TEST";

	private static final Logger logger = LoggerFactory.getLogger(AndroidPushNotification.class);
	private static final String GOOGLE_SERVER_KEY = "AAAA688XcXg:APA91bHlR0PODk2jryUAkgpkGASCsLpztVoNYf7dAQhxbX4dghA0AErOCU4T14Hf7ChdoOEXRiSxifOKVqWY--grtL_2uS4EXV8ENfQdS5zbDtlF7dezuazx8BgC7r8k_Fauxa4b0EFO";//"AIzaSyDhBAqFv9ta596CDhz_L7AK0c49E-QFgdo";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	//Test
	public static void main(String[] args) {
		String token="APA91bEN29syK-DNfTVCpqSzewPsWHIIqwYi9Ln2JcQxG8IYlOmrodKkt-fAyOgIsMLvk3PMB8-JT1nlHcTzdaUTdxVotym4EwUkPdvL3cEKn9AZEovXbSc";//APA91bEwc2gCi80b1QgqU9u3rB3Qw9DnKTwgciPAKqbkG2x0CdAT9FT_SW4WZyCvUZwGXpiz_DefA1fIEUYBaoEwGJk_qd9Iqm1b54PaqPRfTM5VtxsW2Ac";
		String message="ANDROID_DEVICE_TEST";
		pushMessage(token, message);
	}

	@SuppressWarnings("unused")
	public static String getCanonicalId(String token) {
		Sender sender = new Sender(GOOGLE_SERVER_KEY);
		Message message=null;
		try {
			 message = new Message.Builder().collapseKey("message").timeToLive(3).delayWhileIdle(true)
					.addData("message", ANDROID_DEVICE_TEST).build();

			Result result = sender.send(message,token,1);
			String canonicalRegId = result.getCanonicalRegistrationId();
			return canonicalRegId;
		} catch (Exception e) {
			logger.error("ERROR-->"+token+"-->"+(message!=null?message:""),e);
			e.printStackTrace();
		}
		return null;
	}


	@SuppressWarnings("unused")
	public static String pushMessage(String token,String messageStr) {
		URL url;
		try {
			url = new URL(API_URL_FCM);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "key=" + GOOGLE_SERVER_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			JSONObject data = new JSONObject();
			data.put("to",token);
			JSONObject info = new JSONObject();
			info.put("title", "ClinIV"); // Notification title
			info.put("body", messageStr); // Notification body
			data.put("notification", info);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data.toString());
			wr.flush();
			wr.close();
			int responseCode = conn.getResponseCode();
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		
//		Sender sender = new Sender(GOOGLE_SERVER_KEY);
//		Message message=null;
//		try {
//			 message = new Message.Builder().collapseKey("message").timeToLive(3).delayWhileIdle(true)
//					.addData("message", messageStr).build();
//
//			Result result = sender.send(message,token,1);
//			String canonicalRegId = result.getCanonicalRegistrationId();
//			logger.info("SUCCESS-->"+token+"-->"+message);
//			return canonicalRegId;
//		} catch (Exception e) {
//			logger.error("ERROR-->"+token+"-->"+(message!=null?message:""),e);
//			e.printStackTrace();
//		}
		return null;
	}
	
	
	@SuppressWarnings("unused")
	public static String pushPrescriptionMessage(String token, String messageStr) {
		Sender sender = new Sender(GOOGLE_SERVER_KEY);
		Message message=null;
		try {
			 message = new Message.Builder().collapseKey("message").timeToLive(3).delayWhileIdle(true)
					.addData("message",messageStr).build();
			 
			Result result = sender.send(message,token,1);
			String canonicalRegId = result.getCanonicalRegistrationId();
			logger.info("SUCCESS-->"+token+"-->"+message);
			return canonicalRegId;
		} catch (Exception e) {
			logger.error("ERROR-->"+token+"-->"+(message!=null?message:""),e);
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unused")
	public static String pushStockMessage(String token,String messageStr, String chatmessage) {
		Sender sender = new Sender(GOOGLE_SERVER_KEY);
		Message message=null;
		try {
			 message = new Message.Builder().collapseKey("message").timeToLive(3).delayWhileIdle(true)
					.addData("message", chatmessage+" "+messageStr).build();

			Result result = sender.send(message,token,1);
			String canonicalRegId = result.getCanonicalRegistrationId();
			logger.info("SUCCESS-->"+token+"-->"+message);
			return canonicalRegId;
		} catch (Exception e) {
			logger.error("ERROR-->"+token+"-->"+(message!=null?message:""),e);
			e.printStackTrace();
		}
		return null;
	}


	@SuppressWarnings("unused")
	public static String push(String token,String message) {
		String canonicalId=getCanonicalId(token);
		if(!Util.isEmpty(canonicalId))
			return canonicalId;
		return pushMessage(token,message);
	}

	@SuppressWarnings("unused")
	private String createBody(String token, String jsonMessage) {
		String gcmJSON = null;
		try {
			gcmJSON = "{" + "\"registration_ids\" : [\"" + token + "\"]," + "\"data\" : {" + jsonMessage + "}" + "}";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return gcmJSON;
	}
}
