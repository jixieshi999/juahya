package com.xml.inflate.inflater;

import org.xmlpull.v1.XmlPullParser;

import com.juahya.guis.JEditText;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;

public class IFEditTextInFlater extends IFTextViewInFlater {

	@Override
	public TextView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		EditText layout = new EditText(context);
		return layout;
	}

	@Override
	public boolean shoulInflate(String name) {
		return "EditText".equalsIgnoreCase(name);
	}
}
