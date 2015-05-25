package com.xml.inflate.inflater;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.xml.inflate.inflater.juahya.JuahyaInflater;


/**
 * all view inflater should extends IFalteViewAdapter
 * <p> base inflater class
 * @see com.xml.inflate.inflater.IFTextViewInFlater
 * @see com.xml.inflate.inflater.IFLinearLayoutInFlater
 * */
public abstract class IJuahyaFlateViewInFlaterAdapter extends IFlateViewInFlaterAdapter {
	public String ATTRIBUTE_KEY="attrKey";
	public String ATTRIBUTE_DESCRIPTION="attrDescription";
	public String ATTRIBUTE_TYPE="attrType";
	public String attrKey;
	public String attrDescription;
	public String attrType;

	public IJuahyaFlateViewInFlaterAdapter() {
		super();
	}

	@Override
	public boolean inflate(String nameSpace,String attrName,String attrValue){
		super.inflate(nameSpace, attrName, attrValue);
		if(IJuahyaFlateViewInFlaterAdapter.NAMESPACE_JUAHYA.equals(nameSpace)){
			if(ATTRIBUTE_KEY.equals(attrName)){
				attrKey = attrValue;
			}else if((ATTRIBUTE_DESCRIPTION).equals(attrName)){
				attrDescription=attrValue;
			}else if((ATTRIBUTE_TYPE).equals(attrName)){
				attrType=attrValue;
			}
		}else{
			if((IJuahyaFlateViewInFlaterAdapter.NAMESPACE_JUAHYA+":"+ATTRIBUTE_KEY).equals(attrName)){
				attrKey=attrValue;
			}else if((IJuahyaFlateViewInFlaterAdapter.NAMESPACE_JUAHYA+":"+ATTRIBUTE_DESCRIPTION).equals(attrName)){
				attrDescription=attrValue;
			}else if((IJuahyaFlateViewInFlaterAdapter.NAMESPACE_JUAHYA+":"+ATTRIBUTE_TYPE).equals(attrName)){
				attrType=attrValue;
			}
		}
		return false;
	}
	@Override
	public void onFinishIFlate(final View lay){
		if(lay instanceof IJuahya){
			IJuahya layout = (IJuahya)lay;
			if(null!=attrKey)layout.setAttrKey(attrKey);
			if(null!=attrDescription){
				layout.setAttrDescription(attrDescription);
//				layout.setHint(attrDescription);
			}
			if(null!=attrType){
				layout.setAttrType(attrType);
			}
		}
	
	}

	

}
