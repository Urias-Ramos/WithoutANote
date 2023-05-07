package app.menu;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * Esta clase representa el MenuBar donde estarn todos los menu de opciones disponibles.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class MenuBar extends JMenuBar {
	private MenuEdicion editMenu;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param ventana ventana principal de la aplicacion.
	 */
	public MenuBar(JFrame ventana) {
		editMenu = new MenuEdicion(ventana);
		
		this.add(new MenuArchivo(ventana));
		this.add(editMenu);
		this.add(new MenuFormato(ventana));
		this.add(new MenuVer());
		this.add(new MenuAyuda(ventana));
	}

	public MenuEdicion getEditMenu() {
		return editMenu;
	}
}