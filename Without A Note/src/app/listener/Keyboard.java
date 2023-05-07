package app.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.without.WithoutANote;

/**
 * Esta clase se encarga de verificar si a la caja de texto se le agrego texto o no.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class Keyboard implements KeyListener {
	private int length;

	public Keyboard() {
		length = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		length = WithoutANote.TXTPANTALLA.getText().length();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(length != WithoutANote.TXTPANTALLA.getText().length()) {
			if(WithoutANote.WITHOUTMANAGER.isOpenFile()) {
				WithoutANote.WITHOUTMANAGER.setModifiedFile(true);
			}
			else if((WithoutANote.WITHOUTMANAGER.isNewFile())&&(WithoutANote.TXTPANTALLA.getText().length() > 0)) {
				WithoutANote.WITHOUTMANAGER.setModifiedFile(true);
			}
			else if(WithoutANote.WITHOUTMANAGER.isNewFile() && WithoutANote.TXTPANTALLA.getText().length() == 0) {
				WithoutANote.WITHOUTMANAGER.setModifiedFile(false);
			}
			length = WithoutANote.TXTPANTALLA.getText().length();
		}
	}
}