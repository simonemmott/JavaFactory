package com.k2.JavaFactory.type.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IMethodSignature;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.JavaFactory.type.wigetModel.ParameterContainer;
import com.k2.Util.StringUtil;

/**
 * A basic implementation of the IMethodSignature interface
 * 
 * @author simon
 *
 */
public class MethodSignatureImpl implements IMethodSignature, ParameterContainer {
	
	/**
	 * Create a method signature for a package visible method with void return type
	 * @param name	The name of the method
	 */
	public MethodSignatureImpl(String name) {
		this.visibility = Visibility.PACKAGE;
		this.returnType = null;
		this.name = name;
	}

	/**
	 * Create a method signature for the given visibility and name and returning void
	 * @param visibility		The visibility of the method signature
	 * @param name			The name of the method
	 */
	public MethodSignatureImpl(Visibility visibility, String name) {
		this.visibility = visibility;
		this.returnType = null;
		this.name = name;
	}

	/**
	 * Create a method signature setting the visibility, return type and name
	 * @param visibility		The visibility of the method signature
	 * @param returnType		The return type of the method signature
	 * @param name			The name of the method signature
	 */
	public MethodSignatureImpl(Visibility visibility, IType returnType, String name) {
		this.visibility = visibility;
		this.returnType = returnType;
		this.name = name;
	}

	/**
	 * Create a package visible method for the given return type and name 
	 * @param returnType		The method signature return type
	 * @param name			The name of the method signature
	 */
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
	/**
	 * Set the description of the return value
	 * @param returnTypeDescription		The description of the return value
	 * @return	This method signature for method chaining
	 */
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
	/**
	 * Set the title of the method signature
	 * @param title		The title of the method signature
	 * @return			This method signature for method chaining
	 */
	public MethodSignatureImpl setTitle(String title) {
		this.title = title;
		return this;
	}
	
	private String description;
	@Override
	public String getDescription() { return description; }
	/**
	 * Set the description of the method signature
	 * @param description	The description of the method signature
	 * @return		This method signature for method chaining
	 */
	public MethodSignatureImpl setDescription(String description) {
		this.description = description;
		return this;
	}
	
	private Boolean includeJavaDoc;
	@Override
	public boolean getIncludeJavaDoc() { return (includeJavaDoc==null) ? (StringUtil.isSet(title) || StringUtil.isSet(description)) : includeJavaDoc; }
	/**
	 * Define whether to include the javadoc for the field. If the title or description is set the default is to include the javadoc
	 * @param includeJavaDoc		True if the javadoc should be included in the generated source code
	 * @return	This field for method chaining
	 */
	public MethodSignatureImpl setIncludeJavaDoc(Boolean includeJavaDoc) {
		this.includeJavaDoc = includeJavaDoc;
		return this;
	}
	
	private Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	/**
	 * Annotate this field with the given annotation
	 * @param annotation		The annotation to add to the field
	 * @return		This field for method chaining
	 */
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
	/**
	 * Add the parameter to the method signature
	 * @param parameter	The parameter to add to the method signature
	 * @return		This method signature for method chaining
	 */
	public MethodSignatureImpl add(IParameter parameter) {
		parameters.add(parameter);
		return this;
	}
	/**
	 * Define a new parameter for this method signature
	 * @param type		The type of the new parameter
	 * @param name		The name of the new parameter
	 * @param isVarArgs		True if this parameter is an Array
	 * @return		The newly defined parameter for method chaining
	 */
	public ParameterImpl define(IType type, String name, boolean isVarArgs) {
		ParameterImpl parm = new ParameterImpl(this, type, name);
		if (isVarArgs)
			parm.varArgs();
		add(parm);
		return parm;
	}
	/**
	 * Define a new non Array parameter for this method signature with the given type and name
	 * @param type	The type of the new parameter
	 * @param name	The name of the new parameter
	 * @return		The newly defined parameter for method chaining
	 */
	public ParameterImpl define(IType type, String name) {
		return define(type, name, false);
	}
	@Override
	public List<IParameter> getParameters() {
		return parameters;
	}

	@Override
	public int compareTo(IMethodSignature o) { return name.compareTo(o.getName()); }


}
