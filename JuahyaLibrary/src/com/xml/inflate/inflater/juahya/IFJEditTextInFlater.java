package com.xml.inflate.inflater.juahya;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.juahya.guis.JEditText;
import com.juahya.guis.JTextView;
import com.xml.inflate.inflater.IFEditTextInFlater;

public class IFJEditTextInFlater extends IFEditTextInFlater {

	@Override
	public TextView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		JEditText layout = new JEditText(context);
		return layout;
	}
	
	@Override
	public boolean shoulInflate(String name) {
		return "com.juahya.guis.JEditText".equalsIgnoreCase(name);
	}

	
}
