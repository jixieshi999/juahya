package com.xml.inflate.inflater;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.TextView;

import com.juahya.guis.JEditText;
import com.juahya.guis.JTextView;
/**
 * CheckBox inflater 
 * @see IFTextViewInFlater
 * */
public class IFCheckBoxInFlater extends IFTextViewInFlater {

	public String ATTRIBUTE_check="checked";
	public boolean checked=false;
	
	@Override
	public TextView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		CheckBox layout = new CheckBox(context);
		return layout;
	}
	@Override
	public void onFinishIFlate(View lay) {
		super.onFinishIFlate(lay);
		CheckBox layout = (CheckBox)lay;
		layout.setChecked(checked);
	}

	@Override
	public boolean OnInflateProperty(String nameSpace, String attrName, String attrValue) {
		super.OnInflateProperty(nameSpace, attrName, attrValue);
		if(NAMESPACE_JUAHYA.equals(nameSpace)){
			if((ATTRIBUTE_check).equals(attrName)){
				checked="true".equals(attrValue);
			}
		}else{
			if((NAMESPACE_ANDROID+":"+ATTRIBUTE_check).equals(attrName)){
				checked="true".equals(attrValue);
			}
		}
		return false;
	}
	
	@Override
	public boolean shoulInflate(String name) {
		return name.equalsIgnoreCase("CheckBox");
	}

}
