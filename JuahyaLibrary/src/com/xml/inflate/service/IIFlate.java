package com.xml.inflate.service;

import android.content.Context;
import android.view.View;
/**inflate service */
public interface IIFlate {

	/**可以将xml问题替换成json array来解析，json比xml更具有兼容性，但是xml更具直观性*/
	View inflate(String source,Context context);
}
