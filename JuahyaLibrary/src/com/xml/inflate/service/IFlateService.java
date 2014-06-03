package com.xml.inflate.service;

import java.io.StringReader;
import java.util.Stack;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.android.apis.util.Debug;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xml.inflate.factory.IFLalterSImpleLinearLayoutFactory;
import com.xml.inflate.factory.IFlateInteface;
import com.xml.inflate.inflater.IFlateViewAdapter;
import com.xml.inflate.inflater.IJuahya;
import com.xml.inflate.inflater.IJuahyaLayoutInflateListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class IFlateService implements IIFlate,IJuahyaLayoutInflateListener{
	IJuahyaLayoutInflateListener mIJuahyaLayoutInflateListener;
	public IJuahyaLayoutInflateListener getIJuahyaLayoutInflateListener() {
		return mIJuahyaLayoutInflateListener;
	}
	public void setIJuahyaLayoutInflateListener(
			IJuahyaLayoutInflateListener mIJuahyaLayoutInflateListener) {
		this.mIJuahyaLayoutInflateListener = mIJuahyaLayoutInflateListener;
	}
	@Override
	public View inflate(String source,Context context) {
		initImageLoader(context);
		View layout=null;
		try{  
//			xpp.setInput( new StringReader ( ¡°xml string¡± ) );  
			IFLalterSImpleLinearLayoutFactory mIFLalterSImpleFactory=new IFLalterSImpleLinearLayoutFactory();
			XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
			parser.setInput(new StringReader(source));
			int eventType = parser.getEventType();  
			View view =null;
			LayoutParams param=null;
			LayoutParams parrentParam=null;
			int allDeep=0;
			int currentDeep=0;
			View viewPre=null;
			IFlateViewAdapter inflateCur;
			//¶ÑÕ»½âÎö
			Stack<View> stack=new Stack<View>();
			Stack<LayoutParams> stackParamList=new Stack<LayoutParams>();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_TAG:
					String name=parser.getName();
					Debug.dLog("start_tag depth:"+parser.getDepth());
					Debug.dLog("start_tag name:"+parser.getName());
					inflateCur=mIFLalterSImpleFactory.createIFlater(name);
					if(null!=inflateCur){
						viewPre=stack.isEmpty()?null:stack.peek();
						parrentParam=stackParamList.isEmpty()?null:stackParamList.peek();
						view=inflateCur.inflate(parser, context,parrentParam);
						inflateCur.onFinishIFlate(view);
						param=inflateCur.getLayoutParams();
						stackParamList.add(param);
						stack.add(view);
						
						if(view instanceof IJuahya){
							onJuahyaLayoutInflate((IJuahya)view);
							if(null!=mIJuahyaLayoutInflateListener){
								mIJuahyaLayoutInflateListener.onJuahyaLayoutInflate((IJuahya)view);
							}
						}
					}
//					IFLalterSImpleFactory
					if(null==layout){
						layout=view;
					}
					if(null==viewPre){
					}else{
//						((ViewGroup)layout).addView(view);
						((ViewGroup)viewPre).addView(view,inflateCur.getParrentLayoutParams());
						inflateCur.onFinishLayout(view);
					}
					break;
				case XmlPullParser.END_TAG:
					Debug.dLog("end_tag name:"+parser.getName());
					Debug.dLog("end_tag depth:"+parser.getDepth());
					stack.pop();
					stackParamList.pop();
					break;
				}
				eventType = parser.next();  
			}
		} catch (Exception e) {
			Debug.dLog(e);
		}
		return layout;
	}
	@Override
	public void onJuahyaLayoutInflate(IJuahya ijuahya) {
	}

    public static void initImageLoader(Context context) {
        int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 8);

        MemoryCacheAware<String, Bitmap> memoryCache;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
//            memoryCache = new LruMemoryCache(memoryCacheSize);
//        } else {
//        }
        memoryCache = new LRULimitedMemoryCache(memoryCacheSize);

        // This configuration tuning is custom. You can tune every option, you may tune some of them, 
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(memoryCache)
                .denyCacheImageMultipleSizesInMemory()
//                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .enableLogging() // Not necessary in common
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        
    }
}
