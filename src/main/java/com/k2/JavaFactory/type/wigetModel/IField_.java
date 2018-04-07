package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
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
public class IField_<W extends Wiget> {
	public WigetParameter<W, IClass> declaringClass;
	public WigetParameter<W, Visibility> visibility;
	public WigetParameter<W, IType> javaType;
	public WigetParameter<W, String> name;
	public WigetParameter<W, String> title;
	public WigetParameter<W, String> description;
	public WigetParameter<W, String> fullName;
	public WigetParameter<W, String> canonicalName;
	public WigetParameter<W, Boolean> includeJavaDoc;
	public WigetParameter<W, Boolean> includeGetter;
	public WigetParameter<W, Boolean> includeSetter;
	public WigetParameter<W, Set<IAnnotation>> annotations;
	public WigetParameter<W, Object> unwrap;

}
