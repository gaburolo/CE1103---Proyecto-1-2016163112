package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.stream.events.StartDocument;

import pantalla.Juego;
import pantalla.Sprite;
/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */

public class miServidor extends Thread {
	//Variables de la clase SERVIDOR
	private static ServerSocket serverSocket;
	private static Socket socket;
	private static BufferedReader br;
	private static InputStreamReader isr;
	public static String mensaje="";
	private int x;
	private int y;
	
	/*
	 * Override del metodo run de la Clase Thread 
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		
		
		try {
			
			
			
			
			
			while(true) {
			serverSocket = new ServerSocket(5000);
			socket=serverSocket.accept();

			
			isr = new InputStreamReader(socket.getInputStream());//Recibe los datos
			br= new BufferedReader(isr);
			mensaje = br.readLine();
			if(mensaje.equals("dispara")&& Juego.contBalas==1) {
				Juego.balasy-=10;
				Juego.contBalas+=1;
			}if(mensaje.equals("Izquierda")&&Juego.getOmnimonX()>=100) {
				Juego.setOmnimonX(Juego.getOmnimonX()-10);
			
			}else if(mensaje.equals("Derecha")&&Juego.getOmnimonX()<=600) {
				Juego.setOmnimonX(Juego.getOmnimonX()+10);
			
			}
				
			
			isr.close();
			br.close();
			serverSocket.close();
			socket.close();
			
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
