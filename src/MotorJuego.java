import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona la lógica interna del motor del juego en consola.
 *
 * Mantiene un estado simple del juego (MENU, JUGANDO, PAUSA, GAME_OVER)
 * y una lista de entidades activas en el nivel. Provee métodos para añadir
 * y eliminar entidades, cambiar el estado y actualizar el bucle lógico.
 */
public class MotorJuego {

	// Estado del juego: 'MENU', 'JUGANDO', 'PAUSA', 'GAME_OVER'
	private String estado;

	// Lista de entidades activas en el nivel
	private List<EntidadVideojuego> entidades;

	/**
	 * Constructor por defecto: inicia en 'MENU' y lista vacía de entidades.
	 */
	public MotorJuego() {
		this.estado = "MENU";
		this.entidades = new ArrayList<>();
	}

	/**
	 * Añade una entidad activa al motor y hace un log por consola.
	 */
	public void añadirEntidad(EntidadVideojuego e) {
		entidades.add(e);
		System.out.println("[MotorJuego] Entidad añadida: " + e.getNombre());
	}

	/**
	 * Elimina una entidad del motor y hace un log por consola.
	 */
	public void eliminarEntidad(EntidadVideojuego e) {
		if (entidades.remove(e)) {
			System.out.println("[MotorJuego] Entidad eliminada: " + e.getNombre());
		} else {
			System.out.println("[MotorJuego] Intento eliminar entidad no presente: " + e.getNombre());
		}
	}

	/**
	 * Cambia el estado del juego y registra la transición por consola.
	 */
	public void cambiarEstado(String nuevoEstado) {
		System.out.println("[MotorJuego] Estado cambiado: " + this.estado + " -> " + nuevoEstado);
		this.estado = nuevoEstado;
	}

	/**
	 * Bucle lógico de actualización. Si el estado es 'JUGANDO' imprimirá
	 * la información de cada entidad (usando toString) y luego verificará
	 * colisiones entre naves y asteroides.
	 */
	public void actualizar() {
		if (!"JUGANDO".equals(this.estado)) {
			System.out.println("[MotorJuego] actualizar() ignorado. Estado actual: " + this.estado);
			return;
		}

		// Mostrar información de cada entidad
		for (EntidadVideojuego e : entidades) {
			System.out.println("[MotorJuego] Entidad: " + e.toString());
		}

		// Verificar colisiones tras actualizar estado de entidades
		verificarColisiones();
	}

	/**
	 * Funcionalidad avanzada 1: detección de colisiones AABB entre naves y asteroides.
	 *
	 * Recorre todas las entidades buscando pares (NaveJugador, Asteroide) y aplica
	 * la intersección por rectángulos (AABB). Si hay colisión se resta 10 puntos
	 * de salud a la nave y se genera un log de alerta.
	 */
	private void verificarColisiones() {
		for (EntidadVideojuego a : entidades) {
			if (!(a instanceof Asteroide)) continue;
			Asteroide ast = (Asteroide) a;

			int ax = ast.getX();
			int ay = ast.getY();
			int aw = ast.getWidth();
			int ah = ast.getHeight();

			for (EntidadVideojuego n : entidades) {
				if (!(n instanceof NaveJugador)) continue;
				NaveJugador nave = (NaveJugador) n;

				int nx = nave.getX();
				int ny = nave.getY();
				int nw = nave.getWidth();
				int nh = nave.getHeight();

				// Comprobación AABB (Axis-Aligned Bounding Box)
				boolean intersecta = (nx < ax + aw) && (nx + nw > ax) && (ny < ay + ah) && (ny + nh > ay);

				if (intersecta) {
					// Aplicar daño y log
					int nuevaSalud = nave.getSalud() - 10;
					nave.setSalud(nuevaSalud);
					System.out.println("[ALERTA] Colisión detectada: " + nave.getNombre() + " impactado por " + ast.getNombre() + ". Salud ahora: " + nave.getSalud());
				}
			}
		}
	}

	/**
	 * Funcionalidad avanzada 2 (Quick Save): Simula un guardado rápido y
	 * devuelve un String JSON con el estado, la cantidad de entidades en pantalla
	 * y la salud de la nave principal (la primera encontrada).
	 */
	public String guardadoRapido() {
		int cantidad = entidades.size();
		Integer saludNave = null;

		for (EntidadVideojuego e : entidades) {
			if (e instanceof NaveJugador) {
				saludNave = ((NaveJugador) e).getSalud();
				break; // tomar la nave principal (la primera encontrada)
			}
		}

		if (saludNave == null) saludNave = -1; // indicador de ausencia de nave

		// Construcción simple de JSON
		String json = String.format("{\"estado\":\"%s\",\"entidades\":%d,\"saludNave\":%d}",
				this.estado, cantidad, saludNave);

		System.out.println("[MotorJuego] Guardado rápido realizado: " + json);
		return json;
	}

}
