package com.activity;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.widget.Toast;

import com.android.apis.util.DateUtil;
import com.android.apis.util.Debug;
import com.xml.inflate.inflater.IJuahya;


/**save data to database cache*/
public class CacheActivity extends JuahyaActivity{

	public static String SYNC_STATUS_PREPARE="1";
	public static String SYNC_STATUS_UPLOADING="2";
	public static String SYNC_STATUS_DONE="3";
	public static String SYNC_STATUS_SUCCESS="4";
	public static String SYNC_STATUS_ERROR="5";
	
	protected static String ATTRTABLE_NAME="action_tablename";
	protected String tablename;
	protected String tableDescription;
	protected String record;
	protected String headid;
	protected DataProvider mDataProvider;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mDataProvider=App.getDataProvider(this);
	}

	@Override
	public void onJuahyaLayoutInflate(IJuahya ijuahya) {
		if(ATTRTABLE_NAME.equals(ijuahya.getAttrKey())){
			tableDescription=ijuahya.getAttrDescription();
			tablename=ijuahya.getAttrType();
		}else{
			super.onJuahyaLayoutInflate(ijuahya);
		}
	}
	
	@Override
	protected String fomatData() {
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
	@Override
	protected String saveData(String fomatData) {
//		if(mDataProvider.getCount(
//				"SELECT count(*) FROM sqlite_master WHERE type='table' AND name=? ",
//				new String[]{tablename})>0){
		String date=DateUtil.getDefaultDateTime();
		headid=UUID.randomUUID().toString();
		
		//将数据插入到采集主表
		String sqlhead="replace into CollectHead(headID,configTableName,createtime,updatetime,record,dirty) values(?,?,?,?,?,1)";
			mDataProvider.execute(sqlhead, new String[]{headid,tablename,date,date,record});
			
			//将数据插入到配置表中
			String sqlConfig="replace into ConfigTables(name,lastUpdateTime,description) values(?,?,?)";
			mDataProvider.execute(sqlConfig, new String[]{tablename,date,tableDescription});
			
			String sqlcol="replace into ConfigColumn(columnname,type,configTableName,description) values(?,?,?,?)";
			
			String sqlcoldetail="replace into CollectDetail(collectDetailID,configTableName,value,collectHeadID,columnName,dirty) " +
					"values(?,?,?,?,?,1)";
			String guidline=null;
			ArrayList<IJuahya> IJuahyaList=getIJuahyaList();
			StringBuilder sbcol=new StringBuilder();
			for(IJuahya ij:IJuahyaList){
				//将数据插入到列配置表中
				mDataProvider.execute(sqlcol, new String[]{ij.getAttrKey(),ij.getAttrType(),tablename,ij.getAttrDescription()});

				//将数据插入到采集明细表中
				guidline=UUID.randomUUID().toString();
				mDataProvider.execute(sqlcoldetail, new String[]{guidline,tablename,ij.getValue(),headid,ij.getAttrKey()});
				
			}
			
//			String sql="create table ("+sbcol.toString()+") ";
			
//		}
		
		return fomatData;
	}
	@Override
	protected void uploadData(String saveData) {
		updateSyncLog(headid,SYNC_STATUS_PREPARE,tableDescription+"("+DateUtil.getFormatTime("MM-dd HH:mm")+")");
		Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * @param headid 
	 * @param status 
	 * @param updatetime 
	 * @param logName 
	 * */
	public void updateSyncLog(String headid,String status,String logname){
		String date=DateUtil.getDefaultDateTime();
		String sqlhead="replace into LogTable(collectHeadID,status,updatetime,logName) values(?,?,?,?)";
		mDataProvider.execute(sqlhead, new String[]{headid,status,date,logname});
	}

	@Override
	public void onStartLink(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		
	}
}
