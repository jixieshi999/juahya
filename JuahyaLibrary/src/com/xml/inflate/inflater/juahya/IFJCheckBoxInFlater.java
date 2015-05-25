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
import com.xml.inflate.inflater.IFCheckBoxInFlater;
import com.xml.inflate.inflater.IFTextViewInFlater;
/**
 * CheckBox inflater 
 * @see IFTextViewInFlater
 * */
public class IFJCheckBoxInFlater extends IFCheckBoxInFlater {

	@Override
	public TextView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		JCheckBox layout = new JCheckBox(context);
		return layout;
	}
	@Override
	public void onFinishIFlate(View lay) {
		super.onFinishIFlate(lay);
	}

	
	@Override
	public boolean shoulInflate(String name) {
		return name.equalsIgnoreCase("com.juahya.guis.JCheckBox");
	}

}
