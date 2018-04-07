package com.k2.JavaFactory.spec;

import com.k2.Wiget.annotation.WigetSpecification;
import com.k2.JavaFactory.JavaWiget;
import com.k2.JavaFactory.type.IType;
import com.k2.JavaFactory.type.wigetModel.IType_;
import com.k2.Wiget.WigetContainer;

/**
 * The compilation unit wiget writes the package and imports clauses of a compilation unit before writing the wigets added to 
 * the compilation unit body.
 * 
 * Only one wiget should be added to the compilation unit body
 * 
 * The output is initially generated on a PrintWriter generated from a StringWriter. This allows the wigets to be output and their 
 * dependencies recorded on the wiget assembly. The dependnecies are then output on the output print writer before the generated output
 * on the String print writer is appended to the output print writer.
 * 
 * @author simon
 *
 */
@WigetSpecification
public interface CompilationUnitWiget extends JavaWiget<IType> {
	
	public static class Model extends IType_<CompilationUnitWiget> {
		public WigetContainer<CompilationUnitWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
