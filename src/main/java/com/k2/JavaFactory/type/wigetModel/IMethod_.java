package com.k2.JavaFactory.type.wigetModel;

import java.util.List;
import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class IMethod_<W extends Wiget> extends IMethodSignature_ {

	public WigetParameter<W, IType> declaringType;
	public WigetParameter<W, String> methodBody;
	public WigetParameter<W, Set<Dependency>> dependencies;
	public WigetParameter<W, String> fullName;
	public WigetParameter<W, String> canonicalName;
	public WigetParameter<W, Object> unwrap;

}
