package app.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import app.without.WithoutANote;

/**
 * Esta clase representa la ventana de dialogo, que muestra la interfaz de buscar y reemplazar
 * una cadena de texto.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class DialogEdicion extends JDialog implements ActionListener, KeyListener {
	private JTextField textBusqueda;
	private JTextField textReemplazar;
	
	private JButton btnSiguiente, btnReemplazar, btnReemplazarTodo, btnCancelar;
	
	private JPanel panelContenedorDireccion, panelContenedorReemplazar;
	private JPanel panelContenedorControles;
	
	private JCheckBox checkMayuscula, checkAjusteAutomatico;
	private JRadioButton radioSubir, radioBajar;
	
	private String cadenaAuxiliar;
	private String palabraClave, palabraReemplazar;
	private int indiceInicio = 0, indiceFinal = 0, indiceAnterior = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param ventana ventana principal del programa.
	 */
	public DialogEdicion(JFrame ventana) {
		super(ventana);
		
		palabraClave = "";
		
		this.setSize(450, 160);
		this.setTitle("Buscar");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(5, 5));
		
		this.add(crearUI(), BorderLayout.CENTER);
	}
	
	/**
	 * Este metodo crea el panel principal con toda la interfaz, para ser agregada al JDialog
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel crearUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setOpaque(false);
		panelContenedor.setLayout(new BorderLayout(5, 5));
		panelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panelContenedor.add(panelBusqueda(), BorderLayout.NORTH);
		panelContenedor.add(panelCentro(), BorderLayout.CENTER);
		
		panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones(), BorderLayout.EAST);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel con la interfaz de busqueda.
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelBusqueda() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JPanel panelLabel = new JPanel();
		panelLabel.setOpaque(false);
		panelLabel.setLayout(new BorderLayout(0, 0));
		
		panelContenedorReemplazar = new JPanel();
		panelContenedorReemplazar.setOpaque(false);
		panelContenedorReemplazar.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitulo = new JLabel("Buscar");
		lblTitulo.setPreferredSize(new Dimension(63, 16));
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTitulo.setHorizontalAlignment(JLabel.LEFT);
		
		textBusqueda = new JTextField(22);
		textBusqueda.setFont(new Font("Arial", Font.PLAIN, 12));
		textBusqueda.addKeyListener(this);
		
		JLabel lblReemplazar = new JLabel("<html>Reemplaza<br>por:</html>");
		lblReemplazar.setPreferredSize(new Dimension(63, 28));
		lblReemplazar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblReemplazar.setHorizontalAlignment(JLabel.LEFT);
		
		textReemplazar = new JTextField(22);
		textReemplazar.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JPanel panelFlowBusqueda = new JPanel();
		panelFlowBusqueda.setOpaque(false);
		panelFlowBusqueda.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		panelFlowBusqueda.add(textBusqueda);
		
		panelLabel.add(lblTitulo, BorderLayout.WEST);
		panelLabel.add(panelFlowBusqueda, BorderLayout.CENTER);
		
		JPanel panelFlow = new JPanel();
		panelFlow.setOpaque(false);
		panelFlow.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		panelFlow.add(textReemplazar);
		
		panelContenedorReemplazar.add(lblReemplazar, BorderLayout.WEST);
		panelContenedorReemplazar.add(panelFlow, BorderLayout.CENTER);
		
		panelPrincipal.add(panelLabel, BorderLayout.NORTH);
		panelPrincipal.add(panelContenedorReemplazar, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel con la interfaz de busqueda.
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelCentro() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panelContenedorCheck = new JPanel();
		panelContenedorCheck.setOpaque(false);
		panelContenedorCheck.setLayout(new GridLayout(2, 1, 5, 5));
		
		checkMayuscula = new JCheckBox("Coincidir mayusculas y minusculas");
		checkMayuscula.setFocusPainted(false);
		checkMayuscula.setSelected(false);
		
		checkAjusteAutomatico = new JCheckBox("Ajuste automatico");
		checkAjusteAutomatico.setFocusPainted(false);
		checkAjusteAutomatico.setSelected(false);
		
		panelContenedorDireccion = new JPanel();
		panelContenedorDireccion.setOpaque(false);
		panelContenedorDireccion.setLayout(new GridLayout(2, 1, 5, 5));
		panelContenedorDireccion.setBorder(new TitledBorder("Direccion"));
		
		ButtonGroup grupo = new ButtonGroup();
		
		radioSubir = new JRadioButton("Subir");
		radioSubir.setFocusPainted(false);
		radioSubir.setSelected(false);
		
		radioBajar = new JRadioButton("Bajar");
		radioBajar.setFocusPainted(false);
		radioBajar.setSelected(true);
		
		grupo.add(radioSubir);
		grupo.add(radioBajar);
		
		panelContenedorCheck.add(checkMayuscula);
		panelContenedorCheck.add(checkAjusteAutomatico);
		
		panelContenedorDireccion.add(radioSubir);
		panelContenedorDireccion.add(radioBajar);
		
		panelPrincipal.add(panelContenedorCheck);
		panelPrincipal.add(panelContenedorDireccion);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel con la interfaz de busqueda.
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelBotones() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		panelContenedorControles = new JPanel();
		panelContenedorControles.setOpaque(false);
		panelContenedorControles.setLayout(new GridLayout(4, 1, 5, 5));
		
		btnSiguiente = new JButton("Buscar siguiente");
		btnSiguiente.setFocusPainted(false);
		btnSiguiente.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSiguiente.setActionCommand("buscarSiguiente");
		btnSiguiente.addActionListener(this);
		
		btnReemplazar = new JButton("Reemplazar");
		btnReemplazar.setFocusPainted(false);
		btnReemplazar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReemplazar.setActionCommand("reemplazar");
		btnReemplazar.addActionListener(this);
		
		btnReemplazarTodo = new JButton("Reemplazar todo");
		btnReemplazarTodo.setFocusPainted(false);
		btnReemplazarTodo.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReemplazarTodo.setActionCommand("reemplazarTodo");
		btnReemplazarTodo.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFocusPainted(false);
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);
		
		panelContenedorControles.add(btnSiguiente);
		panelContenedorControles.add(btnReemplazar);
		panelContenedorControles.add(btnReemplazarTodo);
		panelContenedorControles.add(btnCancelar);
		
		panelPrincipal.add(panelContenedorControles);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo devuelve un String con la palabra clave que se buscara.
	 * 
	 * @return devuelve un string con la palabra que se busca
	 */
	public String getPalabraClave() {
		return palabraClave;
	}
	
	/**
	 * Este metodo establece a la variable y a la caja de texto la palabra clave que se buscara.
	 * 
	 * @param palabraClave palabra clave que se buscara
	 */
	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
		textBusqueda.setText(palabraClave);
	}
	
	/**
	 * Este metodo se encarga de mostar u ocultar ciertos componentes
	 * de la interfaz de usuario.
	 * 
	 * @param idVisualizar indice de la vista de la ventana
	 */
	public void visualizar(int idVisualizar) {
		estadoBotones();
		switch(idVisualizar) {
		case 1:
			this.setSize(500, 175);
			this.setTitle("Buscar");
			panelContenedorReemplazar.setVisible(false);
			btnReemplazar.setVisible(false);
			btnReemplazarTodo.setVisible(false);
			panelContenedorDireccion.setVisible(true);
			
			panelContenedorControles.removeAll();
			panelContenedorControles.setLayout(new GridLayout(2, 1, 5, 5));
			
			panelContenedorControles.add(btnSiguiente);
			panelContenedorControles.add(btnCancelar);
			
			break;
		case 2:
			this.setSize(500, 180);
			this.setTitle("Reemplazar");
			panelContenedorReemplazar.setVisible(true);
			btnReemplazar.setVisible(true);
			btnReemplazarTodo.setVisible(true);
			panelContenedorDireccion.setVisible(false);
			
			panelContenedorControles.removeAll();
			panelContenedorControles.setLayout(new GridLayout(4, 1, 5, 5));
			
			panelContenedorControles.add(btnSiguiente);
			panelContenedorControles.add(btnReemplazar);
			panelContenedorControles.add(btnReemplazarTodo);
			panelContenedorControles.add(btnCancelar);
			break;
		}
		this.setVisible(true);
	}
	
	/**
	 * Este metodo establece el valor que contendra el JTextField
	 * 
	 * @param text valor que tendra el JTextField
	 */
	public void setSelectedText(String text) {
		textBusqueda.setText(text);
	}
	
	/**
	 * Este metodo cambia la direccion de busqueda
	 * 
	 * @param isNext la nueva direccion de busqueda
	 */
	public void findWord(boolean isNext) {
		setPalabraClave(textBusqueda.getText());
		
		if(isNext) {
			radioBajar.setSelected(true);
		}
		else {
			radioBajar.setSelected(true);
		}
	}
	
	/**
	 * Este metodo se encarga de buscar la palabra clave anterior a la ubicacion del cursor.
	 */
	public void findPrevious() {
		if(checkMayuscula.isSelected()) {
			cadenaAuxiliar = WithoutANote.TXTPANTALLA.getText();
			palabraClave = textBusqueda.getText();
		}
		else {
			cadenaAuxiliar = WithoutANote.TXTPANTALLA.getText().toLowerCase();
			palabraClave = textBusqueda.getText().toLowerCase();
		}
		
		indiceInicio = WithoutANote.TXTPANTALLA.getCaretPosition() - 1;
		indiceAnterior = cadenaAuxiliar.lastIndexOf(palabraClave, indiceInicio - palabraClave.length());
		if(indiceAnterior != -1) {
			indiceFinal = indiceAnterior + palabraClave.length();
			WithoutANote.TXTPANTALLA.select(indiceAnterior, indiceFinal);
		}
		else {
			if(!checkAjusteAutomatico.isSelected()) {
				JOptionPane.showMessageDialog(this, "No se encontro: "+palabraClave, "Without A Note", JOptionPane.INFORMATION_MESSAGE);
			}
			else if((checkAjusteAutomatico.isSelected())&&(cadenaAuxiliar.indexOf(palabraClave) == -1)) {
				JOptionPane.showMessageDialog(this, "No se encontro: "+palabraClave, "Without A Note", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				indiceInicio = cadenaAuxiliar.lastIndexOf(palabraClave);
				indiceFinal = indiceInicio + palabraClave.length();
				
				WithoutANote.TXTPANTALLA.setCaretPosition(indiceFinal);
				WithoutANote.TXTPANTALLA.select(indiceInicio, indiceFinal);
			}
		}
	}
	
	/**
	 * Este metodo se encarga de buscar la palabra clave siguiente a la ubicacion del cursor.
	 */
	public void findNext() {
		if(checkMayuscula.isSelected()) {
			cadenaAuxiliar = WithoutANote.TXTPANTALLA.getText();
			palabraClave = textBusqueda.getText();
		}
		else {
			cadenaAuxiliar = WithoutANote.TXTPANTALLA.getText().toLowerCase();
			palabraClave = textBusqueda.getText().toLowerCase();
		}
		
		indiceInicio = cadenaAuxiliar.indexOf(palabraClave, WithoutANote.TXTPANTALLA.getSelectionEnd());
		indiceFinal = indiceInicio + palabraClave.length();
		
		if(indiceInicio >= 0) {
			WithoutANote.TXTPANTALLA.setCaretPosition(indiceFinal);
			WithoutANote.TXTPANTALLA.select(indiceInicio, indiceFinal);
		}
		else {
			if(!checkAjusteAutomatico.isSelected()) {
				JOptionPane.showMessageDialog(this, "No se encontro: "+palabraClave, "Without A Note", JOptionPane.INFORMATION_MESSAGE);
			}
			else if((checkAjusteAutomatico.isSelected())&&(cadenaAuxiliar.indexOf(palabraClave) == -1)) {
				JOptionPane.showMessageDialog(this, "No se encontro: "+palabraClave, "Without A Note", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				indiceInicio = cadenaAuxiliar.indexOf(palabraClave);
				indiceFinal = indiceInicio + palabraClave.length();
				
				WithoutANote.TXTPANTALLA.setCaretPosition(indiceFinal);
				WithoutANote.TXTPANTALLA.select(indiceInicio, indiceFinal);
			}
		}
	}
	
	/**
	 * Este metodo se encarga de reemplazar la coincidencia encontrada con otro texto
	 */
	private void reemplazar() {
		palabraReemplazar = "";
		if(textReemplazar.getText() != null) {
			palabraReemplazar = textReemplazar.getText();
		}
		
		palabraClave = textBusqueda.getText();
		
		cadenaAuxiliar = WithoutANote.TXTPANTALLA.getText();
		
		if(WithoutANote.TXTPANTALLA.getSelectedText() != null) {
			WithoutANote.TXTPANTALLA.replaceSelection(palabraReemplazar);
			WithoutANote.WITHOUTMANAGER.setModifiedFile(true);
		}
	}
	
	/**
	 * Este metodo se encarga de reemplazar todas las coincidencias de la palabra clave con otro texto
	 */
	private void remplazarTodo() {
		palabraReemplazar = "";
		if(textReemplazar.getText() != null) {
			palabraReemplazar = textReemplazar.getText();
		}
		palabraClave = textBusqueda.getText();
		
		cadenaAuxiliar = WithoutANote.TXTPANTALLA.getText();
		
		WithoutANote.TXTPANTALLA.setText("");
		WithoutANote.TXTPANTALLA.setText(cadenaAuxiliar.replaceAll(palabraClave, palabraReemplazar));
		WithoutANote.WITHOUTMANAGER.setModifiedFile(true);
	}
	
	/**
	 * Este metodo se encarga de habilitar o desabilitar los botones. si la caja de texto
	 * de la palabra clase esta vacio.
	 */
	private void estadoBotones() {
		if(textBusqueda.getText().length() > 0) {
			btnSiguiente.setEnabled(true);
			btnReemplazar.setEnabled(true);
			btnReemplazarTodo.setEnabled(true);
		}
		else {
			btnSiguiente.setEnabled(false);
			btnReemplazar.setEnabled(false);
			btnReemplazarTodo.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "buscarSiguiente":
			if(radioSubir.isSelected()) {
				findPrevious();
			}
			else if(radioBajar.isSelected()) {
				findNext();
			}
			break;
		case "reemplazar":
			reemplazar();
			break;
		case "reemplazarTodo":
			remplazarTodo();
			break;
		case "cancelar":
			this.setVisible(false);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		estadoBotones();
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			findNext();
			break;
		case KeyEvent.VK_ESCAPE:
			this.setVisible(false);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		estadoBotones();
	}
}