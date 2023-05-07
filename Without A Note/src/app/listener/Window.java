package app.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import app.without.WithoutANote;

/**
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 * 
 * Esta clase se encarga de verificar que antes de abandonar la aplicacion los ficheros modificados
 * sean guardados o ignorados por el usuario.
 *
 */
public class Window extends WindowAdapter {
	
	/**
	 * Constructor de la clase no recibe parametros, pero es necesario utilizarlo para
	 * crear una instancia de la clase
	 */
	public Window() {
		
	}
	
	/**
	 * Este metodo es invocado cuando se intenta cerrar la aplicacion,
	 * determina si el archivo actual es nuevo o editado y toma acciones.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		//si el archivo esta editado procede a mostar el mensaje de guardado
		if(WithoutANote.WITHOUTMANAGER.isModifiedFile()) {
			//si la opcion no es cancelar, abandona la aplicacion
			if(WithoutANote.VERIFY_SAVED.verificar() != 2) {
				WithoutANote.saveProfile();
				System.exit(0);
			}
		}//si el archivo no esta modificado se cierra la aplicacion
		else {
			WithoutANote.saveProfile();
			System.exit(0);
		}
	}
}