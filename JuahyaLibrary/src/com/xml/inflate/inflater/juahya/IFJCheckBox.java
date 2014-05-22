package com.xml.inflate.inflater.juahya;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.TextView;

import com.juahya.guis.JCheckBox;
import com.juahya.guis.JEditText;
import com.juahya.guis.JTextView;
import com.xml.inflate.inflater.IFCheckBox;
import com.xml.inflate.inflater.IFTextView;
/**
 * CheckBox inflater 
 * @see IFTextView
 * */
public class IFJCheckBox extends IFCheckBox {

	public String ATTRIBUTE_KEY="attrKey";
	public String ATTRIBUTE_DESCRIPTION="attrDescription";
	public String ATTRIBUTE_TYPE="attrType";
	public String attrKey;
	public String attrDescription;
	public String attrType;
	
	@Override
	public TextView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		JCheckBox layout = new JCheckBox(context);
		return layout;
	}
	@Override
	public void onFinishIFlate(View lay) {
		super.onFinishIFlate(lay);
		JCheckBox layout = (JCheckBox)lay;
		if(null!=attrKey)layout.setAttrKey(attrKey);
		if(null!=attrDescription){
			layout.setAttrDescription(attrDescription);
//			layout.setHint(attrDescription);
		}
		if(null!=attrType){
			layout.setAttrType(attrType);
		}
	}

	@Override
	public boolean inflate(String nameSpace, String attrName, String attrValue) {
		super.inflate(nameSpace, attrName, attrValue);
		if(NAMESPACE_JUAHYA.equals(nameSpace)){
			if(ATTRIBUTE_KEY.equals(attrName)){
				attrKey = attrValue;
			}else if((ATTRIBUTE_DESCRIPTION).equals(attrName)){
				attrDescription=attrValue;
			}else if((ATTRIBUTE_TYPE).equals(attrName)){
				attrType=attrValue;
			}
		}else{
			if((NAMESPACE_JUAHYA+":"+ATTRIBUTE_KEY).equals(attrName)){
				attrKey=attrValue;
			}else if((NAMESPACE_JUAHYA+":"+ATTRIBUTE_DESCRIPTION).equals(attrName)){
				attrDescription=attrValue;
			}else if((NAMESPACE_JUAHYA+":"+ATTRIBUTE_TYPE).equals(attrName)){
				attrType=attrValue;
			}
		}
		return false;
	}
	
	@Override
	public boolean shoulInflate(String name) {
		return name.equalsIgnoreCase("com.juahya.guis.JCheckBox");
	}

}
