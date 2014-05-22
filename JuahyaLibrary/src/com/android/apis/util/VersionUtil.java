package com.android.apis.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.webkit.URLUtil;

public class VersionUtil {
	public Context context;

	private static final int SHOW_MAIN = 1;
	private static final int CHECK_DATA = 2;

	private static final String TAG = "VersionUtil";
	private ResultListener resultListener;

	/** 下载进度滚动条 */
	private ProgressDialog progressDialog;

	private boolean FLAG;

	// 获取sd卡 目录
	public static String SDPATH = Environment.getExternalStorageDirectory()
			+ "/";

	public String currentVersion;

    private String updatedUrl;
    
	public VersionUtil(Context context, ResultListener resultListener,
			String currentVersion) {
		this.context = context;
		this.resultListener = resultListener;
		FLAG = true;
		this.currentVersion = currentVersion;
//        updatedUrl = GlobalInfo.User.CabFile;
	}

	/** 版本匹配结果 */
	public boolean checkVersion() {
		PackageManager pm = context.getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(context.getPackageName(), 0);
			String newVersion = pi.versionName;
			if (!currentVersion.equals(newVersion) && checkSDCard()) {
				// 检查是否有需要同步的数据
//				if (checkData()) {
//				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("当前版本：");
					sb.append(newVersion).append("\n");
					sb.append("最新版本：").append(currentVersion);
					showInformation(sb.toString());
//				}
				return false;
			} else {
				// 不需要更新或SD卡不存在
				resultListener.resultListener(SHOW_MAIN);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 取到当前程序的版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		int versionNum = 0;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionNum = pi.versionCode;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return versionNum;
	}
	/**
	 * 取版本名
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context){
		String version = "1.0";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			version = pi.versionName;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return version;
	}

	public void showInformation(String str) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setTitle("有新版本需要更新");
		alert.setMessage(str);
		alert.setPositiveButton("确定", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				// 滚动条
				progressDialog = new ProgressDialog(context);
				progressDialog.setMessage("正在下载更新文件,请稍等...");
				progressDialog.setOnDismissListener(dismissListener);
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.show();
				Thread th = new Thread(runDownFile);
				th.start();
			}
		});
		alert.show();

	}

	/** progress dialog dismiss listener */
	private DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener() {
		@Override
		public void onDismiss(DialogInterface dialog) {
			// 停止下载
			FLAG = false;
		}
	};

	Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				progressDialog.dismiss();
				Log.v(TAG, "SDPATH = " + SDPATH);
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(
						Uri.parse("file://" + SDPATH + "SFA.apk"),
						"application/vnd.android.package-archive");
				context.startActivity(intent);
				break;
			}
		};
	};

	Runnable runDownFile = new Runnable() {
		@Override
		public void run() {
		    Debug.dLog("ebest","download file : "+updatedUrl);
			downloadNewFile(updatedUrl);
			if(context!=null){
			    Debug.dLog("delete sfa database ");
			    context.deleteDatabase("SFA");
//                SharedPreferenceProvider.ClearnData(context);
			}
			if (FLAG) {
				// 下载完成
				handler.sendEmptyMessage(1);
			}
		}
	};

	private void downloadNewFile(String urlPath) {
		// Log.i(TAG, "getDataSource()");
		if (!URLUtil.isNetworkUrl(urlPath)) {
			Log.i(TAG, urlPath + " is a wrong URL!");
		} else {
			URL dlURL;
			try {
				dlURL = new URL(urlPath);
				URLConnection conn = dlURL.openConnection();
				conn.connect();

				InputStream is = conn.getInputStream();

				if (is == null) {
					throw new RuntimeException("stream is null");
				}

				File dlFile = new File(SDPATH + "SFA.apk");
				if (dlFile.exists()) {
					// 存在，先将其删除
					dlFile.delete();
				}
				FileOutputStream fos = new FileOutputStream(dlFile);
				byte buf[] = new byte[128];
				do {
					int numread = is.read(buf);
					if (numread <= 0) {
						break;
					}
					fos.write(buf, 0, numread);
				} while (FLAG);
				buf = null;
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/** 结果处理接口 */
	public abstract interface ResultListener {
		public abstract void resultListener(int code);
	}

	/***
	 * sd卡是否存在
	 * 
	 * @return
	 */
	private boolean checkSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return true;
		}
	}

	// 检查数据
}
