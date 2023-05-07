package app.loadsave;

import java.awt.Font;
import java.io.Serializable;

/**
 * 
 * Esta clase es la que se encarga de guardar los datos de la aplicacion
 * que luego son guardados en un archivo.
 * 
 * @author Urias Ramos
 * @version 03-02-2023
 *
 */
public class Profile implements Serializable {
	
	//atributos de la aplicacion
	private int indexFontFamilyName;
	private int indexFontStyle;
	private int indexFontSize;
	
	private boolean statusBar;
	private int zoom;
	private boolean lineWrap;
	
	//atributos de fuente
	private Font font;
	private Font zoomFont;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase no requiere parametros, pero es necesario utilizarlo
	 * para crear una instancia de la clase
	 */
	public Profile() {
		//valores por defecto
		setIndexFontFamilyName(58);//por defecto Consolas.
		setIndexFontStyle(0);//por defecto texto plano normal
		setIndexFontSize(4);//por defecto 12
		
		setStatusBar(true);
		setZoom(100);
		setLineWrap(true);
		
		setFont(new Font("Consolas", Font.PLAIN, 14));
		setZoomFont(new Font("Consolas", Font.PLAIN, 14));
	}
	
	/**
	 * Este metodo devuelve el valor del atributo indexFontFamilyName
	 * 
	 * @return devuelve el valor del atributo indexFontFamilyName
	 */
	public int getIndexFontFamilyName() {
		return indexFontFamilyName;
	}
	
	/**
	 * Este metodo cambia el valor del atributo indexFontFamilyName
	 * 
	 * @param indexFontFamilyName nuevo valor para el atributo
	 */
	public void setIndexFontFamilyName(int indexFontFamilyName) {
		this.indexFontFamilyName = indexFontFamilyName;
	}
	
	/**
	 * Este metodo devuelve el valor del atributo indexFontStyle
	 * 
	 * @return devuelve el valor del atributo indexFontStyle
	 */
	public int getIndexFontStyle() {
		return indexFontStyle;
	}
	
	/**
	 * Este metodo cambia el valor del atributo indexFontStyle
	 * 
	 * @param indexFontStyle nuevo valor para el atributo
	 */
	public void setIndexFontStyle(int indexFontStyle) {
		this.indexFontStyle = indexFontStyle;
	}
	
	/**
	 * Devuelve el valor del atributo indexFontSize
	 * 
	 * @return devuelve el valor del atributo
	 */
	public int getIndexFontSize() {
		return indexFontSize;
	}
	
	/**
	 *Este metodo cambia el valor del atributo indexFontSize
	 * 
	 * @param indexFontSize nuevo valor para el atributo
	 */
	public void setIndexFontSize(int indexFontSize) {
		this.indexFontSize = indexFontSize;
	}
	
	
	/**
	 * 
	 * Este metodo devuelve el valor del satusBar
	 * 
	 * @return el valor del atributo statusBar
	 */
	public boolean isStatusBar() {
		return statusBar;
	}
	
	/**
	 * Este metodo modifica el atributo statusBar
	 * 
	 * @param statusBar nuevo valor para el atributo statusBar
	 */
	public void setStatusBar(boolean statusBar) {
		this.statusBar = statusBar;
	}
	
	/**
	 * Este metodo devuelve el valor actual del zoom
	 * 
	 * @return el valor actual del zoom
	 */
	public int getZoom() {
		return zoom;
	}
	
	/**
	 * Este metodo cambia el valor del atributo zoom
	 * 
	 * @param zoom valor nuevo para el atributo zoom
	 */
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	
	/**
	 * Este metodo devuelve el valor del atributo lineWrap
	 * 
	 * @return el valor del atributo
	 */
	public boolean isLineWrap() {
		return lineWrap;
	}
	
	/**
	 * Este metodo permite modificar el valor del atributo lineWrap
	 * 
	 * @param lineWrap nuevo valor para el atributo
	 */
	public void setLineWrap(boolean lineWrap) {
		this.lineWrap = lineWrap;
	}
	
	/**
	 * Este metodo devuelve el valor del atributo font
	 * 
	 * @return devuelve el atributo font
	 */
	public Font getFont() {
		return font;
	}
	
	/**
	 * Este metodo cambia el valor del atributo font
	 * 
	 * @param font valor de la nueva fuente
	 */
	public void setFont(Font font) {
		this.font = font;
	}
	
	/**
	 * Este metodo devuelve el valor del atributo zoomFont
	 * 
	 * @return devuelve el zoom actual del area de texto
	 */
	public Font getZoomFont() {
		return zoomFont;
	}
	
	/**
	 * Este metodo se encarga de cambiar el valor del atributo zoomFont
	 * 
	 * @param zoomFont zoom actual del area de texto
	 */
	public void setZoomFont(Font zoomFont) {
		this.zoomFont = zoomFont;
	}
}