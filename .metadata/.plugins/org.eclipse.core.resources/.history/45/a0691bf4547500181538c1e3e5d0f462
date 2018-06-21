package model;

import java.util.ArrayList;
import java.util.HashMap;

public class DataList {
	
	private Object[][] list;
	private String[] title;
	
	public DataList(int taille_list, int taille_title) {
		this.list = new Object[taille_list][taille_title];
		this.title = new String[taille_title];
	}
	
	public DataList(Object[][] _list, String[] _title) {
		this.list = _list;
		this.title = _title;
	}
	
	public void addTitle(String value) {
		String[] temp = new String[this.title.length+1];
		for(int i = 0; i < this.title.length; i++) {
			temp[i] = this.title[i];
		}
		temp[this.title.length] = value;
		this.setTitle(temp);
	}
	
	public void addData(Object[] data) {
		Object[][] temp = new Object[this.list.length+1][this.title.length];
		for(int i = 0; i < this.list.length; i++) {
			temp[i] = this.list[i];
		}
		temp[this.list.length] = data;
		this.setList(temp);
	}
	
	public String getListTitle() {
		String result = "";
		for(int i = 0; i < this.title.length; i++) {
			result = result +" "+ this.title[i];
		}
		return result;
	}
	
	public String getDataList() {
		String result = "";
		for(int i = 0; i < this.list.length; i++) {
			result = result + this.getInfoData(this.list[i]);
		}
		return result;
	}
	
	public String getInfoData(Object[] value) {
		String result = "";
		for(int i = 0; i < value.length; i++) {
			result = result + " " + value[i];
		}
		return result;
	}

	public Object[][] getList() {
		return list;
	}

	public void setList(Object[][] list) {
		this.list = list;
	}

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}
	
	
}
