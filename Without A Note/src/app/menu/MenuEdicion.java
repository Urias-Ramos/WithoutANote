package app.menu;

import java.awt.Desktop;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import app.dialog.DialogEdicion;
import app.without.WithoutANote;

public class MenuEdicion extends JMenu implements ActionListener {
	private JMenuItem[] menuIteamEdicion;
	
	private JFrame ventana;
	
	private DialogEdicion dialogEdicion;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuEdicion(JFrame ventana) {
		this.ventana = ventana;
		
		this.setText("Edición");
		
		crearMenu();
		dialogEdicion = new DialogEdicion(ventana);
	}
	
	private void crearMenu() {
		String[] ID = {"Deshacer", "Cortar", "Copiar", "Pegar", "Eliminar", "Busqueda con bing..."
				,"Buscar", "Buscar siguiente", "Buscar anterior", "Reemplazar...", "Ir a...",
				"Seleccionar todo", "Hora y fecha"};
		menuIteamEdicion = new JMenuItem[13];
		for(int i=0; i<menuIteamEdicion.length; i++) {
			menuIteamEdicion[i] = new JMenuItem(ID[i]);
			menuIteamEdicion[i].addActionListener(this);
		}
		menuIteamEdicion[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		menuIteamEdicion[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuIteamEdicion[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuIteamEdicion[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		menuIteamEdicion[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		menuIteamEdicion[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		menuIteamEdicion[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
		menuIteamEdicion[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
		menuIteamEdicion[9].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		menuIteamEdicion[10].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		menuIteamEdicion[11].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuIteamEdicion[12].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		
		menuIteamEdicion[0].setActionCommand("Deshacer");
		menuIteamEdicion[0].setActionCommand("Deshacer");
		menuIteamEdicion[1].setActionCommand("Cortar");
		menuIteamEdicion[2].setActionCommand("Copiar");
		menuIteamEdicion[3].setActionCommand("Pegar");
		menuIteamEdicion[4].setActionCommand("Eliminar");
		menuIteamEdicion[5].setActionCommand("Busqueda");
		menuIteamEdicion[6].setActionCommand("Buscar");
		menuIteamEdicion[7].setActionCommand("findNext");
		menuIteamEdicion[8].setActionCommand("findPrevious");
		menuIteamEdicion[9].setActionCommand("Reemplazar...");
		menuIteamEdicion[10].setActionCommand("Ir a...");
		menuIteamEdicion[11].setActionCommand("Seleccionar todo");
		menuIteamEdicion[12].setActionCommand("Hora y fecha");
		
		JSeparator separadorInicial = new JSeparator(JSeparator.HORIZONTAL);
		JSeparator separadorMedio = new JSeparator(JSeparator.HORIZONTAL);
		JSeparator separadorFinal = new JSeparator(JSeparator.HORIZONTAL);
		
		this.add(menuIteamEdicion[0]);
		this.add(separadorInicial);
		this.add(menuIteamEdicion[1]);
		this.add(menuIteamEdicion[2]);
		this.add(menuIteamEdicion[3]);
		this.add(menuIteamEdicion[4]);
		this.add(separadorMedio);
		this.add(menuIteamEdicion[5]);
		this.add(menuIteamEdicion[6]);
		this.add(menuIteamEdicion[7]);
		this.add(menuIteamEdicion[8]);
		this.add(menuIteamEdicion[9]);
		this.add(menuIteamEdicion[10]);
		this.add(separadorFinal);
		this.add(menuIteamEdicion[11]);
		this.add(menuIteamEdicion[12]);
	}
	
	public void updateMenuItem() {
		
		if(WithoutANote.undoManager.canUndo()) {
			menuIteamEdicion[0].setEnabled(true);
		}
		else {
			menuIteamEdicion[0].setEnabled(false);
		}
		
		if(WithoutANote.TXTPANTALLA.getText().length() > 0) {
			menuIteamEdicion[5].setEnabled(true);
			menuIteamEdicion[6].setEnabled(true);
			menuIteamEdicion[7].setEnabled(true);
			menuIteamEdicion[8].setEnabled(true);
			menuIteamEdicion[9].setEnabled(true);
		}
		else {
			menuIteamEdicion[5].setEnabled(false);
			menuIteamEdicion[6].setEnabled(false);
			menuIteamEdicion[7].setEnabled(false);
			menuIteamEdicion[8].setEnabled(false);
			menuIteamEdicion[9].setEnabled(false);
		}
		
		if(WithoutANote.TXTPANTALLA.getSelectedText() != null) {
			menuIteamEdicion[1].setEnabled(true);
			menuIteamEdicion[2].setEnabled(true);
			menuIteamEdicion[4].setEnabled(true);
		}
		else {
			menuIteamEdicion[1].setEnabled(false);
			menuIteamEdicion[2].setEnabled(false);
			menuIteamEdicion[4].setEnabled(false);
		}
		
		if((WithoutANote.CLIPBOARD.isDataFlavorAvailable(DataFlavor.stringFlavor))&&(WithoutANote.CLIPBOARD.getContents(null) != null)) {
			menuIteamEdicion[3].setEnabled(true);
		}
		else {
			menuIteamEdicion[3].setEnabled(false);
		}
		
		//se habilita o desahilita el JMenuItem Ir a si el Ajuste de linea esta activado o no.
		menuIteamEdicion[10].setEnabled(!WithoutANote.TXTPANTALLA.getLineWrap());
	}
	
	/**
	 * Este metodo permite hacer una busqueda rapida de una palabra ya establecida, 
	 * la busqueda puede ser siguiente o anterior.
	 * 
	 * @param isNext valor que determina la orientacion de la busqueda
	 */
	public void wordFindOrientation(boolean isNext) {
		dialogEdicion.findWord(isNext);
		
		if(WithoutANote.TXTPANTALLA.getSelectedText() != null) {
			dialogEdicion.setSelectedText(""+WithoutANote.TXTPANTALLA.getSelectedText());
		}
		
		if((dialogEdicion.getPalabraClave() != null)&&(!dialogEdicion.getPalabraClave().contentEquals(""))) {
			
			if(isNext) {
				dialogEdicion.findNext();
			}
			else {
				dialogEdicion.findPrevious();;
			}
		}
		else {
			dialogEdicion.visualizar(1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "Deshacer":
			if(WithoutANote.undoManager.canUndo()) {
				WithoutANote.undoManager.undo();
				if((WithoutANote.WITHOUTMANAGER.isNewFile())&&(WithoutANote.TXTPANTALLA.getText().isEmpty())) {
					WithoutANote.WITHOUTMANAGER.setModifiedFile(false);
				}
			}
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
		case "Busqueda":
			if(!WithoutANote.TXTPANTALLA.getSelectedText().isEmpty()) {
				try {
					Desktop.getDesktop().browse(new URI("https://www.google.com/search?q="+URLEncoder.encode(WithoutANote.TXTPANTALLA.getSelectedText(), "UTF-8")));
				} catch (UnsupportedEncodingException e1) {
					JOptionPane.showMessageDialog(ventana, e1.toString(), "Without A Noter", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(ventana, e1.toString(), "Without A Noter", JOptionPane.ERROR_MESSAGE);
				} catch (URISyntaxException e1) {
					JOptionPane.showMessageDialog(ventana, e1.toString(), "Without A Noter", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
		case "Buscar":
			if(WithoutANote.TXTPANTALLA.getSelectedText() != null) {
				dialogEdicion.setSelectedText(""+WithoutANote.TXTPANTALLA.getSelectedText());
			}
			dialogEdicion.visualizar(1);
			break;
		case "findNext":
			wordFindOrientation(true);
			break;
		case "findPrevious":
			wordFindOrientation(false);
			break;
		case "Reemplazar...":
			if(WithoutANote.TXTPANTALLA.getSelectedText() != null) {
				dialogEdicion.setPalabraClave(""+WithoutANote.TXTPANTALLA.getSelectedText());
			}
			dialogEdicion.visualizar(2);
			break;
		case "Ir a...":
			try {
				int numero = 1;
				String cadena = JOptionPane.showInputDialog(ventana, "Numero de linea:", "0");
				if(cadena != null) {
					numero = Integer.parseInt(cadena);
					UbicarLinea(numero);
				}
			} catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(ventana, "Ingrese un numero entero positivo", "Without A Noter", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "Seleccionar todo":
			WithoutANote.TXTPANTALLA.selectAll();
			break;
		case "Hora y fecha":
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm: a. dd/MM/YYYY");
			if(WithoutANote.TXTPANTALLA.getSelectedText() != null) {
				WithoutANote.TXTPANTALLA.replaceSelection(dtf.format(LocalDateTime.now()));
			}
			else {
				WithoutANote.TXTPANTALLA.insert(dtf.format(LocalDateTime.now()), WithoutANote.TXTPANTALLA.getCaretPosition());
			}
			WithoutANote.WITHOUTMANAGER.setModifiedFile(true);
			break;
		}
	}
	
	/**
	 * Este metodo se encarga de ubicar el numero de linea deseado por el usuario.
	 * 
	 * @param ir variable donde esta almacenado el numero de linea donde se desa ubicar el cursor.
	 */
	private void UbicarLinea(int ir) {
		try {
			if((ir <= 0)||(ir > WithoutANote.TXTPANTALLA.getLineCount())) {
				JOptionPane.showMessageDialog(ventana, "El número de líneas es superior al número total de líneas", "Without A Note", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int offset;
				offset = WithoutANote.TXTPANTALLA.getLineStartOffset(ir - 1);
				WithoutANote.TXTPANTALLA.setCaretPosition(offset);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}