package app.loadsave;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.without.WithoutANote;

public class AbrirDocumento extends JFileChooser {
	private JFrame ventana;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbrirDocumento(JFrame ventana) {
		this.ventana = ventana;
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos de texto (*.txt)", "txt");
		
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.setFileFilter(filter);
		this.setDialogTitle("Abrir");
	}
	
	public void abrir() {
		int OPC = 0;
		if(WithoutANote.WITHOUTMANAGER.isModifiedFile()) {
			OPC = WithoutANote.VERIFY_SAVED.verificar();
		}
		
		if(OPC != 2) {
			this.showOpenDialog(ventana);
			readFile(this.getSelectedFile().getAbsoluteFile());
		}
	}
	
	public void abrir(File file) {
		int OPC = 0;
		if(WithoutANote.WITHOUTMANAGER.isModifiedFile()) {
			OPC = WithoutANote.VERIFY_SAVED.verificar();
		}
		
		if(OPC != 2) {
			readFile(file);
		}
	}
	
	private void readFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8"));
			
			WithoutANote.TXTPANTALLA.read(reader, "");
			WithoutANote.WITHOUTMANAGER.setModifiedFile(false);
			WithoutANote.WITHOUTMANAGER.setNewFile(false);
			WithoutANote.WITHOUTMANAGER.setFile(file);
			WithoutANote.WITHOUTMANAGER.setOpenFile(true);
			reader.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}