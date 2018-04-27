package pantalla;




import java.io.File;



import java.util.Random;

import Clases.Basic;
import Clases.Bubblesort;
import Clases.ClaseA;
import Clases.ClaseB;
import Clases.ClaseC;
import Clases.ClaseD;
import Clases.ClaseE;
import ListasCirculares.listaCircular;
import ListasCircularesD.listaCD;
import Servidor.miServidor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import listas.lista;
import listasD.listaD;
/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */


public class Juego extends Application{
	//Variables
	//contadores
	private int cont = 9, cont2=11, cont3=2, mov=0;
	//Contador para nivel
	private int nivel=0 ;
	//Contador para la posicion de las Balas en Y
	public static int balasy=0,contBalas=1;
	//Posicion de nuestro jugador
	private static int omnimonX=300, omnimonY=532-67;
	
	
	//Posicion del jefe final
	private int diaboromonX=350, diabormonY=100;
	//Variables para los 4 botones
	private boolean izquierda, derecha, disparar,iniciarnivel,espada; 
	//Contador de Puntuacion
	private int Score=0;
	//Variables para a posicion de los enemigos
	private int H1x =50, H1y=100,H2x=50, H2y=100,H3x =50,H3y=100,H4x=400,H4y=100;
	//Variables para la velocidad en "x" y "y"
	private double vx=0,vy=0;
	//Contador de hilera actual y la siguiente
	private int numHilera, numHileraSiguiente;
	Random num = new Random();
	
	
	public static int getOmnimonX() {
		return omnimonX;
	}
	public static void setOmnimonX(int omnimonX) {
		Juego.omnimonX = omnimonX;
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}
	public static void main(String[] args) {
		
		launch(args);
		
		
	}
	public void start(Stage ventana) 
    {

		
		
        ventana.setTitle("Digimon Invaders");
        Group root = new Group();
        Scene pantalla = new Scene(root);
        ventana.setScene(pantalla);
        
        Canvas canvas = new Canvas(800,532);
        root.getChildren().add(canvas);
        
        //nueva ventana juego 
        Group root2= new Group();
        Scene pantalla2 = new Scene(root2);
        
        Canvas canvas2 = new Canvas(800,532);
        root2.getChildren().add(canvas2);
        //Pantalla de victoria
        Group root3 = new Group();
        Scene pantallaWin=new Scene(root3);
        
        Canvas canvas3= new Canvas(800,532);
		root3.getChildren().add(canvas3);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        GraphicsContext gc3 = canvas3.getGraphicsContext2D();
        
        //Carga las imagenes
        Image pInicial = new Image("imagenes/p_inicial.png");
        Image internet = new Image("imagenes/internet.png");
        Image iniciar = new Image("imagenes/iniciar.png");
        Image Win= new Image("imagenes/WIN.png");
        Image Derrota= new Image("imagenes/Derrota.png");
        
        
        //Boton de informacion
        Button botonInfo = new Button("Info");
        botonInfo.setDefaultButton(true);
        botonInfo.setPrefSize(60, 20);
        botonInfo.setLayoutX(10);
        botonInfo.setLayoutY(502);

        
        //Evento de botonInfo, ventana con informacion
        botonInfo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert informacion = new Alert(AlertType.INFORMATION);
				informacion.setTitle("Informacion");
				informacion.setHeaderText("Digimon Invaders\nCreado por Martin Calderon\n Proyecto");
				informacion.setContentText("Cuenta con 4 niveles y un Jefe final\nTeclas Utilizables\nZ->Iniciar Niveles\nEspacio->Disparar\nFlechaIzquierda/Derecha->Movimiento del personaje\nX->SuperAtaque Solo en Jefe Final");
				informacion.showAndWait();
				
				
			}
		});
        //Boton para cerrar el juego
        Button botonSalir = new Button("Salir");
        botonSalir.setDefaultButton(true);
        botonSalir.setPrefSize(60, 20);
        botonSalir.setLayoutX(730);
        botonSalir.setLayoutY(502);
        
        //Evento de botonSalir cierra la ventana
        botonSalir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ventana.close();
				
			}
        	
		});
        
        //Bonton para Iniciar el juego
        Button botonJuego=new Button("Iniciar");
        botonJuego.setDefaultButton(true);
        botonJuego.setPrefSize(60, 20);
        botonJuego.setLayoutX(360);
        botonJuego.setLayoutY(246);
        
        
        //Bonton para Conectar con el servidor el juego
        Button botonConectar=new Button("Conectar");
        botonConectar.setDefaultButton(true);
        botonConectar.setPrefSize(70, 20);
        botonConectar.setLayoutX(355);
        botonConectar.setLayoutY(296);
        
        
        
        root.getChildren().addAll(botonSalir, botonInfo, botonJuego, botonConectar);
        
        
         // Crea el personaje principal le da posicion en X y en Y
         
        Sprite omnimon = new Sprite("imagenes/omnimon1.png",80,67,3 );
        omnimon.setPosicion(omnimonX, omnimonY);
        
        Sprite diaboromon = new Sprite("imagenes/diaboromon.png",133,136,100);
        diaboromon.setPosicion(diaboromonX,diabormonY);
        
         //Creacion de el sprite bala y de lista de balas de armas
        
        Sprite cañon = new Sprite("imagenes/garurucannon.png", 32, 43,0);
        lista<Sprite> balas = new lista<>();
        balas.AgregarFinal(cañon);
        balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
       
       
        // Movimiento de Omnimon personaje usado por el jugador/Disparo/Iniciar nivel/Ataque Especial
        
        pantalla2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT:  derecha = true; break;
                    case LEFT: izquierda = true; break;
                    case SPACE: disparar = true; break;
                    case Z: iniciarnivel=true; break;
                    case X: espada=true;break;
                }
            }
        });

        pantalla2.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case RIGHT: derecha=false; break;
                    case LEFT: izquierda=false; break;
                    case SPACE: disparar=false; break;
                    case Z: iniciarnivel=false; break;
                    case X: espada=false;break;
                }
            }
        });
        
        
        
        //Hileras de enemigos  nivel 1
        Basic hilera1= new Basic();
        
    	ClaseA hilera2=new ClaseA();
    	
        

        lista<lista> listaEnemi=new lista<>();
        lista<String> copia1 = new lista<>();
        lista<String> copiaActual1 = new lista<>();
        int g=0;
        while(g!=3) {
        	int rand= num.nextInt((2)-0);
        	
        	if(rand==0) {
        		lista<Sprite> hilera1enemi = hilera1.hilera();
        		listaEnemi.AgregarFinal(hilera1enemi);
        		copia1.AgregarFinal(hilera1.getClass().getSimpleName());
        		copiaActual1.AgregarFinal(hilera1.getClass().getSimpleName());
        	}else {
        		lista<Sprite> hilera2enemi=hilera2.AgregarJefe();
        		listaEnemi.AgregarFinal(hilera2enemi);
        		copia1.AgregarFinal(hilera2.getClass().getSimpleName());
        		copiaActual1.AgregarFinal(hilera2.getClass().getSimpleName());
        	}
        	g++;
        }
        
        
        //Hileras de enemigos NIVEL 2
        ClaseB hilera3=new ClaseB();
        lista<listaD> listaEnemi2 = new lista<>();
        lista<String> copia2=new lista<>();
        lista<String> copiaActual2=new lista<>();
        g=0;
        while(g!=3) {
        	listaD<Sprite> hilera1enemi2 = hilera3.AgregarJefe();
        	
        	listaEnemi2.AgregarFinal(hilera1enemi2);
        	copia2.AgregarFinal(hilera3.getClass().getSimpleName());
        	copiaActual2.AgregarFinal(hilera3.getClass().getSimpleName());
        	g++;
        }
        
        
        //Hileras de enemigos NIVEL 3
        ClaseC hilera4=new ClaseC();
        ClaseD hilera5= new ClaseD();
        lista<listaCircular> listaEnemi3 = new lista<>();
        lista<String> copia3= new lista<>();
        lista<String> copiaActual3= new lista<>();
        
        g=0;
        while(g!=3) {
        	int rand= num.nextInt((2)-0);
        	
        	if(rand==0) {
        		listaCircular<Sprite> hilera1enemi3 = hilera4.AgregarJefe();
        		listaEnemi3.AgregarFinal(hilera1enemi3);
        		copia3.AgregarFinal(hilera4.getClass().getSimpleName());
        		copiaActual3.AgregarFinal(hilera4.getClass().getSimpleName());
        	}else {
        		listaCircular<Sprite> hilera2enemi3=hilera5.AgregarJefe();                     
        		listaEnemi3.AgregarFinal(hilera2enemi3);
        		copia3.AgregarFinal(hilera5.getClass().getSimpleName());
        		copiaActual3.AgregarFinal(hilera5.getClass().getSimpleName());
        	}
        	g++;
        }
        
        
	    //Hileras de enemigos Nivel 4
        ClaseE hilera6=new ClaseE();
        lista<listaCD> listaEnemi4 = new lista<>();
	    
        lista<String> copia4=new lista<>();
        lista<String> copiaActual4=new lista<>();
        
        g=0;
        while(g!=2) {
        	listaCD<Sprite> hilera1enemi4=hilera6.AgregarJefe();
        	listaEnemi4.AgregarFinal(hilera1enemi4);
        	copia4.AgregarFinal(hilera6.getClass().getSimpleName());
        	copiaActual4.AgregarFinal(hilera6.getClass().getSimpleName());
        	g++;
        	
        	
        }
        
        
        //Creamos la URL de los audios
        String urlAtaque1="src\\Audio\\Skill01.mp3";
        String urlAtaque2="src\\Audio\\Skill02.mp3";
        String urlIntro="src\\Audio\\Intro.mp3";
        AudioClip Ataque1= new AudioClip(new File(urlAtaque1).toURI().toString());
        AudioClip Ataque2= new AudioClip(new File(urlAtaque2).toURI().toString());
        AudioClip Intro= new AudioClip(new File(urlIntro).toURI().toString());
        Ataque1.setVolume(0.50);
        Ataque2.setVolume(0.50);
        Intro.setVolume(0.85);
        Intro.play();
        
        //Fuente de texto y tamaño
        Font thefont = Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 24);
        gc2.setFont(thefont);
        gc2.setFill(Color.GREENYELLOW);
        gc2.setLineWidth(1);
        gc3.setFont(thefont);
        gc3.setFill(Color.WHITE);
        
        //Creamos El objeto servidor
        miServidor servidor = new miServidor();
        //Evento del botonJuego trae a la gc2 al frente e inicializa las pantallas
        botonJuego.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Intro.stop();
				ventana.setScene(pantalla2);
				numHilera=num.nextInt((listaEnemi.getTamanio()-1)-0);
				copia1.EliminarPos(numHilera);
				numHileraSiguiente=num.nextInt((listaEnemi.getTamanio()-1)-0);
				
				
				
				
				cont=0;
				cont2=0;
				cont3=0;

			}
		});
        
        //Boton para inicializar el servidor
	    botonConectar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Inciamos el hilo del servidor
				servidor.start();
				
			}
	    	
		});
        
        
        
        //Timeline Ciclo del juego
        Timeline cicloJuego = new Timeline();
        cicloJuego.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				
				//Limpia y Pinta las images de fondo
				gc3.clearRect(0, 0, 800, 532);
				gc2.clearRect(0, 0, 800, 532);
				gc.clearRect(0, 0, 800, 532);
				gc.drawImage(pInicial, 0, 0);
				gc2.drawImage(internet, 0, 0);
				
				
				
				
				//Crea los String para aparecer en la pantalla de Puntuacion y Nivel actual
				String puntos="Score:" +(100*Score);
				String nivelActual= "Nivel :"+(nivel);
				gc2.fillText(puntos, 500, 50);
				gc2.fillText(nivelActual, 500, 80);
				
				//Pinta el mensaje COn la Tecla para iniciar nivel
				if(cont2==0) {
					gc2.drawImage(iniciar, 300, 300);
				}
				
				//mover pj
				if(derecha && omnimon.getposicionX()<=600) omnimonX+=10;
				if(izquierda && omnimon.getposicionX()>=100) omnimonX-=10;
				
				
				//Iniciar cada nivel
				if(iniciarnivel && cont2==0) {
					cont3=0;
					nivel+=1;
					cont2+=1;
					
				}
				
				//Disparar una bala
				if(disparar && contBalas==1) {
					Ataque1.play();
					balas.getDatoPos(0).addVelocidad(0, balasy-=10);
					contBalas+=1;
				}//pinta el reccorrido de la bala
				if(contBalas==2) {
					balas.getDatoPos(0).actualizar(0, balasy);
					balas.getDatoPos(0).render(gc2);
				}
				
				//regresa la bala a su posicion
				else {
					balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
				}
				if(balas.getDatoPos(0).getposicionY()<=100) {
					balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
					balasy=0;
					contBalas-=1;
				}
				//Movimiento de omnimon para cortar al enemigo
				if(espada && nivel==5 && cont3==0 ) {
					Ataque2.play();
					omnimon.addVelocidad(0, cont=-10);
					
					cont3+=1;
				}else if(nivel==5 && cont3==1) {
					omnimon.actualizar(0, cont);
					
					
					omnimon.render(gc2);
				}else {
					omnimon.setPosicion(omnimonX, omnimonY);
					omnimon.render(gc2);
				}
				
			
				
				
				
				
				//Pinta nuestro personaje en los primeros 4 niveles
				if(nivel!=5) {
					// Imprime a nuestro personaje
					omnimon.setPosicion(omnimonX, omnimonY);
					omnimon.render(gc2);
				}
				
				
				
				
				
				
				
				
				//NIVEL1 1 hileras Clase Basic y ClaseA
				
				
				//Comprueba las coliciones de las balas con los enemigos
				if(nivel==1 ) {	
					
					//Pinta en la pantalla la hilera siguiente 
					if(copia1.getTamanio()!=0 && cont3==0) {
						
						gc2.fillText(copia1.getDatoPos(numHileraSiguiente), 120, 70);
						gc2.fillText(copiaActual1.getDatoPos(numHilera), 120, 30);
					}else if(copiaActual1.getTamanio()!=0) {
						gc2.fillText(copiaActual1.getDatoPos(numHilera), 120, 30);
					}
					
					//Comprueba las colisiones de la bala con el enemigo
					
					if(listaEnemi.getTamanio()!=0 && listaEnemi.getTamanio()>numHilera) {
						int i=0;
						while(listaEnemi.getTamanio()>numHilera && i < listaEnemi.getDatoPos(numHilera).getTamanio()) {
						
							if ( balas.getDatoPos(0).interseccion((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)) )
				            {
								if(((Sprite)listaEnemi.getDatoPos(numHilera).getDatoPos(i)).getImagen()=="imagenes/keramon.png" && ((Sprite)listaEnemi.getDatoPos(numHilera).getDatoPos(i)).getVida()==1 ) {
									listaEnemi.getDatoPos(numHilera).eliminar();
									balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
									balasy=0;
									contBalas-=1;
									cont=0;
									Score+=5;
									break;
								}else if(((Sprite)listaEnemi.getDatoPos(numHilera).getDatoPos(i)).getImagen()=="imagenes/keramon.png") {
									((Sprite)listaEnemi.getDatoPos(numHilera).getDatoPos(i)).RestarVida();
								}
								else{
									listaEnemi.getDatoPos(numHilera).EliminarPos(i);
									Score+=1;
								}balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
								balasy=0;
								contBalas-=1;
				               
				                
				            }if (listaEnemi.getDatoPos(numHilera).getTamanio()!=0 && ((Sprite)listaEnemi.getDatoPos(numHilera).getDatoPos(0)).getposicionY()>=450 && omnimon.interseccion((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)) ) {
				            	nivel=7;
				            }
							i++;
						}
					}else if (listaEnemi.getTamanio()!=0 && listaEnemi.getTamanio()!=1 && listaEnemi.getTamanio()<=numHilera ) {
						
						numHilera=num.nextInt((listaEnemi.getTamanio()-1)-0);
						
					}
					
					//Coloca por primera vez los Sprites en sus debidas posiciones
					
					if( listaEnemi.getTamanio()!=0 && listaEnemi.getTamanio()>numHilera && listaEnemi.getDatoPos(numHilera).getTamanio()!=0){
						int i=0;
						if(cont==0) {
							((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(1)).setPosicion(H1x,H1y);
							
							
							
							while(i<listaEnemi.getDatoPos(numHilera).getTamanio()) {
								((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).setPosicion(H1x+=50,H1y);
						       	
								
								((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
								i+=1;
							}cont+=1;
						
						}
					
					}
					
				
				//Movimiento del as hileras Nivel1
				
				
				
					if(listaEnemi.getTamanio()!=0 && listaEnemi.getTamanio()>numHilera && listaEnemi.getDatoPos(numHilera).getTamanio()!=0 && cont==1) {
						
						
						if(((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(listaEnemi.getDatoPos(numHilera).getTamanio()-1)).getposicionX()!= 650 && ((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(listaEnemi.getDatoPos(numHilera).getTamanio()-1)).getposicionY()%100==0 ) {
								
							
								
								for (int i = 0; i < listaEnemi.getDatoPos(numHilera).getTamanio() ; i++) {
									
									((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).actualizar(0.5, 0);
									
									((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
								}
								
						}else if(((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(listaEnemi.getDatoPos(numHilera).getTamanio()-1)).getposicionX()==650 && ((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(listaEnemi.getDatoPos(numHilera).getTamanio()-1)).getposicionY()%100==0) {
							for (int i = 0; i < listaEnemi.getDatoPos(numHilera).getTamanio() ; i++) {
									
								((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).actualizar(0, 50);
									
								((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
							}
							
						
						}else if(((Sprite) listaEnemi.getDatoPos(numHilera).getInicio().getDato()).getposicionX()==100) {
							
							for (int i = 0; i < listaEnemi.getDatoPos(numHilera).getTamanio() ; i++) {
								((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).actualizar(0, 50);
								((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
							}						
							
						}else if(((Sprite) listaEnemi.getDatoPos(numHilera).getInicio().getDato()).getposicionX()!=100  ){
																	
									for (int i = 0; i < listaEnemi.getDatoPos(numHilera).getTamanio() ; i++) {
										
										((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).actualizar(-0.5, 0);										
										((Sprite) listaEnemi.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
									}
									
													
						
							
						}
					}
					
					
					//Llama a la siguiente hilera y elige de forma aleatoria la siguiente/ ademas da como finalizado el nivel si ya no hay mas enemigos
					else if(listaEnemi.getTamanio()!=0 && listaEnemi.getTamanio()>numHilera &&listaEnemi.getDatoPos(numHilera).getTamanio()==0) {
						copiaActual1.EliminarPos(numHilera);
						listaEnemi.EliminarPos(numHilera);
						if(listaEnemi.getTamanio()==0) {
							cont=0;
							cont2=0;
							
							numHilera=num.nextInt((listaEnemi2.getTamanio()-1)-0);
							
							copia2.EliminarPos(numHilera);
							
							numHileraSiguiente=num.nextInt((listaEnemi2.getTamanio()-1)-0);
							
							cont3=0;
						}else {
							if(listaEnemi.getTamanio()==1) {
								
								copia1.eliminar();
								
								
								H1x=50;
								H1y=100;
								cont=0;
								numHilera=0;
							}else {
								numHilera=numHileraSiguiente;
								copia1.EliminarPos(numHilera);
								if(copia1.getTamanio()==1) {
									numHileraSiguiente=0;
								}else {
									numHileraSiguiente=num.nextInt((copia1.getTamanio()-1)-0);
								}
								
								
								H1x=50;
								H1y=100;
								
								cont=0;
								cont3=0;
							}
							
							
							 
						}
						
					}
				}
			//Nivel 2
			
				
			if(nivel==2) {
				//Pinta la hilera siguiente de enemigos en el nivel 2
				if(copia2.getTamanio()!=0 && cont3==0) {
					
					
					gc2.fillText(copia2.getDatoPos(numHileraSiguiente), 120, 70);
					gc2.fillText(copiaActual2.getDatoPos(numHilera), 120, 30);
				}else if(copiaActual2.getTamanio()!=0) {
					gc2.fillText(copiaActual2.getDatoPos(numHilera), 120, 30);
				}
				
				
				
				
				//Comprueba la interseccion de las balas con los enemigos
				if(listaEnemi2.getTamanio()!=0 && listaEnemi2.getTamanio()>numHilera) {
					
					int i=0;
					while(listaEnemi2.getTamanio()>numHilera && i < listaEnemi2.getDatoPos(numHilera).getTamanio()) {
						
						if ( balas.getDatoPos(0).interseccion((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)) )
				        {
							if(((Sprite)listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).getImagen()=="imagenes/keramon.png" && ((Sprite)listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).getVida()==1) {
								listaEnemi2.getDatoPos(numHilera).eliminar();
								balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
								balasy=0;
								contBalas-=1;
								Score+=5;
								break;
							}else if(((Sprite)listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).getImagen()=="imagenes/keramon.png") {
								((Sprite)listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).RestarVida();		
							}
							else{
								listaEnemi2.getDatoPos(numHilera).EliminarPos(i);
								Score+=1;
							}balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
							balasy=0;
							contBalas-=1;
							
				        }if ( listaEnemi2.getDatoPos(numHilera).getTamanio()!=0 &&((Sprite)listaEnemi2.getDatoPos(numHilera).getDatoPos(0)).getposicionY()>=450&& omnimon.interseccion((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)) ) {
			            	nivel=7;
			            }
						i++;
					}
				}else if (listaEnemi2.getTamanio()!=0 && listaEnemi2.getTamanio()!=1 && listaEnemi2.getTamanio()<=numHilera){
					
					numHilera=num.nextInt((listaEnemi2.getTamanio()-1)-0);
				}
				
				
				//Coloca los Sprites en posicion e intercambia las posiciones del jefe con enemigos aleatorios
				if( listaEnemi2.getTamanio()!=0 && listaEnemi2.getTamanio()>numHilera && listaEnemi2.getDatoPos(numHilera).getTamanio()!=0){
					int i=0;
					if(cont==0) {
						
						((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(1)).setPosicion(H2x,H2y);
						
						
						
						while(i<listaEnemi2.getDatoPos(numHilera).getTamanio()) {
							((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).setPosicion(H2x+=50,H2y);
					       	
							
							((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
							i+=1;
						}cont+=1;
					
					}else if(cont/100==1 && listaEnemi2.getDatoPos(numHilera).getTamanio()!=1) {
						
						H2x= (int) ((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(0)).getposicionX()-50;
						H2y= (int) ((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(0)).getposicionY();
						while(((Sprite)listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).getImagen()!="imagenes/keramon.png") {
							i++;
						}
						Random num=new Random();
						int n=num.nextInt((listaEnemi2.getDatoPos(numHilera).getTamanio()-1)-0);
						
						listaEnemi2.getDatoPos(numHilera).intercambiar(i, n);
						cont=0;
					}
					
					
					
					else {
						while(i<listaEnemi2.getDatoPos(numHilera).getTamanio()) {
							((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
							i+=1;
						
						}cont+=1;
					}
				}
				
				
				
				//Movimiento de los enemigos del nivel 2
				if(listaEnemi2.getTamanio()!=0 && listaEnemi2.getTamanio()>numHilera && listaEnemi2.getDatoPos(numHilera).getTamanio()!=0) {
					
					
					if(((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(listaEnemi2.getDatoPos(numHilera).getTamanio()-1)).getposicionX()!= 650 && ((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(listaEnemi2.getDatoPos(numHilera).getTamanio()-1)).getposicionY()%100==0 ) {
						
						
							
							for (int i = 0; i < listaEnemi2.getDatoPos(numHilera).getTamanio() ; i++) {
								
								((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).actualizar(1, 0);
								
								((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
							}
							
					}else if(((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(listaEnemi2.getDatoPos(numHilera).getTamanio()-1)).getposicionX()==650 && ((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(listaEnemi2.getDatoPos(numHilera).getTamanio()-1)).getposicionY()%100==0) {
						for (int i = 0; i < listaEnemi2.getDatoPos(numHilera).getTamanio() ; i++) {
								
							((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).actualizar(0, 50);
								
							((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
						}
						
					
					}else if(((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(0)).getposicionX()==100) {
						
						for (int i = 0; i < listaEnemi2.getDatoPos(numHilera).getTamanio() ; i++) {
							((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).actualizar(0, 50);
							((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
						}						
						
					}else if(((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(0)).getposicionX()!=100  ){
								
								for (int i = 0; i < listaEnemi2.getDatoPos(numHilera).getTamanio() ; i++) {
									
									((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).actualizar(-1, 0);										
									((Sprite) listaEnemi2.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
								}
								
												
					
						
					}
				}else if(listaEnemi2.getTamanio()!=0 && listaEnemi2.getTamanio()>numHilera && listaEnemi2.getDatoPos(numHilera).getTamanio()==0) {
					copiaActual2.EliminarPos(numHilera);
					listaEnemi2.EliminarPos(numHilera);
					if(listaEnemi2.getTamanio()==0) {
						
						cont=0;
						cont2=0;
						numHilera=num.nextInt((listaEnemi3.getTamanio()-1)-0);
						
						copia3.EliminarPos(numHilera);
						
						numHileraSiguiente=num.nextInt((listaEnemi3.getTamanio()-1)-0);
						
						cont3=0;
						
					}else {
						if(listaEnemi2.getTamanio()==1) {
							
							numHilera=0;
							copia2.eliminar();
						}else {
							
						numHilera=numHileraSiguiente;
						copia2.EliminarPos(numHilera);
						
						if(copia2.getTamanio()==1) {
							numHileraSiguiente=0;
						}else {
							numHileraSiguiente=num.nextInt((copia2.getTamanio()-1));
						}
						
						cont3=0;
						}						
						H2x=50;
						H2y=100;
						cont=0;
						
					
					}
					
				
				
				}
			
			}
			//Nivel 3
			if(nivel==3 ) {	
				//Pinta en la pantalla la hilera siguiente 
				if(copia3.getTamanio()!=0) {
					
					
					gc2.fillText(copia3.getDatoPos(numHileraSiguiente), 120, 70);
					gc2.fillText(copiaActual3.getDatoPos(numHilera), 120, 30);
				}else if(copiaActual3.getTamanio()!=0) {
					gc2.fillText(copiaActual3.getDatoPos(numHilera), 120, 30);
				
				}

				
				
				
				
				
				
				//Comprueba si la bala pego contra un enemigo
				if(listaEnemi3.getTamanio()!=0 && listaEnemi3.getTamanio()>numHilera) {
					int i=0;
					while(listaEnemi3.getTamanio()>numHilera && i < listaEnemi3.getDatoPos(numHilera).getTamanio()) {
						
						if ( balas.getDatoPos(0).interseccion((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)) )
			            {	
							
							if(((Sprite)listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).getImagen()=="imagenes/keramon.png" &&((Sprite)listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).getVida()==1) {
								
								listaEnemi3.getDatoPos(numHilera).EliminarPos(i);
								if(listaEnemi3.getDatoPos(numHilera).getTamanio()!=0) {
									int n=num.nextInt((listaEnemi3.getDatoPos(numHilera).getTamanio())-0);
									
								
									
									
									
									int Numvida=2+num.nextInt(4);
									Sprite keramon = new Sprite("imagenes/keramon.png",62,46,Numvida);
									H3x= (int) ((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(0)).getposicionX()-50;
									H3y= (int) ((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(0)).getposicionY();
									keramon.setPosicion(H3x, H3y);
									listaEnemi3.getDatoPos(numHilera).EliminarPos(n);
									
									listaEnemi3.getDatoPos(numHilera).AgregarEnPosicion(n, keramon);
									
									if(listaEnemi3.getDatoPos(numHilera).getNombreClase()=="ClaseD") {
										
										Bubblesort b=new Bubblesort();
										b.Bubblesort(listaEnemi3.getDatoPos(numHilera));
									}
									
									
									
									cont=0;
									balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
									balasy=0;
									contBalas-=1;
									Score+=5;
									break;
								}balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
								cont=0;
								balasy=0;
								contBalas-=1;
								break;
							}else if(((Sprite)listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).getImagen()=="imagenes/keramon.png") {
								
								((Sprite)listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).RestarVida();
								
								
								
								balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
								if(listaEnemi3.getDatoPos(numHilera).getNombreClase()=="ClaseD") {
									H3x= (int) ((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(0)).getposicionX()-50;
									H3y= (int) ((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(0)).getposicionY();
									
									Bubblesort b=new Bubblesort();
									b.Bubblesort(listaEnemi3.getDatoPos(numHilera));
									cont=0;
								}
								balasy=0;
								contBalas-=1;
								break;
							}
							else{
								
								if(((Sprite)listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).getVida()==1){
									
									listaEnemi3.getDatoPos(numHilera).EliminarPos(i);
									Score+=1;
								}else {
									
									((Sprite)listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).RestarVida();
								}
								if(listaEnemi3.getDatoPos(numHilera).getNombreClase()=="ClaseD") {
									H3x= (int) ((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(0)).getposicionX()-50;
									H3y= (int) ((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(0)).getposicionY();
									
									Bubblesort b=new Bubblesort();
									b.Bubblesort(listaEnemi3.getDatoPos(numHilera));
									cont=0;
								}
								
								
							}balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
							balasy=0;
							contBalas-=1;
							break;
			                
			            }if ( omnimon.interseccion((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)) ) {
			            	nivel=7;
			            }
						i++;
					}
				}else if (listaEnemi3.getTamanio()!=0 && listaEnemi3.getTamanio()!=1 && listaEnemi3.getTamanio()<=numHilera ) {
					
					numHilera=num.nextInt((listaEnemi3.getTamanio()-1)-0);
					
				}
				
				//Coloca por primera vez los Sprites en sus debidas posiciones
				
				if( listaEnemi3.getTamanio()!=0 && listaEnemi3.getTamanio()>numHilera && listaEnemi3.getDatoPos(numHilera).getTamanio()!=0){
					int i=0;
					if(cont==0) {
						if(listaEnemi3.getDatoPos(numHilera).getNombreClase()=="ClaseD") {
							
							
							Bubblesort b=new Bubblesort();
							b.Bubblesort(listaEnemi3.getDatoPos(numHilera));
							
						}
						

						((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(0)).setPosicion(H3x,H3y);
						
						while(i<listaEnemi3.getDatoPos(numHilera).getTamanio()) {
							((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).setPosicion(H3x+=50,H3y);
					       	
							
							((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
							i+=1;
						}cont+=1;
						
					
					
					}
				
				}
				
			
			//Movimiento de las hileras Nivel3
			
			
			
				if(listaEnemi3.getTamanio()!=0 && listaEnemi3.getTamanio()>numHilera && listaEnemi3.getDatoPos(numHilera).getTamanio()!=0) {
					
					
					if(((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(listaEnemi3.getDatoPos(numHilera).getTamanio()-1)).getposicionX()!= 650 && ((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(listaEnemi3.getDatoPos(numHilera).getTamanio()-1)).getposicionY()%100==0 ) {
						
						
							
							for (int i = 0; i < listaEnemi3.getDatoPos(numHilera).getTamanio() ; i++) {
								
								((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).actualizar(2, 0);
								
								((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
							}
							
					}else if(((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(listaEnemi3.getDatoPos(numHilera).getTamanio()-1)).getposicionX()==650 && ((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(listaEnemi3.getDatoPos(numHilera).getTamanio()-1)).getposicionY()%100==0) {
						for (int i = 0; i < listaEnemi3.getDatoPos(numHilera).getTamanio() ; i++) {
								
							((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).actualizar(0, 50);
								
							((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
						}
						
					
					}else if(((Sprite) listaEnemi3.getDatoPos(numHilera).getInicio().getDato()).getposicionX()==100) {
						
						for (int i = 0; i < listaEnemi3.getDatoPos(numHilera).getTamanio() ; i++) {
							((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).actualizar(0, 50);
							((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
						}						
						
					}else if(((Sprite) listaEnemi3.getDatoPos(numHilera).getInicio().getDato()).getposicionX()!=100  ){
																
								for (int i = 0; i < listaEnemi3.getDatoPos(numHilera).getTamanio() ; i++) {
									
									((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).actualizar(-2, 0);										
									((Sprite) listaEnemi3.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
								}
								
												
					
						
					}
				}
				
				
				//Llama a la siguiente hilera y elige de forma aleatoria la siguiente/ ademas da como finalizado el nivel si ya no hay mas enemigos
				else if(listaEnemi3.getTamanio()!=0 && listaEnemi3.getTamanio()>numHilera &&listaEnemi3.getDatoPos(numHilera).getTamanio()==0) {
					copiaActual3.EliminarPos(numHilera);
					listaEnemi3.EliminarPos(numHilera);
					if(listaEnemi3.getTamanio()==0) {
						cont=0;
						cont2=0;
						
						cont3=0;
						numHilera=num.nextInt((listaEnemi4.getTamanio()-1)-0);
						copia4.EliminarPos(numHilera);
						numHileraSiguiente=num.nextInt((listaEnemi4.getTamanio()-1)-0);
						
					}else {
						if(listaEnemi3.getTamanio()==1) {
							
							copia3.eliminar();
							H3x=50;
							H3y=100;
							cont=0;
							numHilera=0;
						}else {
						numHilera=numHileraSiguiente;
						copia3.EliminarPos(numHilera);
						if(copia3.getTamanio()==1) {
							numHileraSiguiente=0;
						}else {
							numHileraSiguiente=num.nextInt((listaEnemi3.getTamanio())-0);
						}
						
						
						H3x=50;
						H3y=100;
						cont=0; 
						cont3=0;
						}
					}
					
				}
			}
			
			
			
			if(nivel==4) {
				if(copia4.getTamanio()!=0) {
					
					
					gc2.fillText(copia4.getDatoPos(numHileraSiguiente), 120, 70);
					gc2.fillText(copiaActual4.getDatoPos(numHilera), 120, 30);
				}else if(copiaActual4.getTamanio()!=0) {
					gc2.fillText(copiaActual4.getDatoPos(numHilera), 120, 30);
				
				
				}
				
				
				
				//Interseccion entre la bala y los enemigos
				if(listaEnemi4.getTamanio()!=0 && listaEnemi4.getTamanio()>numHilera) {
					int i=0;
					while(listaEnemi4.getTamanio()>numHilera && i < listaEnemi4.getDatoPos(numHilera).getTamanio()) {
					
						if ( balas.getDatoPos(0).interseccion((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(i)) )
			            {
							if(((Sprite)listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).getImagen()=="imagenes/keramon.png" && ((Sprite)listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).getVida()==1 ) {
								int numVida=1;
								Sprite keramon = new Sprite("imagenes/keramon.png",62,46,numVida);
								H4x= 400;
								H4y= (int) ((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).getposicionY();
								
								keramon.setPosicion(H4x, H4y);
								listaEnemi4.getDatoPos(numHilera).EliminarPos(i);
								if(listaEnemi4.getDatoPos(numHilera).getTamanio()!=0) {
								
									int centro=(int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2;
									
									if(listaEnemi4.getDatoPos(numHilera).getTamanio()!=1) {
										int n=num.nextInt(listaEnemi4.getDatoPos(numHilera).getTamanio()-1);
										
										listaEnemi4.getDatoPos(numHilera).EliminarPos(n);
										listaEnemi4.getDatoPos(numHilera).AgregarEnPosicion(centro, keramon);
									}else {
										
										int n=0;
										listaEnemi4.getDatoPos(numHilera).AgregarEnPosicion(n, keramon);
										listaEnemi4.getDatoPos(numHilera).EliminarPos(1);
										
									}
									
									
									
									balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
									balasy=0;
									contBalas-=1;
									cont=0;
									Score+=5;
									break;
								}else {
									balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
									balasy=0;
									contBalas-=1;
									
									break;
								}
							}else if(((Sprite)listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).getImagen()=="imagenes/keramon.png") {
								((Sprite)listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).RestarVida();
							}
							else{
								int centro=((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2);
								int numVida=((Sprite)listaEnemi4.getDatoPos(numHilera).getDatoPos(centro)).getVida();
								Sprite keramon = new Sprite("imagenes/keramon.png",62,46,numVida);
								H4x= 400;
								H4y= (int) ((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(centro)).getposicionY();
								
								keramon.setPosicion(H4x, H4y);
								listaEnemi4.getDatoPos(numHilera).EliminarPos(i);
								
								
								int centroNuevo=(int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2;
								int t=0;
								while(((Sprite)listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).getImagen()!="imagenes/keramon.png") {
									t++;
								}
								listaEnemi4.getDatoPos(numHilera).EliminarPos(t);
								
								listaEnemi4.getDatoPos(numHilera).AgregarEnPosicion(centroNuevo, keramon);
								
								Score+=1;
								cont=0;
								balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
								balasy=0;
								contBalas-=1;
								break;
							}balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
							balasy=0;
							contBalas-=1;
			               
			                
			            }if ( omnimon.interseccion((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(i)) ) {
			            	nivel=7;
			            }
						i++;
					}
				}else if (listaEnemi4.getTamanio()!=0 && listaEnemi4.getTamanio()!=1 && listaEnemi4.getTamanio()<=numHilera ) {
					
					numHilera=num.nextInt((listaEnemi4.getTamanio()-1)-0);
					
				}
				
				//Coloca por primera vez los Sprites en sus debidas posiciones
				

				if( listaEnemi4.getTamanio()!=0 && listaEnemi4.getTamanio()>numHilera && listaEnemi4.getDatoPos(numHilera).getTamanio()!=0){
					int i=((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2);
					int j=i+1;
					if(cont==0) {
						((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2)).setPosicion(H4x,H4y);
						((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2)).render(gc2);
						
						while(i>=0) {
							((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).setPosicion(H4x-=50,H4y);
							((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
							i-=1;
						}
						H4x=(int) ((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2)).getposicionX();
						H4y=(int) ((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2)).getposicionY();
						while(j<listaEnemi4.getDatoPos(numHilera).getTamanio()) {
							((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).setPosicion(H4x+=50,H4y);
					       	
							
							((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).render(gc2);
							j+=1;
						}
						mov=0;
						vy=0;
						vx=0;
						cont++;
						
					}
				
				}
				
			
			//Movimiento del as hileras Nivel4
			
			
			
				if(listaEnemi4.getTamanio()!=0 && listaEnemi4.getTamanio()>numHilera && listaEnemi4.getDatoPos(numHilera).getTamanio()!=0) {
					
					

				if((int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos((int)(listaEnemi4.getDatoPos(numHilera).getTamanio()/2))).getposicionY()!=500 ) {
						
						int t=((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2)-1;
						int j=((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2)+1;
						if((int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(0)).getposicionX()==(int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(listaEnemi4.getDatoPos(numHilera).getTamanio()-1)).getposicionX() && mov==0){
							
							
							mov+=1;
						}
						if((int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(0)).getposicionY()== (int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(listaEnemi4.getDatoPos(numHilera).getTamanio()-1)).getposicionY() && mov==1 ) {
							
							mov+=1;
						}
						if((int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(0)).getposicionX()==(int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(listaEnemi4.getDatoPos(numHilera).getTamanio()-1)).getposicionX() && mov==2) {
							mov+=1;
							
						}if((int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(0)).getposicionY()== (int)((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(listaEnemi4.getDatoPos(numHilera).getTamanio()-1)).getposicionY() && mov==3 ) {
							
							mov=0;
						}
						
						//((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos((int)listaEnemi4.getDatoPos(numHilera).getTamanio()/2)).actualizar(0, 0.1);
						
						for (int i = 0; i < listaEnemi4.getDatoPos(numHilera).getTamanio() ; i++) {
								
							((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).actualizar(0, 0.5);
								
							((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(i)).render(gc2);
						}
						if(mov==0) {
							while(t>=0) {
								((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).actualizar(vx+=1,vy-=1);
								
								((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).render(gc2);
								t-=1;
							}vx=0;
							vy=0;
							while(j<listaEnemi4.getDatoPos(numHilera).getTamanio()) {
								
								
									((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).actualizar(vx-=1,vy+=1);
									
									((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).render(gc2);
									
								
								
								j++;
							}vx=0;
							vy=0;
						}else if(mov==1){
							while(t>=0) {
								((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).actualizar(vx+=1,vy+=1);
								
								((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).render(gc2);
								t-=1;
							}vx=0;
							vy=0;
							while(j<listaEnemi4.getDatoPos(numHilera).getTamanio()) {
								
								
									((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).actualizar(vx-=1,vy-=1);
									
									((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).render(gc2);
									
								
								
								j++;
							}vx=0;
							vy=0;
							
						}
						else if(mov==2){
							while(t>=0) {
								((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).actualizar(vx-=1,vy+=1);
								
								((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).render(gc2);
								t-=1;
							}vx=0;
							vy=0;
							while(j<listaEnemi4.getDatoPos(numHilera).getTamanio()) {
								
								
									((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).actualizar(vx+=1,vy-=1);
									
									((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).render(gc2);
									
								
								
								j++;
							}vx=0;
							vy=0;
						}else {
							while(t>=0) {
								((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).actualizar(vx-=1,vy-=1);
								
								((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(t)).render(gc2);
								t-=1;
							}vx=0;
							vy=0;
							while(j<listaEnemi4.getDatoPos(numHilera).getTamanio()) {
								
								
									((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).actualizar(vx+=1,vy+=1);
									
									((Sprite) listaEnemi4.getDatoPos(numHilera).getDatoPos(j)).render(gc2);
									
								
								
								j++;
							}vx=0;
							vy=0;
						}
							
						
					}
					
					
				}
				
				
				//Llama a la siguiente hilera y elige de forma aleatoria la siguiente/ ademas da como finalizado el nivel si ya no hay mas enemigos
				else if(listaEnemi4.getTamanio()!=0 && listaEnemi4.getTamanio()>numHilera &&listaEnemi4.getDatoPos(numHilera).getTamanio()==0) {
					copiaActual4.EliminarPos(numHilera);
					listaEnemi4.EliminarPos(numHilera);
					if(listaEnemi4.getTamanio()==0) {
						cont=0;
						cont2=0;
						cont3=0;
						
					}else {
						if(listaEnemi4.getTamanio()==1) {
							
							copia4.eliminar();
							H4x=400;
							H4y=100;
							cont=0;
							numHilera=0;
						}else {
							numHilera=numHileraSiguiente;
							copia4.EliminarPos(numHilera);
							if(copia4.getTamanio()==1) {
								numHileraSiguiente=0;
							}else {
								numHileraSiguiente=num.nextInt((listaEnemi4.getTamanio())-0);
							}
							
							
							
							
							H4x=400;
							H4y=100;
							
							cont=0;
							cont3=0;
					
						}
					}
				}
			}
		
			
			
			if(nivel==5) {
				
				//Si la vida del Jefe es 0, ganas la partida
				if(diaboromon.getVida()<=0) {
					Score+=50;
					nivel=6;
				}
				if(balas.getDatoPos(0).interseccion(diaboromon)) {
					diaboromon.RestarVida();
					
					balas.getDatoPos(0).setPosicion(omnimonX, omnimonY);
					balasy=0;
					contBalas-=1;
				}if(omnimon.interseccion(diaboromon)) {
					
					int i=0;
					while(i!=5) {
						diaboromon.RestarVida();
						
						i++;
					}
					omnimonY=532-67;
					
					omnimon.setPosicion(omnimonX, omnimonY);
					cont3=0;
					
				}if(omnimon.getposicionY()<=100) {
					omnimonY=532-67;
					omnimon.setPosicion(omnimonX, omnimonY);
					cont3=0;
				}
				
				
				
				//Movimiento de JEFE final
				if((int)diaboromon.getposicionY()!=400 ) {
					
					if(diaboromon.getposicionX()!=700-136 && diaboromon.getposicionY()%10==0) {
						diaboromon.actualizar(2, 0);
						
						diaboromon.render(gc2);
					}else if(diaboromon.getposicionX()==700-136 && diaboromon.getposicionY()%10==0) {
						
						diaboromon.actualizar(0, 25);
						diaboromon.render(gc2);
					}else if(diaboromon.getposicionX()!=100) {
						diaboromon.actualizar(-2, 0);
						
						diaboromon.render(gc2);
					}
					
					
					else {
						
						diaboromon.actualizar(0, 25);
						diaboromon.render(gc2);
						
					}
				}else {
					nivel=7;
				}
				
			}
			
			
			
			//Pantalla de Victoria si completas los Niveles Anteriores
			if(nivel==6) {
				
				ventana.setScene(pantallaWin);
				gc3.drawImage(Win, 0, 0);
				gc3.fillText(puntos, 350, 350);
			}
			if(nivel==7) {
				ventana.setScene(pantallaWin);
				gc3.drawImage(Derrota, 0, 0);
				gc3.fillText(puntos, 350, 350);
			}
			
			

		}	
			
	});cicloJuego.getKeyFrames().add(kf);
	cicloJuego.play();
	ventana.show();
	
		
		
		
    }
	
}
