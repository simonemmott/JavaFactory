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

import example.model.MyAnnotation;
import example.model.MyClass;
import example.model.MyEnum;
import example.wiget.MyWiget;

import com.k2.JavaFactory.JavaAssembly;
import com.k2.JavaFactory.JavaFactory;
import com.k2.JavaFactory.spec.ClassWiget;
import com.k2.JavaFactory.spec.CompilationUnitWiget;
import com.k2.JavaFactory.spec.EnumWiget;
import com.k2.JavaFactory.spec.InterfaceWiget;
import com.k2.JavaFactory.type.IAnnotation;
import com.k2.JavaFactory.type.IClass;
import com.k2.JavaFactory.type.IEnum;
import com.k2.JavaFactory.type.IInterface;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.Visibility;
import com.k2.JavaFactory.type.impl.AnnotationImpl;
import com.k2.JavaFactory.type.impl.ClassImpl;
import com.k2.JavaFactory.type.impl.EnumImpl;
import com.k2.JavaFactory.type.impl.FieldImpl;
import com.k2.JavaFactory.type.impl.InterfaceImpl;
import com.k2.JavaFactory.type.impl.MethodImpl;
import com.k2.JavaFactory.type.impl.MethodSignatureImpl;
import com.k2.JavaFactory.type.impl.TypeImpl;

public class JavaFactoryTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
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

	@Test
	public void classWigetTest() 
    {
		JavaFactory factory = new JavaFactory("com.k2.JavaFactory.impl", "example.wiget");
				
		MyClass my = new MyClass();
		my.setAlias("myAlias");
		my.setTitle("myTitle");
		my.setDescription("myDescription");
				
		IClass iClass = new ClassImpl("my.test.MyClass")
				.setTitle("My Class")
				.setDescription("This is my class")
				.add(Dependency.forClass(Set.class))
				.add(Dependency.forClass(HashSet.class))
				.extendsClass(new ClassImpl("my.test.myType"))
				.implementsInterface("my.test.interfaces.IMyClass")
				.implementsInterface("my.test.interfaces.AnotherInterface")
				.annotate(new AnnotationImpl("my.test.annotations.OtherAnnotation")
						.define(tString, "name", false).up(AnnotationImpl.class)
						.set("name", "iAnnotation")
					)
				.visibility(Visibility.PUBLIC)
				.defineField(Visibility.PUBLIC, tFloat, "myFloat")
						.setTitle("My Float")
						.setDescription("This field is my float")
						.annotate(new AnnotationImpl("javax.persistence.Column")
								.define(tString, "name", false).up(AnnotationImpl.class)
								.set("name", "MYFLOAT")
							)
						.up(ClassImpl.class)
				.defineField(tLong, "myLong")
						.setTitle("My Long")
						.setDescription("This field is the ID field\nAnd my long field")
						.annotate(new AnnotationImpl("javax.persistence.Id"))
						.annotate(new AnnotationImpl("javax.persistence.Column")
								.define(tString, "name", false).up(AnnotationImpl.class)
								.set("name", "MYLONG")
							)
						.up(ClassImpl.class)
				.defineMethod(Visibility.PRIVATE, "doIt")
						.setTitle("Do It")
						.setDescription("Output the Do count")
						.define(tInt, "count")
								.setTitle("Do Count")
								.up(MethodImpl.class)
						.setMethodBody(
								"for (int do = 0; do<count: do++) {\n"+
								"	System.out.println(\"Do: \"+do);\n"+
								"}"
						)
						.up(ClassImpl.class)
				.wrap(my)
			;
		
		JavaAssembly<CompilationUnitWiget, ClassImpl> cu = factory.getAssembly(CompilationUnitWiget.class);
		cu.root()
			.add(CompilationUnitWiget.model.body, ClassWiget.class)
				.add(ClassWiget.model.body, MyWiget.class, ClassWiget.model.unwrap);
		
		StringWriter sw = new StringWriter();
		
		cu.output(iClass, new PrintWriter(sw)).flush();
		
//		System.out.println(sw.toString());
		
		String expected = "package my.test;\n" + 
				"\n" + 
				"import java.util.HashSet;\n" + 
				"import java.util.Set;\n" + 
				"\n" + 
				"import javax.persistence.Column;\n" + 
				"import javax.persistence.Id;\n" + 
				"import my.test.annotations.OtherAnnotation;\n" + 
				"import my.test.interfaces.AnotherInterface;\n" + 
				"import my.test.interfaces.IMyClass;\n" + 
				"\n" + 
				"/*************************************************************************\n" + 
				" * <strong>My Class</stong>\n" + 
				" * This is my class\n" + 
				" */\n" + 
				"@OtherAnnotation(name = \"iAnnotation\")\n" + 
				"public class MyClass extends myType implements AnotherInterface, IMyClass {\n" + 
				"\n" + 
				"	myAlias: myAlias\n" + 
				"	myTitle: myTitle\n" + 
				"	myDescription: myDescription\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>My Float</stong>\n" + 
				"	 * This field is my float\n" + 
				"	 */\n" + 
				"	@Column(name = \"MYFLOAT\")\n" + 
				"	public float myFloat;\n" + 
				"	public float getMyFloat() { return myFloat: }\n" + 
				"	public MyClass setMyFloat( float myFloat ) { this.myFloat = myFloat: return this; }\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>My Long</stong>\n" + 
				"	 * This field is the ID field\n" + 
				"	 * And my long field\n" + 
				"	 */\n" + 
				"	@Column(name = \"MYLONG\")\n" + 
				"	@Id\n" + 
				"	private long myLong;\n" + 
				"	public long getMyLong() { return myLong: }\n" + 
				"	public MyClass setMyLong( long myLong ) { this.myLong = myLong: return this; }\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>Do It</strong>\n" + 
				"	 * Output the Do count\n" + 
				"	 * @param count Do Count\n" + 
				"	 */\n" + 
				"	private void doIt(int count) {\n" + 
				"		for (int do = 0; do<count: do++) {\n" + 
				"			System.out.println(\"Do: \"+do);\n" + 
				"		}\n" + 
				"	}\n" + 
				"}\n";
		
		assertEquals(expected, sw.toString());
		
	}

	@Test
	public void interfaceWigetTest() 
    {
		JavaFactory factory = new JavaFactory("com.k2.JavaFactory.impl", "example.wiget");
											
		MyClass my = new MyClass();
		my.setAlias("myAlias");
		my.setTitle("myTitle");
		my.setDescription("myDescription");

		IInterface iInterface = new InterfaceImpl("my.test.myClass")
				.setTitle("My Class Interface")
				.setDescription("This is the source for\ninterface IMyClass.class")
				.setAuthor("Simon")
				.add(Dependency.forClass(Set.class))
				.add(Dependency.forClass(HashSet.class))
				.extendsInterface(new InterfaceImpl("my.test.interfaces.IMyClass"))
				.extendsInterface(new InterfaceImpl("my.test.interfaces.AnotherInterface"))
				.annotate(new AnnotationImpl("my.test.annotations.OtherAnnotation")
						.define(tString, "name", false).up(AnnotationImpl.class)
						.set("name", "iAnnotation"))
				.add(new MethodSignatureImpl(Visibility.PUBLIC, tString, "stringMethod")
						.setTitle("A String Method")
						.setDescription("The description of \na string method")
						.setReturnTypeDescription("This method returns a String")
						.define(tBoolean, "boolValue", false)
							.setTitle("A Boolean Value")
							.setDescription("The description of\na boolean value")
							.up(MethodSignatureImpl.class)
						.define(tDouble, "doubleValue", false)
							.setTitle("A Double Value")
							.setDescription("The description of\na double value")
							.up(MethodSignatureImpl.class)
						.define(tString, "strings", true)
							.setTitle("An Array Of Strings")
							.setDescription("The description of\nan array of strings")
							.up(MethodSignatureImpl.class)
						.annotate(new AnnotationImpl("my.test.annotations.MethodAnnotation1")
								.define(tString, "name", false).up(AnnotationImpl.class)
								.set("name", "method1")
							)
						.annotate(new AnnotationImpl("my.test.annotations.MethodAnnotation2")
								.define(tString, "name", false).up(AnnotationImpl.class)
								.set("name", "method2")
							)
					)
				.add(new MethodSignatureImpl(tBoolean, "booleanMethod")
						.setTitle("A Boolean Method")
						.setDescription("This is a boolean method")
						.setReturnTypeDescription("This method returns a boolean value")
						.define(tInt, "num1", false)
							.annotate(new AnnotationImpl("my.test.annotations.MetaParm")
									.define(tString, "name", false).up(AnnotationImpl.class)
									.set("name", "num1"))
							.annotate(new AnnotationImpl("my.test.annotations.MetaParm2")
									.define(tString, "name", false).up(AnnotationImpl.class)
									.set("name", "num1"))
							.up(MethodSignatureImpl.class)
  					)
				;
		
		JavaAssembly<CompilationUnitWiget, ClassImpl> cu = factory.getAssembly(CompilationUnitWiget.class);
		cu.root()
			.add(CompilationUnitWiget.model.body, InterfaceWiget.class);
		
		StringWriter sw = new StringWriter();
		
		cu.output(iInterface, new PrintWriter(sw)).flush();
		
//		System.out.println(sw.toString());
		
		String expected = "package my.test;\n" + 
				"\n" + 
				"import java.util.HashSet;\n" + 
				"import java.util.Set;\n" + 
				"\n" + 
				"import my.test.annotations.MetaParm2;\n" + 
				"import my.test.annotations.MetaParm;\n" + 
				"import my.test.annotations.MethodAnnotation1;\n" + 
				"import my.test.annotations.MethodAnnotation2;\n" + 
				"import my.test.annotations.OtherAnnotation;\n" + 
				"import my.test.interfaces.AnotherInterface;\n" + 
				"import my.test.interfaces.IMyClass;\n" + 
				"\n" + 
				"/*************************************************************************\n" + 
				" * <strong>My Class Interface</stong>\n" + 
				" * This is the source for\n" + 
				" * interface IMyClass.class\n" + 
				" * @author Simon\n" + 
				" */\n" + 
				"@OtherAnnotation(name = \"iAnnotation\")\n" + 
				"public interface myClass extends AnotherInterface, IMyClass {\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>A Boolean Method</strong>\n" + 
				"	 * This is a boolean method\n" + 
				"	 * @param num1 null\n" + 
				"	 * @return This method returns a boolean value\n" + 
				"	 */\n" + 
				"	boolean booleanMethod(@MetaParm(name = \"num1\")@MetaParm2(name = \"num1\")int num1);\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>A String Method</strong>\n" + 
				"	 * The description of \n" + 
				"	 * a string method\n" + 
				"	 * @param boolValue A Boolean Value\n" + 
				"	 * 	The description of\n" + 
				"	 * 	a boolean value\n" + 
				"	 * @param doubleValue A Double Value\n" + 
				"	 * 	The description of\n" + 
				"	 * 	a double value\n" + 
				"	 * @param strings An Array Of Strings\n" + 
				"	 * 	The description of\n" + 
				"	 * 	an array of strings\n" + 
				"	 * @return This method returns a String\n" + 
				"	 */\n" + 
				"	@MethodAnnotation1(name = \"method1\")\n" + 
				"	@MethodAnnotation2(name = \"method2\")\n" + 
				"	public String stringMethod(boolean boolValue, double doubleValue, String ... strings);\n" + 
				"}\n";
		
		assertEquals(expected, sw.toString());

	}


	@Test
	public void annotationWigetTest() 
    {
		JavaFactory factory = new JavaFactory("com.k2.JavaFactory.impl", "example.wiget");
				
		MyClass my = new MyClass();
		my.setAlias("myAlias");
		my.setTitle("myTitle");
		my.setDescription("myDescription");
		
				
		String[] aString = {"aaa", "bbb"};
		int[] aInt = {1, 2, 3, 4};
		long[] aLong = {10L, 20L, 30L};
		float[] aFloat = {123.456f, 456.789f};
		double[] aDouble = {1.23456, 2.345678};
		char[] aChar = {'a', 'b', 'c'};
		short[] aShort = {(short)1, (short)2, (short)3, (short)4};
		byte[] aByte = {(byte)1, (byte)2, (byte)3, (byte)4};
		boolean[] aBoolean = {true, false, true};
		Class<?>[] aClass = {Long.class, Boolean.class, Double.class};
		Enum<?>[] aEnum = {MyEnum.ONE, MyEnum.TWO};

		IAnnotation[] aAnnotation = {
				new AnnotationImpl("my.test.annotations.OtherAnnotation")
						.define(tString, "name", false).up(AnnotationImpl.class)
						.set("name", "Name1"),
				new AnnotationImpl("my.test.annotations.OtherAnnotation")
						.define(tString, "name", false).up(AnnotationImpl.class)
						.set("name", "Name2"),
				};
		
		IClass iClass = new ClassImpl("my.test.myClass")
				.add(Dependency.forClass(Set.class))
				.add(Dependency.forClass(HashSet.class))
				.extendsClass(new ClassImpl("my.test.myType"))
				.implementsInterface(new InterfaceImpl("my.test.interfaces.IMyClass"))
				.implementsInterface(new InterfaceImpl("my.test.interfaces.AnotherInterface"))
				.annotate(new AnnotationImpl("my.test.annotations.MyAnnotation")
						.define(tString, "value", false).up(AnnotationImpl.class)
						.define(tString, "stringVal", false).up(AnnotationImpl.class)
						.define(tInt, "intVal", false).up(AnnotationImpl.class)
						.define(tLong, "longVal", false).up(AnnotationImpl.class)
						.define(tFloat, "floatVal", false).up(AnnotationImpl.class)
						.define(tDouble, "doubleVal", false).up(AnnotationImpl.class)
						.define(tBoolean, "booleanVal", false).up(AnnotationImpl.class)
						.define(tChar, "charVal", false).up(AnnotationImpl.class)
						.define(tShort, "shortVal", false).up(AnnotationImpl.class)
						.define(tByte, "byteVal", false).up(AnnotationImpl.class)
						.define(tClass, "classVal", false).up(AnnotationImpl.class)
						.define(tEnum, "enumVal", false).up(AnnotationImpl.class)
						.define(tAnnotation, "annotationVal", false).up(AnnotationImpl.class)
						.define(tString, "stringArr", true).up(AnnotationImpl.class)
						.define(tInt, "intArr", true).up(AnnotationImpl.class)
						.define(tLong, "longArr", true).up(AnnotationImpl.class)
						.define(tFloat, "floatArr", true).up(AnnotationImpl.class)
						.define(tDouble, "doubleArr", true).up(AnnotationImpl.class)
						.define(tBoolean, "booleanArr", true).up(AnnotationImpl.class)
						.define(tChar, "charArr", true).up(AnnotationImpl.class)
						.define(tShort, "shortArr", true).up(AnnotationImpl.class)
						.define(tByte, "byteArr", true).up(AnnotationImpl.class)
						.define(tClass, "classArr", true).up(AnnotationImpl.class)
						.define(tEnum, "enumArr", true).up(AnnotationImpl.class)
						.define(tAnnotation, "annotationArr", true).up(AnnotationImpl.class)
						.set("value", "This is the value")
						.set("stringVal", "This is the string value")
						.set("intVal", 123)
						.set("longVal", 999L)
						.set("floatVal", 123.456f)
						.set("doubleVal", 123.456789)
						.set("charVal", 'c')
						.set("shortVal", (short)123)
						.set("byteVal", (byte)1)
						.set("classVal", MyClass.class)
						.set("enumVal", MyEnum.ONE)
						.set("annotationVal", new AnnotationImpl("my.test.annotations.OtherAnnotation")
								.define(tString, "name", false).up(AnnotationImpl.class)
								.set("name", "iAnnotation"))
						.set("stringArr", aString)
						.set("intArr", aInt)
						.set("longArr",aLong)
						.set("floatArr", aFloat)
						.set("doubleArr", aDouble)
						.set("booleanArr", aBoolean)
						.set("charArr", aChar)
						.set("shortArr", aShort)
						.set("byteArr", aByte)
						.set("classArr", aClass)
						.set("enumArr", aEnum)
						.set("annotationArr", aAnnotation)
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
		
		String expected = "package my.test;\n" + 
				"\n" + 
				"import java.util.HashSet;\n" + 
				"import java.util.Set;\n" + 
				"\n" + 
				"import example.model.MyClass;\n" + 
				"import example.model.MyEnum;\n" + 
				"import my.test.annotations.MyAnnotation;\n" + 
				"import my.test.annotations.OtherAnnotation;\n" + 
				"import my.test.interfaces.AnotherInterface;\n" + 
				"import my.test.interfaces.IMyClass;\n" + 
				"\n" + 
				"@MyAnnotation(\"This is the value\", \n" + 
				"	stringVal = \"This is the string value\", \n" + 
				"	intVal = 123, \n" + 
				"	longVal = 999L, \n" + 
				"	floatVal = 123.456f, \n" + 
				"	doubleVal = 123.456789, \n" + 
				"	charVal = 'c', \n" + 
				"	shortVal = (short)123, \n" + 
				"	byteVal = (byte)1, \n" + 
				"	classVal = MyClass.class, \n" + 
				"	enumVal = MyEnum.ONE, \n" + 
				"	annotationVal = 	@OtherAnnotation(name = \"iAnnotation\"), \n" + 
				"	stringArr = {\n" + 
				"		\"aaa\", \n" + 
				"		\"bbb\"\n" + 
				"	}, \n" + 
				"	intArr = {1, 2, 3, 4}, \n" + 
				"	longArr = {10L, 20L, 30L}, \n" + 
				"	floatArr = {123.456f, 456.789f}, \n" + 
				"	doubleArr = {1.23456, 2.345678}, \n" + 
				"	booleanArr = {true, false, true}, \n" + 
				"	charArr = {'a', 'b', 'c'}, \n" + 
				"	shortArr = {(short)1, (short)2, (short)3, (short)4}, \n" + 
				"	byteArr = {(byte)1, (byte)2, (byte)3, (byte)4}, \n" + 
				"	classArr = {\n" + 
				"		Long.class, \n" + 
				"		Boolean.class, \n" + 
				"		Double.class\n" + 
				"	}, \n" + 
				"	enumArr = {\n" + 
				"		MyEnum.ONE, \n" + 
				"		MyEnum.TWO\n" + 
				"	}, \n" + 
				"	annotationArr = {\n" + 
				"		@OtherAnnotation(name = \"Name1\"), \n" + 
				"		@OtherAnnotation(name = \"Name2\")\n" + 
				"	})\n" + 
				"public class myClass extends myType implements AnotherInterface, IMyClass {\n" + 
				"\n" + 
				"	myAlias: myAlias\n" + 
				"	myTitle: myTitle\n" + 
				"	myDescription: myDescription\n" + 
				"}\n";

//		System.out.println(sw.toString());
		
		assertEquals(expected, sw.toString());

		
	}
	
	@Test
	public void enumWigetTest() 
    {
		JavaFactory factory = new JavaFactory("com.k2.JavaFactory.impl", "example.wiget");
				
		MyClass my = new MyClass();
		my.setAlias("myAlias");
		my.setTitle("myTitle");
		my.setDescription("myDescription");
				
		IEnum iEnum = new EnumImpl("my.test.MyEnum")
				.setTitle("My Enumeration")
				.setDescription("This is my enumaration")
				.add(Dependency.forClass(Set.class))
				.add(Dependency.forClass(HashSet.class))
				.implementsInterface("my.test.interfaces.IMyEnum")
				.implementsInterface("my.test.interfaces.AnotherEnum")
				.annotate(new AnnotationImpl("my.test.annotations.OtherEnumAnnotation")
						.define(tString, "name", false).up(AnnotationImpl.class)
						.set("name", "iAnnotation")
					)
				.visibility(Visibility.PUBLIC)
				.defineValue("ONE")
						.setTitle("Value One")
						.setDescription("This is the first enumeration value")
						.annotate(new AnnotationImpl("com.k2.MetaModel.annotations.MetaSubTypeValue")
								.define(tString, "title", false).up(AnnotationImpl.class)
								.set("title", "Value One - Annotated")
								.define(tString, "description", false).up(AnnotationImpl.class)
								.set("description", "This is the first enumeration value - Annotated")
							)
						.up(EnumImpl.class)
				.defineValue("TWO")
						.annotate(new AnnotationImpl("com.k2.MetaModel.annotations.MetaSubTypeValue")
								.define(tString, "title", false).up(AnnotationImpl.class)
								.set("title", "Value Two - Annotated")
								.define(tString, "description", false).up(AnnotationImpl.class)
								.set("description", "This is the second enumeration value - Annotated")
							)
						.up(EnumImpl.class)
				.wrap(my)
			;
		
		JavaAssembly<CompilationUnitWiget, EnumImpl> cu = factory.getAssembly(CompilationUnitWiget.class);
		cu.root()
			.add(CompilationUnitWiget.model.body, EnumWiget.class)
				.add(EnumWiget.model.body, MyWiget.class, EnumWiget.model.unwrap);
		
		StringWriter sw = new StringWriter();
		
		cu.output(iEnum, new PrintWriter(sw)).flush();
		
//		System.out.println(sw.toString());
		
		String expected = "package my.test;\n" + 
				"\n" + 
				"import java.util.HashSet;\n" + 
				"import java.util.Set;\n" + 
				"\n" + 
				"import com.k2.MetaModel.annotations.MetaSubTypeValue;\n" + 
				"import my.test.annotations.OtherEnumAnnotation;\n" + 
				"import my.test.interfaces.AnotherEnum;\n" + 
				"import my.test.interfaces.IMyEnum;\n" + 
				"\n" + 
				"/*************************************************************************\n" + 
				" * <strong>My Enumeration</stong>\n" + 
				" * This is my enumaration\n" + 
				" */\n" + 
				"@OtherEnumAnnotation(name = \"iAnnotation\")\n" + 
				"public enum MyEnum implements AnotherEnum, IMyEnum {\n" + 
				"\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>Value One</stong>\n" + 
				"	 * This is the first enumeration value\n" + 
				"	 */\n" + 
				"	@MetaSubTypeValue(title = \"Value One - Annotated\", \n" + 
				"		description = \"This is the first enumeration value - Annotated\")\n" + 
				"	ONE;\n" + 
				"\n" + 
				"	@MetaSubTypeValue(title = \"Value Two - Annotated\", \n" + 
				"		description = \"This is the second enumeration value - Annotated\")\n" + 
				"	TWO;\n" + 
				"	myAlias: myAlias\n" + 
				"	myTitle: myTitle\n" + 
				"	myDescription: myDescription\n" + 
				"}\n";
		
		assertEquals(expected, sw.toString());
		
	}

	@Test
	public void embeddedTypesWigetTest() 
    {
		JavaFactory factory = new JavaFactory("com.k2.JavaFactory.impl", "example.wiget");

		
		IClass iClass = new ClassImpl("my.test.MyClass")
				.setTitle("My Class")
				.setDescription("This is my class")
				.extendsClass(new ClassImpl("my.test.myType"))
				.implementsInterface("my.test.interfaces.IMyClass")
				.annotate(new AnnotationImpl("my.test.annotations.OtherAnnotation")
						.define(tString, "name", false).up(AnnotationImpl.class)
						.set("name", "iAnnotation")
					)
				.visibility(Visibility.PUBLIC)
				.declares(
						new EnumImpl("my.test.MyEnum")
								.setTitle("My Enumeration")
								.setDescription("This is my enumaration")
								.implementsInterface("my.test.interfaces.IMyEnum")
								.annotate(new AnnotationImpl("my.test.annotations.OtherEnumAnnotation")
										.define(tString, "name", false).up(AnnotationImpl.class)
										.set("name", "iAnnotation")
									)
								.visibility(Visibility.PUBLIC)
								.defineValue("ONE")
										.setTitle("Value One")
										.setDescription("This is the first enumeration value")
										.annotate(new AnnotationImpl("com.k2.MetaModel.annotations.MetaSubTypeValue")
												.define(tString, "title", false).up(AnnotationImpl.class)
												.set("title", "Value One - Annotated")
												.define(tString, "description", false).up(AnnotationImpl.class)
												.set("description", "This is the first enumeration value - Annotated")
											)
										.up(EnumImpl.class)
								.defineValue("TWO")
										.annotate(new AnnotationImpl("com.k2.MetaModel.annotations.MetaSubTypeValue")
												.define(tString, "title", false).up(AnnotationImpl.class)
												.set("title", "Value Two - Annotated")
												.define(tString, "description", false).up(AnnotationImpl.class)
												.set("description", "This is the second enumeration value - Annotated")
											)
										.up(EnumImpl.class),
						ClassImpl.class)
				.declares(
						new EnumImpl("my.test.MyOtherEnum")
								.setTitle("My Other Enumeration")
								.setDescription("This is my other enumaration")
								.implementsInterface("my.test.interfaces.AnotherEnum")
								.annotate(new AnnotationImpl("my.test.annotations.OtherEnumAnnotation")
										.define(tString, "name", false).up(AnnotationImpl.class)
										.set("name", "iAnnotation")
									)
								.visibility(Visibility.PUBLIC)
								.defineValue("AAA")
										.setTitle("Value One")
										.setDescription("This is the first enumeration value")
										.annotate(new AnnotationImpl("com.k2.MetaModel.annotations.MetaSubTypeValue")
												.define(tString, "title", false).up(AnnotationImpl.class)
												.set("title", "Value One - Annotated")
												.define(tString, "description", false).up(AnnotationImpl.class)
												.set("description", "This is the first enumeration value - Annotated")
											)
										.up(EnumImpl.class)
								.defineValue("BBB")
										.annotate(new AnnotationImpl("com.k2.MetaModel.annotations.MetaSubTypeValue")
												.define(tString, "title", false).up(AnnotationImpl.class)
												.set("title", "Value Two - Annotated")
												.define(tString, "description", false).up(AnnotationImpl.class)
												.set("description", "This is the second enumeration value - Annotated")
											)
										.up(EnumImpl.class),
						ClassImpl.class)
				.defineField(Visibility.PUBLIC, tFloat, "myFloat")
						.setTitle("My Float")
						.setDescription("This field is my float")
						.annotate(new AnnotationImpl("javax.persistence.Column")
								.define(tString, "name", false).up(AnnotationImpl.class)
								.set("name", "MYFLOAT")
							)
						.up(ClassImpl.class)
				.defineField(tLong, "myLong")
						.setTitle("My Long")
						.setDescription("This field is the ID field\nAnd my long field")
						.annotate(new AnnotationImpl("javax.persistence.Id"))
						.annotate(new AnnotationImpl("javax.persistence.Column")
								.define(tString, "name", false).up(AnnotationImpl.class)
								.set("name", "MYLONG")
							)
						.up(ClassImpl.class)
				.defineMethod(Visibility.PRIVATE, "doIt")
						.setTitle("Do It")
						.setDescription("Output the Do count")
						.define(tInt, "count")
								.setTitle("Do Count")
								.up(MethodImpl.class)
						.setMethodBody(
								"for (int do = 0; do<count: do++) {\n"+
								"	System.out.println(\"Do: \"+do);\n"+
								"}"
						)
						.up(ClassImpl.class)
			;
		
		JavaAssembly<CompilationUnitWiget, EnumImpl> cu = factory.getAssembly(CompilationUnitWiget.class);
		cu.root()
			.add(CompilationUnitWiget.model.body, ClassWiget.class);
		
		StringWriter sw = new StringWriter();
		
		cu.output(iClass, new PrintWriter(sw)).flush();
		
//		System.out.println(sw.toString());
		
		String expected = "package my.test;\n" + 
				"\n" + 
				"\n" + 
				"import com.k2.MetaModel.annotations.MetaSubTypeValue;\n" + 
				"import javax.persistence.Column;\n" + 
				"import javax.persistence.Id;\n" + 
				"import my.test.annotations.OtherAnnotation;\n" + 
				"import my.test.annotations.OtherEnumAnnotation;\n" + 
				"import my.test.interfaces.AnotherEnum;\n" + 
				"import my.test.interfaces.IMyClass;\n" + 
				"import my.test.interfaces.IMyEnum;\n" + 
				"\n" + 
				"/*************************************************************************\n" + 
				" * <strong>My Class</stong>\n" + 
				" * This is my class\n" + 
				" */\n" + 
				"@OtherAnnotation(name = \"iAnnotation\")\n" + 
				"public class MyClass extends myType implements IMyClass {\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>My Enumeration</stong>\n" + 
				"	 * This is my enumaration\n" + 
				"	 */\n" + 
				"	@OtherEnumAnnotation(name = \"iAnnotation\")\n" + 
				"	public enum MyEnum implements IMyEnum {\n" + 
				"\n" + 
				"\n" + 
				"		/*************************************************************************\n" + 
				"		 * <strong>Value One</stong>\n" + 
				"		 * This is the first enumeration value\n" + 
				"		 */\n" + 
				"		@MetaSubTypeValue(title = \"Value One - Annotated\", \n" + 
				"			description = \"This is the first enumeration value - Annotated\")\n" + 
				"		ONE;\n" + 
				"\n" + 
				"		@MetaSubTypeValue(title = \"Value Two - Annotated\", \n" + 
				"			description = \"This is the second enumeration value - Annotated\")\n" + 
				"		TWO;\n" + 
				"	}\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>My Other Enumeration</stong>\n" + 
				"	 * This is my other enumaration\n" + 
				"	 */\n" + 
				"	@OtherEnumAnnotation(name = \"iAnnotation\")\n" + 
				"	public enum MyOtherEnum implements AnotherEnum {\n" + 
				"\n" + 
				"\n" + 
				"		/*************************************************************************\n" + 
				"		 * <strong>Value One</stong>\n" + 
				"		 * This is the first enumeration value\n" + 
				"		 */\n" + 
				"		@MetaSubTypeValue(title = \"Value One - Annotated\", \n" + 
				"			description = \"This is the first enumeration value - Annotated\")\n" + 
				"		AAA;\n" + 
				"\n" + 
				"		@MetaSubTypeValue(title = \"Value Two - Annotated\", \n" + 
				"			description = \"This is the second enumeration value - Annotated\")\n" + 
				"		BBB;\n" + 
				"	}\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>My Float</stong>\n" + 
				"	 * This field is my float\n" + 
				"	 */\n" + 
				"	@Column(name = \"MYFLOAT\")\n" + 
				"	public float myFloat;\n" + 
				"	public float getMyFloat() { return myFloat: }\n" + 
				"	public MyClass setMyFloat( float myFloat ) { this.myFloat = myFloat: return this; }\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>My Long</stong>\n" + 
				"	 * This field is the ID field\n" + 
				"	 * And my long field\n" + 
				"	 */\n" + 
				"	@Column(name = \"MYLONG\")\n" + 
				"	@Id\n" + 
				"	private long myLong;\n" + 
				"	public long getMyLong() { return myLong: }\n" + 
				"	public MyClass setMyLong( long myLong ) { this.myLong = myLong: return this; }\n" + 
				"\n" + 
				"	/*************************************************************************\n" + 
				"	 * <strong>Do It</strong>\n" + 
				"	 * Output the Do count\n" + 
				"	 * @param count Do Count\n" + 
				"	 */\n" + 
				"	private void doIt(int count) {\n" + 
				"		for (int do = 0; do<count: do++) {\n" + 
				"			System.out.println(\"Do: \"+do);\n" + 
				"		}\n" + 
				"	}\n" + 
				"}\n";
		
		assertEquals(expected, sw.toString());
		
	}


}
