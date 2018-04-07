package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.wigetModel.ParameterContainer;

/**
 * A basic implementation of the IParameter interface
 * 
 * @author simon
 *
 */
public class ParameterImpl implements IParameter {
	
	private ParameterContainer owner;
	private String name;
	/**
	 * Create a parameter for the given parameter container of the given type and name
	 * @param owner		The parameter container
	 * @param type		The type of the parameter
	 * @param name		The name of the parameter
	 */
	public ParameterImpl(ParameterContainer owner, IType type, String name) { 
		this.owner = owner;
		this.type = type;
		this.name = name; 
	}
	
	@Override
	public String getName() { return name; }

	private IType type;
	@Override
	public IType getType() { return type; }

	private boolean isVarArgs = false;
	@Override
	public boolean getIsVarArgs() { return isVarArgs; }
	/**
	 * make this parameter an array type parameter
	 * @return	This parameter for method chaining
	 */
	public ParameterImpl varArgs() { isVarArgs = true; return this; }

	private String title;
	@Override
	public String getTitle() { return title; }
	/**
	 * Set the title of the parameter
	 * @param title	The title of the parameter
	 * @return		This parameter for method chaining
	 */
	public ParameterImpl setTitle(String title) {
		this.title = title;
		return this;
	}
	
	private String description;
	@Override
	public String getDescription() { return description; }
	/**
	 * Set the description of this parameter
	 * @param description	The description of this parameter
	 * @return	The parameter for method chaining
	 */
	public ParameterImpl setDescription(String description) {
		this.description = description;
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <O> O up(Class<O> ownerClass) {
		return (O)owner;
	}
	
	private Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	/**
	 * Annotate this field with the given annotation
	 * @param annotation		The annotation to add to the field
	 * @return		This parameter for method chaining
	 */
	public ParameterImpl annotate(IAnnotation annotation) {
		if (annotations == null)
			annotations = new TreeSet<IAnnotation>();
		annotations.add(annotation);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		ParameterImpl other = (ParameterImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}


	
	

}
