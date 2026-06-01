/**
 * GestorEntradas simula un InputManager sencillo para el juego.
 *
 * Provee un método para procesar comandos en forma de String que afectan
 * a la nave del jugador o al estado del motor (pausar/guardar).
 */
public class GestorEntradas {

	/**
	 * Procesa un comando recibido como String. Dependiendo del comando se
	 * modifica la posición de la nave, se cambia el estado del motor o se
	 * solicita un guardado rápido.
	 *
	 * Comandos soportados:
	 * - "MOVER_ARRIBA": resta 10 a la coordenada Y de la nave.
	 * - "MOVER_ABAJO": suma 10 a la coordenada Y de la nave.
	 * - "PAUSAR": cambia el estado del motor a 'PAUSA'.
	 * - "GUARDAR": llama a guardadoRapido() del motor e imprime el JSON.
	 *
	 * @param comando el comando a procesar
	 * @param nave la nave del jugador a afectar
	 * @param motor instancia del MotorJuego para acciones globales
	 */
	public void procesarComando(String comando, NaveJugador nave, MotorJuego motor) {
		if (comando == null) {
			System.out.println("Comando no reconocido");
			return;
		}

		switch (comando) {
			case "MOVER_ARRIBA": {
				int nuevaY = nave.getY() - 10;
				nave.setY(nuevaY);
				System.out.println("[Input] MOVER_ARRIBA -> nueva posición Y: " + nave.getY());
				break;
			}
			case "MOVER_ABAJO": {
				int nuevaY = nave.getY() + 10;
				nave.setY(nuevaY);
				System.out.println("[Input] MOVER_ABAJO -> nueva posición Y: " + nave.getY());
				break;
			}
			case "PAUSAR": {
				motor.cambiarEstado("PAUSA");
				break;
			}
			case "GUARDAR": {
				String json = motor.guardadoRapido();
				System.out.println("[Input] Guardado rápido -> " + json);
				break;
			}
			default: {
				System.out.println("Comando no reconocido");
				break;
			}
		}
	}

}

