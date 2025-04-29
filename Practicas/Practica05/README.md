# Práctica05 - Clases abstractas,polimorfismo. 

## Autor

- Carlos Daniel Cortés Jiménez

## Uso de herencia y clases abstractas

- Definimos una superclase abstracta `FiguraGeometrica` que recopila comportamientos comunes como:
  - Métodos abstractos `calcularArea()` y `calcularPerimetro()`.
  - Método `toString()` también es abstracto porque cada figura debe describir su propia configuración de lados y dimensiones, a continuación decimos el por que de la elección.

- **Decisión sobre hacer abstracto a `toString()`**:
  
  - Hacer a `toString()` abstracto hacemos que las subclases puedan proporcionar su propia representación de la figura, y así mostrar exactamente las medidas de cada lado.
  
  - Por otro lado, si hiceramos que `toString()` estuviera en la superclase, no podríamos defnir en especifico cada figura con sus respectivos detalles, y si fuera así ocupariamos recurrir a comprobaciones de tipo (`instanceof`), pero esto solo rompería el principio de abierto/cerrado.

## Extra

- El punto extra se encuentra dentro de los códigos

## Instrucciones para ejecutar el programa.

1. **Compilar el programa:**
   - Primero abre una terminal en la carpeta Practica05 donde se encuentre el archivos `.java`.
   - En seguida introduzca el siguiente comando para compilar los archivos:
     ```bash
     javac Main.java
     ```

2. **Ejecutar el programa:**
   - Una vez tengamos los archivos compilados, introduzca el siguiente comando:
     ```bash
     java Main
     ```

3. **Funcionamiento del programa:**
   - Se mostrará en terminal el funcionamiento de todos los métodos solicitados de la práctica.


