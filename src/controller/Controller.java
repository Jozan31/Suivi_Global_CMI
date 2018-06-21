package controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DataList;
import view.Window;

public class Controller {
	
	private DataList datalist;
	private Window window;
	
	public Controller() {
		this.window = new Window();
		
		this.window.menuItem_import.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					getExcelData(selectedFile.getAbsolutePath());
				}
			}
			
		});
	}

	public void getExcelData(String filename) {
		this.datalist = new DataList(0,0);
		
		try {
			FileInputStream excelFile = new FileInputStream(new File(filename));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheet("Echeancier");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while(iterator.hasNext()) {
				Row currentRow = iterator.next();
				Object[] data = new Object[0];
				Iterator<Cell> cellIterator = currentRow.iterator();
				while(cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentRow.getRowNum() == 1) {
						if(currentCell.getCellTypeEnum() == CellType.STRING) {
							this.datalist.addTitle(currentCell.getStringCellValue());
						} else if(currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							this.datalist.addTitle(currentCell.getDateCellValue().getDay()+"/"+currentCell.getDateCellValue().getMonth()+"/"+currentCell.getDateCellValue().getYear());
						}
					} else {
						String cell = "";
						if(currentCell.getCellTypeEnum() == CellType.STRING) {
							cell = currentCell.getStringCellValue();
						} else if(currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							cell = currentCell.getNumericCellValue()+"";
						}
						Object[] temp = new Object[data.length+1];
						for(int i = 0; i < data.length; i++) {
							temp[i] = data[i];
						}
						temp[data.length] = cell;
						data = temp;
					}
				}
				this.datalist.addData(data);
			}
			excelFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.window.table = new JTable(this.datalist.getList(), this.datalist.getTitle());
		this.window.setDataList();
		this.window.panel_datalist.add(new JScrollPane(this.window.table));
		this.window.window.repaint();
		this.window.window.revalidate();
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Controller controller = new Controller();
		
	}

}
