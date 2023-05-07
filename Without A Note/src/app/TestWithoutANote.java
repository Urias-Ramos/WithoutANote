package app;

import java.awt.Toolkit;

import javax.swing.JFrame;
import app.without.WithoutANote;

/**
 * Estas clase contiene el metodo principal del programa
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 *
 */
public class TestWithoutANote {
	
	public static void main(String[] args) {
		JFrame ventana = new WithoutANote("Without A Note");
		ventana.setIconImage(Toolkit.getDefaultToolkit().getImage(ventana.getClass().getResource("/icon/icon_app.png")));
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setSize(750, 500);
		ventana.setLocationRelativeTo(null);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setVisible(true);
	}
}