package listas;


/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */
public class Nodo<T> {
	//Variables
	private T dato;
	private Nodo<T> siguiente;
	
	
	

	/**
	 *Contructor de Nodo<T> 
	 */
	
	public void Nodo() {
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
	public Nodo<T> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
	
	
}
