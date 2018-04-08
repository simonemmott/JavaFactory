package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IEnumValue;
import com.k2.JavaFactory.type.wigetModel.IEnumValue_;

/**
 * This wiget output the java source code for an enumeration value
 * 
 * This wiget should only be included within an enumeration wiget
 * 
 * @author simon
 *
 */
@WigetSpecification
public interface EnumValueWiget extends JavaWiget<IEnumValue> {
	
	public static class Model extends IEnumValue_<EnumValueWiget> {
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
