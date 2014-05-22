package com.example.juahyatestdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.PostJuahyaActivity;
import com.android.apis.util.Debug;
import com.xml.inflate.service.IFlateServicePoxy;


public class MainActivity extends PostJuahyaActivity {


	public static String IPARAM_NAME="prase_name";
	String name;
	IFlateServicePoxy service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		service = new IFlateServicePoxy();
		service.setIJuahyaLayoutInflateListener(MainActivity.this);
		
		name=getIntent().getStringExtra(IPARAM_NAME);
		if(null==name||"".equals(name)){
			name="test.xml";
		}
//		getNetStr("test.txt");
		getNetStr(name);
		
//		View view=test();
//		view=null;
//		if(null!=view){
//			setContentView(view);
//		}else{
//			setContentView(R.layout.activity_main);
//			TextView tv = (TextView)findViewById(R.id.txt);
//			String attrValue="@drawable/ic_launcher";
////			String attrValue="@android:drawable/presence_online";
//			if(attrValue.startsWith("@android:drawable/")){
//				String value = attrValue.substring(18);
//				int id=getResources().getIdentifier("android:drawable/"+value, null,null);
//				tv.setBackgroundResource(id);
//			}else if(attrValue.startsWith("@drawable/")){
//				String value = attrValue.substring(10);
//				int id=getResources().getIdentifier(value, "drawable", getPackageName());
//				tv.setBackgroundResource(id);
//			}
//			
//		}
	}

	Handler handler = new Handler();

	@Override
	public void postData(String datass) {
		postNewToast(datass);
		super.postData(datass);
	}
	void postNewToast(final String str){
		handler.post(new Runnable(){
			@Override
			public void run() {
				Toast.makeText(MainActivity.this,str, Toast.LENGTH_LONG).show();
			}});
	}


	View  testInflate(String str){
		String Result="";
		IFlateServicePoxy service = new IFlateServicePoxy();
		service.setIJuahyaLayoutInflateListener(this);
		try {
			  try {
	                InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open("test.xml")); 
	                BufferedReader bufReader = new BufferedReader(inputReader);
	                String line="";
	                while((line = bufReader.readLine()) != null)
	                    Result += line;
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service.inflate(Result, this);
	}
	View test(){
		String str=""
				+"<LinearLayout android:orientation=\"vertical\" "
					+" android:layout_width=\"fill_parent\" android:layout_height=\"fill_parent\" "
						+"	android:gravity=\"center_horizontal\" android:layout_below=\"@id/title_id\"> "
						+"<LinearLayout android:orientation=\"vertical\" "
						+" android:layout_width=\"fill_parent\" android:layout_height=\"wrap_content\" "
						+"	android:gravity=\"center_horizontal\" android:layout_below=\"@id/title_id\"> "
						+"<TextView "
						+" android:id=\"@+id/Report_text\" android:layout_height=\"wrap_content\" android:layout_width=\"fill_parent\" "
						+" android:text=\"qwe\" android:layout_marginTop=\"50\"  "
						+"	/>"
						+"</LinearLayout>"
					
						+"<TextView "
						+" android:id=\"@+id/Report_text\" android:layout_height=\"wrap_content\" android:layout_width=\"fill_parent\" "
							+" android:text=\"asd\" android:layout_marginTop=\"50\"  "
							+"	/>"
							+"<LinearLayout android:orientation=\"vertical\" "
							+" android:layout_width=\"fill_parent\" android:layout_height=\"fill_parent\" "
							+"	android:gravity=\"center_horizontal\" android:layout_below=\"@id/title_id\"> "
							+"<TextView "
							+" android:id=\"@+id/Report_text\" android:layout_height=\"fill_parent\" android:layout_width=\"fill_parent\" "
							+" android:text=\"bnm\" android:layout_marginTop=\"50\"  "
							+"	/>"
							+"</LinearLayout>"
			+"</LinearLayout>"
							;
		
		//http://jixieshi999.github.io/ilife/juahya/test.xml
		return testInflate(str);
	}

	void getNetStr(final String name){
		  new Thread(new Runnable(){
			@Override
			public void run() {
				final String result=getNetStrThread(name);
				handler.post(new Runnable(){
					@Override
					public void run() {
						View view= service.inflate(result, MainActivity.this);
						setContentView(view);
					}});
			}}).start();
	}
	  String getNetStrThread(String name){
		  	StringBuilder sbURL = new StringBuilder();
			sbURL.append("http://").
			append("jixieshi999.github.io/ilife").append("/")
			.append("juahya").append("/")
			.append(name);
			
		String baseUrl = sbURL.toString();
		Debug.dLog("url:"+baseUrl);
		//将URL与参数拼接  
		HttpGet getMethod = new HttpGet(baseUrl);  
		              
		HttpClient httpClient = new DefaultHttpClient();  
		String result=null;
		try {
		    HttpResponse response = httpClient.execute(getMethod); //发起GET请求  
//		    Debug.dLog("reponse:"+response.toString());
		    result=EntityUtils.toString(response.getEntity(), "utf-8");
		    Debug.dLog("reponse:"+result);
		    
//		    Log.i(TAG, "resCode = " + response.getStatusLine().getStatusCode()); //获取响应码  
//		    Log.i(TAG, "result = " + EntityUtils.toString(response.getEntity(), "utf-8"));//获取服务器响应内容  
		} catch (Exception e) {
			Debug.dLog(e);
		}
		return result;
	}
	
}
