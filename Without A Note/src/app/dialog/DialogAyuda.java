package app.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Esta clase representa una ventana de dialogo que muestra el manual de usuario.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class DialogAyuda extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param ventana ventana principal del programa
	 */
	public DialogAyuda(JFrame ventana) {
		super(ventana);
		this.setModal(true);
		this.setSize(600, 400);
		this.setTitle("Ayuda");
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
		
		panelPrincipal.add(panelTitulo(), BorderLayout.NORTH);
		panelPrincipal.add(panelCentro(), BorderLayout.CENTER);
		panelPrincipal.add(panelBotones(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel con e titulo de la ventana: CENTRO DE AYUDA
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelTitulo() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JLabel lblTitulo = new JLabel("CENTRO DE AYUDA");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 42));
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panelPrincipal.add(lblTitulo, BorderLayout.CENTER);
		panelPrincipal.add(new JSeparator(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel central donde se visualiza el archivo de manual de usuario
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelCentro() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JTextPane textPane = new JTextPane();
		textPane.setOpaque(false);
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		textPane.setEditorKit(new HTMLEditorKit());
		
		JScrollPane desplazadorPantalla = new JScrollPane(textPane);
		desplazadorPantalla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//se lee el fichero .html que esta dentro del proyecto y se muestra en el textPane
		try {
			BufferedReader buffered = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("html/help.html"), "UTF-8"));
			textPane.read(new BufferedReader(buffered), null);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, e.toString(), "Without A Note", JOptionPane.ERROR_MESSAGE);
		}
		
		panelPrincipal.add(desplazadorPantalla, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel inferior de la ventana donde se encuentra el boton aceptar.
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelBotones() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFocusPainted(false);
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAceptar.setActionCommand("accept");
		btnAceptar.addActionListener(this);
		
		panelPrincipal.add(btnAceptar);
		
		return panelPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "accept":
			setVisible(false);
			break;
		}
	}
}