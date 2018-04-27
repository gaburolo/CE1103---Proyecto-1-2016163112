package listasD;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */
public class NodoD<T> {
	//Variables
	private T dato;
	private NodoD<T> siguiente;
	private NodoD<T> anterior;
	
	
	/**
	 * Contructor de la clase NodoD cuando la lista esta vacia
	 * @param a dato a agregar
	 */
	public NodoD(T a) {
		this(a,null,null);
	}
	/**
	 * Contructor de la clase NodoD cuando la lista ya tiene elementps
	 * @param a Dato a agregar
	 * @param ant Dato anterior 
	 * @param sig Dato siguiente
	 */
	public NodoD(T a,NodoD<T> ant,NodoD<T> sig) {
		this.dato=a;
		this.siguiente=sig;
		this.anterior=ant;
		
	}
	
	
	//Metodos get y set para los atributos

	
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public NodoD<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoD<T> siguiente) {
		this.siguiente = siguiente;
	}

	public NodoD<T> getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoD<T> anterior) {
		this.anterior = anterior;
	}

	
	

}
