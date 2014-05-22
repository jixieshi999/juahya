package com.xml.inflate.inflater;

import org.xmlpull.v1.XmlPullParser;

import com.xml.inflate.tools.IFlaterTools;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;
/**
 * TextView inflater
 * */
public class IFTextView extends IFlateViewAdapter {
	public String ATTRIBUTE_TEXT="text";
	public String ATTRIBUTE_textSize="textSize";
	public String ATTRIBUTE_textColor="textColor";

	public String text;
	public int  textSize=NONE_INT;
	public int  textColor=NONE_INT;
	@Override
	public TextView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		TextView layout = new TextView(context);
		
		
		return layout;
	}
	@Override
	public void onFinishIFlate(View lay) {
		super.onFinishIFlate(lay);
		TextView layout = (TextView)lay;
		layout.setText(text);
		if(NONE_INT!=gravity)layout.setGravity(gravity);
		if(NONE_INT!=textSize)layout.setTextSize(textSize);
		if(NONE_INT!=textColor)layout.setTextColor(textColor);
	}
	@Override
	public boolean inflate(String nameSpace, String attrName, String attrValue) {
		if(NAMESPACE_ANDROID.equals(nameSpace)){
			if(ATTRIBUTE_TEXT.equals(attrName)){
				text = attrValue;
			}
		}else{
			if((NAMESPACE_ANDROID+":"+ATTRIBUTE_TEXT).equals(attrName)){
					text=attrValue;
			}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_textSize).equals(attrName)){
				textSize=IFlaterTools.px2dip(getContext(), attrValue);
			}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_textColor).equals(attrName)){
				textColor=Color.parseColor(attrValue);
			}
		}
		return false;
	}
	@Override
	public boolean shoulInflate(String name) {
		return "TextView".equalsIgnoreCase(name);
	}

}
