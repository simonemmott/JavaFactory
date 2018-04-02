package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.wigetModel.IType_;
import com.k2.Wiget.WigetContainer;

@WigetSpecification
public interface CompilationUnitWiget extends JavaWiget<IType> {
	
	public static class Model extends IType_<CompilationUnitWiget> {
		public WigetContainer<CompilationUnitWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
