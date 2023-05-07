package app.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import app.dialog.DialogFuente;
import app.without.WithoutANote;

public class MenuFormato extends JMenu implements ActionListener {
	private JMenuItem menuItemFuente;
	private JCheckBoxMenuItem checkBoxAjustesLinea;
	
	private DialogFuente dialogFuente;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuFormato(JFrame ventana) {
		dialogFuente = new DialogFuente(ventana);
		
		this.setText("Formato");
		
		crearMenu();
	}
	
	private void crearMenu() {
		checkBoxAjustesLinea = new JCheckBoxMenuItem("Ajuste de linea", WithoutANote.PROFILE.isLineWrap());
		checkBoxAjustesLinea.addActionListener(this);
		checkBoxAjustesLinea.setActionCommand("AjustarLinea");
		
		menuItemFuente = new JMenuItem("Fuente...");
		menuItemFuente.addActionListener(this);
		menuItemFuente.setActionCommand("Fuente");
		
		this.add(checkBoxAjustesLinea);
		this.add(menuItemFuente);
		
		updateLine(checkBoxAjustesLinea.isSelected());
	}
	
	private void updateLine(boolean isSelected) {
		WithoutANote.TXTPANTALLA.setWrapStyleWord(isSelected);
		WithoutANote.TXTPANTALLA.setLineWrap(isSelected);
		WithoutANote.PROFILE.setLineWrap(isSelected);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "AjustarLinea":
			updateLine(checkBoxAjustesLinea.isSelected());
			break;
		case "Fuente":
			dialogFuente.setVisible(true);
			break;
		}
	}
}