package com.k2.JavaFactory.type;

import java.util.Set;


/**
 * This interface defines an enumeration value in java source code
 * 
 * @author simon
 *
 */
public interface IEnumValue extends Comparable<IEnumValue>{

	/**
	 * @return	The definition of the declaring enumeration
	 */
	public IEnum getDeclaringEnum();
	
	/**
	 * @return	The name of the enumerated value. This is the exact string value that represents the enumerated value
	 */
	public String getName();
	
	/**
	 * @return	The title of the enumerated value. This value should be used to represent this enumerated value through the user interface
	 */
	public String getTitle();
	
	/**
	 * @return	The description of the enumerated value. This value should be used to describe the enumerated value in contextual help.
	 */
	public String getDescription();
	
	/**
	 * @return	This method returns a short convenience name to identify this enumerated value in logging or other internal purposes
	 */
	public default String getFullName() { return getDeclaringEnum().getBasename()+"."+getName(); }
	
	/**
	 * @return	This method returns a long string that uniquely identifies this enumerated value
	 */
	public default String getCanonicalName() { return getDeclaringEnum().getName()+"."+getName(); }
	
	/**
	 * @return	True if the java doc should be included in the source code
	 */
	public boolean getIncludeJavaDoc();
	
	/**
	 * @return	The annotations on this enumeration. If there are no annotations then an emprty set is returned
	 */
	public Set<IAnnotation> getAnnotations();
	
	/**
	 * @return	If this interface has been implemented through an adapter or similar this method returns the underlying object. Allowing embedded wigets access to all the methods of the underlying object.
	 */
	public Object getUnwrap();

}
