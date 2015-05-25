package com.xml.inflate.inflater;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.apis.util.Debug;
import com.android.juahya.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.xml.inflate.i.IBaseInflateInterface;
import com.xml.inflate.inflater.juahya.JuahyaInflater;
import com.xml.inflate.tools.IFlaterTools;


/**
 * all view inflater should extends IFalteViewAdapter
 * <p> base inflater class
 * @see com.xml.inflate.inflater.IFTextViewInFlater
 * @see com.xml.inflate.inflater.IFLinearLayoutInFlater
 * */
public abstract class IFlateViewInFlaterAdapter implements IBaseInflateInterface {
	/**
	 * attr default int value mean nothing cannot be use*/
	public static final int NONE_INT=-123;
	/**
	 * attr default float value mean nothing cannot be use*/
	public static final float NONE_FLOAT=-123;
	/**
	 * attr default String value mean nothing cannot be use*/
	public static final String NONE_STRING="-123";
	public int layout_width;
	public int layout_height;
	public float layout_weight=NONE_FLOAT;
	public int topMargin=NONE_INT;
	public int bottomMargin=NONE_INT;
	public int rightMargin=NONE_INT;
	public int leftMargin=NONE_INT;
	public int margin=NONE_INT;
	
	public int paddingBottom=NONE_INT;
	public int paddingRight=NONE_INT;
	public int paddingTop=NONE_INT;
	public int paddingLeft=NONE_INT;
	public int padding=NONE_INT;
	protected int backgroundColor=NONE_INT;
	protected int background=NONE_INT;

	protected int id=NONE_INT;
	protected boolean layout_alignParentTop=false;
	protected boolean layout_alignParentRight=false;
	protected boolean layout_alignParentLeft=false;
	protected boolean layout_alignParentBottom=false;
	
	protected boolean layout_centerInParent=false;
	protected boolean layout_centerHorizontal=false;
	protected boolean layout_centerVertical=false;
	
	protected int gravity=NONE_INT;
	protected int layout_gravity=NONE_INT;
	
	LayoutParams paramParrent=null;
	
	public static String NAMESPACE_JUAHYA="juahya";
	public String NAMESPACE_ANDROID="android";
	public String ATTRIBUTE_LAYOUT_WIDTH="layout_width";
	public String ATTRIBUTE_LAYOUT_HEIGHT="layout_height";
	
	public String ATTRIBUTE_ID="id";
	
	public String ATTRIBUTE_LAYOUT_WEIGHT="layout_weight";
	public String ATTRIBUTE_LAYOUT_MARGINTOP="layout_marginTop";
	public String ATTRIBUTE_LAYOUT_MARGINBOTTOM="layout_marginBottom";
	public String ATTRIBUTE_LAYOUT_MARGINRIGHT="layout_marginRight";
	public String ATTRIBUTE_LAYOUT_MARGINLEFT="layout_marginLeft";
	public String ATTRIBUTE_LAYOUT_MARGIN="layout_margin";
	
	public String ATTRIBUTE_PADDING="padding";
	public String ATTRIBUTE_PADDINGLEFT="paddingLeft";
	public String ATTRIBUTE_PADDINGTOP="paddingTop";
	public String ATTRIBUTE_PADDINGRIGHT="paddingRight";
	public String ATTRIBUTE_PADDINGBOTTOM="paddingBottom";
	
	public String ATTRIBUTE_BACKGROUND="background";
	public String ATTRIBUTE_GRAVITY="gravity";
	public String ATTRIBUTE_LAYOUT_GRAVITY="layout_gravity";
	
	
	//--------------------------------RelativeLayout-------------------------------------------//
	public String ATTRIBUTE_layout_alignParentTop="layout_alignParentTop";
	public String ATTRIBUTE_layout_alignParentRight="layout_alignParentRight";
	public String ATTRIBUTE_layout_alignParentLeft="layout_alignParentLeft";
	public String ATTRIBUTE_layout_alignParentBottom="layout_alignParentBottom";

	public String ATTRIBUTE_layout_centerInParent="layout_centerInParent";
	public String ATTRIBUTE_layout_centerHorizontal="layout_centerHorizontal";
	public String ATTRIBUTE_layout_centerVertical="layout_centerVertical";
	//--------------------------------RelativeLayout-------------------------------------------//
	

    protected ImageLoader imageLoader = ImageLoader.getInstance();
    static DisplayImageOptions options;
    
	Context context;
	public String imageuri=null;
	@Override
	public Context getContext() {
		return context;
	}


	public IFlateViewInFlaterAdapter() {
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
	}


	@Override
	public View inflate(XmlPullParser parser, Context context,LayoutParams paramParrent) {
		this.context=context;
		int count=parser.getAttributeCount();
		for(int i=0;i<count;i++){
			String nameSpace=parser.getAttributeNamespace(i);
			String attrName=parser.getAttributeName(i);
			String attrValue=parser.getAttributeValue(i);
			if(NAMESPACE_ANDROID.equals(nameSpace)){
				if(ATTRIBUTE_LAYOUT_HEIGHT.equals(attrName)){
					layout_height=IFlaterTools.getWidth(attrValue,getContext());
				}else if(ATTRIBUTE_LAYOUT_WIDTH.equals(attrName)){
					layout_width=IFlaterTools.getWidth(attrValue,getContext());
				}else if(ATTRIBUTE_LAYOUT_WEIGHT.equals(attrName)){
					layout_weight=Float.valueOf(attrValue);
				}else if((ATTRIBUTE_LAYOUT_MARGINTOP).equals(attrName)){
					topMargin=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_LAYOUT_MARGINBOTTOM).equals(attrName)){
					bottomMargin=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_LAYOUT_MARGINRIGHT).equals(attrName)){
					rightMargin=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_LAYOUT_MARGINLEFT).equals(attrName)){
					leftMargin=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_PADDING).equals(attrName)){
					padding=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_PADDINGBOTTOM).equals(attrName)){
					paddingBottom=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_PADDINGLEFT).equals(attrName)){
					paddingLeft=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_PADDINGRIGHT).equals(attrName)){
					paddingRight=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_PADDINGTOP).equals(attrName)){
					paddingTop=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_ID).equals(attrName)){
					id=Integer.valueOf(attrValue);
				}else if((ATTRIBUTE_layout_alignParentBottom).equals(attrName)){
					layout_alignParentBottom="true".equals(attrValue);
				}else if((ATTRIBUTE_layout_alignParentRight).equals(attrName)){
					layout_alignParentRight="true".equals(attrValue);
				}else if((ATTRIBUTE_layout_alignParentLeft).equals(attrName)){
					layout_alignParentLeft="true".equals(attrValue);
				}else if((ATTRIBUTE_layout_alignParentTop).equals(attrName)){
					layout_alignParentTop="true".equals(attrValue);
				}
			}else{
				if((NAMESPACE_ANDROID+":"+ATTRIBUTE_LAYOUT_HEIGHT).equals(attrName)){
//					tmpview.setOrientation("vertical".equals(attrValue)?LinearLayout.VERTICAL:LinearLayout.HORIZONTAL);
					layout_height=IFlaterTools.getWidth(attrValue,getContext());
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_LAYOUT_WIDTH).equals(attrName)){
					layout_width=IFlaterTools.getWidth(attrValue,getContext());
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_LAYOUT_WEIGHT).equals(attrName)){
					layout_weight=Float.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_LAYOUT_MARGINTOP).equals(attrName)){
					topMargin=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_LAYOUT_MARGINBOTTOM).equals(attrName)){
					bottomMargin=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_LAYOUT_MARGINRIGHT).equals(attrName)){
					rightMargin=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_LAYOUT_MARGINLEFT).equals(attrName)){
					leftMargin=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_PADDING).equals(attrName)){
					padding=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_PADDINGBOTTOM).equals(attrName)){
					paddingBottom=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_PADDINGLEFT).equals(attrName)){
					paddingLeft=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_PADDINGRIGHT).equals(attrName)){
					paddingRight=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_PADDINGTOP).equals(attrName)){
					paddingTop=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_BACKGROUND).equals(attrName)){
					if(attrValue.startsWith("#")){
						backgroundColor=IFlaterTools.getBackgroundColor(attrValue,getContext());
					}else if(attrValue.startsWith("@url/")){
						imageuri=IFlaterTools.getDrawableInternet(attrValue);
					}else{
						background=IFlaterTools.getDrawableLocal(attrValue,getContext());
					}
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_GRAVITY).equals(attrName)){
					gravity=IFlaterTools.getGravity(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_LAYOUT_GRAVITY).equals(attrName)){
					layout_gravity=IFlaterTools.getGravity(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_ID).equals(attrName)){
					id=Integer.valueOf(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_layout_alignParentBottom).equals(attrName)){
					layout_alignParentBottom="true".equals(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_layout_alignParentRight).equals(attrName)){
					layout_alignParentRight="true".equals(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_layout_alignParentLeft).equals(attrName)){
					layout_alignParentLeft="true".equals(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_layout_alignParentTop).equals(attrName)){
					layout_alignParentTop="true".equals(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_layout_centerInParent).equals(attrName)){
					layout_centerInParent="true".equals(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_layout_centerHorizontal).equals(attrName)){
					layout_centerHorizontal="true".equals(attrValue);
				}else if((NAMESPACE_ANDROID+":"+ATTRIBUTE_layout_centerVertical).equals(attrName)){
					layout_centerVertical="true".equals(attrValue);
				}
			}
//			Debug.dLog(" "+nameSpace+":"+attrName+"="+attrValue);
			inflate(nameSpace, attrName, attrValue);
		}
		if(null!=paramParrent){
			if(paramParrent instanceof LinearLayout.LayoutParams){
//				LinearLayout.LayoutParams linearlayoutparam=(LinearLayout.LayoutParams)param;
				LinearLayout.LayoutParams linearlayoutparam=new LinearLayout.LayoutParams(paramParrent);
				if(NONE_FLOAT!=layout_weight)linearlayoutparam.weight=layout_weight;
				
				if(NONE_INT!=topMargin)linearlayoutparam.topMargin=IFlaterTools.px2dip(context,topMargin);
				if(NONE_INT!=bottomMargin)linearlayoutparam.bottomMargin=IFlaterTools.px2dip(context,bottomMargin);
				if(NONE_INT!=leftMargin)linearlayoutparam.leftMargin=IFlaterTools.px2dip(context,leftMargin);
				if(NONE_INT!=rightMargin)linearlayoutparam.rightMargin=IFlaterTools.px2dip(context,rightMargin);
				if(NONE_INT!=layout_gravity)linearlayoutparam.gravity=IFlaterTools.px2dip(context,layout_gravity);
				
				
				if(NONE_INT!=layout_width)linearlayoutparam.width=layout_width;
				if(NONE_INT!=layout_height)linearlayoutparam.height=layout_height;
//				if(NONE_INT!=layout_width)linearlayoutparam.width=IFlaterTools.px2dip(context, layout_width);
//				if(NONE_INT!=layout_height)linearlayoutparam.height=IFlaterTools.px2dip(context, layout_height);
				this.paramParrent=linearlayoutparam;
			}else if(paramParrent instanceof RelativeLayout.LayoutParams){
//				RelativeLayout.LayoutParams relativelayoutparam=(RelativeLayout.LayoutParams) param;
				RelativeLayout.LayoutParams relativelayoutparam=new RelativeLayout.LayoutParams(paramParrent) ;
				
				if(NONE_INT!=topMargin)relativelayoutparam.topMargin=IFlaterTools.px2dip(context,topMargin);
				if(NONE_INT!=bottomMargin)relativelayoutparam.bottomMargin=IFlaterTools.px2dip(context,bottomMargin);
				if(NONE_INT!=leftMargin)relativelayoutparam.leftMargin=IFlaterTools.px2dip(context,leftMargin);
				if(NONE_INT!=rightMargin)relativelayoutparam.rightMargin=IFlaterTools.px2dip(context,rightMargin);
				
				if(NONE_INT!=layout_width)relativelayoutparam.width=layout_width;
				if(NONE_INT!=layout_height)relativelayoutparam.height= layout_height;
				if(layout_alignParentTop)relativelayoutparam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				if(layout_alignParentRight)relativelayoutparam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				if(layout_alignParentLeft)relativelayoutparam.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				if(layout_alignParentBottom)relativelayoutparam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				if(layout_centerVertical)relativelayoutparam.addRule(RelativeLayout.CENTER_VERTICAL);
				if(layout_centerHorizontal)relativelayoutparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
				if(layout_centerInParent)relativelayoutparam.addRule(RelativeLayout.CENTER_IN_PARENT);
				this.paramParrent=relativelayoutparam;
			}
		}
		return new View(context);
	}
	boolean inflate(String nameSpace,String attrName,String attrValue){
		return OnInflateProperty(nameSpace, attrName, attrValue);
	}
	public abstract boolean OnInflateProperty(String nameSpace,String attrName,String attrValue);
	public void onFinishLayout(View layout){
	}
	public void finishLayout(View layout){
		onFinishLayout(layout);
	}
	public void onFinishIFlate(final View layout){
		
	}
	public void finishIFlate(final View layout){
		layout.setPadding(
				paddingLeft==NONE_INT?0:paddingLeft, 
				paddingTop==NONE_INT?0:paddingTop, 
				paddingRight==NONE_INT?0:paddingRight, 
				paddingBottom==NONE_INT?0:paddingBottom);
//		if(NONE_INT!=gravity)layout.setGravity(gravity);
		if(NONE_INT!=backgroundColor)layout.setBackgroundColor(backgroundColor);
		if(NONE_INT!=background)layout.setBackgroundResource(background);
		if(NONE_INT!=id)layout.setId(id);
		if(null!=imageuri){
			imageLoader.loadImage(imageuri, options, new SimpleImageLoadingListener(){

				@Override
				public void onLoadingComplete(String imageUri, View view,
						Bitmap loadedImage, String filePath) {
					try{
						Debug.dLog("loadImage:"+filePath);
						layout.setBackground(new BitmapDrawable(loadedImage));
						layout.postInvalidate();
					}catch(Exception e){
						Debug.dLog(e);
					}
					super.onLoadingComplete(imageUri, view, loadedImage, filePath);
				}
				
			});
		}
		onFinishIFlate(layout);
	}

	@Override
	public LayoutParams getLayoutParams() {
		return new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}
	@Override
	public LayoutParams getParrentLayoutParams() {
		return paramParrent;
	}
	

}
