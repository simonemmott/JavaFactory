package com.k2.JavaFactory.type;

import java.util.Set;

import com.k2.Util.classes.Dependency;

public interface IType extends Comparable<IType>{

	public String getPackageName();
	public String getBasename();
	public String getName();
	public Set<Dependency> getDependencies();

}
