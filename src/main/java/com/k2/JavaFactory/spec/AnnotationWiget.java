package com.k2.JavaFactory.spec;

import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.wigetModel.IAnnotation_;
import com.k2.Wiget.annotation.WigetSpecification;

/**
 * This wiget includes an annotation in the assembled java code
 * Annotations are written on their own line(s) by default but can be overriden by setting the parameter inline to true
 * The annotation wiget does not end with a newline on the output to allow annotations to be inline if required
 * 
 * @author simon
 *
 */
@WigetSpecification
public interface AnnotationWiget extends JavaWiget<IAnnotation> {
	
	public static class Model extends IAnnotation_<AnnotationWiget> {
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
