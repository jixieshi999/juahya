package com.xml.inflate.tools;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.apis.util.Debug;
import com.xml.inflate.i.IBaseInflateInterface;


/**
 * all view inflater should extends IFalteViewAdapter
 * <p> base inflater class
 * @see com.xml.inflate.inflater.IFTextViewInFlater
 * @see com.xml.inflate.inflater.IFLinearLayoutInFlater
 * */
public  class IFlaterTools   {

	/**
	 * attr default int value mean nothing cannot be use*/
	public static final int NONE_INT=-123;
	/**
	 * attr default float value mean nothing cannot be use*/
	public static final float NONE_FLOAT=-123;
	/**
	 * attr default String value mean nothing cannot be use*/
	public static final String NONE_STRING="-123";
	
	/**need prase url*/
	public static final int STATUS_NEED_URL=-124;

	static float scale=NONE_FLOAT;
	

	public static int getBackgroundColor(String color,Context context){
		return Color.parseColor(color);
	}
	public static int getGravity(String gravity){
		int result=Gravity.NO_GRAVITY;
		if(gravity.length()>0){
			String[]gra=gravity.split("\\|");
			for(String str:gra){
				if("top".equalsIgnoreCase(str))result|=Gravity.TOP;
				if("BOTTOM".equalsIgnoreCase(str))result|=Gravity.BOTTOM;
				if("CENTER".equalsIgnoreCase(str))result|=Gravity.CENTER;
				if("LEFT".equalsIgnoreCase(str))result|=Gravity.LEFT;
				if("RIGHT".equalsIgnoreCase(str))result|=Gravity.RIGHT;
				if("CENTER_HORIZONTAL".equalsIgnoreCase(str))result|=Gravity.CENTER_HORIZONTAL;
				if("CENTER_VERTICAL".equalsIgnoreCase(str))result|=Gravity.CENTER_VERTICAL;
			}
		}
		return result;
	}
	
	
	/**获取本地drawble
	 * @return res id,if return IFlaterTools.STATUS_NEED_URL,then use getDrawableInternetto prase url;
	 * */
	public static int getDrawableLocal(String color,Context context){
		String value=null;
		if(color.startsWith("@drawable/")){
			value = color.substring(10);
			return context.getResources().getIdentifier(value, "drawable", context.getPackageName());
		}else if(color.startsWith("@dictionary/")){
			//从字典表里面读取内容 txt
		}else if(color.startsWith("@android:drawable/")){
			value = color.substring(18);
			return context.getResources().getIdentifier("android:drawable/"+value, null,null);
		}else if(color.startsWith("@url/")){
			value = color.substring(5);
			
		}
		return NONE_INT;
	}
	/**获取网络图片url
	 * @return return url or null*/
	public static String getDrawableInternet(String attr){
		String value=null;
		if(attr.startsWith("@url/")){
			value = attr.substring(5);
			return value;
		}
		return null;
	}
	/**获取字典数据*/
	public static String getDictionaryLocal(String attr){
		return NONE_STRING;
	}

	/**获取长宽高*/
	public static int getWidth(String attr,Context context){

		int result=0;
		if("fill_parent".equals(attr)){
			result=LayoutParams.FILL_PARENT;
		}else if("wrap_content".equals(attr)){
			result=LayoutParams.WRAP_CONTENT;
		}else{
			try{
				result=Integer.valueOf(attr);
				result=px2dip(context,result);
			}catch (Exception e) {
				result=LayoutParams.WRAP_CONTENT;
			}
		}
		return result;
	}
	public static int dip2px(Context context, float dipValue){ 
		if(NONE_FLOAT==scale){
			scale= context.getResources().getDisplayMetrics().density; 
		}
        return (int)(dipValue * scale + 0.5f); 
	} 
	public static int px2dip(Context context, String pxValue){
		int result=0;
		try{
			result=Integer.valueOf(pxValue);
			result=px2dip(context,result);
		}catch (Exception e) {
			result=0;
		}
		return result;
	}
	public static int px2dip(Context context, float pxValue){
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				pxValue, context.getResources().getDisplayMetrics());
//		px =(int) (pxValue * (context.getResources().getDisplayMetrics().density/160));
		return px;
//		if(NONE_FLOAT==scale){
//			scale= context.getResources().getDisplayMetrics().density; 
//		}
//	    return (int)(pxValue / scale + 0.5f); 
	} 
}
