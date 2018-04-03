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
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IParameterValue;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.JavaFactory.type.wigetModel.ParameterContainer;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.Dependency;

public class MethodSignatureImpl implements IMethodSignature, ParameterContainer {
	
	public MethodSignatureImpl(String name) {
		this.visibility = Visibility.PACKAGE;
		this.returnType = null;
		this.name = name;
	}

	public MethodSignatureImpl(Visibility visibility, String name) {
		this.visibility = visibility;
		this.returnType = null;
		this.name = name;
	}

	public MethodSignatureImpl(Visibility visibility, IType returnType, String name) {
		this.visibility = visibility;
		this.returnType = returnType;
		this.name = name;
	}

	public MethodSignatureImpl(IType returnType, String name) {
		this.visibility = Visibility.PACKAGE;
		this.returnType = returnType;
		this.name = name;
	}

	private final Visibility visibility;
	@Override
	public Visibility getVisibility() { return visibility; }
	
	private final IType returnType;
	@Override
	public IType getReturnType() { return returnType; }
	
	private String returnTypeDescription;
	@Override
	public String getReturnTypeDescription() { return returnTypeDescription; }
	public MethodSignatureImpl setReturnTypeDescription(String returnTypeDescription) {
		this.returnTypeDescription = returnTypeDescription;
		return this;
	}
	
	private final String name;
	@Override
	public String getName() { return name; }
	
	private String title;
	@Override
	public String getTitle() { return title; }
	public MethodSignatureImpl setTitle(String title) {
		this.title = title;
		return this;
	}
	
	private String description;
	@Override
	public String getDescription() { return description; }
	public MethodSignatureImpl setDescription(String description) {
		this.description = description;
		return this;
	}
	
	private Boolean includeJavaDoc;
	@Override
	public boolean getIncludeJavaDoc() { return (includeJavaDoc==null) ? (StringUtil.isSet(title) || StringUtil.isSet(description)) : includeJavaDoc; }
	public MethodSignatureImpl setIncludeJavaDoc(Boolean includeJavaDoc) {
		this.includeJavaDoc = includeJavaDoc;
		return this;
	}
	
	private Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	public MethodSignatureImpl annotate(IAnnotation annotation) {
		if (annotations == null)
			annotations = new TreeSet<IAnnotation>();
		annotations.add(annotation);
		return this;
	}

	@Override
	public boolean getHasParameters() { return (parameters.size() > 0); }
	
	@Override 
	public int getParameterCount() { return parameters.size(); }
	
	private final List<IParameter> parameters = new ArrayList<IParameter>();
	@Override
	public IParameter getParameter(String name) {
		for (IParameter p : parameters)
			if (p.getName().equals(name))
				return p;
		return null;
	}
	public MethodSignatureImpl add(IParameter parameter) {
		parameters.add(parameter);
		return this;
	}
	public ParameterImpl define(IType type, String name, boolean isVarArgs) {
		ParameterImpl parm = new ParameterImpl(this, type, name);
		if (isVarArgs)
			parm.varArgs();
		add(parm);
		return parm;
	}
	@Override
	public List<IParameter> getParameters() {
		return parameters;
	}

	@Override
	public int compareTo(IMethodSignature o) { return name.compareTo(o.getName()); }


}
