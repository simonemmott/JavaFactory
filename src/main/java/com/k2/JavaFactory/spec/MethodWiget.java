package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.wigetModel.IClass_;
import com.k2.JavaFactory.type.wigetModel.IInterface_;
import com.k2.JavaFactory.type.wigetModel.IMethodSignature_;
import com.k2.JavaFactory.type.wigetModel.IMethod_;
import com.k2.Wiget.WigetContainer;
import com.k2.Wiget.WigetParameter;

@WigetSpecification
public interface MethodWiget extends JavaWiget<IMethod> {
	
	public static class Model extends IMethod_<MethodWiget> {
		public WigetContainer<MethodWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
