package com.juahya.guis;

import com.xml.inflate.inflater.IJuahya;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**采集数据的edittext*/
public class JEditText extends EditText implements IJuahya{

	protected String attrKey;
	protected String attrDescription;
	protected String attrType;
	
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getAttrKey() {
		return attrKey;
	}

	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}

	public String getAttrDescription() {
		return attrDescription;
	}

	public void setAttrDescription(String attrDescription) {
		this.attrDescription = attrDescription;
	}

	public JEditText(Context context) {
		super(context);
	}

	public JEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public JEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public String getValue() {
		return getText().toString();
	}

	@Override
	public String getAttrType() {
		return attrType;
	}

	
	
}
