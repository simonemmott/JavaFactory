package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class IEnumValue_<W extends Wiget> {
	public WigetParameter<W, IEnum> declaringEnum;
	public WigetParameter<W, IType> javaType;
	public WigetParameter<W, String> name;
	public WigetParameter<W, String> title;
	public WigetParameter<W, String> description;
	public WigetParameter<W, String> fullName;
	public WigetParameter<W, String> canonicalName;
	public WigetParameter<W, Boolean> includeJavaDoc;
	public WigetParameter<W, Set<IAnnotation>> annotations;
	public WigetParameter<W, Object> unwrap;

}
