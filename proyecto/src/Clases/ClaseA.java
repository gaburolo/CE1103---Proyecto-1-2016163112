package Clases;

import java.util.Random;
import listas.lista;
import pantalla.Sprite;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */

public class ClaseA {
	//Variables
	
	private lista<Sprite> hilera;
	/**
	 * Contructor
	 */
	public ClaseA() {
		
	}
	/**
	 * Creacion de lista de enemigos de tipo CLASE A 
	 * @return lista de enemigo Clase Normales
	 */
	private lista<Sprite> hilera() {
		Sprite kuramon1 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon2 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon3 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon4 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon5 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon6 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon7 = new Sprite("imagenes/kuramon.png",50,50,1);
		lista<Sprite> enemilist = new lista<Sprite>();
		
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
	 * Agrega a la Clase A un Jefe en una posicion Aleatoria 
	 * @return Lista con los enemigos normales y el jefe
	 */
	public lista<Sprite> AgregarJefe(){
		Random n= new Random();
		
		int num=1+n.nextInt((hilera().getTamanio()-1)-0);
		int Numvida=2+n.nextInt(4);
		Sprite keramon = new Sprite("imagenes/keramon.png",62,46,Numvida);
		hilera=hilera();
		hilera.EliminarPos(num);
		hilera.AgregarEnPosicion(num, keramon);
		
		return hilera;
	}
}
