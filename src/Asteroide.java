/**
 * Clase que representa un asteroide en el juego.
 *
 * Hereda de EntidadVideojuego para reutilizar los atributos comunes
 * (posición, tamaño, nombre, salud e imagen). Se provee un constructor
 * que recibe solo la posición y delega el resto de valores por defecto
 * al constructor de la clase base mediante super(...).
 */
public class Asteroide extends EntidadVideojuego {

	/**
	 * Crea un asteroide en la posición (x,y) con valores por defecto:
	 * tamaño 30x30, nombre "Asteroide Mortal", 20 de salud e imagen "roca.png".
	 *
	 * @param x posición X en píxeles
	 * @param y posición Y en píxeles
	 */
	public Asteroide(int x, int y) {
		super(x, y, 30, 30, "Asteroide Mortal", 20, "roca.png");
	}

}
