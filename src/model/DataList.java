package model;

import java.util.ArrayList;

public class DataList implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	private static DataList instance;
	
	private ArrayList<Data> datalist;
	
	private DataList() {
		this.setDatalist(new ArrayList<Data>());
	}
	
	public static synchronized DataList getInstance() {
		if(instance == null) {
			instance = new DataList();
		}
		return instance;
	}
	
	public void add(Data data) {
		this.datalist.add(data);
	}
	
	public void delete(int index) {
		this.datalist.remove(index);
	}
	
	public ArrayList<Data> getDatalist() {
		return this.datalist;
	}

	public void setDatalist(ArrayList<Data> datalist) {
		this.datalist = datalist;
	}

}
