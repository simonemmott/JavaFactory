package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IType;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class IParameter_<W extends Wiget> {

	public WigetParameter<W, IType> type;
	public WigetParameter<W, String> name;
	public WigetParameter<W, Boolean> isVarArgs;
	public WigetParameter<W, Set<IAnnotation>> annotations;

}
