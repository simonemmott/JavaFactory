package com.k2.JavaFactory.type.impl;

import java.util.Set;
import java.util.TreeSet;

import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IField;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IMethod;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.classes.Dependency;

/**
 * A basic implementation of the IClass interface
 * 
 * @author simon
 *
 */
public class ClassImpl extends TypeImpl implements IClass {
	
	public ClassImpl(String name) { super(name); }

	@Override public ClassImpl setAuthor(String author) { super.setAuthor(author); return this; }
	@Override public ClassImpl setTitle(String title) { super.setTitle(title); return this; }
	@Override public ClassImpl setDescription(String description) { super.setDescription(description); return this; }
	@Override public ClassImpl setIncludeJavaDoc(Boolean includeJavaDoc) { super.setIncludeJavaDoc(includeJavaDoc); return this; }
	@Override public ClassImpl annotate(IAnnotation annotation) { super.annotate(annotation); return this; }
	@Override public ClassImpl wrap(Object wrap) { super.wrap(wrap); return this; }
	@Override public ClassImpl add(Dependency dependency) { super.add(dependency); return this; }
	
	private Visibility visibility;
	@Override
	public Visibility getVisibility() { return visibility; }
	/**
	 * Set the visibility of the class
	 * @param visibility		The visibility of the class
	 * @return	This class definition for method chaining
	 */
	public ClassImpl visibility(Visibility visibility) {
		this.visibility = visibility;
		return this;
	}

	private IClass extendsClass;
	@Override
	public IClass getExtendsClass() { return extendsClass; }
	/**
	 * Set the definition of the class this this class extends
	 * @param extendsClass	The class definition that this class extends
	 * @return	This class definition for method chaining
	 */
	public ClassImpl extendsClass(IClass extendsClass) { 
		this.extendsClass = extendsClass; 
		return this; 
	}
	/**
	 * Identify this class as extending a class with the given class name
	 * @param className	The canonical name of the class that this class extends
	 * @return	This class for method chaining
	 */
	public ClassImpl extendsClass(String className) { 
		return extendsClass(new ClassImpl(className)); 
	}

	private Set<IInterface> implementsInterfaces = new TreeSet<IInterface>();
	@Override
	public Set<IInterface> getImplementsInterfaces() { return implementsInterfaces; }
	/**
	 * Identify that this class implements this given interface definition
	 * @param implementsInterface	The definition of the interface that this class implements
	 * @return		This class for method chaining
	 */
	public ClassImpl implementsInterface(IInterface implementsInterface) {
		implementsInterfaces.add(implementsInterface);
		return this;
	}
	/**
	 * Identify that this class implements an interface with the given canonical name
	 * @param interfaceName	The canonical name of the interface that this class implements
	 * @return	This class for method chaining
	 */
	public ClassImpl implementsInterface(String interfaceName) {
		return implementsInterface(new InterfaceImpl(interfaceName));
	}

	private final Set<IField> fields = new TreeSet<IField>();
	@Override
	public Set<IField> getFields() { return fields; }
	/**
	 * Define a new field on this class definition
	 * @param visibility		The visibility of the new field
	 * @param javaType		The definition of the java type of the new field
	 * @param name			The name of the new field
	 * @return	The newly defined field for method chaining
	 */
	public FieldImpl defineField(Visibility visibility, IType javaType, String name) {
		FieldImpl field = new FieldImpl(this, visibility, javaType, name);
		fields.add(field);	
		return field;
	}
	/**
	 * Define a new private field on this class definition
	 * @param javaType	The definition of the java type of the new field
	 * @param name		The name of the new field
	 * @return		The newly defined field for method chaining
	 */
	public FieldImpl defineField(IType javaType, String name) {
		FieldImpl field = new FieldImpl(this, javaType, name);
		fields.add(field);
		return field;
	}

	private final Set<IMethod> methods = new TreeSet<IMethod>();
	@Override
	public Set<IMethod> getMethods() { return methods; }
	/**
	 * Define a new method on this class with the given signature
	 * @param visibility		The visibility of the method
	 * @param returnType		The definition of the java type of the new field
	 * @param name			The name of the method
	 * @return			The newly defined method for method chaining
	 */
	public MethodImpl defineMethod(Visibility visibility, IType returnType, String name) {
		MethodImpl method = new MethodImpl(this, visibility, returnType, name);
		methods.add(method);
		return method;
	}
	/**
	 * Define a new package visible method on this class with the given signature
	 * @param returnType		The definition of the java type of the new field
	 * @param name			The name of the method
	 * @return			The newly defined method for method chaining
	 */
	public MethodImpl defineMethod(IType returnType, String name) {
		MethodImpl method = new MethodImpl(this, returnType, name);
		methods.add(method);
		return method;
	}
	/**
	 * Define a new package visible method on this class with the given name that returns void
	 * @param name			The name of the method
	 * @return			The newly defined method for method chaining
	 */
	public MethodImpl defineMethod(String name) {
		MethodImpl method = new MethodImpl(this, name);
		methods.add(method);
		return method;
	}
	/**
	 * Define a new method on this class with the given name that return void
	 * @param visibility		The visibility of the method
	 * @param name			The name of the method
	 * @return			The newly defined method for method chaining
	 */
	public MethodImpl defineMethod(Visibility visibility, String name) {
		MethodImpl method = new MethodImpl(this, visibility, name);
		methods.add(method);
		return method;
	}

	
}
