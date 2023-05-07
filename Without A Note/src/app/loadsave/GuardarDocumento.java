package app.loadsave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.without.WithoutANote;

public class GuardarDocumento extends JFileChooser {
	private JFrame ventana;
	
	private FileNameExtensionFilter filter;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuardarDocumento(JFrame ventana) {
		this.ventana = ventana;
		
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		filter = new FileNameExtensionFilter("Documentos de texto (*.txt)", "txt");
		this.setFileFilter(filter);
		this.setDialogTitle("Guardar");
	}
	
	/**
	 * Este Metodo permite guardar el archivo. si no esta creado anteriormente este se guardara con extension .txt por defecto.
	 */
	public void save() {
		File ruta = null;
		int opc = -1;
		try {
			setFileFilter(filter);
			
			//si no hay un archivo abierto
			if(!WithoutANote.WITHOUTMANAGER.isOpenFile()) {
				
				//se abre la ventana de guardado y se verifica que se acepte el guardado
				if(showSaveDialog(ventana) == JFileChooser.APPROVE_OPTION) {
					opc = 0;
					
					//se guarda la ruta del archivo que el usuario selecciono
					ruta = this.getSelectedFile().getAbsoluteFile();
					
					//se verifica que el archivo exista
					if(ruta.exists()) {
						//si el archivo existe, se lanza un aviso alertando al usuario y que escoga la opcion de conveniencia
						opc = JOptionPane.showOptionDialog(ventana, ""+ruta.getName()+" Ya existe.\n¿Quieres Reemplazarlo?", "Without a note", 
								JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Guardar", "Cancelar"}, "Cancelar");
					}
				}
			}//si el archivo esta abierto
			else {
				opc = 0;
				ruta = WithoutANote.WITHOUTMANAGER.getFile();
			}
			
			//opc es 0 si no existe el archivo o que el usuario aya aceptado que se reemplaze el archivo si este existe 
			if(opc == 0) {
				
				//aqui se guarda el archivo en la ruta seleccionada
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta), StandardCharsets.UTF_8));
				writer.write(WithoutANote.TXTPANTALLA.getText());
				
				//se cierra el BufferedWriter cuando se aya guardado el archivo
				writer.close();
				
				//se establecen las nuevas propiedades del editor
				WithoutANote.WITHOUTMANAGER.setFile(ruta);
				WithoutANote.WITHOUTMANAGER.setModifiedFile(false);
				WithoutANote.WITHOUTMANAGER.setNewFile(false);
				WithoutANote.WITHOUTMANAGER.setOpenFile(true);
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Este metodo permite guardar un archivo con cualquier extension
	 */
	public void saveAs() {
		File ruta = null;
		int opc = -1;
		try {
			setFileFilter(null);
			
			//se abre la ventana de guardado y se verifica que se acepte el guardado
			if(showSaveDialog(ventana) == JFileChooser.APPROVE_OPTION) {
				opc = 0;
				
				//se guarda la ruta del archivo que el usuario selecciono
				ruta = this.getSelectedFile().getAbsoluteFile();
				
				//se verifica que el archivo exista
				if(ruta.exists()) {
					//si el archivo existe, se lanza un aviso alertando al usuario y que escoga la opcion de conveniencia
					opc = JOptionPane.showOptionDialog(ventana, ""+ruta.getName()+" Ya existe.\n¿Quieres Reemplazarlo?", "Without a note", 
							JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Guardar", "Cancelar"}, "Cancelar");
				}
			}
			
			//opc es 0 si no existe el archivo o que el usuario aya aceptado que se reemplaze el archivo si este existe 
			if(opc == 0) {
				
				//aqui se guarda el archivo en la ruta seleccionada
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta), StandardCharsets.UTF_8));
				writer.write(WithoutANote.TXTPANTALLA.getText());
				
				//se cierra el BufferedWriter cuando se aya guardado el archivo
				writer.close();
				
				//se establecen las nuevas propiedades del editor
				WithoutANote.WITHOUTMANAGER.setFile(ruta);
				WithoutANote.WITHOUTMANAGER.setModifiedFile(false);
				WithoutANote.WITHOUTMANAGER.setNewFile(false);
				WithoutANote.WITHOUTMANAGER.setOpenFile(true);
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}