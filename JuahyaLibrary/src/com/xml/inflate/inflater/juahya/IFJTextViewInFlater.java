package com.xml.inflate.inflater.juahya;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.juahya.guis.JEditText;
import com.juahya.guis.JTextView;
import com.xml.inflate.inflater.IFTextViewInFlater;
/**
 * IFJTextView inflater
 * ´ø IJuahya½âÎöµÄinflater
 * @see JTextView
 * */
public class IFJTextViewInFlater extends IFTextViewInFlater {

	@Override
	public TextView inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		JTextView layout = new JTextView(context);
		
		return layout;
	}
	
	@Override
	public boolean shoulInflate(String name) {
		return name.equalsIgnoreCase("com.juahya.guis.JTextView");
	}

}
