package com.xml.inflate.inflater.juahya;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juahya.guis.JEditText;
import com.juahya.guis.JLinearlayout;
import com.juahya.guis.JTextView;
import com.xml.inflate.inflater.IFLinearLayoutInFlater;
import com.xml.inflate.inflater.IFTextViewInFlater;
/**
 * IFJTextView inflater
 * ´ø IJuahya½âÎöµÄinflater
 * @see JTextView
 * */
public class IFJLinearlayoutInFlater extends IFLinearLayoutInFlater {

	@Override
	public LinearLayout inflate(XmlPullParser parser,Context context,LayoutParams paramParrent) {
		super.inflate(parser, context,paramParrent);
		JLinearlayout layout = new JLinearlayout(context);
		return layout;
	}
	
	@Override
	public boolean shoulInflate(String name) {
		return name.equalsIgnoreCase("com.juahya.guis.JLinearlayout");
	}

}
