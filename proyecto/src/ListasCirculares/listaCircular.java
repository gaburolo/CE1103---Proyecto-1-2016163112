package ListasCirculares;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */

public class listaCircular<T> {
	//Nodo cabeza y nodo final
	private NodoCircular<T> inicio,fin;
	//Variable para registrar el tamaño de la lista
	private int tamanio;
	private String NombreClase;
	
	

	/**
	 * Constructor lista Circular simple
	 */
	public listaCircular() {
		inicio=null;
		fin=null;
		tamanio=0;
	}
	
	/**
	 * Agrega un dato al final de la lista
	 * @param dato el cual se desea agregar a la lista
	 */
	public void AgregarFinal(T dato) {
		NodoCircular<T> nuevo = new NodoCircular<T>();
		nuevo.setDato(dato);
		if(Vacia()) {
			inicio=nuevo;
			fin=nuevo;
			fin.setSiguiente(inicio);
		}else {
			fin.setSiguiente(nuevo);
			nuevo.setSiguiente(inicio);
			fin=nuevo;
		}tamanio++;
	}
	
	/**
	 * Agrega el dato al inicio de la lista
	 * @param dato que se desea agregar a la lista
	 */
	public void AgregarInicio(T dato) {
		NodoCircular<T> nuevo = new NodoCircular<T>();
		nuevo.setDato(dato);
		if(Vacia()) {
			inicio=nuevo;
			fin=nuevo;
			fin.setSiguiente(inicio);
		}else {
			nuevo.setSiguiente(inicio);
			inicio=nuevo;
			fin.setSiguiente(inicio);
		}tamanio++;
	}
	/**
	 * Metodo para agregar un dato en cualquier posicion de la lista
	 * @param posicion en la cual se desea agregar el dato
	 * @param dato a agregar en la lista
	 */
	public void AgregarEnPosicion(int posicion, T dato) {
		if(posicion>=0 && posicion <=getTamanio()) {
			NodoCircular<T> nuevo = new NodoCircular<T>();
			nuevo.setDato(dato);
			if(posicion==0) {
				AgregarInicio(dato);
			}else {
				if(posicion==getTamanio()) {
					AgregarFinal(dato);
					
				}else {
					NodoCircular<T> copia = inicio;
					for (int i = 0; i < (posicion-1); i++) {
						copia = copia.getSiguiente();
					}NodoCircular<T> siguiente = copia.getSiguiente();
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
		NodoCircular<T> copia=inicio;
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
		if(!Vacia()) {
			if(posicion>=0 && posicion<tamanio) {
				if(posicion==0) {
					inicio=inicio.getSiguiente();
					fin.setSiguiente(inicio);
				}else {
					NodoCircular<T> copia = inicio;
					for (int i = 0; i < (posicion-1); i++) {
						copia=copia.getSiguiente();
					}if(copia.getSiguiente()==fin) {
						copia.setSiguiente(inicio);
						fin=copia;
					}else {
						NodoCircular<T> siguiente = copia.getSiguiente();
						copia.setSiguiente(siguiente.getSiguiente());
					}
				}tamanio--;
			}
		}
	}
	
	/**
	 * Elimina toda la lista
	 */
	public void eliminar() {
		inicio=null;
		fin=null;
		tamanio=0;
	}
	
	/**
	 * Imprime los elementos de la lista
	 */
	public void listar(){
        
        if (!Vacia()) {            
            NodoCircular<T> aux = inicio;            
            int i = 0;            
            while(i != getTamanio()){                
                System.out.print(i + ".[ " + aux.getDato() + " ]" + " ->  ");                
                aux = aux.getSiguiente();                
                i++;
            }
        }
    }
	
	public void intercambiar (int pos1, int pos2) {
        if (pos1 <= getTamanio ()-1 && pos2 <= getTamanio()-1 )    {
            NodoCircular<T> copia1 = inicio;
            for (int f = 1 ; f <= pos1 ; f++)
                copia1 = copia1.getSiguiente();
            NodoCircular<T> copia2 = inicio;
            for (int f = 1 ; f <= pos2 ; f++)
                copia2 = copia2.getSiguiente();
            T aux = copia1.getDato();
            copia1.setDato(copia2.getDato()); 
            copia2.setDato(aux);;
        }else {
        	throw new IllegalArgumentException("Posicion no exite");
        }
    }
	
	
	
	/**
	 * Comprobar que la lista esta vacia
	 * @return inicio==null si la lista esta vacia
	 */
	public boolean Vacia() {
		return this.inicio==null;
	}
	
	//Get y Set de las variables
	public NodoCircular<T> getInicio() {
		return inicio;
	}
	
	public int getTamanio() {
		return tamanio;
	}
	public String getNombreClase() {
		return NombreClase;
	}

	public void setNombreClase(String nombreClase) {
		NombreClase = nombreClase;
	}

}
