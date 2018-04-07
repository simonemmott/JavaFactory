package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.wigetModel.IClass_;
import com.k2.JavaFactory.type.wigetModel.IInterface_;
import com.k2.JavaFactory.type.wigetModel.IMethodSignature_;
import com.k2.Wiget.WigetContainer;
import com.k2.Wiget.WigetParameter;

/**
 * Write the method signature 
 * 
 * The method signature wiget does not end with a new line, neither does it end with a ';' this allows the method signature wiget to be used to write
 * the method signature of both interface method signatures and class method signatures
 * 
 * @author simon
 *
 */
@WigetSpecification
public interface MethodSignatureWiget extends JavaWiget<IMethodSignature> {
	
	public static class Model extends IMethodSignature_<MethodSignatureWiget> {
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
