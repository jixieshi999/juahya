package com.activity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.apis.util.Debug;
import com.xml.inflate.inflater.IJuahya;


/**
 * ���ɼ������ݷ�װ��json array ͨ��post����ϴ� 
 * */
public class PostJuahyaActivity extends JuahyaActivity {

	static String ATTRPOSTURL="action_url";
	String postUrl;

	@Override
	public void onJuahyaLayoutInflate(IJuahya ijuahya) {
		if(ATTRPOSTURL.equals(ijuahya.getAttrKey())){
			postUrl=ijuahya.getAttrDescription();
		}else{
			super.onJuahyaLayoutInflate(ijuahya);
		}
	}
	/**�����ݸ�ʽ����json array*/
	protected String fomatData(){
		ArrayList<IJuahya> IJuahyaList=getIJuahyaList();
		JSONArray ja=new JSONArray();
		JSONObject jo=new JSONObject();
		for(IJuahya ij:IJuahyaList){
			try {
				jo=new JSONObject();
				jo.put(ij.getAttrKey(), ij.getValue());
				ja.put(jo);
			} catch (JSONException e) {
				Debug.dLog(e);
			}
		}
		return ja.toString();
	}
	
	/**
	 * ȷ��postUrl ��Ϊ��
	 * */
	public void postData(String datass){
		if(null==postUrl||"".equals(postUrl)){
			Debug.dLog("on postData but postUrl is null ");
			return;
		}
		String EncryptionResult=datass;
		
		/**�������ݣ�֧�ֶ�μ���*/
		if(null!=mFomatDataListenerList){
			for(FomatDataListener fd:mFomatDataListenerList){
				EncryptionResult=fd.onEncryption(EncryptionResult);
			}
		}
		
		int TimeOut=9000;
		boolean enabelgzip=false;
		String charset="UTF-8";
//		java.io.ByteArrayOutputStream stream = new ByteArrayOutputStream();
//		byte[] data=stream.toByteArray();
		byte[] data=null;
		try {
			data = EncryptionResult.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			Debug.dLog(e);
		}
		int length = 0; // ��¼���ͺͽ��յ����������ȡ�
		String result=null;
		try {
			Debug.dLog(postUrl);
			// ��������������
			URL postURL = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) postURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Language", "en-CA");
			conn.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			if (enabelgzip)conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
			conn.setConnectTimeout(TimeOut);// ���ó�ʱ
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Charset", charset);
			conn.setReadTimeout(TimeOut);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();

			length += data.length;

			OutputStream out = null;
			if (enabelgzip) {
				out = new GZIPOutputStream(conn.getOutputStream());
			} else {
				out = new DataOutputStream(conn.getOutputStream());
			}
			out.write(data);
			out.flush();
			out.close(); // flush and close

			// ��ȡ��Ӧ����
			InputStream input = conn.getInputStream();
			InputStreamReader inputreader = null;
			if (enabelgzip) {
				inputreader = new InputStreamReader(new GZIPInputStream(input));
			} else {
				inputreader = new InputStreamReader(input);
			}
			BufferedReader reader = new BufferedReader(inputreader);
			StringBuilder sb = new StringBuilder();

			char[] downloadData = new char[4096];
			int len = 0;
			while ((len = reader.read(downloadData, 0, downloadData.length)) > -1) {
				sb.append(downloadData, 0, len);
			}
			result=sb.toString();
		} catch (Exception ex) {
			Debug.dLog(ex);
		}
		Debug.dLog(result);
//		return result;
	
	}
	@Override
	String saveData(final String fomatData) {
		return fomatData;
//		return "<t n=\"BM_Other_details_Tranc\"><r>8D5E2E09-0B24-4AFE-9BB4-35AB2D4CD3F9|9F91D248-66F4-42F4-AD26-66EF889F3704|||���ƽ�ʲô|ԭ����ʲô</r></t>" +
//				"<t n=\"BM_Other_Head_Tranc\"><r>9F91D248-66F4-42F4-AD26-66EF889F3704|4353|194459|1||2014-04-27 10:50:11||</r></t>";
	}
	@Override
	void uploadData(final String saveData) {
		new Thread(new Runnable(){
		@Override
		public void run() {
			postData(saveData);
		}}).start();
		}
	@Override
	public void onStartLink(String name) {
		
	}
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}
	
}
