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
import android.widget.LinearLayout;
import android.widget.ScrollView;

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
 * ´ø IJuahya½âÎöµÄinflater
 * @see ScrollView
 * */
public class IFScrollViewInFlater extends IJuahyaFlateViewInFlaterAdapter {

    
    public IFScrollViewInFlater() {
		super();
	}
	@Override
	public ScrollView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		ScrollView layout = new ScrollView(context);
		return layout;
	}
	@Override
	public void onFinishIFlate(View lay) {
		super.onFinishIFlate(lay);
	}
	
	@Override
	public void onFinishLayout(View lay) {
//		ScrollView layout = (ScrollView)lay;
//		super.onFinishLayout(layout);
	}
	@Override
	public boolean OnInflateProperty(String nameSpace, String attrName, String attrValue) {
		String value=null;
		if(NAMESPACE_ANDROID.equals(nameSpace)){
		}else{
		}
		return false;
	}
	@Override
	public ScrollView.LayoutParams getLayoutParams() {
		ScrollView.LayoutParams param = new ScrollView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);
		return param;
	}
	@Override
	public boolean shoulInflate(String name) {
		return "ScrollView".equalsIgnoreCase(name);
	}

}
