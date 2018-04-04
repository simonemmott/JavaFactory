package com.k2.JavaFactory.type.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.Lists;
import com.k2.JavaFactory.JavaFactoryError;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IParameterValue;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.JavaFactory.type.wigetModel.ParameterContainer;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;

public class MethodImpl extends MethodSignatureImpl implements IMethod {
	
	public MethodImpl(IType declaringType, String name) {
		super(name);
		this.declaringType = declaringType;
	}

	public MethodImpl(IType declaringType, Visibility visibility, String name) {
		super(visibility, name);
		this.declaringType = declaringType;
	}

	public MethodImpl(IType declaringType, Visibility visibility, IType returnType, String name) {
		super(visibility, returnType, name);
		this.declaringType = declaringType;
	}

	public MethodImpl(IType declaringType, IType returnType, String name) {
		super(returnType, name);
		this.declaringType = declaringType;
	}

	private final IType declaringType;
	@Override
	public IType getDeclaringType() { return declaringType; }
	
	private String methodBody;
	@Override
	public String getMethodBody() { return methodBody; }
	public MethodImpl setMethodBody(String methodBody) {
		this.methodBody = methodBody;
		return this;
	}
	
	private Set<Dependency> dependencies;
	@Override
	public Set<Dependency> getDependencies() { return dependencies; }
	public MethodImpl dependsOn(Dependency dependency) {
		if (dependencies == null)
			dependencies = new TreeSet<Dependency>();
		dependencies.add(dependency);
		return this;
	}
	public MethodImpl dependsOn(Class<?> dependsOnClass) {
		return dependsOn(new Dependency(dependsOnClass));
	}

	protected Object unwrap;
	@Override
	public Object getUnwrap() { return (unwrap==null) ? this : unwrap; }
	public MethodImpl wrap(Object wrap) { this.unwrap = wrap; return this; }

	@SuppressWarnings("unchecked")
	public <C extends IClass> C up(Class<C> cls) {
		return (C)declaringType;
	}



}
