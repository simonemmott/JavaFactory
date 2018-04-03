package com.k2.JavaFactory.impl;

import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Wiget.annotation.WigetImplementation;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFactory;
import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.spec.AnnotationWiget;
import com.k2.JavaFactory.spec.ClassWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IParameterValue;
import com.k2.JavaFactory.type.Visibility;
import com.k2.Util.ObjectUtil;
import com.k2.Util.java.JavaUtil;
import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class AnnotationWigetImpl extends AJavaWiget<IAnnotation> implements AnnotationWiget{
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, IAnnotation> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();
		
		ja.addDependencyFor(a.get(AnnotationWiget.model.name));
		boolean inline = a.get(AnnotationWiget.model.inline);
		
		logger.trace("Outputting annotation: {}", a.get(AnnotationWiget.model.name));
		
		List<IParameterValue> parameterValues = a.get(AnnotationWiget.model.parameterValues);
		
		if (parameterValues == null || parameterValues.size() == 0)
			if ( inline )
				out.print("@"+a.get(AnnotationWiget.model.basename));
			else
				out.print(ja.getIndent()+"@"+a.get(AnnotationWiget.model.basename));
		else {

			if ( inline )
				out.print("@"+a.get(AnnotationWiget.model.basename)+"(");
			else {
				out.print(ja.getIndent()+"@"+a.get(AnnotationWiget.model.basename)+"(");
				ja.indent();
			}

			Iterator<IParameterValue> i = parameterValues.iterator();
			boolean first = true;
			while (i.hasNext()) {

				IParameterValue pValue = i.next();
				
				logger.trace("Outputting parameter {} for annotation {}", pValue.getParameter().getName(), a.get(AnnotationWiget.model.basename));
				
				Object value = pValue.getValue();
				
				if ( ! pValue.getParameter().getName().equals("value"))
					if (first)
						out.print(pValue.getParameter().getName()+" = ");
					else
						out.print(ja.getIndent(inline)+pValue.getParameter().getName()+" = ");
				else
					if (! first)
						out.print(ja.getIndent(inline));
				first = false;
				
				if (pValue.getParameter().getIsVarArgs() ) {
					logger.trace("Parameter {} for annotation {} is an array", pValue.getParameter().getName(), a.get(AnnotationWiget.model.basename));
					out.print("{");
					
					if (value.getClass().isArray() && value.getClass().getComponentType().isPrimitive()) {
						if (value.getClass().getComponentType() == int.class) {
							int[] arr = (int[])value;
							for (int j = 0; j<arr.length; j++) {
								out.print(JavaUtil.toJavaSource(arr[j]));
								if (j < arr.length-1)
									out.print(", ");
							}
						} else if (value.getClass().getComponentType() == long.class) {
							long[] arr = (long[])value;
							for (int j = 0; j<arr.length; j++) {
								out.print(JavaUtil.toJavaSource(arr[j]));
								if (j < arr.length-1)
									out.print(", ");
							}
						} else if (value.getClass().getComponentType() == boolean.class) {
							boolean[] arr = (boolean[])value;
							for (int j = 0; j<arr.length; j++) {
								out.print(JavaUtil.toJavaSource(arr[j]));
								if (j < arr.length-1)
									out.print(", ");
							}
						} else if (value.getClass().getComponentType() == float.class) {
							float[] arr = (float[])value;
							for (int j = 0; j<arr.length; j++) {
								out.print(JavaUtil.toJavaSource(arr[j]));
								if (j < arr.length-1)
									out.print(", ");
							}
						} else if (value.getClass().getComponentType() == double.class) {
							double[] arr = (double[])value;
							for (int j = 0; j<arr.length; j++) {
								out.print(JavaUtil.toJavaSource(arr[j]));
								if (j < arr.length-1)
									out.print(", ");
							}
						} else if (value.getClass().getComponentType() == char.class) {
							char[] arr = (char[])value;
							for (int j = 0; j<arr.length; j++) {
								out.print(JavaUtil.toJavaSource(arr[j]));
								if (j < arr.length-1)
									out.print(", ");
							}
						} else if (value.getClass().getComponentType() == short.class) {
							short[] arr = (short[])value;
							for (int j = 0; j<arr.length; j++) {
								out.print(JavaUtil.toJavaSource(arr[j]));
								if (j < arr.length-1)
									out.print(", ");
							}
						} else if (value.getClass().getComponentType() == byte.class) {
							byte[] arr = (byte[])value;
							for (int j = 0; j<arr.length; j++) {
								out.print(JavaUtil.toJavaSource(arr[j]));
								if (j < arr.length-1)
									out.print(", ");
							}
						} 
						out.print("}");
					} else {
						Iterator<Object> j;
					
						if ( ! inline ) {
							out.println();
							ja.indent();
						}

						if (value.getClass().isArray()) {
							logger.trace("Array parameter {} for annotation {} is an array", pValue.getParameter().getName(), a.get(AnnotationWiget.model.basename));
							j = Arrays.asList((Object[])value).iterator();
						} else if (value instanceof Collection) {
							logger.trace("Array parameter {} for annotation {} is a Collection", pValue.getParameter().getName(), a.get(AnnotationWiget.model.basename));
							Collection collectionValue = (Collection<?>)value;
							j = collectionValue.iterator();
						} else {
							logger.trace("Array parameter {} for annotation {} is a Singleton", pValue.getParameter().getName(), a.get(AnnotationWiget.model.basename));
							j = ObjectUtil.singletonSet(value).iterator();
						}
						
						int k=0;
						while (j.hasNext()) {
							Object listValue = j.next(); 
							
							logger.trace("Array parameter {} for annotation {} has value {} at [{}]", pValue.getParameter().getName(), a.get(AnnotationWiget.model.basename), listValue, k++);
	
							writeValue(ja, inline, listValue, out);
							
							if (j.hasNext())
								out.print(", ");
							if ( ! inline )
								out.println();
	
						}
						if ( ! inline ) {
							ja.outdent();
						}
						out.print(ja.getIndent(inline)+"}");
					}
				} else {

					logger.trace("Parameter {} for annotation {} has value {}", pValue.getParameter().getName(), a.get(AnnotationWiget.model.basename), value);

					writeValue(ja, true, value, out);

				}
				if (i.hasNext())
					out.print(", ");
				if ( ! inline && i.hasNext())
					out.println();
				
			}
			
			if ( inline )
				out.print(")");
			else {
				ja.outdent();
				out.print(")");
			}
		}
		
		return out;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void writeValue(JavaAssembly ja, boolean inline, Object value, PrintWriter out) {
		if (value.getClass().equals(String.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(value));
		if (value.getClass().equals(Integer.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(((Integer)value).intValue()));
		if (value.getClass().equals(Long.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(((Long)value).longValue()));
		if (value.getClass().equals(Float.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(((Float)value).floatValue()));
		if (value.getClass().equals(Double.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(((Double)value).doubleValue()));
		if (value.getClass().equals(Boolean.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(((Boolean)value).booleanValue()));
		if (value.getClass().equals(Character.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(((Character)value).charValue()));
		if (value.getClass().equals(Short.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(((Short)value).shortValue()));
		if (value.getClass().equals(Byte.class))
			out.print(ja.getIndent(inline)+JavaUtil.toJavaSource(((Byte)value).byteValue()));
		else if (value instanceof IAnnotation) {
			IAnnotation ann = (IAnnotation)value;
			ja.addDependencyFor(ann.getName());
			AssembledWiget<JavaFamily, PrintWriter, AnnotationWiget, IAnnotation> aw = ja.assemble(AnnotationWiget.class);
			out = aw.output(ann, out);
		} else if (value instanceof Class) {
			Class<?> classValue = (Class<?>)value;
			ja.addDependencyFor(classValue);
			out.print(ja.getIndent(inline)+classValue.getSimpleName()+".class");
		} else if (value.getClass().isEnum()) {
			Enum<?> enumValue = (Enum<?>)value;
			ja.addDependencyFor(enumValue.getDeclaringClass());
			out.print(ja.getIndent(inline)+enumValue.getDeclaringClass().getSimpleName()+"."+enumValue.name());
		}
	}

	




	
	

}
