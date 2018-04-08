package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.wigetModel.IEnum_;
import com.k2.Wiget.WigetContainer;

/**
 * This wiget writes a java type of enum
 * The wiget does not include the package clause of the list of dependencies to allow the enum wiget to be embedded in other type wigets
 * 
 * @author simon
 *
 */
@WigetSpecification
public interface EnumWiget extends JavaWiget<IEnum> {
	
	public static class Model extends IEnum_<EnumWiget> {
		public WigetContainer<EnumWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
