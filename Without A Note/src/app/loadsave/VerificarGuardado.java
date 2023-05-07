package app.loadsave;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import app.without.WithoutANote;

/**
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 * 
 * Esta clase despliega la ventana para saber si el usuario desea guardar o no un archivo editado.
 *
 */
public class VerificarGuardado {
	private JFrame ventana;
	
	public VerificarGuardado(JFrame ventana) {
		this.ventana = ventana;
	}
	
	/**
	 * Este metodo despliega la ventana para que el usuario eliga si desea guardar o no un archivo modificado
	 * 
	 * @return retorna el valor de la opcion seleccionada por el usuario.
	 */
	public int verificar() {
		int opc = 1;
		opc = JOptionPane.showOptionDialog(ventana, "¿Deseas Guardar los cambios en: "+WithoutANote.WITHOUTMANAGER.getFile().getName()+"?", "Without A note", 
				JOptionPane.YES_NO_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Guardar", "No Guardar", "Cancelar"}, "Guardar");
		if(opc == 0) {
			WithoutANote.SAVE_FILE.save();
		}
		return opc;
	}
}