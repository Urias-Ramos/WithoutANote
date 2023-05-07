package app.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import app.without.WithoutANote;

/**
 * Esta clase representa una ventana de dialogo, que muestra la interfaz grafica,
 * para cambiar el tipo de fuente, el estilo y el tamaño del area de texto.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class DialogFuente extends JDialog implements ActionListener, ItemListener {
	private JTextArea textArea = new JTextArea();
	
	private JComboBox<Object> fontBox, fontStyle;
	private JComboBox<Integer> fontSize;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param ventana ventana principal de la aplicacion
	 */
	public DialogFuente(JFrame ventana) {
		this.setModal(true);
		this.setSize(525, 425);
		this.setTitle("Fuente");
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
	 * Este metodo crea el panel con e titulo de la ventana: Perzonalizar
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelTitulo() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JLabel lblTitulo = new JLabel("Perzonalizar");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
		
		panelPrincipal.add(lblTitulo, BorderLayout.CENTER);
		panelPrincipal.add(new JSeparator(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel central donde se visualiza la pantalla de previsualizacion
	 * y otros controles de diseño.
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelCentro() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		textArea.setFont(WithoutANote.FUENTE_PRINCIPAL);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setText("Lorem ipsum dolor sit amet consectetur adipisicing elit. Saepe, necessitatibus! Facilis, adipisci! Officiis totam, consequatur, accusantium nihil architecto recusandae ex culpa perferendis maxime commodi voluptatibus cupiditate, nisi accusamus delectus. Consequuntur.\r\n"
				+ "Nemo doloremque nam odio ex error ut esse qui quidem officia quibusdam asperiores consequatur facere natus accusantium repudiandae eveniet inventore laboriosam placeat reprehenderit corporis, non repellat! Quibusdam aperiam perferendis ab!\r\n"
				+ "Tenetur provident odio officiis soluta sunt sapiente voluptas unde facilis dignissimos ab cum cupiditate, id quo adipisci ea est voluptatum qui nulla, magni hic ipsum. Sunt recusandae magni nostrum aliquam!\r\n"
				+ "Rerum officia nam architecto officiis iusto expedita consectetur beatae ducimus quaerat, deserunt illo soluta quasi distinctio ex, id labore qui facilis. In doloribus voluptate quod, eveniet laboriosam facilis cum. Ut.\r\n"
				+ "Natus nam itaque voluptatum distinctio cum ipsam, asperiores ducimus debitis. Nam vero mollitia exercitationem atque nobis impedit, esse aperiam eius fuga, quia architecto eum perspiciatis totam! Sunt laudantium sequi possimus!");
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panelFont = new JPanel();
		panelFont.setOpaque(false);
		panelFont.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilyNames = ge.getAvailableFontFamilyNames();
        fontBox = new JComboBox<Object>(fontFamilyNames);
        fontBox.setSelectedIndex(WithoutANote.PROFILE.getIndexFontFamilyName());
        fontBox.addItemListener(this);
        
        String[] style = {"Normal", "Bold"};
        fontStyle = new JComboBox<Object>(style);
        fontStyle.setSelectedIndex(WithoutANote.PROFILE.getIndexFontStyle());
        fontStyle.addItemListener(this);
        
        Integer[] sizeFont = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        fontSize = new JComboBox<Integer>(sizeFont);
        fontSize.setSelectedIndex(WithoutANote.PROFILE.getIndexFontSize());
        fontSize.addItemListener(this);
    
        panelFont.add(fontBox);
        panelFont.add(fontStyle);
        panelFont.add(fontSize);
        
		panelPrincipal.add(scroll, BorderLayout.CENTER);
		panelPrincipal.add(panelFont, BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea el panel inferior de la ventana donde se encuentra el boton aceptar y cancelar.
	 * 
	 * @return devuelve un JPanel
	 */
	private JPanel panelBotones() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnAceptar = new JButton("Guardar");
		btnAceptar.setFocusPainted(false);
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAceptar.setActionCommand("aceptar");
		btnAceptar.addActionListener(this);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFocusPainted(false);
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(this);
		panelPrincipal.add(btnAceptar);
		panelPrincipal.add(btnCancelar);
		
		return panelPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "aceptar":
			WithoutANote.FUENTE_PRINCIPAL = textArea.getFont();
			
			WithoutANote.PROFILE.setFont(WithoutANote.FUENTE_PRINCIPAL);
			WithoutANote.PROFILE.setZoomFont(new Font(WithoutANote.FUENTE_PRINCIPAL.getFontName(), WithoutANote.FUENTE_PRINCIPAL.getStyle(), WithoutANote.PROFILE.getZoomFont().getSize()));
			
			WithoutANote.PROFILE.setIndexFontFamilyName(fontBox.getSelectedIndex());
			WithoutANote.PROFILE.setIndexFontStyle(fontStyle.getSelectedIndex());
			WithoutANote.PROFILE.setIndexFontSize(fontSize.getSelectedIndex());
			
			WithoutANote.TXTPANTALLA.setFont(WithoutANote.PROFILE.getZoomFont());
			
			setVisible(false);
			break;
		case "cancelar":
			textArea.setFont(WithoutANote.FUENTE_PRINCIPAL);
			
			fontBox.setSelectedIndex(WithoutANote.PROFILE.getIndexFontFamilyName());
			fontStyle.setSelectedIndex(WithoutANote.PROFILE.getIndexFontStyle());
			fontSize.setSelectedIndex(WithoutANote.PROFILE.getIndexFontSize());
			
			setVisible(false);
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == fontBox) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				textArea.setFont(new Font(fontBox.getSelectedItem().toString(), fontStyle.getSelectedIndex(), Integer.parseInt(fontSize.getSelectedItem().toString())));
            }
		}
		else if(e.getSource() == fontStyle) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				textArea.setFont(new Font(fontBox.getSelectedItem().toString(), fontStyle.getSelectedIndex(), Integer.parseInt(fontSize.getSelectedItem().toString())));
            }
		}
		else if(e.getSource() == fontSize) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
                textArea.setFont(new Font(fontBox.getSelectedItem().toString(), fontStyle.getSelectedIndex(), Integer.parseInt(fontSize.getSelectedItem().toString())));
            }
		}
	}
}