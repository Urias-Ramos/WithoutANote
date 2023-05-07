package app.listener;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

import app.without.WithoutANote;

/**
 * Esta clase se encarga de obtener la linea y columna donde se encuentra el cursor en el area de texto.
 * Despues de obtenerlos, llama a la barra de estado para que se actualize.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class Caret implements CaretListener {
	private int linea, columna;
	private int posicionCaret;

	public Caret() {
		linea = 1;
		columna = 1;
		posicionCaret = 1;
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		posicionCaret = WithoutANote.TXTPANTALLA.getCaretPosition();
		try {
			linea = WithoutANote.TXTPANTALLA.getLineOfOffset(posicionCaret);
			columna = posicionCaret - WithoutANote.TXTPANTALLA.getLineStartOffset(linea);
			linea += 1;
			columna += 1;
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		WithoutANote.BARRA_ESTADOS.actualizarLineasColumnas(linea, columna);
		
		linea = 1;
		columna = 1;
	}
}