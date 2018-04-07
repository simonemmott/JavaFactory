package com.k2.JavaFactory.type.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IType;
import com.k2.Util.StringUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.Dependencies;
import com.k2.Util.classes.Dependency;

/**
 * A basic implementation of the IType interface
 * 
 * @author simon
 *
 */
public class TypeImpl implements IType {
	
	/**
	 * Create a type for the given canonical name
	 * @param name	The canonical name of the type
	 */
	public TypeImpl(String name) { this.name = name; }

	protected IType declaringType;
	@Override
	public IType getDeclaringType() { return declaringType; }
	@Override
	public IType setDeclaringType(IType declaringType) { this.declaringType = declaringType; return this; }

	protected String name;
	@Override
	public String getPackageName() { return ClassUtil.getPackageNameFromCanonicalName(name); }

	@Override
	public String getBasename() { return ClassUtil.getBasenameFromCanonicalName(name); }

	@Override
	public String getName() { return name; }

	private String author;
	@Override
	public String getAuthor() { return author; }
	/**
	 * Set the author of the type
	 * @param author		The author of the type
	 * @return		This type for method chaining
	 */
	public TypeImpl setAuthor(String author) {
		this.author = author;
		return this;
	}
	
	private String title;
	@Override
	public String getTitle() { return title; }
	/**
	 * Set the title of the type
	 * @param title	The title of the type
	 * @return	This type for method chaining
	 */
	public TypeImpl setTitle(String title) {
		this.title = title;
		return this;
	}
	
	private String description;
	@Override
	public String getDescription() { return description; }
	/**
	 * Set the description of the type
	 * @param description	The description of the type
	 * @return		This type for method chaining
	 */
	public TypeImpl setDescription(String description) {
		this.description = description;
		return this;
	}
	
	private Boolean includeJavaDoc;
	@Override
	public boolean getIncludeJavaDoc() { return (includeJavaDoc==null) ? (StringUtil.isSet(title) || StringUtil.isSet(description) || StringUtil.isSet(author)) : includeJavaDoc; }
	/**
	 * Define whether to include the javadoc for the field. If the title or description is set the default is to include the javadoc
	 * @param includeJavaDoc		True if the javadoc should be included in the generated source code
	 * @return	This field for method chaining
	 */
	public TypeImpl setIncludeJavaDoc(Boolean includeJavaDoc) {
		this.includeJavaDoc = includeJavaDoc;
		return this;
	}
	
	protected Set<IAnnotation> annotations= new TreeSet<IAnnotation>();
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	/**
	 * Annotate this field with the given annotation
	 * @param annotation		The annotation to add to the field
	 * @return		This type for method chaining
	 */
	public TypeImpl annotate(IAnnotation annotation) {
		annotations.add(annotation);
		return this;
	}

	protected List<IType> declaredTypes = new ArrayList<IType>();
	@Override
	public List<IType> getDeclaredTypes() { return declaredTypes; }
	@SuppressWarnings("unchecked")
	/**
	 * Add a declared type to this type
	 * @param type	The type to be declared by this type
	 * @param declaringType	The class of the declaring type. Setting this type class allows the return value to be of the type of the declaring class
	 * @return	This type for method chaining
	 */
	public <T extends IType> T declares(TypeImpl type, Class<T> declaringType) {
		type.setDeclaringType(this);
		declaredTypes.add(type);
		return (T)this;
	}

	protected Object unwrap;
	@Override
	public Object getUnwrap() { return (unwrap==null) ? this : unwrap; }
	/**
	 * Wrap the given object to be returned as the unwrap parameter in the wiget
	 * @param wrap	The object to wrap
	 * @return	This enumerated value for method chaining
	 */
	public TypeImpl wrap(Object wrap) { this.unwrap = wrap; return this; }

	Dependencies dependencies;
	@Override
	public Set<Dependency> getDependencies() { 
		if (dependencies == null)
			dependencies = Dependencies.forName(name);
		return dependencies.getDependencies(); 
	}
	/**
	 * Add a dependency to the type
	 * @param dependency		The dependency to add to the type. This shouldn't be required as the dependencies are calculated when the java source is generated
	 * @return	This type for method chaining
	 */
	public IType add(Dependency dependency) {
		if (dependencies == null)
			dependencies = Dependencies.forName(name);
		dependencies.add(dependency);
		return this;
	}

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
		TypeImpl other = (TypeImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(IType o) { return name.compareTo(o.getName()); }
	
	

}
