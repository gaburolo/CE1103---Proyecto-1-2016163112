package Clases;

import java.util.Random;
import ListasCirculares.listaCircular;
import pantalla.Sprite;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */
public class ClaseD {
	//Variables
	private listaCircular<Sprite> hilera;
	/**
	 * Constructor de la Clase
	 */
	public ClaseD() {
		
	}
	/**
	 * Crea una lista Circular, con enemigso de vidas aleatorias
	 * @return retorna una lsita de enemigos con vidas aleatorias entre 1-3
	 */
	private listaCircular<Sprite> hilera(){
			Random n= new Random();
			int Numvida=1+n.nextInt(2);
			Sprite kuramon1 = new Sprite("imagenes/kuramon.png",50,50,Numvida);
			Numvida=1+n.nextInt(2);
			Sprite kuramon2 = new Sprite("imagenes/kuramon1.png",50,50,Numvida);
			Numvida=1+n.nextInt(2);
			Sprite kuramon3 = new Sprite("imagenes/kuramon2.png",50,50,Numvida);
			Numvida=1+n.nextInt(2);
			Sprite kuramon4 = new Sprite("imagenes/kuramon3.png",50,50,Numvida);
			Numvida=1+n.nextInt(2);
			Sprite kuramon5 = new Sprite("imagenes/kuramon4.png",50,50,Numvida);
			Numvida=1+n.nextInt(2);
			Sprite kuramon6 = new Sprite("imagenes/kuramon5.png",50,50,Numvida);
			Numvida=1+n.nextInt(2);
			Sprite kuramon7 = new Sprite("imagenes/kuramon.png",50,50,Numvida);
			;
			listaCircular<Sprite> enemilist = new listaCircular<Sprite>();
			enemilist.setNombreClase("ClaseD");
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
	 * Agrega un jefe de forma aleatoria en la hilera
	 * @return retorna la lista de enemigos
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
	
	//get de variable
	public listaCircular<Sprite> getHilera() {
		return hilera;
	}
	
	
}
