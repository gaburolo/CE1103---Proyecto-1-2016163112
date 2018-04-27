package listasD;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */

public class listaD<T> {
	//Nodo cabeza y nodo final
	private NodoD<T> inicio,fin;
	
	//Variable para registrar el tamaño de la lista
	private int tamanio;
	
	/**
	 * Constructor lista doble
	 */
	public listaD() {
		this.inicio=null;
		this.fin = null;
		this.tamanio=0;
	}
	
	/**
	 * Agrega un dato al final de la lista
	 * @param dato el cual se desea agregar a la lista
	 */
	public void AgregarFinal(T dato) {
		if(!Vacia()) {
			NodoD<T> nuevo = new NodoD<T>(dato,fin,null);
			
			fin.setSiguiente(nuevo);
			fin=nuevo;
		}else {
		
			inicio=fin=new NodoD<T>(dato);
		}tamanio++;
	}
	
	/**
	 * Agrega el dato al inicio de la lista
	 * @param dato que se desea agregar a la lista
	 */
	public void AgregarInicio(T dato) {
		if(!Vacia()) {
			
			NodoD<T> nuevo = new NodoD<T>(dato,null,inicio);
			inicio.setAnterior(nuevo);
			inicio=nuevo;
			
			
		}else {
			inicio=fin=new NodoD<T>(dato);
		}tamanio++;
	}
	
	
	
	
	/**
	 * Metodo para agregar un dato en cualquier posicion de la lista
	 * @param posicion en la cual se desea agregar el dato
	 * @param dato a agregar en la lista
	 */
	public void AgregarEnPosicion(int posicion, T dato) {
		if(posicion <= getTamanio()) {			
			NodoD<T> nuevo = new NodoD<T>(dato);
			if(posicion==0) {
				AgregarInicio(dato);
			}else {
				if (posicion == getTamanio() )    {
                   AgregarFinal(dato);
				}else {
                    NodoD<T> copia = inicio;
                    for (int f = 1 ; f < posicion ; f++)
                        copia = copia.getSiguiente();
                    NodoD<T> siguiente = copia.getSiguiente();
                    copia.setSiguiente(nuevo);
                    nuevo.setAnterior(copia);
                    nuevo.setSiguiente(siguiente);
                    siguiente.setAnterior(nuevo);
                    tamanio++;
				}
			}
		}else{
			throw new IllegalArgumentException("posicion no existe");
		}
	}
	
	/**
	 * Metodo para obtener el dato de una posicion especifica
	 * @param posicion de la cual se desea obtener los datos
	 * @return el valor desea, o error en caso de que la posicion no exista
	 */
	public T getDatoPos(int posicion) {
		NodoD<T> copia=inicio;
		if(!Vacia()) {
			if(posicion<getTamanio()) {
				while(copia!=null && posicion!=0) {
					
					copia=copia.getSiguiente();
					posicion--;
				}return copia.getDato();
			
			}throw new IndexOutOfBoundsException("Posicion no exite");
		
		}throw new  IndexOutOfBoundsException("Lista Vacia");
	}
    
	public void intercambiar (int pos1, int pos2) {
        if (pos1 <= getTamanio ()-1 && pos2 <= getTamanio()-1 )    {
            NodoD<T> copia1 = inicio;
            for (int f = 1 ; f <= pos1 ; f++)
                copia1 = copia1.getSiguiente();
            NodoD<T> copia2 = inicio;
            for (int f = 1 ; f <= pos2 ; f++)
                copia2 = copia2.getSiguiente();
            T aux = copia1.getDato();
            copia1.setDato(copia2.getDato()); 
            copia2.setDato(aux);;
        }else {
        	throw new IllegalArgumentException("Posicion no exite");
        }
    }
	
	
	public T eliminarInicio() {
		T dato = inicio.getDato();
		inicio= inicio.getSiguiente();
		if(!this.Vacia()) {
			inicio.setAnterior(null);
		}else {
			fin=null;
		}tamanio--;
		return dato;
		
	}
	
	public T eliminarFin() {
		T dato = fin.getDato();
		fin= fin.getAnterior();
		if(fin!=null) {
			fin.setSiguiente(null);
		}else {
			inicio=null;
		}tamanio--;
		return dato;
	}
	
	/**
	 * Eliminar un dato en una posicion espeficica
	 * @param posicion en la cual se desea eliminar el dato
	 */
	public void EliminarPos(int posicion) {
		if(!Vacia()) {
			if(posicion==0) {
				eliminarInicio();
			}else if(posicion == getTamanio()-1) {
				eliminarFin();
			}else {
				NodoD<T> copia=inicio;
				NodoD<T> atras=null;
				while(posicion!=0) {
					atras=copia;
					copia=copia.getSiguiente();
					
					posicion--;
				}tamanio--;
				atras.setSiguiente(copia.getSiguiente());
				copia.getSiguiente().setAnterior(copia.getAnterior());
				
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
	//Inicio a fin
	public void MostrarIaF() {
		if(!Vacia()) {
			
			NodoD<T> copia = inicio;	
			int i=0;
			while(copia !=null) {
				System.out.println("["+copia.getDato()+"]<=>"+i);
				i+=1;
				copia=copia.getSiguiente();
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
	public NodoD<T> getInicio() {
		return inicio;
	}
	
	public NodoD<T> getFin() {
		return fin;
	}
	public int getTamanio() {
		return tamanio;
	}
	

	
}
