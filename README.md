# 🚀 Motor de Juego de Naves Espaciales

**Temática Elegida:** Juego de naves espaciales estilo arcade 2D. El jugador controla una nave para evitar o destruir obstáculos y enemigos en un entorno espacial simulado por consola.

## 🏗️ Arquitectura del Software

Para este proyecto se ha utilizado un diseño orientado a objetos minimalista, respetando la restricción estricta de un máximo de 6 clases. La arquitectura elegida permite una alta cohesión y bajo acoplamiento:
* **Main**: Punto de entrada que simula el bucle de juego.
* **MotorJuego**: Clase central ("Cerebro") que gestiona el estado general y la lista de entidades.
* **GestorEntradas**: Procesa los comandos del jugador.
* **EntidadVideojuego**: Clase abstracta que define la estructura básica de cualquier objeto en pantalla.
* **NaveJugador** y **Asteroide**: Clases concretas que heredan de la entidad base.

## 📊 Diagrama de Clases UML

A continuación se detalla la estructura y relación de las clases programadas:

```mermaid
classDiagram
    class EntidadVideojuego {
        <<abstract>>
        -int x
        -int y
        -int width
        -int height
        -String nombre
        -int salud
        -String imagen
        +EntidadVideojuego(x, y, w, h, nombre, salud, imagen)
        +getX() int
        +setX(x)
        +getY() int
        +setY(y)
        +getSalud() int
        +setSalud(salud)
        +toString() String
    }

    class NaveJugador {
        +NaveJugador(int x, int y)
    }

    class Asteroide {
        +Asteroide(int x, int y)
    }

    class MotorJuego {
        -String estadoJuego
        -List~EntidadVideojuego~ entidades
        +MotorJuego()
        +añadirEntidad(EntidadVideojuego e)
        +eliminarEntidad(EntidadVideojuego e)
        +cambiarEstado(String nuevoEstado)
        +actualizar()
        -verificarColisiones()
        +guardadoRapido() String
    }

    class GestorEntradas {
        +procesarComando(String comando, NaveJugador nave, MotorJuego motor)
    }

    class Main {
        +main(String[] args)$
    }

    EntidadVideojuego <|-- NaveJugador : Herencia
    EntidadVideojuego <|-- Asteroide : Herencia
    MotorJuego o-- EntidadVideojuego : Agregación
    GestorEntradas ..> MotorJuego : Usa
    Main ..> MotorJuego : Instancia
    Main ..> GestorEntradas : Instancia
  ```
## 👥 Diagrama de Casos de Uso UML

A continuación se representa la interacción del jugador con el sistema principal:

```mermaid
flowchart LR
    Actor([Jugador])
    
    CU1((CU-01: Mover Nave))
    CU2((CU-02: Guardado Rápido))
    CU3((CU-03: Pausar Juego))

    Actor --- CU1
    Actor --- CU2
    Actor --- CU3

 ## 📝 Especificación de Casos de Uso

A continuación se detallan 2 casos de uso clave implementados en el motor:

### Caso de Uso 1: Mover Nave (y Colisión)

| Campo | Descripción |
| :--- | :--- |
| **Nombre** | CU-01 Mover Nave |
| **Objetivo** | Desplazar la nave del jugador por el escenario actualizando sus coordenadas. |
| **Actor Principal** | Jugador |
| **Precondiciones** | El motor del juego debe estar en estado 'JUGANDO' y la nave instanciada. |
| **Flujo Principal** | 1. El jugador introduce un comando de movimiento (ej. 'MOVER_ABAJO').<br>2. El `GestorEntradas` procesa el comando y actualiza la coordenada Y de la nave.<br>3. El `MotorJuego` llama a `actualizar()`.<br>4. Se verifica si la nueva posición coincide con un obstáculo. |
| **Flujos Alternativos** | Si en el paso 4 se detecta una colisión (Intersección AABB), se restan 10 puntos de salud a la nave y se lanza una alerta. |
| **Postcondiciones** | La nave tiene unas nuevas coordenadas y la pantalla simula la actualización. |
| **Reglas de Negocio** | La nave no puede moverse si el estado es distinto a 'JUGANDO'. |

### Caso de Uso 2: Guardado Rápido (Quick Save)

| Campo | Descripción |
| :--- | :--- |
| **Nombre** | CU-02 Guardado Rápido Simulado |
| **Objetivo** | Exportar el estado actual de la partida a un formato de texto estructurado. |
| **Actor Principal** | Jugador |
| **Precondiciones** | Debe haber una partida iniciada con al menos una entidad (la nave) cargada. |
| **Flujo Principal** | 1. El jugador introduce el comando 'GUARDAR'.<br>2. El `GestorEntradas` invoca el método `guardadoRapido()` del motor.<br>3. El motor recopila el estado actual, número de entidades y salud.<br>4. El sistema imprime una cadena formateada en JSON simulando el archivo de guardado. |
| **Flujos Alternativos** | N/A |
| **Postcondiciones** | Se genera un log por consola con los datos persistentes listos para ser guardados. |
| **Reglas de Negocio** | El formato de salida debe mantener una estructura JSON estricta. |

---

## 🤖 Bitácora del Uso de Inteligencia Artificial

### Herramienta utilizada y Rol
Se ha utilizado **GitHub Copilot** (integrado en VS Code) actuando bajo el rol de *Desarrollador Java Experto* para la generación de la estructura de clases y algoritmos matemáticos (colisiones).

### Muestra de Prompts
Para mantener el control del proyecto, se utilizaron prompts estructurados y restrictivos.
**Prompt 1 (Clase Base):**
> "Actúa como un desarrollador Java experto. Crea el código para el archivo `EntidadVideojuego.java`. Esta debe ser una clase abstracta que represente la base de cualquier elemento de un juego 2D. Debe cumplir estas restricciones estrictas: Atributos privados: coordenadas (x, y) de tipo int, dimensiones (width, height)..."

**Prompt 2 (Clases Hijas):**
> "Ahora, genera el código para dos clases hijas en Java: `NaveJugador` y `Asteroide`. Ambas deben heredar (extends) de la clase abstracta `EntidadVideojuego`. Crea un constructor que reciba las coordenadas (x, y) y llama a super() con valores por defecto..."

### Control de Errores de la IA y Corrección
Durante la creación de las clases base, la IA sugirió proactivamente y se ofreció a generar clases de pruebas con JUnit y una clase `Main` provisional de testeo. Esto supuso un grave riesgo de **sobre-ingeniería**, ya que la rúbrica del proyecto establecía una restricción técnica inamovible de un máximo de 6 clases. Se le ordenó rectificar mediante un prompt correctivo: *"Sí añade el toString(), pero NO crees ninguna clase de tests JUnit ni ninguna clase Main de prueba, ya que tengo una restricción estricta de un máximo de 6 clases"*. Yo mismo me encargué de orquestar la clase conductora final.

### Reflexión Crítica
**Ventajas:** Programar este motor bajo presión de tiempo con asistencia de IA ha permitido delegar la escritura del código "boilerplate" (getters, setters, constructores) y de lógicas matemáticas puras (intersección de rectángulos AABB para colisiones). Esto me ha permitido centrarme como desarrollador en la arquitectura y el diseño UML.
**Peligros:** El principal peligro es la pérdida de control del alcance del proyecto. Las IAs generativas de código tienden a intentar "completar" el framework añadiendo interfaces y patrones de diseño innecesarios que pueden hacerte suspender si existen restricciones técnicas estrictas (como el límite de clases de este examen).
