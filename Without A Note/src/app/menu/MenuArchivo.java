package app.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import app.without.WithoutANote;

/**
 * Esta clase representa un Menu y crea todas sus opciones.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class MenuArchivo extends JMenu implements ActionListener {
	private JMenuItem[] menuItemArchivo;
	
	private JFrame ventana;
	
	private int OPC;
	
	private PrinterJob printerJob;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param ventana
	 */
	public MenuArchivo(JFrame ventana) {
		this.ventana = ventana;
		
		printerJob = PrinterJob.getPrinterJob();
		
		this.setText("Archivo");
		crearMenu();
	}
	
	/**
	 * Este metodo crea toda las opciones que tendra el Menu
	 */
	private void crearMenu() {
		String[] nombreMenuArchivo = {"Nuevo", "Nueva ventana", "Abrir...", "Guardar",
				"Guardar Como...", "Configurar pagina...", "Imprimir...", "Salir"};
		menuItemArchivo = new JMenuItem[8];
		for(int i=0; i<menuItemArchivo.length; i++) {
			menuItemArchivo[i] = new JMenuItem(nombreMenuArchivo[i]);
			menuItemArchivo[i].addActionListener(this);
		}
		menuItemArchivo[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItemArchivo[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		menuItemArchivo[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItemArchivo[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		menuItemArchivo[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItemArchivo[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		
		JSeparator separadorInicio = new JSeparator(JSeparator.HORIZONTAL);
		JSeparator separadorFin = new JSeparator(JSeparator.HORIZONTAL);
		
		this.add(menuItemArchivo[0]);
		this.add(menuItemArchivo[1]);
		this.add(menuItemArchivo[2]);
		this.add(menuItemArchivo[3]);
		this.add(menuItemArchivo[4]);
		this.add(separadorInicio);
		this.add(menuItemArchivo[5]);
		this.add(menuItemArchivo[6]);
		this.add(separadorFin);
		this.add(menuItemArchivo[7]);
		
		menuItemArchivo[0].setActionCommand("Nuevo");
		menuItemArchivo[1].setActionCommand("ventana");
		menuItemArchivo[2].setActionCommand("Abrir...");
		menuItemArchivo[3].setActionCommand("guardar");
		menuItemArchivo[4].setActionCommand("saveAs");
		menuItemArchivo[5].setActionCommand("conigurarPagina");
		menuItemArchivo[6].setActionCommand("impresora");
		menuItemArchivo[7].setActionCommand("Salir");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Nuevo":
			if(WithoutANote.WITHOUTMANAGER.isModifiedFile()) {
				OPC = WithoutANote.VERIFY_SAVED.verificar();
				if(OPC != 2) {
					WithoutANote.WITHOUTMANAGER.setNewFile(true);
				}
			}
			else {
				WithoutANote.WITHOUTMANAGER.setNewFile(true);
			}
			break;
		case "ventana":
			ProcessBuilder pb = new ProcessBuilder("java", "-jar", new File("").getAbsolutePath()+"\\Without A Note.jar");
			try {
				pb.start();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(ventana, e1.toString(), "Without A Note", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "Salir":
			OPC = 1;
			if(WithoutANote.WITHOUTMANAGER.isModifiedFile()) {
				OPC = WithoutANote.VERIFY_SAVED.verificar();
			}
			if(OPC != 2) {
				WithoutANote.saveProfile();
				System.exit(0);
			}
			break;
		case "Abrir...":
			WithoutANote.OPEN_FILE.abrir();
			break;
		case "guardar":
			WithoutANote.SAVE_FILE.save();
			break;
		case "saveAs":
			WithoutANote.SAVE_FILE.saveAs();
			break;
		case "impresora":
			printerJob.setPrintable(new Printable() {

				@Override
				public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
					if(pageIndex > 0) {
						return Printable.NO_SUCH_PAGE;
					}
					
					Graphics2D g2d = (Graphics2D) graphics;
					g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
					
					WithoutANote.TXTPANTALLA.printAll(g2d);
					
					return Printable.PAGE_EXISTS;
				}
				
			});
			
			if(printerJob.printDialog()) {
				try {
					printerJob.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(ventana, "Impresion Cancelada", "Impresora", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		case "conigurarPagina":
			printerJob.pageDialog(printerJob.defaultPage());
			break;
		}
	}
}