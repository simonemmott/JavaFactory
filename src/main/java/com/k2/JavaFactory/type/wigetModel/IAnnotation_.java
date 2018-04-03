package com.k2.JavaFactory.type.wigetModel;

import java.util.List;

import com.k2.JavaFactory.type.IParameterValue;
import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class IAnnotation_<W extends Wiget> extends IType_<W> {
	public WigetParameter<W, Boolean> inline;
	public WigetParameter<W, Boolean> hasParameters;
	public WigetParameter<W, Boolean> parameterCount;
	public WigetParameter<W, List<IParameterValue>> parameterValues;

}
