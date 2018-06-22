package controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;

import model.Data;
import model.DataListTableModel;
import view.Window;

public class Controller {
	
	private DataListTableModel tableModel;
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
		
		this.window.window.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Adieu");
				//window.setWindowClose();
				try ( ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("DataListTableModel.dat"))){
					os.writeObject(tableModel);
				} catch (IOException e) {
					System.err.println("Erreur pendant la sérialization : " + e);
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("bonjour");
				try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("DataListTableModel.dat"))){
					try {
						tableModel = (DataListTableModel)is.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch(IOException e) {
					System.err.println("Erreur pendant la désérialisation : "+e);
				}
				
				window.table = new JTable(tableModel);
				window.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				window.table.setRowHeight(50);
				JScrollPane jsp = new JScrollPane(window.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				jsp.setPreferredSize(window.panel.getSize());
				window.panel.add(jsp);
				window.window.repaint();
				window.window.revalidate();
			}
			
		});
	}

	public void getExcelData(String filename) {
		this.tableModel = new DataListTableModel();
		try {
			FileInputStream excelFile = new FileInputStream(new File(filename));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheet("Echeancier");
			Iterator<Row> iterator = datatypeSheet.iterator();
			while(iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				Data data = new Data();
				while(cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();
					if (currentRow.getRowNum() == 1) {
						if(currentCell.getCellTypeEnum() == CellType.STRING) {
							//System.out.println(currentCell.getStringCellValue());
							this.tableModel.addTitle(currentCell.getStringCellValue());
						} else if(currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							//System.out.println(currentCell.getNumericCellValue());
							this.tableModel.addTitle(currentCell.getNumericCellValue()+"");
						}
					} else {
						if(currentCell.getCellTypeEnum() == CellType.STRING) {
							//System.out.println(currentCell.getStringCellValue());
							data.add(currentCell.getStringCellValue());
						} else if(currentCell.getCellTypeEnum() == CellType.NUMERIC) {
							//System.out.println(currentCell.getNumericCellValue());
							data.add(currentCell.getNumericCellValue()+"");
						}
					}
				}
				this.tableModel.addDataList(data);
			}
			excelFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.window.table = new JTable(this.tableModel);
		this.window.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.window.table.setRowHeight(50);
		JScrollPane jsp = new JScrollPane(this.window.table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setPreferredSize(this.window.panel.getSize());
		this.window.panel.add(jsp);
		this.window.window.repaint();
		this.window.window.revalidate();
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Controller controller = new Controller();
	}

}
