package app.without;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import app.listener.Caret;
import app.listener.DragDropListener;
import app.listener.Focus;
import app.listener.Keyboard;
import app.listener.Mouse;
import app.listener.Window;
import app.loadsave.AbrirDocumento;
import app.loadsave.GuardarDocumento;
import app.loadsave.Profile;
import app.loadsave.VerificarGuardado;
import app.menu.MenuBar;

/**
 * Esta clase representa el Frame principal de la aplicacion
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class WithoutANote extends JFrame {
	public static JTextArea TXTPANTALLA;
	public static UndoManager undoManager;
	public static Document doc;
	public static Clipboard CLIPBOARD;
	
	public static WithoutManager WITHOUTMANAGER;
	
	public static BarraDeEstado BARRA_ESTADOS;
	public static int ZOOM;
	
	public static Font FUENTE_PRINCIPAL;
	
	public static AbrirDocumento OPEN_FILE;
	public static GuardarDocumento SAVE_FILE;
	public static VerificarGuardado VERIFY_SAVED;
	
	private Popup popupMenu;
	
	private MenuBar menuBar;
	
	public static Profile PROFILE;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contructor de la clase.
	 * 
	 * @param titulo titlo de la aplicacion
	 */
	public WithoutANote(String titulo) {
		super("Sin titulo"+": "+titulo);
		LookAndFeel();
		
		PROFILE = new Profile();
		loadProfile();
		
		ZOOM = PROFILE.getZoom();
		FUENTE_PRINCIPAL = PROFILE.getFont();
		
		SAVE_FILE = new GuardarDocumento(this);
		VERIFY_SAVED = new VerificarGuardado(this);
		OPEN_FILE = new AbrirDocumento(this);
		
		crearUI();
		
		WITHOUTMANAGER = new WithoutManager(this);
	}
	
	/**
	 * Este metodo aplica el LookAndFeel a la aplicacion. para que tenga el aspecto de aplicaciones de Windows.
	 */
	private void LookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Este metodo crea y agrega todos los componetes de la interfaz de usuario
	 * y lo agrega a la ventana principal de la aplicacion.
	 */
	private void crearUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBackground(Color.WHITE);
		
		TXTPANTALLA = new JTextArea();
		TXTPANTALLA.getDocument().addUndoableEditListener(new UndoableEditListener() {
			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				undoManager.addEdit(e.getEdit());
			}
		});
		TXTPANTALLA.setFont(PROFILE.getZoomFont());
		TXTPANTALLA.setWrapStyleWord(true);
		TXTPANTALLA.setLineWrap(true);
		
		undoManager = new UndoManager();
		doc = TXTPANTALLA.getDocument();
		CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		popupMenu = new Popup();
		
		TXTPANTALLA.add(popupMenu);
		
		TXTPANTALLA.setDropTarget(new DropTarget(TXTPANTALLA, new DragDropListener()));
		TXTPANTALLA.addKeyListener(new Keyboard());
		TXTPANTALLA.addMouseListener(new Mouse(popupMenu));
		
		menuBar = new MenuBar(this);
		
		Focus focus = new Focus(menuBar);
		
		TXTPANTALLA.addFocusListener(focus);
		
		Caret caret = new Caret();
		TXTPANTALLA.addCaretListener(caret);
		
		this.addWindowListener(new Window());
		
		JScrollPane desplazadorPantalla = new JScrollPane(TXTPANTALLA);
		desplazadorPantalla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		BARRA_ESTADOS = new BarraDeEstado();
		BARRA_ESTADOS.actualizarZoom(ZOOM);
		
		panelPrincipal.add(desplazadorPantalla, BorderLayout.CENTER);
		panelPrincipal.add(BARRA_ESTADOS, BorderLayout.SOUTH);
		
		this.setJMenuBar(menuBar);
		this.add(panelPrincipal, BorderLayout.CENTER);
		
		caret.caretUpdate(null);
		
		focus.focusLost(null);
		focus.focusGained(null);
	}
	
	public static File getDirectory() {
		String path = new File("").getAbsolutePath();
		return new File(path+"\\data\\profile.dat");
	}
	
	public static void loadProfile() {
		//se verifica que exista la ruta y el archivo
		if(existProfile(getDirectory())) {
			//si existe se carga
			load(getDirectory());
		}
		else {
			//se guarda los valores por defecto
			saveProfile();
		}
	}
	
	public static boolean existProfile(File file) {
		if(file.exists()) {
			return true;
		}
		else {
			file.getParentFile().mkdirs();
			return false;
		}
	}
	
	private static void load(File file) {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		
		try {
			fis = new FileInputStream(file);
			entrada = new ObjectInputStream(fis);
			
			PROFILE = (Profile) entrada.readObject();
			entrada.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			PROFILE = new Profile();
		}
	}
	
	public static void saveProfile() {
		
		existProfile(getDirectory());
		
		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		
		try {
			fos = new FileOutputStream(getDirectory());
			salida = new ObjectOutputStream(fos);
			
			salida.writeObject(PROFILE);
			
			fos.close();
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}