package com.k2.JavaFactory.type.wigetModel;

import java.util.List;
import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class IMethodSignature_<W extends Wiget> {

	public WigetParameter<W, Visibility> visibility;
	public WigetParameter<W, IType> returnsType;
	public WigetParameter<W, String> name;
	public WigetParameter<W, List<IParameter>> parameters;
	public WigetParameter<W, Set<IAnnotation>> annotations;

}
