# Proyecto 3: Pick-a-Perro

### Autor

Carlos Daniel Cortés Jiménez

### Descripción del Proyecto

En este proyecto se implementa un sistema usando listas y arreglos para el juego de mesa **Pick-a-Perro**, en esta versión solo será una jugador contra un jugador artificial.

El objetivo del juego es formar la secuencia de cartas más larga posible siguiendo reglas específicas: cada carta debe ser igual a la anterior o diferir en una sola característica. Las cartas poseen cinco atributos diferentes, y hay un total de 96 cartas barajadas de manera aleatoria.

El programa gestiona las rondas, turnos de jugador humano e IA, verifica las secuencias de ambos jugadores, la puntuación y finalización del juego, para asegurar que se cumplan las reglas originales del juego. Se utilizaron arreglos y una implementación propia de listas ligadas para manejar las cartas y las secuencias de cada jugador.

El jugador humano puede eligir cartas o terminar su turno, el jugador artificial solo toma cartas si se le permite extender su secuencia y si no hay mas cartas se detiene y termina la ronda.

### Inconvenientes

No hubo inconvenientes durante el desarrollo del proyecto, solo muy confuso al principio dado que no se conocia el juego de mesa.

### Instrucciones para Ejecutar el Proyecto

1. Tener instalado Java 8 o superior.

2. Abrir una terminal y dirigirse a la carpeta **PickAPerro** que contiene los archivos `.java`.

3. Compilar todos los archivos `.java` con el siguiente comando:

   ```bash
   javac Main.java
   ```

4. Ejecutar la clase principal del proyecto con el siguiente comando:

   ```bash
   java Main
   ```

Se desplegará en la terminal el estado actual del juego, se le pedirá al usuario que tome decisiones durante su turno, como tomar una carta o deternerse, de igual forma para las siguientes rondas y se mostrará el progreso de cada jugador hasta que finalice la partida.