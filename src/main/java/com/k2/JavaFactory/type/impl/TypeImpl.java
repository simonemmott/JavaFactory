package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IType;
import com.k2.Util.classes.ClassUtil;
import com.k2.Util.classes.Dependencies;
import com.k2.Util.classes.Dependency;

public class TypeImpl implements IType {
	
	public TypeImpl(String name) { this.name = name; }
	
	protected String name;
	@Override
	public String getPackageName() { return ClassUtil.getPackageNameFromCanonicalName(name); }

	@Override
	public String getBasename() { return ClassUtil.getBasenameFromCanonicalName(name); }

	@Override
	public String getName() { return name; }

	Dependencies dependencies;
	@Override
	public Set<Dependency> getDependencies() { 
		if (dependencies == null)
			dependencies = Dependencies.forName(name);
		return dependencies.getDependencies(); 
	}
	public IType addDependency(Dependency dependency) {
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
