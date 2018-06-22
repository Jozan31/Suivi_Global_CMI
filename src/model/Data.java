package model;

import java.util.ArrayList;

public class Data implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> values;
	
	public Data() {
		this.values = new ArrayList<String>();
	}
	
	public void add(String value) {
		this.values.add(value);
	}
	
	public void modify(int index, String value) {
		this.values.set(index, value);
	}
	
	public String getValue(int index) {
		if (index < this.values.size()) {
			return this.values.get(index);
		}
		return "";
	}

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	
	

}
