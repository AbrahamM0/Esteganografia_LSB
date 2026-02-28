# 👁️ Esteganografía LSB en Imagen BMP 👁️

Este proyecto implementa un sistema de esteganografía utilizando la técnica LSB (Least Significant Bit) para ocultar y extraer texto dentro de imágenes en formato BMP.

El programa modifica los bits menos significativos de los píxeles de la imagen para insertar un mensaje sin que los cambios sean perceptibles a simple vista.

---

## Funcionalidades

- Ocultar un mensaje dentro de una imagen BMP.
- Extraer un mensaje oculto desde una imagen BMP.
- Uso de contraseña almacenada en la carpeta de recursos.
- Lectura y escritura de archivos binarios.

---

## Tecnologías Utilizadas

- Java
- Maven
- Manipulación de archivos binarios
- Modelo de color RGB
- Formato de imagen BMP
- Técnica de esteganografía LSB

---

## Estructura del Proyecto

```text
Proyecto/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── (clases del programa)
│       │
│       └── resources/
│           ├── imagen.bmp
│           └── contraseña.txt
│
├── pom.xml
└── README.md
```

---

## Carpeta resources

En la carpeta `resources` se encuentran:

- `imagen.bmp` → Imagen base donde se oculta o extrae el mensaje.  
- `contraseña.txt` → Archivo que contiene la contraseña utilizada por el programa.

---

## Cómo Compilar el Proyecto

Ubícate en la carpeta raíz del proyecto (donde se encuentra el archivo `pom.xml`) y ejecuta:

```bash
mvn clean compile
```

Esto compilará todas las clases del proyecto.

---

## Cómo Ejecutar el Programa

### Desde IntelliJ IDEA

1. Abrir el proyecto.
2. Localizar la clase que contiene el método `main`.
3. Hacer clic derecho.
4. Seleccionar **Run**.

### Desde la Terminal

Después de compilar, ejecutar:

```bash
mvn exec:java -Dexec.mainClass="nombre.del.paquete.ClasePrincipal"
```

Reemplaza `nombre.del.paquete.ClasePrincipal` por el nombre real de la clase principal del proyecto.

---

## Funcionamiento Interno

- Se carga la imagen BMP desde la carpeta `resources`.
- Se omiten los primeros 54 bytes del archivo (encabezado BMP).
- Se utilizan los bits menos significativos de los píxeles para insertar el mensaje.
- Para extraer el mensaje, se leen esos mismos bits y se reconstruye el texto original.
- La contraseña se valida leyendo el archivo correspondiente en `resources`.

---

> [!IMPORTANT]
> 
> - La imagen debe ser **BMP sin compresión**.
> - No se deben modificar los **primeros 54 bytes del archivo** (header BMP).
> - Si la imagen es comprimida o convertida a otro formato, el mensaje puede perderse.
> - La capacidad del mensaje depende directamente del tamaño de la imagen.

---

## Capturas de Pantalla

### 1. Preparación de Archivos

Archivos iniciales en la carpeta `resources`.

<p align="center">
  <img width="434" height="364" alt="Captura de pantalla 2026-02-27 a la(s) 11 46 29 p m" src="https://github.com/user-attachments/assets/857cb79a-5a87-4f59-9da2-edac3b553cb4" />
</p>

---

### 2. Ejecución del Programa

Proceso de ocultamiento y recuperación en consola.

<p align="center">
  <img width="862" height="612" alt="Captura de pantalla 2026-02-27 a la(s) 11 47 18 p m" src="https://github.com/user-attachments/assets/748ceb28-5f6e-4bb2-84ac-36e3c689d488" />
</p>

---

### 3. Resultado Final

Comparación de imagen y mensaje extraído.

<p align="center">
  <img width="1280" height="720" alt="Captura de pantalla 2026-02-27 a la(s) 11 49 26 p m -Photoroom" src="https://github.com/user-attachments/assets/a92b6dec-0ce0-4919-9775-7c773571147d" />
</p>

---

## Propósito Académico

Este proyecto demuestra:

- Manipulación de archivos binarios en Java.
- Comprensión de la estructura del formato BMP.
- Aplicación práctica del modelo de color RGB.
- Implementación básica de esteganografía con LSB.



<p align="center">
<img width="700" height="617" alt="Pink and Green Photo Cute   Sweet Couple Valentine&#39;s Day Mug-Photoroom" src="https://github.com/user-attachments/assets/fcc58447-91d1-4a77-8791-d824e57df867" />

</p>

