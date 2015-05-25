package com.xml.inflate.inflater.juahya;

import android.view.View;

import com.xml.inflate.inflater.IJuahyaFlateViewInFlaterAdapter;
import com.xml.inflate.inflater.IJuahya;

public class JuahyaInflater {

	public String ATTRIBUTE_KEY="attrKey";
	public String ATTRIBUTE_DESCRIPTION="attrDescription";
	public String ATTRIBUTE_TYPE="attrType";
	public String attrKey;
	public String attrDescription;
	public String attrType;

	public void onFinishIFlate(View lay) {
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

	public boolean inflate(String nameSpace, String attrName, String attrValue) {
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
	
	
}
