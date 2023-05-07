package app.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import app.menu.MenuBar;

/**
 * Esta clase se encarga de actualizar los JMenuItem cuando el area de texto gane o pierda el foco.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class Focus implements FocusListener {
	private MenuBar menuBar;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param menuBar JMenuBar con los JMenu y JMenuItems que se desea actualizar
	 */
	public Focus(MenuBar menuBar) {
		this.menuBar =  menuBar;
	}

	@Override
	public void focusGained(FocusEvent e) {
		menuBar.getEditMenu().updateMenuItem();
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		menuBar.getEditMenu().updateMenuItem();
	}
}