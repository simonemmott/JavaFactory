package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.wigetModel.IClass_;
import com.k2.Wiget.WigetContainer;
import com.k2.Wiget.WigetParameter;

/**
 * This wiget writes a java type of class
 * The wiget does not include the package clause of the list of dependencies to allow the class wiget to be embedded in other wigets
 * 
 * @author simon
 *
 */
@WigetSpecification
public interface ClassWiget extends JavaWiget<IClass> {
	
	public static class Model extends IClass_<ClassWiget> {
		public WigetContainer<ClassWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
