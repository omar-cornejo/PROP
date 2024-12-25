package main.persistence.classes;


import java.io.*;
import java.nio.file.Path;
import java.util.Objects;

public class BinaryHandler {
    private static BinaryHandler instance;

    private BinaryHandler() {
    }

    public static BinaryHandler getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BinaryHandler();
        }
        return instance;
    }

    /**
     * Saves an object to a JSON file.
     *
     * @param object   The object to save.
     * @param path The path of the file to save the object to.
     * @return false if an error occurs during saving, else true
     */
    public <T> boolean save(T object, Path path) {
        try {
            if (!Objects.isNull(object) && !Objects.isNull(path)) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
                    oos.writeObject(object);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Loads an object from a binary file.
     *
     * @param valueType The class type of the object to load.
     * @param path The path of the binary file.
     * @param <T> The type of the object.
     * @return The deserialized object.
     * @throws IOException If an I/O error occurs during reading.
     * @throws IllegalArgumentException If the path is null or the file does not exist.
     */
    public <T> T load(Class<T> valueType, Path path) throws IOException, IllegalArgumentException {
        if (Objects.isNull(path)) {
            throw new IllegalArgumentException("La ruta no debe ser nula.");
        }

        File file = path.toFile();
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("El archivo no existe o no es un archivo válido");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object object = ois.readObject();

            // Ensure the deserialized object matches the expected type
            if (valueType.isInstance(object)) {
                return valueType.cast(object);
            } else {
                throw new IllegalArgumentException("El objeto deserializado no es del tipo esperado: " + valueType.getName());
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("No se pudo deserializar el objeto: Clase no encontrada.", e);
        }
    }
}
