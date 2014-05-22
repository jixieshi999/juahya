package com.android.xmlinflationdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import com.activity.CacheActivity;
import com.android.juahya.R;
import com.xml.inflate.service.IFlateServicePoxy;

public class MainActivity extends CacheActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View view=test();
//		view=null;
		if(null!=view){
			setContentView(view);
		}else{
			setContentView(R.layout.activity_main);
			TextView tv = (TextView)findViewById(R.id.txt);
			String attrValue="@drawable/ic_launcher";
//			String attrValue="@android:drawable/presence_online";
			if(attrValue.startsWith("@android:drawable/")){
				String value = attrValue.substring(18);
				int id=getResources().getIdentifier("android:drawable/"+value, null,null);
				tv.setBackgroundResource(id);
			}else if(attrValue.startsWith("@drawable/")){
				String value = attrValue.substring(10);
				int id=getResources().getIdentifier(value, "drawable", getPackageName());
				tv.setBackgroundResource(id);
			}
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
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
		return testInflate(str);
	}
	
	
}
