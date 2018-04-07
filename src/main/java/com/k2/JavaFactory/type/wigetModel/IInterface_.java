package com.k2.JavaFactory.type.wigetModel;

import java.util.Set;

import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethodSignature;
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
public class IInterface_<W extends Wiget> extends IType_<W> {

	public WigetParameter<W, Set<IInterface>> extendsInterfaces;
	public WigetParameter<W, Set<IMethodSignature>> methods;
	

}
