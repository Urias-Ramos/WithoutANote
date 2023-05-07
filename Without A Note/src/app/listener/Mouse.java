package app.listener;

import java.awt.datatransfer.DataFlavor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import app.without.Popup;
import app.without.WithoutANote;

/**
 * Esta clase se encarga de mostar el PopupMenu si se da un clic derecho en el area de texto.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class Mouse extends MouseAdapter {
	private Popup popupMenu;

	public Mouse(Popup popupMenu) {
		this.popupMenu = popupMenu;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)) {
			
			if(WithoutANote.undoManager.canUndo()) {
				popupMenu.getJMenuItemPopup()[0].setEnabled(true);
			}
			else {
				popupMenu.getJMenuItemPopup()[0].setEnabled(false);
			}
			
			if((WithoutANote.CLIPBOARD.isDataFlavorAvailable(DataFlavor.stringFlavor))&&(WithoutANote.CLIPBOARD.getContents(null) != null)) {
				popupMenu.getJMenuItemPopup()[3].setEnabled(true);
			}
			else {
				popupMenu.getJMenuItemPopup()[3].setEnabled(false);
			}
			
			if(WithoutANote.TXTPANTALLA.getSelectedText() != null) {
				popupMenu.getJMenuItemPopup()[1].setEnabled(true);
				popupMenu.getJMenuItemPopup()[2].setEnabled(true);
				popupMenu.getJMenuItemPopup()[4].setEnabled(true);
			}
			else {
				popupMenu.getJMenuItemPopup()[1].setEnabled(false);
				popupMenu.getJMenuItemPopup()[2].setEnabled(false);
				popupMenu.getJMenuItemPopup()[4].setEnabled(false);
			}
			
			popupMenu.show(WithoutANote.TXTPANTALLA, e.getXOnScreen(), e.getYOnScreen());
		}
	}
}