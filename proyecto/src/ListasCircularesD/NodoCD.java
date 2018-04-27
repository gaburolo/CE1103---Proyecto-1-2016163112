package ListasCircularesD;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */

public class NodoCD<T> {
	//Variables 
	private T dato;
	private NodoCD<T> siguiente, anterior;
	/**
	 * Constructor de la clase Dato
	 * 
	 */
	public NodoCD() {
		this.dato=dato;
		this.siguiente=null;
		this.anterior=null;
	}
	
	
	
	//get y set de las variables
	public T getDato() {
		return dato;
	}
	public void setDato(T dato) {
		this.dato = dato;
	}
	public NodoCD<T> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoCD<T> siguiente) {
		this.siguiente = siguiente;
	}
	public NodoCD<T> getAnterior() {
		return anterior;
	}
	public void setAnterior(NodoCD<T> anterior) {
		this.anterior = anterior;
	}
	
	
}
