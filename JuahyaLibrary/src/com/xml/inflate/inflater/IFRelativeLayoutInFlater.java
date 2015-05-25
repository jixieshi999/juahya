package com.xml.inflate.inflater;

import org.xmlpull.v1.XmlPullParser;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * IFLinearLayout inflater
 * */
public class IFRelativeLayoutInFlater extends IJuahyaFlateViewInFlaterAdapter {

	public String ATTRIBUTE_ORIENTATION="orientation";
	public int orientation=NONE_INT;
	@Override
	public RelativeLayout inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		RelativeLayout layout = new RelativeLayout(context);
		return layout;
	}
	@Override
	public void onFinishIFlate(View lay) {
		super.onFinishIFlate(lay);
		RelativeLayout layout = (RelativeLayout)lay;
//		if(NONE_INT!=orientation)layout.setOrientation(orientation);
		if(NONE_INT!=gravity)layout.setGravity(gravity);
	}
	@Override
	public boolean OnInflateProperty(String nameSpace, String attrName, String attrValue) {
		if("android".equals(nameSpace)){
			if("orientation".equals(attrName)){
				orientation = "vertical".equals(attrValue)?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL;
			}
		}else{
			if((NAMESPACE_ANDROID+":"+ATTRIBUTE_ORIENTATION).equals(attrName)){
				orientation = "vertical".equals(attrValue)?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL;
			}
		}
		return false;
	}
	@Override
	public RelativeLayout.LayoutParams getLayoutParams() {
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		return param;
	}
	@Override
	public boolean shoulInflate(String name) {
		return name.equalsIgnoreCase("RelativeLayout");
	}

}
