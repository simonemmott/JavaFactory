package com.k2.JavaFactory.spec;

import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.wigetModel.IAnnotation_;
import com.k2.Wiget.annotation.WigetSpecification;

@WigetSpecification
public interface AnnotationWiget extends JavaWiget<IAnnotation> {
	
	public static class Model extends IAnnotation_<AnnotationWiget> {
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
