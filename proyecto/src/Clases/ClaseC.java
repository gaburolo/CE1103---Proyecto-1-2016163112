package Clases;

import java.util.Random;
import ListasCirculares.listaCircular;
import pantalla.Sprite;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */
public class ClaseC {
	//variables
	private listaCircular<Sprite> hilera;
	/**
	 * Constructor de la Clase
	 */
	public ClaseC() {
		
	}
	/**
	 * Crea una lista Circular simple con enemigos normales
	 * @return Lista de enemigos normales
	 */
	private listaCircular<Sprite> hilera(){
		
	
			Sprite kuramon1 = new Sprite("imagenes/kuramon.png",50,50,1);
			Sprite kuramon2 = new Sprite("imagenes/kuramon.png",50,50,1);
			Sprite kuramon3 = new Sprite("imagenes/kuramon.png",50,50,1);
			Sprite kuramon4 = new Sprite("imagenes/kuramon.png",50,50,1);
			Sprite kuramon5 = new Sprite("imagenes/kuramon.png",50,50,1);
			Sprite kuramon6 = new Sprite("imagenes/kuramon.png",50,50,1);
			Sprite kuramon7 = new Sprite("imagenes/kuramon.png",50,50,1);
			listaCircular<Sprite> enemilist = new listaCircular<Sprite>();
			enemilist.setNombreClase("ClaseC");
			enemilist.AgregarFinal(kuramon1);
			enemilist.AgregarFinal(kuramon2);
			enemilist.AgregarFinal(kuramon3);
			enemilist.AgregarFinal(kuramon4);
			enemilist.AgregarFinal(kuramon5);
			enemilist.AgregarFinal(kuramon6);
			enemilist.AgregarFinal(kuramon7);
			return enemilist;
	}
	
	/**
	 * Agrega un jefe en posicion aleatoria con vida aleatoria entre 2-5
	 * @return Lista con enemigos normales y un jefe aleatorio
	 */
	public listaCircular<Sprite> AgregarJefe(){
		Random n= new Random();
		int num=n.nextInt((hilera().getTamanio()-1)-0);
		int Numvida=2+n.nextInt(4);
		Sprite keramon = new Sprite("imagenes/keramon.png",62,46,Numvida);
		hilera=hilera();
		hilera.EliminarPos(num);
		hilera.AgregarEnPosicion(num, keramon);
		
		return hilera;
	}
	
}
