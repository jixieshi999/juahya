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

	/** ���ؽ��ȹ����� */
	private ProgressDialog progressDialog;

	private boolean FLAG;

	// ��ȡsd�� Ŀ¼
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

	/** �汾ƥ���� */
	public boolean checkVersion() {
		PackageManager pm = context.getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(context.getPackageName(), 0);
			String newVersion = pi.versionName;
			if (!currentVersion.equals(newVersion) && checkSDCard()) {
				// ����Ƿ�����Ҫͬ��������
//				if (checkData()) {
//				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("��ǰ�汾��");
					sb.append(newVersion).append("\n");
					sb.append("���°汾��").append(currentVersion);
					showInformation(sb.toString());
//				}
				return false;
			} else {
				// ����Ҫ���»�SD��������
				resultListener.resultListener(SHOW_MAIN);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * ȡ����ǰ����İ汾��
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
	 * ȡ�汾��
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
		alert.setTitle("���°汾��Ҫ����");
		alert.setMessage(str);
		alert.setPositiveButton("ȷ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				// ������
				progressDialog = new ProgressDialog(context);
				progressDialog.setMessage("�������ظ����ļ�,���Ե�...");
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
			// ֹͣ����
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
				// �������
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
					// ���ڣ��Ƚ���ɾ��
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

	/** �������ӿ� */
	public abstract interface ResultListener {
		public abstract void resultListener(int code);
	}

	/***
	 * sd���Ƿ����
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

	// �������
}
