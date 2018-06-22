package model;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DataListTableModel implements TableModel, java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private ArrayList<String> title;
	
	private DataList datalist;
	
	
	public DataListTableModel() {
		this.datalist = DataList.getInstance();
		this.title = new ArrayList<String>();
	}
	
	public ArrayList<Data> getAllData(){
		return this.datalist.getDatalist();
	}
	
	public void addTitle(String value) {
		this.title.add(value);
	}
	
	public void addDataList(Data data) {
		this.datalist.add(data);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.title.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return this.title.get(columnIndex);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.datalist.getDatalist().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return this.datalist.getDatalist().get(rowIndex).getValue(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

}
