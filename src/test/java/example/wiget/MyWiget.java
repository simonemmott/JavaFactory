package example.wiget;

import com.k2.Wiget.WigetContainer;
import com.k2.Wiget.annotation.WigetSpecification;

import example.model.MyClass;
import example.model.MyClass_;

import com.k2.JavaFactory.JavaWiget;

@WigetSpecification
public interface MyWiget extends JavaWiget<MyClass> {
	
	public static class Model extends MyClass_<MyWiget> {
		public WigetContainer<MyWiget> body;
	}
	
	public static final Model model = new Model();
	@Override public default Object staticModel() { return model; }
	@Override public default Class<?> modelType() { return Model.class; }
	
}
