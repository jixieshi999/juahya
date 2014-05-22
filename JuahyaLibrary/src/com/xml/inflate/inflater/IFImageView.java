package com.xml.inflate.inflater;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.juahya.guis.JEditText;
import com.juahya.guis.JTextView;
import com.xml.inflate.tools.IFlaterTools;
/**
 * IFJTextView inflater
 * 带 IJuahya解析的inflater
 * @see JTextView
 * */
public class IFImageView extends IFlateViewAdapter {
	public String ATTRIBUTE_SRC="src";

	public int src=NONE_INT;
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
