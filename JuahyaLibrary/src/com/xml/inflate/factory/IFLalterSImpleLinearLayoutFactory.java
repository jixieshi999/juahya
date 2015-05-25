package com.xml.inflate.factory;

import java.util.ArrayList;

import com.xml.inflate.inflater.IFCheckBoxInFlater;
import com.xml.inflate.inflater.IFEditTextInFlater;
import com.xml.inflate.inflater.IFImageViewInFlater;
import com.xml.inflate.inflater.IFLinearLayoutInFlater;
import com.xml.inflate.inflater.IFRelativeLayoutInFlater;
import com.xml.inflate.inflater.IFScrollViewInFlater;
import com.xml.inflate.inflater.IFTextViewInFlater;
import com.xml.inflate.inflater.IJuahyaFlateViewInFlaterAdapter;
import com.xml.inflate.inflater.juahya.IFJCheckBoxInFlater;
import com.xml.inflate.inflater.juahya.IFJEditTextInFlater;
import com.xml.inflate.inflater.juahya.IFJLinearlayoutInFlater;
import com.xml.inflate.inflater.juahya.IFJTextViewInFlater;

/**
 * after new Ifxxx api ,add to iflater list
 * */
public class IFLalterSImpleLinearLayoutFactory extends IFlaterBaseFactory{

	static ArrayList<IJuahyaFlateViewInFlaterAdapter> list=null;
	static{
		list=new ArrayList<IJuahyaFlateViewInFlaterAdapter>();
		list.add(new IFLinearLayoutInFlater());
		list.add(new IFTextViewInFlater());
		list.add(new IFEditTextInFlater());
		list.add(new IFJEditTextInFlater());
		list.add(new IFJTextViewInFlater());
		list.add(new IFImageViewInFlater());
		list.add(new IFCheckBoxInFlater());
		list.add(new IFJCheckBoxInFlater());
		list.add(new IFRelativeLayoutInFlater());
		list.add(new IFJLinearlayoutInFlater());
		list.add(new IFScrollViewInFlater());
	}
	public static void addIFlateViewAdapter(IJuahyaFlateViewInFlaterAdapter ifl){
		if(null==list)list=new ArrayList<IJuahyaFlateViewInFlaterAdapter>();
		if(!list.contains(ifl))list.add(ifl);
	}
	@Override
	public IFLinearLayoutInFlater createIFlater() {
		return new IFLinearLayoutInFlater();
	}

	@Override
	public IJuahyaFlateViewInFlaterAdapter createIFlater(String name) {
		for(IJuahyaFlateViewInFlaterAdapter cls:list){
			if(cls.shoulInflate(name)){
				try {
					return (IJuahyaFlateViewInFlaterAdapter)cls.getClass().newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
//		if(name==null||"".equals(name)){
//			return null;
//		}
//		if(name.equals("LinearLayout")){
//			return new IFLinearLayout();
//		}
//		if(name.equals("TextView")){
//			return new IFTextView();
//		}
		return new IJuahyaFlateViewInFlaterAdapter(){
			@Override
			public boolean shoulInflate(String name) {
				return false;
			}
			@Override
			public boolean OnInflateProperty(String nameSpace, String attrName,
					String attrValue) {
				return false;
			}};
	}


}
