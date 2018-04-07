package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.StringUtil;

/**
 * A basic implementation of the IField interface
 * 
 * @author simon
 *
 */
public class FieldImpl implements IField {
	
	/**
	 * Create a field on the declaringClass with the given visibility, type and name
	 * @param declaringClass		The class declaring the new field
	 * @param visibility			The visibility of the new field
	 * @param javaType		The java type of the new field
	 * @param name		The name of the field
	 */
	public FieldImpl(IClass declaringClass, Visibility visibility, IType javaType, String name) { 
		this.declaringClass = declaringClass; 
		this.visibility = visibility; 
		this.javaType = javaType; 
		this.name = name; 
	}
	
	/**
	 * Create a private field on the declaring class with the given type and name
	 * @param declaringClass		The class declaring the field
	 * @param javaType			The java type of the new field
	 * @param name				The name of the new field
	 */
	public FieldImpl(IClass declaringClass, IType javaType, String name) { 
		this.declaringClass = declaringClass; 
		this.visibility = Visibility.PRIVATE; 
		this.javaType = javaType; 
		this.name = name; 
	}
	
	protected final IClass declaringClass;
	@Override public IClass getDeclaringClass() { return declaringClass; }
	protected final Visibility visibility;
	@Override public Visibility getVisibility() { return visibility; }
	protected final IType javaType;
	@Override public IType getJavaType() { return javaType; }
	protected final String name;
	@Override public String getName() { return name; }

	private String title;
	@Override
	public String getTitle() { return title; }
	/**
	 * Set the title of the field
	 * @param title	The new title for the field
	 * @return	This field for method chaining
	 */
	public FieldImpl setTitle(String title) {
		this.title = title;
		return this;
	}
	
	private String description;
	@Override
	public String getDescription() { return description; }
	/**
	 * Set the description of the field
	 * @param description	The new description for the field
	 * @return	This field for method chaining
	 */
	public FieldImpl setDescription(String description) {
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
	public FieldImpl setIncludeJavaDoc(Boolean includeJavaDoc) {
		this.includeJavaDoc = includeJavaDoc;
		return this;
	}
	
	private Boolean includeGetter = true;
	@Override
	public boolean getIncludeGetter() { return (includeGetter==null) ? true: includeGetter; }
	/**
	 * Set whether or not to include a getter method with this field. defaults to true
	 * @param includeGetter	True if a getter should be included with the field
	 * @return		This field for method chaining
	 */
	public FieldImpl setIncludeGetter(Boolean includeGetter) {
		this.includeGetter = includeGetter;
		return this;
	}
	
	private Boolean includeSetter = true;
	@Override
	public boolean getIncludeSetter() { return (includeSetter==null) ? true : includeSetter; }
	/**
	 * Set whether or not to include a setter. defaults to true
	 * @param includeSetter		True if a setter should be included with the field
	 * @return		This field for method chaining
	 */
	public FieldImpl setIncludeSetter(Boolean includeSetter) {
		this.includeSetter = includeSetter;
		return this;
	}
	
	protected Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	/**
	 * Annotate this field with the given annotation
	 * @param annotation		The annotation to add to the field
	 * @return		This field for method chaining
	 */
	public FieldImpl annotate(IAnnotation annotation) {
		if (annotations == null)
			annotations = new TreeSet<IAnnotation>();
		annotations.add(annotation);
		return this;
	}

	protected Object unwrap;
	@Override
	public Object getUnwrap() { return (unwrap==null) ? this : unwrap; }
	/**
	 * Wrap the given object to be returned as the unwrap parameter in the wiget
	 * @param wrap	The object to wrap
	 * @return	This enumerated value for method chaining
	 */
	public FieldImpl wrap(Object wrap) { this.unwrap = wrap; return this; }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldImpl other = (FieldImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(IField o) { return getCanonicalName().compareTo(o.getCanonicalName()); }

	@SuppressWarnings("unchecked")
	public <C extends IClass> C up(Class<C> cls) {
		return (C)declaringClass;
	}

	
	

}
