package app.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

/**
 * Esta clase representa una ventana de dialogo que muestra detalles de la aplicacion, como la version.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class DialogAcercaDe extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param ventana ventana principal de la aplicacion.
	 */
	public DialogAcercaDe(JFrame ventana) {
		super(ventana);
		this.setModal(true);
		this.setSize(475, 428);
		this.setTitle("Acerca de Without a note");
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
	 * Este metodo crea el panel con e titulo de la ventana: Without a note
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelTitulo() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JLabel lblTitulo = new JLabel("Without a note");
		lblTitulo.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icon/icon_app.png")));
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 42));
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panelPrincipal.add(lblTitulo, BorderLayout.CENTER);
		panelPrincipal.add(new JSeparator(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel central donde se visualiza la informacion de la apicacion.
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelCentro() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JLabel lblInfo = new JLabel();
		lblInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblInfo.setFont(new Font("Arial", Font.PLAIN, 11));
		lblInfo.setText("<HTML>Microsoft Windows<BR>\r\n"
				+ "Version 22H2 (Compilacion SO 19045.2364)<BR>\r\n"
				+ "@Microsoft Corporation. Todos los derechos reservados.<BR>\r\n"
				+ "<BR>\r\n"
				+ "El sistema operativo windows 10 Home Single Language y sus interfaz de<BR>\r\n"
				+ "usuario estan protegidos por las leyes de marca comercial y otros derechos<BR>\r\n"
				+ "de propiedad intelectual actuales y pendientes en los Estados Unidos y<BR>\r\n"
				+ "otros paises o region.<BR>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "<BR>\r\n"
				+ "La licencia de este producto se concede de acuerdo con los<BR>\r\n"
				+ "Terminos de licencia del sotfware de Microsoft a:<BR><BR>\r\n"
				+ "\r\n"+System.getProperty("user.name")
				+ "<BR></HTML>");
		
		panelPrincipal.add(lblInfo, BorderLayout.CENTER);
		
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