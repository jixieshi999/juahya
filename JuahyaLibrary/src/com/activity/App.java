package com.activity;

import android.content.Context;

public class App {

	static DataProvider mDataProvider;
	public static DataProvider getDataProvider(Context context){
		if(null==mDataProvider){
			mDataProvider=new DataProvider(context,com.android.apis.util.VersionUtil.getVersionCode(context));
		}
		return mDataProvider;
	}
}
