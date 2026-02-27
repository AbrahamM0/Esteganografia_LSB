# 📌 Esteganografía LSB en Imagen BMP

## 📖 Descripción

Este proyecto implementa un sistema de esteganografía utilizando la técnica LSB (Least Significant Bit) para ocultar y extraer texto dentro de imágenes en formato BMP.

El programa modifica los bits menos significativos de los píxeles de la imagen para insertar un mensaje sin que los cambios sean perceptibles a simple vista.

---

## 🎯 Funcionalidades

- Ocultar un mensaje dentro de una imagen BMP.
- Extraer un mensaje oculto desde una imagen BMP.
- Uso de contraseña almacenada en la carpeta de recursos.
- Lectura y escritura de archivos binarios.

---

## 🛠 Tecnologías Utilizadas

- Java
- Maven
- Manipulación de archivos binarios
- Modelo de color RGB
- Formato de imagen BMP
- Técnica de esteganografía LSB

---

## 📂 Estructura del Proyecto

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

---

## 📁 Carpeta resources

En la carpeta `resources` se encuentran:

- imagen.bmp → Imagen base donde se oculta o extrae el mensaje.
- contraseña.txt → Archivo que contiene la contraseña utilizada por el programa.

---

## ⚙️ Cómo Compilar el Proyecto

Ubícate en la carpeta raíz del proyecto (donde se encuentra el archivo `pom.xml`) y ejecuta:

mvn clean compile

Esto compilará todas las clases del proyecto.

---

## ▶️ Cómo Ejecutar el Programa

### Desde IntelliJ IDEA

1. Abrir el proyecto.
2. Localizar la clase que contiene el método `main`.
3. Hacer clic derecho.
4. Seleccionar Run.

---

### Desde la Terminal

Después de compilar, ejecutar:

mvn exec:java -Dexec.mainClass="nombre.del.paquete.ClasePrincipal"

Reemplaza `nombre.del.paquete.ClasePrincipal` por el nombre real de la clase principal del proyecto.

---

## 🔎 Funcionamiento Interno

1. Se carga la imagen BMP desde la carpeta `resources`.
2. Se omiten los primeros 54 bytes del archivo (encabezado BMP).
3. Se utilizan los bits menos significativos de los píxeles para insertar el mensaje.
4. Para extraer el mensaje, se leen esos mismos bits y se reconstruye el texto original.
5. La contraseña se valida leyendo el archivo correspondiente en `resources`.

---

## ⚠️ Consideraciones

- La imagen debe ser BMP sin compresión.
- No se deben modificar los primeros 54 bytes del archivo (header).
- Si la imagen es comprimida o convertida a otro formato, el mensaje puede perderse.
- La capacidad del mensaje depende del tamaño de la imagen.

---

## 🎓 Propósito Académico

Este proyecto demuestra:

- Manipulación de archivos binarios en Java.
- Comprensión de la estructura del formato BMP.
- Aplicación práctica del modelo de color RGB.
- Implementación básica de esteganografía con LSB.
