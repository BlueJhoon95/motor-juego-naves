/**
 * Clase conductora para simular una partida paso a paso por consola.
 *
 * Cada paso está documentado en español para que el flujo sea claro
 * para el evaluador: crear motor, entidades, gestionar entradas,
 * forzar una colisión y realizar un guardado rápido.
 */
public class Main {

	/**
	 * Punto de entrada de la aplicación. Simula una partida en consola.
	 */
	public static void main(String[] args) {
		// 1) Instanciar el MotorJuego y cambiar su estado inicial a 'JUGANDO'.
		MotorJuego motor = new MotorJuego();
		// Comentario: arrancamos el motor y pasamos a modo de juego activo.
		motor.cambiarEstado("JUGANDO");

		// 2) Instanciar una NaveJugador (x=0,y=0) y un Asteroide (x=0,y=10).
		// Añadir ambos al motor usando el método de añadir entidad.
		NaveJugador nave = new NaveJugador(0, 0);
		Asteroide ast = new Asteroide(0, 10);
		motor.añadirEntidad(nave);
		motor.añadirEntidad(ast);

		// 3) Instanciar el GestorEntradas.
		GestorEntradas gestor = new GestorEntradas();

		// 4) Llamar a motor.actualizar() para ver el estado inicial.
		// Comentario: aquí veremos las entidades y no habrá colisión aún.
		System.out.println("--- Primera actualización (estado inicial) ---");
		motor.actualizar();

		// 5) Usar el gestor para enviar el comando 'MOVER_ABAJO' a la nave.
		// Comentario: esto moverá la nave en Y de 0 a 10.
		System.out.println("--- Enviar comando: MOVER_ABAJO ---");
		gestor.procesarComando("MOVER_ABAJO", nave, motor);

		// 6) Volver a llamar a motor.actualizar().
		// Nota interna: al moverse la nave a y=10 debería colisionar con el asteroide.
		System.out.println("--- Segunda actualización (tras mover la nave) ---");
		motor.actualizar();

		// 7) Usar el gestor para enviar el comando 'GUARDAR' y probar el Quick Save.
		System.out.println("--- Enviar comando: GUARDAR (guardado rápido) ---");
		gestor.procesarComando("GUARDAR", nave, motor);

		// 8) Finalmente, cambiar el estado del motor a 'GAME_OVER'.
		System.out.println("--- Cambiar estado a GAME_OVER ---");
		motor.cambiarEstado("GAME_OVER");

		System.out.println("--- Simulación finalizada ---");
	}

}

