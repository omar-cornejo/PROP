#!/bin/bash

# Directorio de salida
OUTPUT_DIR="build"

# Rutas a los archivos JAR (asegúrate de que existen en estas ubicaciones)
JUNIT_JAR="lib/junit-4.13.1.jar"
HAMCREST_JAR="lib/hamcrest-core-1.3.jar"

# Verificar que los JARs existen
if [ ! -f "$JUNIT_JAR" ]; then
    echo "Error: No se encontró $JUNIT_JAR. Verifica que esté en la ruta especificada."
    exit 1
fi

if [ ! -f "$HAMCREST_JAR" ]; then
    echo "Error: No se encontró $HAMCREST_JAR. Verifica que esté en la ruta especificada."
    exit 1
fi

# Limpiar el directorio de salida
if [ -d "$OUTPUT_DIR" ]; then
    echo "Limpiando el directorio $OUTPUT_DIR..."
    rm -rf "$OUTPUT_DIR"
fi
mkdir -p "$OUTPUT_DIR"

# CLASSPATH con las rutas a los JARs
CLASSPATH="$JUNIT_JAR:$HAMCREST_JAR"

# Compilar los archivos .java
echo "Compilando los archivos .java..."
javac -d "$OUTPUT_DIR" -sourcepath src -cp "$CLASSPATH" $(find src -name "*.java")

# Verificar si la compilación fue exitosa
if [ $? -eq 0 ]; then
    echo "Compilación exitosa. Archivos generados en $OUTPUT_DIR."
else
    echo "Error en la compilación."
    exit 1
fi

# Finalización
echo "El script ha terminado."