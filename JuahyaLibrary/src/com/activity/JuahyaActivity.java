package com.activity;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xml.inflate.inflater.IJuahya;
import com.xml.inflate.inflater.IJuahyaLayoutInflateListener;


public abstract class JuahyaActivity extends BaseActivity implements IJuahyaLayoutInflateListener{

	/**保存数据到本地*/
	static String ATTRACTIONSAVE="action_save";
	/**直接上传数据，不保存本地*/
	static String ATTRACTIONUPLOAD="action_upload";
	/**保存本地，然后上传数据*/
	static String ATTRACTIONSAVE_UPLOAD="action_save_upload";
	static String ATTRACTIONBACK="action_back";
	String ID_ACTIONSAVE;
	String ID_ACTION_SAVE_UPLOAD;
	String ID_ACTION_UPLOAD;
	String ID_ACTIONBACK;
	

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    DisplayImageOptions options;
//    imageLoader.displayImage(item._field, img, options, animateFirstListener);
    
	protected ArrayList<IJuahya> mIJuahyaList=new ArrayList<IJuahya>();
	public ArrayList<IJuahya> getIJuahyaList() {
		return mIJuahyaList;
	}
	/**格式化数据*/
	abstract String fomatData();
	/**保存数据
	 * @param str fomatData return */
	abstract String saveData(String fomatData);
	/**上传数据
	 * @param saveData return
	 * */
	abstract void uploadData(String saveData);

	ArrayList<FomatDataListener> mFomatDataListenerList;
	public interface FomatDataListener{
		String onDecrypt(String str);
		String onEncryption(String str);
	}

    public void initImageLoaderOptions() {
//		mAsynHandler = AsyncHandlerFactory.createAsynHandler(AsyncHandlerFactory.Mode.MODE_NET, mAsynHandlerCallback);

        options = new DisplayImageOptions.Builder()
            .showStubImage(android.R.drawable.progress_horizontal)
            .showImageForEmptyUri(android.R.drawable.progress_horizontal)
            .showImageOnFail(android.R.drawable.progress_horizontal)
            .cacheInMemory()
            .cacheOnDisc()
            .displayer(new RoundedBitmapDisplayer(2))
            .build();
        
	}
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initOnSaveAction();
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		initOnSaveAction();
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		initOnSaveAction();
	}

	protected void initOnSaveAction() {
		if(null!=ID_ACTIONSAVE){
			int ids=Integer.valueOf(ID_ACTIONSAVE);
			View view = findViewById(ids);
			if(null==view)return;
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					saveData(fomatData());
				}
			});
		}
		if(null!=ID_ACTIONBACK){
			int ids=Integer.valueOf(ID_ACTIONBACK);
			View view = findViewById(ids);
			if(null==view)return;
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
//					finish();
					onBackPressed();
				}
			});
		}
		if(null!=ID_ACTION_UPLOAD){
			int ids=Integer.valueOf(ID_ACTION_UPLOAD);
			View view = findViewById(ids);
			if(null==view)return;
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					uploadData(fomatData());
				}
			});
		}
		if(null!=ID_ACTION_SAVE_UPLOAD){
			int ids=Integer.valueOf(ID_ACTION_SAVE_UPLOAD);
			View view = findViewById(ids);
			if(null==view)return;
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
//					saveData(fomatData());
					uploadData(saveData(fomatData()));
				}
			});
		}
	}
	@Override
	public void onJuahyaLayoutInflate(IJuahya ijuahya) {
		if(ATTRACTIONSAVE.equals(ijuahya.getAttrKey())){
			ID_ACTIONSAVE=ijuahya.getAttrDescription();
		}else if(ATTRACTIONBACK.equals(ijuahya.getAttrKey())){
			ID_ACTIONBACK=ijuahya.getAttrDescription();
		}else if(ATTRACTIONSAVE_UPLOAD.equals(ijuahya.getAttrKey())){
			ID_ACTION_SAVE_UPLOAD=ijuahya.getAttrDescription();
		}else if(ATTRACTIONUPLOAD.equals(ijuahya.getAttrKey())){
			ID_ACTION_UPLOAD=ijuahya.getAttrDescription();
		}else{
			boolean exist=false;
			for(IJuahya ij:mIJuahyaList){
				if(ij.getAttrKey().equals(ijuahya.getAttrKey())){
					exist=true;
					break;
				}
			}
			if(!exist){
				mIJuahyaList.add(ijuahya);
			}
		}
	}
	@Override
	protected void onDestroy() {
		if(null!=mIJuahyaList){
			mIJuahyaList.clear();
		}
		super.onDestroy();
	}
	
}
