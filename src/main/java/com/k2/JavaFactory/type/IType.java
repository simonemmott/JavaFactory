package com.k2.JavaFactory.type;

import java.util.List;
import java.util.Set;

import com.k2.Util.classes.Dependency;

/**
 * This interface defines the generic data for all java types in java source code
 * 
 * @author simon
 *
 */
public interface IType extends Comparable<IType>{

	/**
	 * @return	The package name of the java type
	 */
	public String getPackageName();
	
	/**
	 * @return	The short name of the type
	 */
	public String getBasename();
	
	/**
	 * @return	The canonical name of the type
	 */
	public String getName();
	
	/**
	 * @return	The name of the author of the type
	 */
	public String getAuthor();
	
	/**
	 * @return	The title of the type. This value should be used when identifying this type to a user
	 */
	public String getTitle();
	
	/**
	 * @return	The description of this type that should be used when describing this type in contextual help
	 */
	public String getDescription();
	
	/**
	 * @return	True if the javadoc should be included for this type
	 */
	public boolean getIncludeJavaDoc();
	
	/**
	 * @return	The set of annotations on this java type. If this type is not annotated then an empty set it returned
	 */
	public Set<IAnnotation> getAnnotations();
	
	/**
	 * @return	The set of dependencies that should be imported with this java type, over and above those dependencies identifies during generation of the java type
	 */
	public Set<Dependency> getDependencies();
	
	/**
	 * @return	The set of types declared by this type. If this type does not declare any other types then an empty set is returned
	 */
	public List<IType> getDeclaredTypes();
	
	/**
	 * @return	The definition of the type that declares this type if this type is declared by another type. If this type is  contained directly in a package this method will return null
	 */
	public IType getDeclaringType();
	
	/**
	 * This method should be called when an type is added to the list of declaring types in a parent type;
	 * @param declaringType	The type that declares this type
	 * @return	This type for method chaining
	 */
	public IType setDeclaringType(IType declaringType);
	
	/**
	 * @return If this interface has been implemented through an adapter or similar this method should return the underlying object. Allowing embedded wigets access to all the methods of the underlying object.
	 */
	public Object getUnwrap();

}
