package app.without;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

/**
 * Esta clase representa un PopupMenu, que se despliega al hacer clic derecho sobre el area de texto
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class Popup extends JPopupMenu implements ActionListener {
	private JMenuItem[] itemMenu;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase
	 */
	public Popup() {
		String[] texto = {"Deshacer", "Cortar", "Copiar", "Pegar", "Eliminar", "Seleccionar todo"};
		itemMenu = new JMenuItem[texto.length];
		for(int i=0; i<itemMenu.length; i++) {
			itemMenu[i] = new JMenuItem(texto[i]);
			itemMenu[i].setFocusPainted(false);
			itemMenu[i].addActionListener(this);
		}
		
		JSeparator separadorInicial = new JSeparator(JSeparator.HORIZONTAL);
		JSeparator separadorMedio = new JSeparator(JSeparator.HORIZONTAL);
		
		this.add(itemMenu[0]);
		this.add(separadorInicial);
		this.add(itemMenu[1]);
		this.add(itemMenu[2]);
		this.add(itemMenu[3]);
		this.add(itemMenu[4]);
		this.add(separadorMedio);
		this.add(itemMenu[5]);
	}
	
	public JMenuItem[] getJMenuItemPopup() {
		return itemMenu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Deshacer":
			break;
		case "Cortar":
			WithoutANote.TXTPANTALLA.cut();
			WithoutANote.WITHOUTMANAGER.setModifiedFile(true);
			break;
		case "Copiar":
			WithoutANote.TXTPANTALLA.copy();
			break;
		case "Pegar":
			WithoutANote.TXTPANTALLA.paste();
			WithoutANote.WITHOUTMANAGER.setModifiedFile(true);
			break;
		case "Eliminar":
			WithoutANote.TXTPANTALLA.replaceSelection("");
			WithoutANote.WITHOUTMANAGER.setModifiedFile(true);
			break;
		case "Seleccionar todo":
			WithoutANote.TXTPANTALLA.selectAll();
			break;
		}
	}
}