package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window {
	
	public JFrame window;
	public JFrame window_close;
	
	//Barre de menu
	public JMenuBar menuBar;
	public JMenu fichier;
	public JMenuItem menuItem_import;
	public JFileChooser filechooser_pathname;
	
	
	//Contenu
	public JPanel panel;
	public JTable table;
	public JPanel panel_close;
	public JLabel label_close;
	public JButton yes_button_close;
	public JButton no_button_close;
	
	public Window() {
		this.window = new JFrame();
		this.window.setLayout(new BorderLayout());
		this.window.setSize(1500, 900);
		this.window.setLocationRelativeTo(null);
		this.window.setTitle("Suivi Global CMI");
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
	
	public void setWindowClose() {
		this.window_close = new JFrame();
		this.window_close.setTitle("Fermeture de l'application");
		this.window_close.setLayout(new BorderLayout());
		this.window_close.setSize(300, 200);
		this.window_close.setLocationRelativeTo(null);
		this.panel_close = new JPanel();
		this.yes_button_close = new JButton("Oui");
		this.no_button_close = new JButton("Non");
		this.label_close = new JLabel("Voulez-vous sauvegarder les donn�es?");
		this.window_close.add(this.panel_close);
		this.panel_close.add(this.label_close, BorderLayout.CENTER);
		this.panel_close.add(this.yes_button_close, BorderLayout.SOUTH);
		this.panel_close.add(this.no_button_close, BorderLayout.SOUTH);
		this.window_close.setVisible(true);
	}
}
