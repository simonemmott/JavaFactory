package com.k2.JavaFactory;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.Util.classes.Dependency;

import example.model.MyClass;
import example.wiget.MyWiget;

import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFactory;
import com.k2.JavaFactory.spec.ClassWiget;
import com.k2.JavaFactory.spec.CompilationUnitWiget;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.JavaFactory.type.impl.AnnotationImpl;
import com.k2.JavaFactory.type.impl.ClassImpl;
import com.k2.JavaFactory.type.impl.InterfaceImpl;
import com.k2.JavaFactory.type.impl.TypeImpl;

public class JavaFactoryTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Test
	public void classWigetTest() 
    {
		JavaFactory factory = new JavaFactory("com.k2.JavaFactory.impl", "example.wiget");
				
		MyClass my = new MyClass();
		my.setAlias("myAlias");
		my.setTitle("myTitle");
		my.setDescription("myDescription");
		
		
		IType tString = new TypeImpl("java.lang.String");
		IType tInt = new TypeImpl("int");
		IType tLong = new TypeImpl("long");
		IType tBoolean = new TypeImpl("boolean");
		IType tFloat = new TypeImpl("float");
		IType tDouble = new TypeImpl("double");
		IType tChar = new TypeImpl("char");
		IType tByte = new TypeImpl("byte");
		IType tShort = new TypeImpl("short");
		IType tClass = new TypeImpl("java.lang.Class");
		IType tEnum = new TypeImpl("java.lang.Enum");
		IType tAnnotation = new TypeImpl("java.lang.annotation.Annotation");
		
		IClass iClass = new ClassImpl("my.test.myClass")
				.addDependency(Dependency.forClass(Set.class))
				.addDependency(Dependency.forClass(HashSet.class))
				.extendsClass(new ClassImpl("my.test.myType"))
				.implementsInterface(new InterfaceImpl("my.test.interfaces.IMyClass"))
				.implementsInterface(new InterfaceImpl("my.test.interfaces.AnotherInterface"))
				.addAnnotation(new AnnotationImpl("my.test.annotations.MyAnnotation")
						.define(tString, "value", false)
						.define(tString, "stringVal", false)
						.define(tInt, "intVal", false)
						.define(tLong, "longVal", false)
						.define(tFloat, "floatVal", false)
						.define(tDouble, "doubleVal", false)
						.define(tBoolean, "booleanVal", false)
						.define(tChar, "charVal", false)
						.define(tShort, "shortVal", false)
						.define(tByte, "byteVal", false)
						.define(tClass, "classVal", false)
						.define(tEnum, "enumVal", false)
						.define(tAnnotation, "annotationVal", false)
						.define(tString, "stringArr", true)
						.define(tInt, "intArr", true)
						.define(tLong, "longArr", true)
						.define(tFloat, "floatArr", true)
						.define(tDouble, "doubleArr", true)
						.define(tBoolean, "booleanArr", true)
						.define(tChar, "charArr", true)
						.define(tShort, "shortArr", true)
						.define(tByte, "byteArr", true)
						.define(tClass, "classArr", true)
						.define(tEnum, "enumArr", true)
						.define(tAnnotation, "annotationArr", true)
						.set("value", "This is the value")
						.set("stringVal", "This is the string value")
						.set("intVal", 123)
						.set("longVal", 999L)
						.set("floatVal", 123.456f)
						.set("doubleVal", 123.456789)
						.set("charVal", 'c')
						.set("shortVal", (short)123)
						.set("byteVal", (byte)1)
						.set("classVal", "This is the value")
						.set("enumVal", "This is the value")
						.set("annotationVal", "This is the value")
						.set("stringArr", "This is the value")
						.set("intArr", "This is the value")
						.set("longArr", "This is the value")
						.set("floatArr", "This is the value")
						.set("doubleArr", "This is the value")
						.set("booleanArr", "This is the value")
						.set("charArr", "This is the value")
						.set("shortArr", "This is the value")
						.set("byteArr", "This is the value")
						.set("classArr", "This is the value")
						.set("enumArr", "This is the value")
						.set("annotationArr", "This is the value")
						)
				.visibility(Visibility.PUBLIC)
				.wrap(my)
			;
		
		JavaAssembly<CompilationUnitWiget, ClassImpl> cu = factory.getAssembly(CompilationUnitWiget.class);
		cu.root()
			.add(CompilationUnitWiget.model.body, ClassWiget.class)
				.add(ClassWiget.model.body, MyWiget.class, ClassWiget.model.unwrap);
		
		StringWriter sw = new StringWriter();
		
		cu.output(iClass, new PrintWriter(sw)).flush();
		
		System.out.println(sw.toString());
		
		
	}
}
