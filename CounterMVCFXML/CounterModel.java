package CounterMVCFXML;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CounterModel {
	private final IntegerProperty count = new SimpleIntegerProperty(0);
	
	public IntegerProperty countProperty() {return count;}
	public int getCount() {return count.get();}
	public void setCount(int v) {count.set(v);}
	
	public void increment() {setCount(getCount()+1);}
	public void reset() {setCount(0);}
}
