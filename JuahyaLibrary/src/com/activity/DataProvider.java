package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.apis.util.Debug;
import com.android.apis.util.DebugUtil;
import com.android.juahya.R;


/**
 * @author steven.jiang
 * 
 */
public class DataProvider extends SQLiteOpenHelper {
	private String TAG =DataProvider.class.getSimpleName(); 
	/** The name of the database file on the file system */
	private static final String DATABASE_NAME = "juahya";
	/** The Initial version  */
	private static int dataBaseVersion = 1;
	/** Keep track of context so that we can load SQL from string resources */
	private final Context mContext;
	static HashMap<String, String> mapLocal ;
	// private SQLiteDatabase db;

	// private SQLiteDatabase getDB()
	// {
	//		
	// if(db == null)
	// db = this.getWritableDatabase();
	//		
	// return db;
	// }

	/** Constructor */
	public DataProvider(Context context, int version) {
		super(context, DATABASE_NAME, null, version);
		this.mContext = context;
		dataBaseVersion = version;
		getWritableDatabase();
		initLocalTable();
		// getDB();
	}
	void initLocalTable(){
		if(null==mapLocal){
			mapLocal= new HashMap<String, String>();
		}else{
			return;
		}
		mapLocal.put("CollectHead", "CREATE TABLE  IF NOT EXISTS CollectHead("
				+"headID varchar PRIMARY KEY,"
				+"configTableName  VARCHAR ,"
				+"createtime  DATETIME ,"
				+"updatetime  DATETIME ,"
				+"record  VARCHAR ,"
				+"dirty int default 0 "
				+");"
				);
		mapLocal.put("CollectDetail", "CREATE TABLE  IF NOT EXISTS CollectDetail("
				+"collectDetailID varchar PRIMARY KEY,"
				+"configTableName  VARCHAR ,"
				+"value  VARCHAR ,"
				+"collectHeadID  VARCHAR ,"
				+"columnName  VARCHAR ,"
				+"dirty int default 0 "
				+");"
				);
		mapLocal.put("ConfigTables", "CREATE TABLE  IF NOT EXISTS ConfigTables("
				+"name varchar PRIMARY KEY,"
	            +"lastUpdateTime  DATETIME ,"
	            +"description varchar"
				+");"
				);
		mapLocal.put("ConfigColumn", "CREATE TABLE  IF NOT EXISTS ConfigColumn("
				+"columnname varchar ,"
				+"type  VARCHAR ,"
				+"configTableName  VARCHAR ,"
				+"description  VARCHAR ,"
				+"PRIMARY KEY(configTableName,columnname)"
				+");"
				);
		mapLocal.put("LogTable", "CREATE TABLE  IF NOT EXISTS LogTable("
				+"collectHeadID varchar PRIMARY KEY,"
				+"status  VARCHAR ,"
	            +"updatetime  VARCHAR ,"
	            +"logName  VARCHAR "
	            +");"
            );
		Iterator<Entry<String, String>> iter = mapLocal.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next(); 
		    String key = entry.getKey(); 
		    String val = entry.getValue(); 
		    if(!tableIsExist(key)){
				SQLiteDatabase db = getWritableDatabase();
				db.execSQL(val);
			}
		} 
	}
	
//	/** 设置数据库版本 */
//	public void needUpgrade(){
//		SQLiteDatabase db = getWritableDatabase();
//		int oldVersion = db.getVersion();
//		dataBaseVersion = ++oldVersion;
//		db.close();
//	}
	
	public  android.database.sqlite.SQLiteCursor query(String SQL) {
		// getDB();
		SQLiteDatabase db = getWritableDatabase();
		// SQLiteDatabase db = this.getReadableDatabase();
		if(db.isOpen()){
			SQLiteCursor cursor = (android.database.sqlite.SQLiteCursor) db
			.rawQuery(SQL, null);
			return cursor;
		}else{
			return null;
		}
	
	}
	
	public  android.database.sqlite.SQLiteCursor query(String SQL,String[] bindArgs) {
		// getDB();
		SQLiteDatabase db = getWritableDatabase();
		// SQLiteDatabase db = this.getReadableDatabase();
		if(db.isOpen()){
			SQLiteCursor cursor = (android.database.sqlite.SQLiteCursor) db
			.rawQuery(SQL, bindArgs);
			return cursor;
		}else{
			return null;
		}
	
	}

	// /执行SQL语句
	public void execute(String SQL) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(SQL);
		///============================db.close();
	}

	// 数据库更新操作
	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		SQLiteDatabase db = getWritableDatabase();
		int num = db.update(table, values, whereClause, whereArgs);
		// db.close();
		return num;
	}

	// 数据库数据插入操作 
	public long insert(String table, String nullColumnHack, ContentValues values) {
		SQLiteDatabase db = getWritableDatabase();
		//  Level 8
//		long num = db.insertWithOnConflict(table, nullColumnHack, values,
//				SQLiteDatabase.CONFLICT_IGNORE);
		long num = db.insert(table, nullColumnHack, values);
		// db.close();
		return num;
		// return db.insert(table, nullColumnHack, values);
	}

	public void  execute(String SQL, Object[] bindArgs) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(SQL, bindArgs);
	///============================db.close();
	}

	public android.database.sqlite.SQLiteCursor getCursor(String TableName, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy )
	{
		SQLiteDatabase db = getWritableDatabase();
		SQLiteCursor cursor= (android.database.sqlite.SQLiteCursor)db.query(TableName, columns, selection, selectionArgs, groupBy, having, orderBy);
		// db.close();
		return cursor;
	}

	/**
	 * Execute all of the SQL statements in the String[] array
	 * 
	 * @param db
	 *            The database on which to execute the statements
	 * @param sql
	 *            An array of SQL statements to execute
	 */
	private void execMultipleSQL(SQLiteDatabase db, String[] sql) {
		for (String s : sql)
			if (s.trim().length() > 0){
				if(s.trim().startsWith("#")){
					//日志
				}else{
					db.execSQL(s);
				}
			}
	}
	/**
	 * 查找字段是否存在
	 * @param colName
	 * @return
	 */
	public boolean columnIsExist(String colName, String tableName){
		boolean isExist = false;
		try{
			String sql = "select " + colName + " from " + tableName;
			SQLiteCursor cursor = this.query(sql);
			while(cursor.moveToNext()){
				isExist = true;
				break;
			}
			cursor.close();
		}catch(Exception ex){
			// ex.printStackTrace();
		}
		return isExist;
	}
	
	/**
	 * 查找某表是否存在
	 * @param tableName
	 * @return
	 */
	public boolean tableIsExist(String tableName){
		boolean isExist = false;
		if(tableName == null){
			return false;
		}
		try{
			String sql = "select count(*) from  Sqlite_master  where type ='table' and name ='"+tableName.trim()+"' ";
			SQLiteCursor cursor = this.query(sql);
			while(cursor.moveToNext()){
				int count = cursor.getInt(0);   
				if(count > 0){
					isExist = true;
				}
			}
			cursor.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return isExist;
	}

	/** Called when it is time to create the database */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] sql = mContext.getString(R.string.DB_INSERT).split(";");
		db.beginTransaction();
		try {
			// Create tables & test data
			// db.setVersion(1);
			execMultipleSQL(db, sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Debug.dLog(e);
			Log.e("Error creating tables and debug data", e.toString());
		} finally {
			db.endTransaction();
		}

	}

	/** Called when the database must be upgraded */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("DBLOG", "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");

		String[] sql = mContext.getString(R.string.DB_UPGRADE).split(";");
		db.beginTransaction();
		try {
			// Create tables & test data
			execMultipleSQL(db, sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Debug.dLog(e);
			Log.e("Error creating tables and debug data", e.toString());
		} finally {
			db.endTransaction();
		}

		// This is cheating. In the real world, you'll need to add columns, not
		// rebuild from scratch
		// onCreate(db);
	}
	public List<Map<String,?>> rawQuery(String sql,String[] args){
    	Log.d(TAG,sql);
		List<Map<String,?>> result = new ArrayList<Map<String,?>>();
		SQLiteDatabase database = getReadableDatabase();
		Cursor cursor=null;
		try {
			cursor= database.rawQuery(sql, args);
			int columnCount = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				Map<String, String> map = new LinkedHashMap<String,String>(); 
				for (int i = 0; i < columnCount; i++) {
					map.put(cursor.getColumnName(i),cursor.getString(i));
//					Log.d(TAG,cursor.getColumnName(i)+"  "+cursor.getString(i));
				}
				result.add(map);
			}
		} catch (Exception e) {
			Log.e(TAG,e.getLocalizedMessage());
			Debug.dLog(e);
		}finally{
			if(cursor!=null && !cursor.isClosed())
				cursor.close();
		}
		
		return result;
	}
	
	public String executeScalar(String sql,String[] args){
    	String result = null;
    	Cursor cursor = null;
    	DebugUtil.dLog(DATABASE_NAME, sql);
		SQLiteDatabase database =getWritableDatabase();
		try{	
			cursor = database.rawQuery(sql, args);
			if (cursor.getCount()>0) {
				if(cursor.moveToNext()) {
					result=cursor.getString(0);
				}
			}
			
		}catch (Exception e) {
			DebugUtil.dLog(DATABASE_NAME, e);
		}finally{
			if(cursor!=null && !cursor.isClosed())
				cursor.close();
		}
		return result;
    }
	public int getCount(String sql,String[] args){
		int result = 0;
		Cursor cursor = null;
		DebugUtil.dLog(DATABASE_NAME, sql);
		SQLiteDatabase database =getWritableDatabase();
		try{	
			cursor = database.rawQuery(sql, args);
			if (cursor.getCount()>0) {
				if(cursor.moveToNext()) {
					result=cursor.getInt(0);
				}
			}
			
		}catch (Exception e) {
			DebugUtil.dLog(DATABASE_NAME, e);
		}finally{
			if(cursor!=null && !cursor.isClosed())
				cursor.close();
		}
		return result;
	}
}
