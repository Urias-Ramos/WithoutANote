package app.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import app.dialog.DialogAcercaDe;
import app.dialog.DialogAyuda;

/**
 * Esta clase representa un menu desplegable, con varias opciones.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class MenuAyuda extends JMenu implements ActionListener {
	public JMenuItem[] menuItemAyuda;
	
	private DialogAyuda dialogAyuda;
	private DialogAcercaDe dialogAcerca;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param ventana ventana principal del proyecto.
	 */
	public MenuAyuda(JFrame ventana) {
		this.setText("Ayuda");
		
		dialogAyuda = new DialogAyuda(ventana);
		dialogAcerca = new DialogAcercaDe(ventana);
		
		crearMenu();
	}
	
	/**
	 * Este metodo crea los Item menu y los agrega.
	 */
	private void crearMenu() {
		String[] ID = {"Ver la ayuda", "Acerca de Without a note"};
		menuItemAyuda = new JMenuItem[2];
		for(int i=0; i<menuItemAyuda.length; i++) {
			menuItemAyuda[i] = new JMenuItem(ID[i]);
			menuItemAyuda[i].addActionListener(this);
		}
		menuItemAyuda[0].setActionCommand("user-manual");
		JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
		menuItemAyuda[1].setActionCommand("about");
		
		this.add(menuItemAyuda[0]);
		this.add(separador);
		this.add(menuItemAyuda[1]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "user-manual":
			dialogAyuda.setVisible(true);
			break;
		case "about":
			dialogAcerca.setVisible(true);
			break;
		}
	}
}
