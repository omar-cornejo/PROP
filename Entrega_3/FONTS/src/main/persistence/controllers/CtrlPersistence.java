package main.persistence.controllers;

import main.domain.classes.DTO.ProductDTO;
import main.domain.classes.DTO.ShelfDTO;
import main.domain.classes.DTO.SimilarityMatrixDTO;
import main.domain.classes.DTO.UserDTO;
import main.persistence.classes.BinaryHandler;
import main.persistence.classes.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CtrlPersistence {
    private static CtrlPersistence instance;
    private final BinaryHandler binaryHandler;

    private CtrlPersistence() {
        binaryHandler = BinaryHandler.getInstance();
    }

    public static CtrlPersistence getInstance() {
        if (Objects.isNull(instance)) {
            instance = new CtrlPersistence();
        }
        return instance;
    }

    public final boolean existsUser(String username) {
        return Files.isDirectory(FileUtils.getUserDirectoryPath(username));
    }

    public final boolean createUser(UserDTO user) {
        if (FileUtils.createDirectory(FileUtils.getUserDirectoryPath(user.getUsername()))) {
            Path fileUserInfoPath = FileUtils.getUserInfoFilePath(user.getUsername());
            if (FileUtils.createFile(fileUserInfoPath)) {
                if (binaryHandler.save(user, fileUserInfoPath)) {
                    return true;
                }
            }
        }
        // Abort operation -> reverse changes
        if (Files.exists(FileUtils.getUserDirectoryPath(user.getUsername()))) {
            FileUtils.deleteDirectoryWithContent(FileUtils.getUserDirectoryPath(user.getUsername()));
        }
        return false;
    }

    public final boolean updateUserUsername(UserDTO user, String oldUsername) {
        Path oldUserPath = FileUtils.getUserDirectoryPath(oldUsername);
        Path newUserPath = FileUtils.getUserDirectoryPath(user.getUsername());
        if (Files.isDirectory(oldUserPath)) {
            if (FileUtils.updateDirectoryName(oldUserPath, newUserPath)) {
                Path fileUserInfoPath = FileUtils.getUserInfoFilePath(user.getUsername());
                if (binaryHandler.save(user, fileUserInfoPath)) {
                    return true;
                } else {
                    // Abort operation -> reverse changes
                    FileUtils.updateDirectoryName(newUserPath, oldUserPath);
                }
            }
        }
        return false;
    }

    public final boolean updateUserPassword(UserDTO user) {
        Path userPath = FileUtils.getUserDirectoryPath(user.getUsername());
        if (Files.isDirectory(userPath)) {
            Path userInfoPath = FileUtils.getUserInfoFilePath(user.getUsername());
            if (binaryHandler.save(user, userInfoPath)) return true;
        }
        return false;
    }

    public final UserDTO getUser(String username) {
        Path path = FileUtils.getUserInfoFilePath(username);
        try {
            return binaryHandler.load(UserDTO.class, path);
        } catch (Exception e) {
            return null;
        }
    }

    public final boolean updateUserSimMatrix(SimilarityMatrixDTO similarityMatrixDTO, String username) {
        Path path = FileUtils.getUserSimMatrixFilePath(username);
        return binaryHandler.save(similarityMatrixDTO, path);
    }

    public final SimilarityMatrixDTO getUserSimMatrix(String username) {
        Path path = FileUtils.getUserSimMatrixFilePath(username);
        try {
            return binaryHandler.load(SimilarityMatrixDTO.class, path);
        } catch (IOException e) {
            return null;
        }
    }

    public final boolean userHasSimMatrix(String username) {
        return Files.exists(FileUtils.getUserSimMatrixFilePath(username));
    }

    /**
     * Carga el productIdCounter desde el archivo binario.
     *
     * @param username Nombre del usuario.
     * @return El valor del productIdCounter o 0 si no existe el archivo.
     */
    public int loadProductCounter(String username) {
        Path counterPath = Path.of(FileUtils.getUserProductCounterPath(username).toString());
        System.out.println("[DEBUG] Ruta del archivo del contador: " + counterPath);

        if (FileUtils.existsFile(counterPath)) {
            System.out.println("[DEBUG] El archivo del contador existe. Intentando cargar el valor...");
            try {
                int counter = binaryHandler.load(Integer.class, counterPath);
                System.out.println("[DEBUG] Contador cargado exitosamente: " + counter);
                return counter;
            } catch (IOException e) {
                System.err.println("[ERROR] Error al cargar el contador desde el archivo: " + e.getMessage());
                return 0;
            }
        } else {
            System.out.println("[DEBUG] El archivo del contador no existe. Creando uno nuevo...");
            try {
                Files.createFile(counterPath);
                System.out.println("[DEBUG] Archivo del contador creado exitosamente. Inicializando a 0.");
                return 0;
            } catch (IOException e) {
                System.err.println("[ERROR] Error al crear el archivo del contador: " + e.getMessage());
                return 0;
            }
        }
    }

    /**
     * Guarda el productIdCounter en un archivo binario.
     *
     * @param username Nombre del usuario.
     * @param productIdCounter Valor actual del productIdCounter.
     */
    public void saveProductCounter(String username, int productIdCounter) {
        Path counterPath = Path.of(FileUtils.getUserProductCounterPath(username).toString());
        try {
            binaryHandler.save(productIdCounter, counterPath);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el productIdCounter", e);
        }
    }

    public final void createUserProductDirectory(String username) {
        Path pathOfProductDirectory = FileUtils.getUserProductsDirectoryPath(username);
        try {
            if(!(Files.exists(pathOfProductDirectory))) Files.createDirectories(pathOfProductDirectory);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el directorio de productos para el usuario");
        }
    }



    public final List<ProductDTO> getUserProducts(String username) {
        List<ProductDTO> userProducts = new ArrayList<>();
        Path productsPath = FileUtils.getUserProductsDirectoryPath(username);

        if (Files.exists(productsPath)) {
            List<String> productNames = FileUtils.getFileNamesInDirectory(productsPath);

            productNames.forEach(productFile -> {
                Path productPath = FileUtils.getUserProductPath(username, productFile);
                try {
                    ProductDTO currentProduct = binaryHandler.load(ProductDTO.class, productPath);
                    userProducts.add(currentProduct);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return userProducts;
    }

    public final boolean addProductToUser(ProductDTO productDTO, String username) {
        Path productPath = Path.of(FileUtils.getUserProductPath(username, productDTO.getName()).toString() + ".bin");
        try {
            if (!Files.exists(productPath)) Files.createFile(productPath);
            else {
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el archivo del producto");
        }
        return binaryHandler.save(productDTO, productPath);
    }

    public final boolean removeProductOfUser(String productName, String username) {
        Path productPath = Path.of(FileUtils.getUserProductPath(username, productName).toString() + ".bin");
        try {
            Files.deleteIfExists(productPath);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public final void createUserShelvesDirectory(String username) {
        Path pathOfShelvesDirectory = FileUtils.getUserShelvesDirectoryPath(username);
        try {
            Files.createDirectories(pathOfShelvesDirectory);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el directorio de estanterías para el usuario");
        }
    }

    public final boolean addShelfToUser(ShelfDTO shelfDTO, String username) {
        Path shelfPath = Path.of(FileUtils.getUserShelfPath(username, shelfDTO.getName()).toString() + ".bin");
        try {
            if (!Files.exists(shelfPath)) {
                Files.createFile(shelfPath);
            } else {
                return false; // El archivo ya existe
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el archivo de estantería", e);
        }
        return binaryHandler.save(shelfDTO, shelfPath);
    }

    public final boolean removeShelfOfUser(String shelfname, String username) {
        Path shelfPath = Path.of(FileUtils.getUserShelfPath(username, shelfname).toString() + ".bin");
        try {
            Files.deleteIfExists(shelfPath);
            return true;
        } catch (IOException e) {
            return false;
        }
    }



    /**
     * Obtiene una estantería de un usuario.
     *
     * @param shelfName Nombre de la estantería.
     * @param username  Nombre del usuario.
     * @return ShelfDTO con los detalles de la estantería, o null si no existe.
     */
    public ShelfDTO getShelfOfUser(String shelfName, String username) {
        Path shelfPath = Path.of(FileUtils.getUserShelfPath(username, shelfName).toString() + ".bin");
        if (!Files.exists(shelfPath)) {
            throw new IllegalArgumentException("El archivo de la estantería no existe: ");
        }
        try {
            return binaryHandler.load(ShelfDTO.class, shelfPath);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de la estantería: ", e);
        }
    }

    public final List<ShelfDTO> getUserShelves(String username) {
        List<ShelfDTO> userProducts = new ArrayList<>();
        Path shelvesDirectoryPath = FileUtils.getUserShelvesDirectoryPath(username);

        if (Files.exists(shelvesDirectoryPath)) {
            List<String> ShelvesNames = FileUtils.getFileNamesInDirectory(shelvesDirectoryPath);

            ShelvesNames.forEach(shelfFile -> {
                Path ShelfPath = FileUtils.getUserShelfPath(username, shelfFile);
                try {
                    ShelfDTO currentProduct = binaryHandler.load(ShelfDTO.class, ShelfPath);
                    userProducts.add(currentProduct);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return userProducts;
    }

    public final boolean existsShelf(String shelfName, String username) {
        return Files.isDirectory(FileUtils.getUserShelfPath(shelfName, username));
    }

}
