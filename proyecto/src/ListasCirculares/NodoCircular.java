package ListasCirculares;


/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */

public class NodoCircular<T> {
	//Variables
	private T dato;
	private NodoCircular<T> siguiente;
	
	
	

	/**
	 *Contructor de NodoCircular<T> 
	 */
	
	public void NodoCircular() {
		this.dato=dato;
		this.siguiente=null;
	}
	
	//Metodos get y set para los atributos
	
	public void setDato(T dato) {
		this.dato = dato;
	}
	public T getDato() {
		return dato;
	}
	public NodoCircular<T> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoCircular<T> siguiente) {
		this.siguiente = siguiente;
	}
	
	
}
