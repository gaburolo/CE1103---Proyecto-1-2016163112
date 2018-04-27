package Clases;

import ListasCirculares.listaCircular;
import pantalla.Sprite;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */

public class Bubblesort {
	/**
	 * 
	 * @param hilera Lista de enemigos 
	 * @return Lista ordenada de Mayor a menor segun el numero de vidas
	 */
	public listaCircular<Sprite> Bubblesort(listaCircular<Sprite> hilera) {
		int t=0;
		int k=0;
		
		
		while(k!=hilera.getTamanio()) {
			int i=0;
			int j=1;
			while(j!=hilera.getTamanio()) {
				if(hilera.getDatoPos(i).getVida()<hilera.getDatoPos(j).getVida()) {
					
					hilera.intercambiar(i, j);
					
					i++;
					j++;
					
				}else {
					i++;
					j++;
				}
			}k++;
			
			
		}//while(t!=hilera.getTamanio()) {
			//System.out.println("Nodo: "+t+" vida: "+hilera.getDatoPos(t).getVida());
			//t++;
	//	}
	return hilera;
	}
}
