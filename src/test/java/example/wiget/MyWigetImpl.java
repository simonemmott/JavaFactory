package example.wiget;

import java.io.PrintWriter;

import com.k2.Wiget.annotation.WigetImplementation;

import example.model.MyClass;

import com.k2.JavaFactory.JavaFamily;
import com.k2.JavaFactory.AJavaWiget;
import com.k2.JavaFactory.JavaAssembly;

import com.k2.Wiget.AssembledWiget;
import com.k2.Wiget.Wiget;

@WigetImplementation
public class MyWigetImpl extends AJavaWiget<MyClass> implements MyWiget{
	
	@SuppressWarnings("rawtypes")
	@Override
	public PrintWriter output(
			AssembledWiget<JavaFamily, PrintWriter, ? extends Wiget, MyClass> a,
			PrintWriter out) {
		
		JavaAssembly ja = (JavaAssembly)a.assembly();
		
		out.println(ja.getIndent()+"myAlias: "+a.get(MyWiget.model.alias));
		out.println(ja.getIndent()+"myTitle: "+a.get(MyWiget.model.title));
		out.println(ja.getIndent()+"myDescription: "+a.get(MyWiget.model.description));
		
		return out;
	}





	
	

}
