package com.xml.inflate.service;


import com.xml.inflate.inflater.IJuahyaLayoutInflateListener;

import android.content.Context;
import android.view.View;
/**解析代理类*/
public class IFlateServicePoxy implements IIFlate{
	private IFlateService service;

	IJuahyaLayoutInflateListener mIJuahyaLayoutInflateListener;

	public IJuahyaLayoutInflateListener getIJuahyaLayoutInflateListener() {
		return mIJuahyaLayoutInflateListener;
	}
	public void setIJuahyaLayoutInflateListener(
			IJuahyaLayoutInflateListener mIJuahyaLayoutInflateListener) {
		this.mIJuahyaLayoutInflateListener = mIJuahyaLayoutInflateListener;
		service.setIJuahyaLayoutInflateListener(mIJuahyaLayoutInflateListener);
	}
	public IFlateServicePoxy() {
		super();
		service=new IFlateService();
	}
	@Override
	public View inflate(String source, Context context) {
		return service.inflate(source, context);
	}

}
