package ListasCircularesD;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */

public class listaCD<T> {
	//Variable
	private NodoCD<T> inicio,fin;
	private int tamanio;
	
	/**
	 * Contructor de la Clase ListaCD
	 */
	public listaCD(){
		this.inicio=null;
		this.fin=null;
		this.tamanio=0;
	}
	
	
	/**
	 * Metodo para agregar a la lista Circular doble
	 * @param dato T que queremos Agregar
	 */
	public void AgregarFinal(T dato) {
		NodoCD<T> nuevo =new NodoCD<T>();
		nuevo.setDato(dato);
		if(Vacia()) {
			inicio=nuevo;
			inicio.setSiguiente(inicio);
			nuevo.setAnterior(fin);
			fin=nuevo;
			
			
			
			
		}else {
			fin.setSiguiente(nuevo);
			nuevo.setSiguiente(inicio);
			nuevo.setAnterior(fin);
			fin=nuevo;
			inicio.setAnterior(fin);
			
			
		}tamanio++;
	}
	public void AgregarInicio(T dato) {
		NodoCD<T> nuevo = new NodoCD<T>();
		nuevo.setDato(dato);
		if(Vacia()) {
			inicio=nuevo;
			
		}else {
			NodoCD<T> copia = inicio;
			while(copia.getSiguiente()!=inicio) {
				copia=copia.getSiguiente();
			}
			copia.setAnterior(nuevo);
			nuevo.setAnterior(copia);
			nuevo.setSiguiente(inicio);
			inicio.setAnterior(nuevo);
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
			NodoCD<T> nuevo = new NodoCD<T>();
			nuevo.setDato(dato);
			if(posicion==0) {
				AgregarInicio(dato);
			}else {
				if(posicion==getTamanio()) {
					
					AgregarFinal(dato);
					
				}else {
					NodoCD<T> copia = inicio;
					for (int i = 0; i < (posicion-1); i++) {
						copia = copia.getSiguiente();
					}NodoCD<T> siguiente = copia.getSiguiente();
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
		NodoCD<T> copia=inicio;
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
	 * Elimina un dato en una posicion especifica
	 * @param posicion Int en el cual se desea eliminar el dato
	 */
	public void EliminarPos(int posicion) {
		if(!Vacia()) {
			if(posicion>=0 && posicion<tamanio ) {
				
				if(posicion==0) {
					inicio=inicio.getSiguiente();
					fin.setSiguiente(inicio);
					inicio.setAnterior(fin);
				}else {
					NodoCD<T> copia = inicio;
					for (int i = 0; i < (posicion-1); i++) {
						copia=copia.getSiguiente();
						
					}if(copia.getSiguiente()==fin) {
						copia.setSiguiente(inicio);
						fin=copia;
						inicio.setAnterior(fin);
					}else {
						NodoCD<T> siguiente=copia.getSiguiente();
						copia.setSiguiente(siguiente.getSiguiente());
					}
				}
			}tamanio--;
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
            NodoCD<T> aux = inicio;            
            int i = 0;            
            while(i != getTamanio()){                
                System.out.print(i + ".[ " + aux.getDato() + " ]" + " ->  ");                
                aux = aux.getSiguiente();                
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
	public NodoCD<T> getInicio() {
		return inicio;
	}
	public NodoCD<T> getFin() {
		return fin;
	}
	public int getTamanio() {
		return tamanio;
	}
}
