package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.wigetModel.IClass_;
import com.k2.Wiget.WigetContainer;
import com.k2.Wiget.WigetParameter;

@WigetSpecification
public interface ClassWiget extends JavaWiget<IClass> {
	
	public static class Model extends IClass_<ClassWiget> {
		public WigetContainer<ClassWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
