package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IParameter;
import com.k2.JavaFactory.type.IType;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.Dependencies;
import com.k2.Util.classes.Dependency;

public class ParameterImpl implements IParameter {
	
	private IType owner;
	private String name;
	public ParameterImpl(IType owner, IType type, String name) { 
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
	public ParameterImpl varArgs() { isVarArgs = true; return this; }

	private Set<IAnnotation> annotations;
	@Override
	public Set<IAnnotation> getAnnotations() { return annotations; }
	public ParameterImpl addAnnotation(IAnnotation annotation) {
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
