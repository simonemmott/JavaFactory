package com.k2.JavaFactory.type.wigetModel;

import java.util.List;
import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IParameter;
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
public class IMethodSignature_<W extends Wiget> {

	public WigetParameter<W, Visibility> visibility;
	public WigetParameter<W, IType> returnType;
	public WigetParameter<W, String> returnTypeDescription;
	public WigetParameter<W, String> name;
	public WigetParameter<W, String> title;
	public WigetParameter<W, String> description;
	public WigetParameter<W, Boolean> includeJavaDoc;
	public WigetParameter<W, List<IParameter>> parameters;
	public WigetParameter<W, Boolean> hasParameters;
	public WigetParameter<W, Integer> parameterCount;
	public WigetParameter<W, Set<IAnnotation>> annotations;

}
