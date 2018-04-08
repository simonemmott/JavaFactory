package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.wigetModel.IMethod_;
import com.k2.Wiget.WigetContainer;

/**
 * Write the java source for a method in a class
 * @author simon
 *
 */
@WigetSpecification
public interface MethodWiget extends JavaWiget<IMethod> {
	
	public static class Model extends IMethod_<MethodWiget> {
		public WigetContainer<MethodWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
