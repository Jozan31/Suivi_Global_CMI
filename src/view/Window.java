package view;

import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window {
	
	public JFrame window;
	
	//Barre de menu
	public JMenuBar menuBar;
	public JMenu fichier;
	public JMenuItem menuItem_import;
	public JFileChooser filechooser_pathname;
	
	//Contenu
	public JPanel panel;
	public JPanel panel_datalist;
	public JTable table;
	
	public Window() {
		this.window = new JFrame();
		this.window.setSize(1500, 900);
		this.window.setLocationRelativeTo(null);
		this.window.setTitle("Suivi Global CMI");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setmenuBar();
		this.setContent();
		this.window.setVisible(true);
	}
	
	public void setmenuBar() {
		this.menuBar = new JMenuBar();
		this.fichier = new JMenu("Fichier");
		this.menuItem_import = new JMenuItem("Importer");
		
		this.fichier.add(this.menuItem_import);
		this.menuBar.add(this.fichier);
		
		this.window.setJMenuBar(this.menuBar);
	}
	
	public void setContent() {
		this.panel = new JPanel();
		this.panel.setBackground(Color.ORANGE);
		this.window.setContentPane(this.panel);
	}
	
	public void setFileChooser() {
		this.filechooser_pathname = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx");
		this.filechooser_pathname.setFileFilter(filter);
		int returnValue = this.filechooser_pathname.showOpenDialog(null);
	}
	
	public void setDataList() {
		this.panel_datalist = new JPanel();
		this.panel_datalist.setBackground(Color.BLUE);
		this.panel.add(this.panel_datalist);
	}
}
