package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.Util.classes.Dependency;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class IType_<W extends Wiget> {
	public WigetParameter<W, String> packageName;
	public WigetParameter<W, String> basename;
	public WigetParameter<W, String> name;
	public WigetParameter<W, Set<Dependency>> dependencies;

}
