package Clases;

import java.util.Random;
import listasD.listaD;
import pantalla.Sprite;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */
public class ClaseB {
	//Variables
	private listaD<Sprite> hilera;
	/**
	 * Constructor de la clase
	 */
	public ClaseB(){
		
		
	}
	/**
	 * Crea una lista Doblemente Enlazada con enemigos normales
	 * @return Lista con enemigos de una Vida
	 */
	private listaD<Sprite> hilera(){
		Sprite kuramon1 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon2 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon3 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon4 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon5 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon6 = new Sprite("imagenes/kuramon.png",50,50,1);
		Sprite kuramon7 = new Sprite("imagenes/kuramon.png",50,50,1);
		listaD<Sprite> enemilistD = new listaD<>();
		enemilistD.AgregarFinal(kuramon1);
		enemilistD.AgregarFinal(kuramon2);
		enemilistD.AgregarFinal(kuramon3);
		
		enemilistD.AgregarFinal(kuramon4);
		enemilistD.AgregarFinal(kuramon5);
		enemilistD.AgregarFinal(kuramon6);
		enemilistD.AgregarFinal(kuramon7);
		return enemilistD;
	}
	/**
	 * Agrega un jefe aleatorio con vida entre 2-5
	 * @return Lista con enemigos normales y el jefe
	 */
	public listaD<Sprite> AgregarJefe(){
		Random n= new Random();
		int num=n.nextInt((hilera().getTamanio()-1)-0);
		int Numvida=2+n.nextInt(4);
		Sprite keramon = new Sprite("imagenes/keramon.png", 62, 46, Numvida);
		
		hilera=hilera();
		hilera.EliminarPos(num);
		hilera.AgregarEnPosicion(num, keramon);
		
		return hilera;
	}
}
