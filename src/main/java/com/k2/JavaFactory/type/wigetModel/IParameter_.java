package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IType;
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
public class IParameter_<W extends Wiget> {

	public WigetParameter<W, IType> type;
	public WigetParameter<W, String> name;
	public WigetParameter<W, Boolean> isVarArgs;
	public WigetParameter<W, Set<IAnnotation>> annotations;

}
