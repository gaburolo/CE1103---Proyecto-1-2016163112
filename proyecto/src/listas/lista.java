package listas;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */
public class lista<T> {
	//Nodo cabeza
	private Nodo<T> inicio;
	//Variable para registrar el tamaño de la lista
	private int tamanio;

	/**
	 * Constructor lista simple
	 */
	public lista() {
		inicio=null;
		tamanio=0;		
	}
	
	/**
	 * Agrega un dato al final de la lista
	 * @param dato el cual se desea agregar a la lista
	 */
	public void AgregarFinal(T dato) {
		Nodo<T> nuevo = new Nodo<T>();
		nuevo.setDato(dato);
		if(this.Vacia()) {
			inicio=nuevo;
			
		}else {
			Nodo<T> copia = inicio;
			while(copia.getSiguiente()!=null) {
				copia=copia.getSiguiente();
			}
			copia.setSiguiente(nuevo);
			
		}tamanio++;
	}
	
	/**
	 * Agrega el dato al inicio de la lista
	 * @param dato que se desea agregar a la lista
	 */
	public void AgregarInicio(T dato) {
		Nodo<T> nuevo = new Nodo<T>();
		nuevo.setDato(dato);
		if(Vacia()) {
			inicio=nuevo;
		}else {
			nuevo.setSiguiente(nuevo);
			inicio=nuevo;
		}tamanio++;
	}
	
	
	/**
	 * Metodo para agregar un dato en cualquier posicion de la lista
	 * @param posicion en la cual se desea agregar el dato
	 * @param dato a agregar en la lista
	 */
	public void AgregarEnPosicion(int posicion, T dato) {
		
		if(posicion>=0  && posicion<=tamanio) {
			
			Nodo<T> nuevo = new Nodo<T>();
			nuevo.setDato(dato);
			if(posicion==0) {
				
				AgregarInicio(dato);
			}else {
				
				if(posicion==tamanio) {
					AgregarFinal(dato);
				}else {
					
					Nodo<T> copia=this.inicio;
					for(int i=0; i<(posicion-1);i++) {
						
						copia=copia.getSiguiente();
					}
					
					Nodo<T> siguiente=copia.getSiguiente();
					
					copia.setSiguiente(nuevo);
					nuevo.setSiguiente(siguiente);
					tamanio++;
				}
			}
		}
			
		
	}
	
	/**
	 * Metodo para obtener el dato de una posicion especifica
	 * @param posicion de la cual se desea obtener los datos
	 * @return el valor desea, o error en caso de que la posicion no exista
	 */
	public T getDatoPos(int posicion) {
		Nodo<T> copia=this.inicio;
		if(!Vacia()) {
			if(posicion<getTamanio()) {
				while(copia!=null && posicion!=0) {
					
					copia=copia.getSiguiente();
					posicion--;
				}return copia.getDato();
			
			
			}throw new IndexOutOfBoundsException("Posicion no exite");
		
		}throw new  IndexOutOfBoundsException("Lista Vacia");
	}
	
	/**
	 * Eliminar un dato en una posicion espeficica
	 * @param posicion en la cual se desea eliminar el dato
	 */
	public void EliminarPos(int posicion) {
		if(!this.Vacia()) {
			Nodo<T> copia=this.inicio;
			Nodo<T> anterior= null;
			tamanio=tamanio-1;
			
			while(copia!=null && posicion>=0) {
				if(posicion ==0) {
					if(anterior==null) {
						inicio = inicio.getSiguiente();
					}else {
						anterior.setSiguiente(copia.getSiguiente());
					}return;
				}anterior = copia;
				copia=copia.getSiguiente();
				posicion--;
			}throw new IndexOutOfBoundsException("Posicion incorrecta");
		}
	}
	
	
	/**
	 * Elimina toda la lista
	 */
	
	public void eliminar() {
			inicio=null;
			tamanio=0;
	}
	
	
	
	
	
	/**
	 * Imprime los elementos de la lista
	 */
	public void listar(){
	        // Verifica si la lista contiene elementoa.
	        if (!Vacia()) {
	            // Crea una copia de la lista.
	            Nodo<T> aux = inicio;
	            // Posicion de los elementos de la lista.
	            int i = 0;
	            // Recorre la lista hasta el final.
	            while(aux != null){
	                // Imprime en pantalla el valor del Nodo<T>.
	                System.out.print(i + ".[ " + aux.getDato() + " ]" + " ->  ");
	                // Avanza al siguiente Nodo<T>.
	                aux = aux.getSiguiente();
	                // Incrementa el contador de la posión.
	                i++;
	            }
	        }
	    }
	
	
	 


	/**
	 * Comprobar que la lista esta vacia
	 * @return inicio==null si la lista esta vacia
	 */
	public boolean Vacia() {
		return this.inicio==null;
	}
	
	//Get de las variables
	public Nodo<T> getInicio() {
		return inicio;
	}
	
	public int getTamanio() {
		return tamanio;
	}



}
