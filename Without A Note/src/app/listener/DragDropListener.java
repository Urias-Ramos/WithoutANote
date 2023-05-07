package app.listener;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import app.without.WithoutANote;

/**
 * Esta clase se encarga de lee un fichero que sea arrastrado y soltado en el area de texto.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class DragDropListener implements DropTargetListener {
	private File file;
	
	public DragDropListener() {
		
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_COPY);
		Transferable transferable = dtde.getTransferable();
		DataFlavor[] flavors = transferable.getTransferDataFlavors();
		for(DataFlavor flavor: flavors) {
			if(flavor.isFlavorJavaFileListType()) {
				try {
					@SuppressWarnings("unchecked")
					List<File> files = (List<File>) transferable.getTransferData(flavor);
					for(File fichero: files) {
						file = fichero.getAbsoluteFile();
					}
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		dtde.dropComplete(true);
		if(file.isFile()) {
			WithoutANote.OPEN_FILE.abrir(file);
		}
	}
}