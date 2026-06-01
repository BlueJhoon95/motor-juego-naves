/**
 * Clase base abstracta para representar cualquier entidad en un juego 2D.
 *
 * Esta clase encapsula las propiedades comunes a la mayoría de elementos de
 * un videojuego 2D: posición en pantalla (x, y), dimensiones (width, height),
 * un nombre identificador, salud y la ruta a la imagen/texture que lo representa.
 *
 * Se declara abstracta porque no se pretende instanciarla directamente; otras
 * clases concretas (por ejemplo, jugador, enemigo, proyectil) deberán heredar
 * de ella y añadir comportamiento específico.
 */
public abstract class EntidadVideojuego {

	// Posición en el eje X (píxeles)
	private int x;
	// Posición en el eje Y (píxeles)
	private int y;
	// Anchura de la entidad (píxeles)
	private int width;
	// Altura de la entidad (píxeles)
	private int height;
	// Nombre identificador de la entidad
	private String nombre;
	// Salud de la entidad (puede interpretarse como puntos de vida)
	private int salud;
	// Ruta o identificador de la imagen/textura asociada
	private String imagen;

	/**
	 * Constructor que inicializa todos los atributos de la entidad.
	 *
	 * @param x posición X en píxeles
	 * @param y posición Y en píxeles
	 * @param width anchura en píxeles
	 * @param height altura en píxeles
	 * @param nombre nombre identificador
	 * @param salud puntos de salud
	 * @param imagen ruta o identificador de la imagen/textura
	 */
	public EntidadVideojuego(int x, int y, int width, int height, String nombre, int salud, String imagen) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.nombre = nombre;
		this.salud = salud;
		this.imagen = imagen;
	}

	// Getters y setters que respetan la encapsulación

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * Devuelve una representación legible de la entidad para la consola.
	 * Incluye el nombre, la posición (x,y) y la salud actual.
	 * Ejemplo: "Nave Heroe @ (100,200) HP=100"
	 */
	@Override
	public String toString() {
		return String.format("%s @ (%d,%d) HP=%d", nombre, x, y, salud);
	}

}
