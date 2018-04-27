package pantalla;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

/**
 * @version 1.8.0_162-b12
 * @author Martin Calderon Blanco
 *
 */
public class Sprite {
	//Variables
	private int vida;
	private String imagen;
	private double posicionX;
	private double posicionY;
	private double velocidadX=1;
	private double velocidadY=1;
	private double ancho;
	private double largo;
	/**
	 * Constructor de la Clase Sprite
	 * @param imagen String de la direccion de la imagen 
	 * @param ancho Double con el ancho de la figura del Sprite
	 * @param largo Double con el largo o alto de la figura del Sprite
	 * @param vida Integer del valor de vidas que tendra el sprite
	 */
	public Sprite(String imagen, double largo, double ancho,int vida) {
		this.imagen=imagen;
		
		this.ancho=ancho;
		this.largo=largo;
		this.vida=vida;
	}
	/**
	 * Resta una vida al valor de total de vidas
	 */
	public void RestarVida() {
		vida-=1;
		
	}
	

	/**
	 * Metodo para actualizar la Posicion de X y Y
	 * @param x Double el numero que se desea agregar a la posicion X
	 * @param y Double el numero que se desea agregar a la posicion Y
	 */
	public void actualizar(double x, double y) {
		posicionX+=x;
		posicionX=((double)Math.round(posicionX*10d)/10d);
		posicionY+=y;
	}
	/**
	 * Metodo que crea y da posicon a la imagen que se mostrara para el Sprite
	 * @param gc EL canvas en el cual se pintara el Sprite
	 */
	public void render(GraphicsContext gc) {
		
		Image i =new  Image(imagen);
		gc.drawImage(i, posicionX, posicionY);
		
	}
	/**
	 * Crea la figura que estara debajo del Sprite
	 * Para usarla en las colisiones
	 * @return la figura 
	 */
	public Rectangle2D getlimite() {
		
		return new Rectangle2D(posicionX, posicionY, ancho, largo);
	}
	/**
	 * Detecta las colisiones con otros Sprites
	 * @param s Sprite que se desea comprobar su Interseccion
	 * @return Retorna la interseccion con otro Sprite
	 */
	public boolean interseccion(Sprite s) {
		return s.getlimite().intersects(this.getlimite());
	}
	/**
	 * Da los valores de velocida a los Sprite
	 * @param x  Double Valor a agregar a Velcidad X 
	 * @param y  Double Valor a agregar a Velcidad Y
	 */
	public void setVelocidad(double x, double y) {
		velocidadX=x;
		velocidadY=y;
	}
	
	public void addVelocidad(double x, double y) {
		posicionX+=x;
		posicionY+=y;
	}
	
	
	
	//Get y Set de las variables
	public double getVelocidad() {
		return  velocidadX;
	}
	public double getposicionX() {
		
		return posicionX;
	}
	public double getposicionY() {
		
		return posicionY;
	}
	public void setPosicion(double x,double y) {
		this.posicionX=x;
		this.posicionY=y;
		
	}
	public String getImagen() {
		return imagen;
	}
	
	public int getVida() {
		return vida;
	}
}
