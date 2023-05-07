package app.without;

import java.io.File;

import javax.swing.JFrame;

/**
 * Esta clase se encarga de administrar el estado de los archivos, si esta editado, si es nuevo, etc...
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class WithoutManager {
	private boolean modifiedFile;
	private boolean newFile;
	private boolean openFile;
	
	private File file;
	
	private JFrame ventana;
	
	/**
	 * Constructor de la clase
	 */
	public WithoutManager(JFrame ventana) {
		this.ventana = ventana;
		
		setFile(new File("Sin titulo"));
		setModifiedFile(false);
		setNewFile(true);
		setOpenFile(false);
	}
	
	/**
	 * Este metodo devuelve el valor del atributo archivo modificado
	 * 
	 * @return devuelve el atributo archivo modificado
	 */
	public boolean isModifiedFile() {
		return modifiedFile;
	}
	
	/**
	 * Este metodo permite cambiar el atributo archivo modificado
	 * si el archivo esta modificado entonces a la barra de titulo le coloca un *.
	 * si no lo es elimina el *.
	 * 
	 * @param modifiedFile
	 */
	public void setModifiedFile(boolean modifiedFile) {
		this.modifiedFile = modifiedFile;
		
		if(isModifiedFile()) {
			ventana.setTitle("*"+getFile().getName()+": Without A Note");
		}
		else {
			ventana.setTitle(getFile().getName()+": Without A Note");
		}
	}
	
	/**
	 * Este metodo permite saber si el archivo en edicion es nuevo o existente
	 * 
	 * @return devuelve true si el archivo es nuevo y falso si no lo es
	 */
	public boolean isNewFile() {
		return newFile;
	}
	
	/**
	 * Este metodo permite establecer si un archivo es nuevo o no si lo es
	 * cambia algunos atributos de la aplicacion
	 * 
	 * @param newFile nuevo valor de newFile
	 */
	public void setNewFile(boolean newFile) {
		this.newFile = newFile;
		
		if(isNewFile()) {
			WithoutANote.TXTPANTALLA.setText("");
			setModifiedFile(false);
			setOpenFile(false);
			
			setFile(new File("Sin titulo"));
			ventana.setTitle(getFile().getName()+": Without A Note");
		}
	}
	
	/**
	 * Este metodo sirve para saber si hay un archivo abierto o no
	 * 
	 * @return devuele el estado de la variable openFile
	 */
	public boolean isOpenFile() {
		return openFile;
	}
	
	/**
	 * Este metodo permite establecer si un archivo esta abierto y
	 * si lo esta cambia algunos atributos de la aplicacion
	 * 
	 * @param openFile valor que tendra la variable
	 */
	public void setOpenFile(boolean openFile) {
		this.openFile = openFile;
		
		if(isOpenFile()) {
			setModifiedFile(false);
			setNewFile(false);
			
			ventana.setTitle(getFile().getName()+": Without A Note");
		}
	}
	
	/**
	 * Este metodo permite obtener el archivo que se esta usando
	 * 
	 * @return devuelve el archivo
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * Este metodo permite cambiar el archivo
	 * 
	 * @param file archivo que se va a cambiar
	 */
	public void setFile(File file) {
		this.file = file;
	}
}