package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class IInterface_<W extends Wiget> extends IType_<W> {

	public WigetParameter<W, Set<IInterface>> extendsInterfaces;
	public WigetParameter<W, Set<IMethodSignature>> methods;
	

}
