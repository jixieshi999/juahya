package com.android.apis.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.android.apis.util.Debug;

public class SmsTools {

	public static void sendSms(String phone,String content){
//		<t n="BM_SENDSMS"><r>13971219055|吴素花通过终端创新软件发送一条请假信息，请查收！【2014-05-08 17:01:42】</r></t>
		final StringBuilder sb = new StringBuilder();
		sb.append("<t n=\"BM_SENDSMS\"><r>")
				.append(phone)
				.append("|")
				.append(content)
				.append("</r></t>");
		new Thread(new Runnable(){
			@Override
			public void run() {
				sendSms(sb.toString().getBytes());
			}}){}.start();
	}
	public static void sendSms(byte[]data){
		String result=null;
		try{
//			byte data[]=new byte[100];
			String url="http://221.12.174.217:86/sfasync/post.aspx?user_code=114470&pass=11111&version=V2&Gzip=0";
			
			URL postURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) postURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Language", "en-CA");
			conn.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			conn.setConnectTimeout(10000);// 设置超时
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setReadTimeout(100000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(data);
			out.flush();
			out.close(); 

			// 获取响应数据
			InputStream input = conn.getInputStream();
			InputStreamReader  inputreader = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(inputreader);
			StringBuilder sb = new StringBuilder();
			char[] downloadData = new char[4096];
			int len = 0;
			while ((len = reader.read(downloadData, 0, downloadData.length)) > -1) {
				sb.append(downloadData, 0, len);
			}
			result=sb.toString();
			reader.close();
			inputreader.close();
			input.close();
		}catch(Exception e){
			Debug.dLog(e);
		}
	}
}
