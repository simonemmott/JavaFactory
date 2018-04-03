package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class IType_<W extends Wiget> {
	public WigetParameter<W, String> packageName;
	public WigetParameter<W, String> basename;
	public WigetParameter<W, String> name;
	public WigetParameter<W, String> author;
	public WigetParameter<W, String> title;
	public WigetParameter<W, String> description;
	public WigetParameter<W, Boolean> includeJavaDoc;
	public WigetParameter<W, Set<IAnnotation>> annotations;
	public WigetParameter<W, Set<Dependency>> dependencies;
	public WigetParameter<W, Object> unwrap;

}
