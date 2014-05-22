package com.xml.inflate.factory;

import com.xml.inflate.i.IBaseInflateInterface;

public abstract class IFlaterBaseFactory implements IFlateInteface{

	public abstract IBaseInflateInterface createIFlater(String name);

//	@Override
//	public boolean addIFalterFactory(Class factory) {
//		if(null==list)list=new ArrayList<Class>();
//		if(!list.contains(factory)){
//			return list.add(factory);
//		}
//		return true;
//	}
}
