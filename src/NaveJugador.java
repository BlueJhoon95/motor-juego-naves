/**
 * Clase que representa la nave controlada por el jugador.
 *
 * Hereda de EntidadVideojuego para obtener las propiedades comunes como
 * posición, tamaño, nombre, salud e imagen. El constructor recibe solo
 * la posición y delega valores por defecto al constructor de la clase base.
 */
public class NaveJugador extends EntidadVideojuego {

    /**
     * Crea una nave de jugador en la posición (x,y) con valores por defecto:
     * tamaño 50x50, nombre "Nave Heroe", 100 de salud e imagen "nave.png".
     *
     * @param x posición X en píxeles
     * @param y posición Y en píxeles
     */
    public NaveJugador(int x, int y) {
        super(x, y, 50, 50, "Nave Heroe", 100, "nave.png");
    }

}
 
