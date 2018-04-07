package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.IEnumValue;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.classes.Dependency;

/**
 * A basic implementation of the IEnum interface
 * 
 * @author simon
 *
 */
public class EnumImpl extends TypeImpl implements IEnum {
	
	public EnumImpl(String name) { super(name); }

	@Override public EnumImpl setAuthor(String author) { super.setAuthor(author); return this; }
	@Override public EnumImpl setTitle(String title) { super.setTitle(title); return this; }
	@Override public EnumImpl setDescription(String description) { super.setDescription(description); return this; }
	@Override public EnumImpl setIncludeJavaDoc(Boolean includeJavaDoc) { super.setIncludeJavaDoc(includeJavaDoc); return this; }
	@Override public EnumImpl annotate(IAnnotation annotation) { super.annotate(annotation); return this; }
	@Override public EnumImpl wrap(Object wrap) { super.wrap(wrap); return this; }
	@Override public EnumImpl add(Dependency dependency) { super.add(dependency); return this; }
	
	private Visibility visibility;
	@Override
	public Visibility getVisibility() { return visibility; }
	/**
	 * Set the visibility of the enumeration
	 * @param visibility		The visibility for the enumertation
	 * @return		This enumeration for method chaining
	 */
	public EnumImpl visibility(Visibility visibility) {
		this.visibility = visibility;
		return this;
	}

	private Set<IInterface> implementsInterfaces = new TreeSet<IInterface>();
	@Override
	public Set<IInterface> getImplementsInterfaces() { return implementsInterfaces; }
	/**
	 * Add the interface to the list interfaces implemented by this enumeration
	 * @param implementsInterface	The interface implemented by this enumeration
	 * @return	This enumeration for method chaining
	 */
	public EnumImpl implementsInterface(IInterface implementsInterface) {
		implementsInterfaces.add(implementsInterface);
		return this;
	}
	/**
	 * Identify that this enumeration implements the interface with the given canonical name
	 * @param interfaceName	The canonical name of the interface implemented by this enumeration
	 * @return	This enumeration for method chaining
	 */
	public EnumImpl implementsInterface(String interfaceName) {
		return implementsInterface(new InterfaceImpl(interfaceName));
	}

	private final Set<IEnumValue> values = new TreeSet<IEnumValue>();
	@Override
	public Set<IEnumValue> getValues() { return values; }
	/**
	 * Define a new value for this enumeration
	 * @param name	The name of the new value
	 * @return	The newly defined enumeration value
	 */
	public EnumValueImpl defineValue(String name) {
		EnumValueImpl value = new EnumValueImpl(this, name);
		values.add(value);
		return value;
	}


	
}
