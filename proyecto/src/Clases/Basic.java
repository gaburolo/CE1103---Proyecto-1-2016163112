package Clases;

import listas.lista;
import pantalla.Sprite;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */
public class Basic {
	
	/**
	 * Constructor de la Clase
	 */
	public Basic() {
		
		
	}
	/**
	 * Lista de enemigos Basic solo tiene enemigos de una vida
	 * @return Lista de enemigos Normales
	 */
	public lista<Sprite> hilera() {
		Sprite kuramon1 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon2 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon3 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon4 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon5 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon6 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon7 = new Sprite("imagenes/kuramon.png",50,50,1);
		lista<Sprite> kuramonlist = new lista<Sprite>();
		kuramonlist.AgregarFinal(kuramon1);
		kuramonlist.AgregarFinal(kuramon2);
		kuramonlist.AgregarFinal(kuramon3);
		kuramonlist.AgregarFinal(kuramon4);
		kuramonlist.AgregarFinal(kuramon5);
		kuramonlist.AgregarFinal(kuramon6);
		kuramonlist.AgregarFinal(kuramon7);
		return kuramonlist;
	}
	
}
