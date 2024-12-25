package main.persistence.classes;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static final String USER_DIRECTORIES = "./resources/";
    public static final String USER_INFO_FILE_NAME = "/usrInfo.bin";
    public static final String USER_SIMILARITY_MATRIX_FILE_NAME = "/simMatrix.bin";
    public static final String USER_PRODUCTS_DIRECTORIES = "/products";
    public static final String USER_SHELVES_DIRECTORIES = "/shelves";
    private static final String PRODUCT_COUNTER_FILE = "/productCounter.bin";


    public static Path getUserDirectoryPath(String username) {
        return Path.of(FileUtils.USER_DIRECTORIES + username);
    }

    public static Path getUserInfoFilePath(String username) {
        return Path.of(FileUtils.USER_DIRECTORIES + username + USER_INFO_FILE_NAME);
    }

    public static Path getUserSimMatrixFilePath(String username) {
        return Path.of(FileUtils.USER_DIRECTORIES + username + FileUtils.USER_SIMILARITY_MATRIX_FILE_NAME);
    }

    public static Path getUserProductsDirectoryPath(String username) {
        return Path.of(FileUtils.getUserDirectoryPath(username).toString() + USER_PRODUCTS_DIRECTORIES);
    }

    public static Path getUserProductPath(String username, String productFile) {
        return Path.of(FileUtils.getUserDirectoryPath(username).toString() + USER_PRODUCTS_DIRECTORIES + "/" + productFile);
    }

    public static Path getUserShelvesDirectoryPath(String username) {
        return Path.of(FileUtils.getUserDirectoryPath(username).toString() + USER_SHELVES_DIRECTORIES);
    }

    public static Path getUserShelfPath(String username, String shelfName) {
        return Path.of(FileUtils.getUserDirectoryPath(username).toString() + USER_SHELVES_DIRECTORIES + "/" + shelfName);
    }

    public  static Path getUserProductCounterPath(String username) {
        return Path.of(FileUtils.getUserDirectoryPath(username).toString() + USER_PRODUCTS_DIRECTORIES + PRODUCT_COUNTER_FILE);
    }


    public static List<String> getFileNamesInDirectory(Path path) {
        List<String> fileNames = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) { // Ensure it's a file
                    if (!entry.getFileName().toString().equals("productCounter.bin")) {
                        fileNames.add(entry.getFileName().toString()); // Agregar el nombre del archivo a la lista
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading directory: " + e.getMessage());
        }

        return fileNames;
    }

    public static boolean createDirectory(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean updateDirectoryName(Path oldPath, Path pathWithNewName) {
        try {
            Files.move(oldPath, pathWithNewName);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void deleteEmptyDirectory(Path path) throws IOException {

        Files.delete(path);
    }

    public static void deleteDirectoryWithContent(Path path) {
        try {
            if (Files.isDirectory(path)) {
                try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                    for (Path entry : entries) {
                        deleteDirectoryWithContent(entry);
                    }
                }
            }
            Files.delete(path);
        } catch (Exception e) {
            System.out.println("Error deleting directory with content");
            throw new RuntimeException(e);
        }
    }

    public static boolean createFile(Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean existsFile(Path path) {

        return Files.exists(path);
    }

    public static void deleteFile(Path path) {

    }

    public static void renameFile(Path path, String newName) {

    }

    public static boolean isPathValid(String pathString) {
        try {
            Path path = Paths.get(pathString);
            return true;
        } catch (InvalidPathException e) {
            return false;
        }
    }
}
