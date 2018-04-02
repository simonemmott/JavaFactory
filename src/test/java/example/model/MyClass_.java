package example.model;

import java.util.Set;

import com.k2.Wiget.Wiget;
import com.k2.Wiget.WigetParameter;

@SuppressWarnings("rawtypes")
public class MyClass_<W extends Wiget> {
	public WigetParameter<W, String> alias;
	public WigetParameter<W, String> title;
	public WigetParameter<W, String> description;

}
