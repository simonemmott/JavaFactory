package com.k2.JavaFactory.type.wigetModel;

import java.util.List;

import com.k2.JavaFactory.type.IParameterValue;
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
public class IAnnotation_<W extends Wiget> extends IType_<W> {
	public WigetParameter<W, Boolean> inline;
	public WigetParameter<W, Boolean> hasParameters;
	public WigetParameter<W, Boolean> parameterCount;
	public WigetParameter<W, List<IParameterValue>> parameterValues;

}
