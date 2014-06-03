package com.xml.inflate.inflater;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.android.apis.util.DebugUtil;
import com.android.juahya.R;
import com.juahya.guis.JTextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xml.inflate.tools.IFlaterTools;
/**
 * IFJTextView inflater
 * 带 IJuahya解析的inflater
 * @see JTextView
 * */
public class IFImageView extends IFlateViewAdapter {
	public String ATTRIBUTE_SRC="src";

	public int src=NONE_INT;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    static DisplayImageOptions options;
    
    public IFImageView() {
		super();
		if(null==options){
			options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_launcher)
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.showImageOnFail(R.drawable.ic_launcher)
			.cacheInMemory()
			.cacheOnDisc()
			.displayer(new RoundedBitmapDisplayer(2))
			.build();
		}
		if(null==animateFirstListener){
			animateFirstListener = new AnimateFirstDisplayListener();
		}
	}
	private ImageLoadingListener animateFirstListener = null;
    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage,String filePath) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                DebugUtil.dLog("onLoadingComplete--uri:"+imageUri+",filePath:"+filePath);
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                } else {
                    imageView.setImageBitmap(loadedImage);
                }
            }
        }
    }
	@Override
	public ImageView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		ImageView layout = new ImageView(context);
		return layout;
	}
	@Override
	public void onFinishIFlate(View lay) {
		super.onFinishIFlate(lay);
		ImageView layout = (ImageView)lay;
		if(src!=NONE_INT)layout.setImageResource(src);
//		if(NONE_INT!=gravity)layout.setGravity(gravity);
	}
	
	@Override
	public void onFinishLayout(View lay) {
		ImageView layout = (ImageView)lay;
		if(null!=imageuri){
			imageLoader.displayImage(imageuri, layout, options, animateFirstListener);
		}
//		super.onFinishLayout(layout);
	}
	@Override
	public boolean inflate(String nameSpace, String attrName, String attrValue) {
		String value=null;
		if(NAMESPACE_ANDROID.equals(nameSpace)){
			if(ATTRIBUTE_SRC.equals(attrName)){
//				src=inflateDrawable(attrValue);
				src=IFlaterTools.getDrawableLocal(attrValue,getContext());
			}
		}else{
			if((NAMESPACE_ANDROID+":"+ATTRIBUTE_SRC).equals(attrName)){
				src=IFlaterTools.getDrawableLocal(attrValue,getContext());
			}
		}
		return false;
	}
	public int inflateDrawable(String attrValue) {
		String value;
		if(attrValue.startsWith("@drawable/")){
			value = attrValue.substring(10);
			return getContext().getResources().getIdentifier(value, "drawable", getContext().getPackageName());
		}else if(attrValue.startsWith("@dictionary/")){
			//从字典表里面读取内容 txt
		}else if(attrValue.startsWith("@android:drawable/")){
			value = attrValue.substring(18);
			return getContext().getResources().getIdentifier("android:drawable/"+value, null,null);
		}else if(attrValue.startsWith("@url/")){
			
		}
		return NONE_INT;
	}
	@Override
	public boolean shoulInflate(String name) {
		return "ImageView".equalsIgnoreCase(name);
	}

}
