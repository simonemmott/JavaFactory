package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.wigetModel.IInterface_;
import com.k2.Wiget.WigetContainer;

/**
 * This wiget writes a java type of interface
 * The wiget does not include the package clause of the list of dependencies to allow the interface wiget to be embedded in other wigets
 * 
 * @author simon
 *
 */
@WigetSpecification
public interface InterfaceWiget extends JavaWiget<IInterface> {
	
	public static class Model extends IInterface_<InterfaceWiget> {
		public WigetContainer<InterfaceWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
