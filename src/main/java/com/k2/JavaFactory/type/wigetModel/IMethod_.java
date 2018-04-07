package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.JavaFactory.type.IClass;
import com.k2.Util.classes.Dependency;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

/**
 * Static Wiget Models mirror the getter methods of the required types of wigets as such the meaning of the fields should be inferred from the Type of the same name
 * 
 * @author simon
 *
 * @param <W>	The wiget that this static wiget model will supply values to.
 */
@SuppressWarnings("rawtypes")
public class IMethod_<W extends Wiget> extends IMethodSignature_ {

	public WigetParameter<W, IClass> declaringClass;
	public WigetParameter<W, String> methodBody;
	public WigetParameter<W, Set<Dependency>> dependencies;
	public WigetParameter<W, String> fullName;
	public WigetParameter<W, String> canonicalName;
	public WigetParameter<W, Object> unwrap;

}
