package com.xml.inflate.factory;

import java.util.ArrayList;

import com.xml.inflate.inflater.IFCheckBox;
import com.xml.inflate.inflater.IFEditText;
import com.xml.inflate.inflater.IFImageView;
import com.xml.inflate.inflater.IFLinearLayout;
import com.xml.inflate.inflater.IFRelativeLayout;
import com.xml.inflate.inflater.IFTextView;
import com.xml.inflate.inflater.IFlateViewAdapter;
import com.xml.inflate.inflater.juahya.IFJCheckBox;
import com.xml.inflate.inflater.juahya.IFJEditText;
import com.xml.inflate.inflater.juahya.IFJLinearlayout;
import com.xml.inflate.inflater.juahya.IFJTextView;

/**
 * after new Ifxxx api ,add to iflater list
 * */
public class IFLalterSImpleLinearLayoutFactory extends IFlaterBaseFactory{

	static ArrayList<IFlateViewAdapter> list=null;
	static{
		list=new ArrayList<IFlateViewAdapter>();
		list.add(new IFLinearLayout());
		list.add(new IFTextView());
		list.add(new IFEditText());
		list.add(new IFJEditText());
		list.add(new IFJTextView());
		list.add(new IFImageView());
		list.add(new IFCheckBox());
		list.add(new IFJCheckBox());
		list.add(new IFRelativeLayout());
		list.add(new IFJLinearlayout());
	}
	public static void addIFlateViewAdapter(IFlateViewAdapter ifl){
		if(null==list)list=new ArrayList<IFlateViewAdapter>();
		if(!list.contains(ifl))list.add(ifl);
	}
	@Override
	public IFLinearLayout createIFlater() {
		return new IFLinearLayout();
	}

	@Override
	public IFlateViewAdapter createIFlater(String name) {
		for(IFlateViewAdapter cls:list){
			if(cls.shoulInflate(name)){
				try {
					return (IFlateViewAdapter)cls.getClass().newInstance();
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
		return new IFlateViewAdapter(){
			@Override
			public boolean shoulInflate(String name) {
				return false;
			}
			@Override
			public boolean inflate(String nameSpace, String attrName,
					String attrValue) {
				return false;
			}};
	}


}
