package app.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import app.without.WithoutANote;

/**
 * Esta clase representa un Menu y crea todas sus opciones.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class MenuVer extends JMenu implements ActionListener {
	private JMenuItem[] menuIteamVer;
	private JCheckBoxMenuItem checkBoxBarraEstado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase
	 */
	public MenuVer() {
		this.setText("Ver");
		
		crearMenu();
	}
	
	/**
	 * Este metodo crea toda las opciones que tendra el Menu
	 */
	private void crearMenu() {
		JMenu menuZoom = new JMenu("Zoom");
		checkBoxBarraEstado = new JCheckBoxMenuItem("Barra de estado", WithoutANote.PROFILE.isStatusBar());
		checkBoxBarraEstado.setActionCommand("BarraEstado");
		checkBoxBarraEstado.addActionListener(this);
		
		String[] ID = {"Acercar", "Alejar", "Restaurar zoom predeterminado"};
		menuIteamVer = new JMenuItem[3];
		for(int i=0; i<menuIteamVer.length; i++) {
			menuIteamVer[i] = new JMenuItem(ID[i]);
			menuIteamVer[i].addActionListener(this);
			
			menuZoom.add(menuIteamVer[i]);
		}
		menuIteamVer[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
		menuIteamVer[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
		menuIteamVer[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK));
		
		menuIteamVer[0].setActionCommand("Acercar");
		menuIteamVer[1].setActionCommand("Alejar");
		menuIteamVer[2].setActionCommand("Restaurar");
		
		this.add(menuZoom);
		this.add(checkBoxBarraEstado);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Acercar":
			WithoutANote.TXTPANTALLA.setFont(new Font(WithoutANote.TXTPANTALLA.getFont().getFontName(), WithoutANote.TXTPANTALLA.getFont().getStyle(), WithoutANote.TXTPANTALLA.getFont().getSize() + 2));
			WithoutANote.ZOOM += 10;
			WithoutANote.BARRA_ESTADOS.actualizarZoom(WithoutANote.ZOOM);
			WithoutANote.PROFILE.setZoom(WithoutANote.ZOOM);
			WithoutANote.PROFILE.setZoomFont(WithoutANote.TXTPANTALLA.getFont());
			break;
		case "Alejar":
			WithoutANote.TXTPANTALLA.setFont(new Font(WithoutANote.TXTPANTALLA.getFont().getFontName(), WithoutANote.TXTPANTALLA.getFont().getStyle(), WithoutANote.TXTPANTALLA.getFont().getSize() - 2));
			WithoutANote.ZOOM -= 10;
			WithoutANote.BARRA_ESTADOS.actualizarZoom(WithoutANote.ZOOM);
			WithoutANote.PROFILE.setZoom(WithoutANote.ZOOM);
			WithoutANote.PROFILE.setZoomFont(WithoutANote.TXTPANTALLA.getFont());
			break;
		case "Restaurar":
			WithoutANote.TXTPANTALLA.setFont(WithoutANote.FUENTE_PRINCIPAL);
			WithoutANote.ZOOM = 100;
			WithoutANote.BARRA_ESTADOS.actualizarZoom(WithoutANote.ZOOM);
			
			WithoutANote.PROFILE.setZoom(WithoutANote.ZOOM);
			WithoutANote.PROFILE.setFont(WithoutANote.FUENTE_PRINCIPAL);
			WithoutANote.PROFILE.setZoomFont(WithoutANote.FUENTE_PRINCIPAL);
			break;
		case "BarraEstado":
			WithoutANote.BARRA_ESTADOS.setVisible(checkBoxBarraEstado.isSelected());
			WithoutANote.PROFILE.setStatusBar(checkBoxBarraEstado.isSelected());
			break;
		}
	}
}
