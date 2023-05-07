package app.without;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * Esta clase representa la Barra de estado que se encuentra en la parte inferior de la aplicacion,
 * Donde se ven informacion del cursor y otros mas.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class BarraDeEstado extends JPanel {
	private JLabel[] lblEtiqueta;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase-
	 */
	public BarraDeEstado() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		this.add(panelEmpresa(), BorderLayout.CENTER);
		this.add(panelEtiquetas(), BorderLayout.EAST);
	}
	
	/**
	 * Este metodo crea la interfaz grafica de usuario
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelEmpresa() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblEmpresa = new JLabel("Without a note");
		lblEmpresa.setHorizontalAlignment(JLabel.LEFT);
		lblEmpresa.setFont(new Font("Arial", Font.BOLD, 12));
		
		panelPrincipal.add(lblEmpresa);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea la interfaz grafica de usuario
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelEtiquetas() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		String[] etiqueta = {"Linea 1, columna 1", "100%", "Windows (CRLF)", "UTF-8"};
		lblEtiqueta = new JLabel[4];
		JSeparator[] separador = new JSeparator[4];
		for(int i=0; i<lblEtiqueta.length; i++) {
			lblEtiqueta[i] = new JLabel(etiqueta[i]);
			lblEtiqueta[i].setHorizontalAlignment(JLabel.CENTER);
			lblEtiqueta[i].setFont(new Font("Arial", Font.PLAIN, 12));
			
			separador[i] = new JSeparator(JSeparator.VERTICAL);
			separador[i].setPreferredSize(new Dimension(8, 16));
			
			panelPrincipal.add(separador[i]);
			panelPrincipal.add(lblEtiqueta[i]);
		}
		
		lblEtiqueta[0].setPreferredSize(new Dimension(150, 16));
		lblEtiqueta[1].setPreferredSize(new Dimension(100, 16));
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo se encarga de actualizar la informacion de la linea y columna del cursor.
	 * 
	 * @param linea
	 * @param columna
	 */
	public void actualizarLineasColumnas(int linea, int columna) {
		lblEtiqueta[0].setText("Linea "+linea+", columna "+columna);
	}
	
	/**
	 * Este metodo se encarga de actualizar la informacion del zoom.
	 * 
	 * @param zoom
	 */
	public void actualizarZoom(int zoom) {
		lblEtiqueta[1].setText(zoom+"%");
	}
}