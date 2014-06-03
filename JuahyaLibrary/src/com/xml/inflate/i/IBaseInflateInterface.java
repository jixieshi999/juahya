package com.xml.inflate.i;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * xml½âÎö»ùÀà
 * */
public interface IBaseInflateInterface {
	/**
	 * inflate view from parser with parrentparam
	 * */
	View inflate(XmlPullParser parser,Context context,LayoutParams parrentParam);
	void onFinishIFlate(View layout);
	void onFinishLayout(View layout);
	/**get this viewgroup 's layout param*/
	LayoutParams getLayoutParams();
	/**get parrent viewgroup layout */
	LayoutParams getParrentLayoutParams();
	boolean shoulInflate(String name);
	Context getContext();
}
