package main.presentation.controllers;

import main.domain.controllers.CtrlDomain;
import main.domain.enums.AlgorithmType;
import main.presentation.views.*;
import main.presentation.views.PantallaRegistro;
import main.presentation.views.InicioApp;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class CtrlPresentacio {

    private CtrlDomain domain; // Controlador de dominio
    private CardLayout cardLayout; // Layout para cambiar entre pantallas
    private JPanel mainPanel; // Panel principal que contiene todas las pantallas
    private JFrame mainFrame; // Ventana principal

    public CtrlPresentacio() {
        domain = CtrlDomain.getInstance(); // Obtener instancia del controlador de dominio
    }

    public void iniPresentacio() {
        // Crear el marco principal
        mainFrame = new JFrame("Pantalla de Inicio");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(900, 500);
        mainFrame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Configurar CardLayout para cambiar entre pantallas
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Crear la pantalla de inicio
        InicioApp inicioApp = new InicioApp(this);
        mainPanel.add(inicioApp.getPanel(), "Inicio");

        // Crear y agregar la pantalla de registro
        PantallaRegistro pantallaRegistro = new PantallaRegistro(this);
        mainPanel.add(pantallaRegistro.getPanel(), "Registro");

        // Crear y agregar la pantalla de login
        PantallaLogin pantallaLogin = new PantallaLogin(this);
        mainPanel.add(pantallaLogin.getPanel(), "Login");

        // Crear y agregar la pantalla de usuario
        PantallaUser pantallaUser = new PantallaUser(this);
        mainPanel.add(pantallaUser.getPanel(), "User");

        PantallaModificarUser pantallaModificarUser = new PantallaModificarUser(this);
        mainPanel.add(pantallaModificarUser.getPanel(), "ModificarUser");

        // Mostrar la pantalla de inicio al comenzar
        cardLayout.show(mainPanel, "Inicio");

        // Agregar el panel principal al marco
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

    }


    public boolean productExists(String productName) {
        try {
            List<String> productNames = domain.getProductNamesForUser(domain.getUsernameSelected());
            return productNames.contains(productName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainPanel, "Error al verificar la existencia del producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void mostrarPantallaSetSimilarity() {
        String username = domain.getUsernameSelected();
        if (username == null) {
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }

        try {
            List<String> productNames = domain.getProductNamesForUser(username);
            if (productNames.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "El usuario no tiene productos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainPanel, "Error al verificar los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PantallaSetSimilarity pantallaSetSimilarity = new PantallaSetSimilarity(this);
        mainPanel.add(pantallaSetSimilarity.getPanel(), "SetSimilarity");
        cardLayout.show(mainPanel, "SetSimilarity");
    }

    public void mostrarPantallaRemoveSimilarity() {
        String username = domain.getUsernameSelected();
        if (username == null) {
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }

        try {
            List<String> productNames = domain.getProductNamesForUser(username);
            if (productNames.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "El usuario no tiene productos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainPanel, "Error al verificar los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PantallaRemoveSimilarity pantallaRemoveSimilarity = new PantallaRemoveSimilarity(this);
        mainPanel.add(pantallaRemoveSimilarity.getPanel(), "RemoveSimilarity");
        cardLayout.show(mainPanel, "RemoveSimilarity");
    }

    public void mostrarPantallaGetSimilarity() {
        String username = domain.getUsernameSelected();
        if (username == null) {
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }

        try {
            List<String> productNames = domain.getProductNamesForUser(username);
            if (productNames.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "El usuario no tiene productos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainPanel, "Error al verificar los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PantallaGetSimilarity pantallaGetSimilarity = new PantallaGetSimilarity(this);
        mainPanel.add(pantallaGetSimilarity.getPanel(), "GetSimilarity");
        cardLayout.show(mainPanel, "GetSimilarity");
    }

    public void mostrarPantallaSimilitudes() {
        String username = domain.getUsernameSelected();
        if (username == null) {
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }

        PantallaSimilitudes pantallaSimilitudes = new PantallaSimilitudes(this);
        mainPanel.add(pantallaSimilitudes.getPanel(), "Similitudes");
        cardLayout.show(mainPanel, "Similitudes");
    }

    public boolean setProductSimilarityForUser(String productName1, String productName2, double newSimilarity) {
        return domain.setProductSimilarityForUser(productName1, productName2, newSimilarity);
    }

    public boolean removeProductSimilarityForUser(String productName1, String productName2) {
        return domain.removeProductSimilarityForUser(productName1, productName2);
    }

    public double getProductSimilarityForUser(String productName1, String productName2) {
        return domain.getProductSimilarityForUser(productName1, productName2);
    }

    //Inicio
    public void mostrarPantallaRegistro() {
        cardLayout.show(mainPanel, "Registro");
        mainFrame.setTitle("Registro de Usuario");
    }

    public void mostrarPantallaLogin() {
        cardLayout.show(mainPanel, "Login");
        mainFrame.setTitle("Inicio de Sesión");
    }

    public boolean modificarUsuario(String username, String actualPassword, String newPassword) {
        boolean usernameUpdated = true;
        boolean passwordUpdated = true;

        if (!username.isEmpty()) {
            usernameUpdated = domain.updateUsername(username,actualPassword);
        }

        if (!newPassword.isEmpty()) {
            passwordUpdated = domain.updatePassword(actualPassword, newPassword);
        }

        return usernameUpdated && passwordUpdated;
    }

    //Usuario

    public boolean registerUser(String name, String username, String password) {
        return domain.registerUser(name, username, password);
    }

    public boolean loginUser(String username, String password) {
        return domain.loginUser(username, password);
    }

    public void mostrarPantallaUsuario() {
        try {
            String username = domain.getUsernameSelected(); // Obtener el usuario autenticado
            setTitulo("Usuario: " + username); // Configurar el título
        } catch (Exception e) {
            setTitulo("Pantalla de Usuario"); // Título genérico en caso de error
            System.err.println("Error al obtener el nombre del usuario: " + e.getMessage());
        }
        cardLayout.show(mainPanel, "User"); // Cambiar a la pantalla de usuario
    }

    public void setTitulo(String title) {
        mainFrame.setTitle(title);
    }

    public void mostrarPantallaShelves() {
        String username = domain.getUsernameSelected();
        if (username == null) { // Asegúrate de que el usuario esté autenticado
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }

        PantallaShelves pantallaShelves = new PantallaShelves(this);
        mainPanel.add(pantallaShelves.getPanel(), "Shelves");
        cardLayout.show(mainPanel, "Shelves");
    }

    public void mostrarPantallaProductos() {
        String username = domain.getUsernameSelected();
        if (username == null) { // Asegúrate de que el usuario esté autenticado
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }

        PantallaProductos pantallaProductos = new PantallaProductos(this);
        mainPanel.add(pantallaProductos.getPanel(), "Productos");
        cardLayout.show(mainPanel, "Productos");
    }

    public void mostrarPantallaModificarUsuario() {
        cardLayout.show(mainPanel, "ModificarUser");
        mainFrame.setTitle("Modificar User");
    }

    public void cerrarSesion() {
        domain.logoutUser(); // Cerrar sesión en CtrlDomain
        mostrarPantallaInicio(); // Volver a la pantalla de inicio
    }

    public void mostrarPantallaInicio() {
        cardLayout.show(mainPanel, "Inicio");
        mainFrame.setTitle("Pantalla de Inicio");
    }

    /*

     **Pantalla Productos**

     */
    public void updateProductUser(String productName, int newPrice) throws Exception {
        domain.updateProductUser(productName, newPrice);
    }

    public java.util.List<String> getProductNamesForUser() throws Exception {
        return domain.getProductNamesForUser(domain.getUsernameSelected());
    }

    public String getProductAttributesUser(String productName) throws Exception {
        return domain.getProductAttributesUser(productName);
    }

    public void removeProductUser(String productName) throws Exception {
        domain.removeProductUser(productName);
    }

    public boolean addProductToUser(String productName, int productPrice) throws Exception {
        return domain.addProductUser(productName, productPrice);
    }

    public void mostrarPantallaAddProduct() {
        PantallaAddProductUser pantallaAddProduct = new PantallaAddProductUser( this);
        mainPanel.add(pantallaAddProduct.getPanel(), "AddProduct");
        cardLayout.show(mainPanel, "AddProduct");
    }

    public void mostrarPantallaDeleteProduct() {
        PantallaDeleteProductUser pantallaDelProduct = new PantallaDeleteProductUser( this);
        mainPanel.add(pantallaDelProduct.getPanel(), "DeleteProduct");
        cardLayout.show(mainPanel, "DeleteProduct");
    }

    public void mostrarPantallaUpdateProduct() {
        PantallaUpdateProductUser pantallaUpdateProductUser = new PantallaUpdateProductUser( this);
        mainPanel.add(pantallaUpdateProductUser.getPanel(), "UpdateProduct");
        cardLayout.show(mainPanel, "UpdateProduct");
    }

    public void mostrarPantallaGetProductAttributes() {
        PantallaGetAttribProduct pantallaGetAttribProduct = new PantallaGetAttribProduct(this);
        mainPanel.add(pantallaGetAttribProduct.getPanel(), "GetAttribs");
        cardLayout.show(mainPanel, "GetAttribs");
    }

    public void mostrarPantallaGetProductNamesForUser() {
        try {
            List<String> productsNames = getProductNamesForUser();
            if(productsNames.size()==0){
                JOptionPane.showMessageDialog(mainPanel, "El usuario con nombre de usuario '" + domain.getUsernameSelected() + "' no tiene productos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            PantallaGetNamesProducts pantallaGetNamesProducts = new PantallaGetNamesProducts( this);
            mainPanel.add(pantallaGetNamesProducts.getPanel(), "GetNames");
            cardLayout.show(mainPanel, "GetNames");
        } catch (Exception exce) {
            JOptionPane.showMessageDialog(mainPanel, exce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    //En PantallaAddShelf
    public boolean organizeShelf(String shelfName, AlgorithmType algorithmType) throws Exception {
        return domain.organizeProductsAndCreateShelf(shelfName, algorithmType);
    }

    public java.util.List<String> getProductsInShelf(String shelfName) throws Exception {
        return domain.getProductsInUserShelf(shelfName);
    }

    public void removeShelfForUser(String shelfName) throws Exception {
        domain.removeShelfForUser(shelfName); // Llama al método correspondiente en CtrlDomain
    }

    public void removeProductFromShelf(String shelfName, String productName) throws Exception {
        domain.removeProductFromShelfForUser(shelfName, productName);
    }

    public java.util.List<Integer> getProductIdsForUser() throws Exception {
        return domain.getProductIdsForUser(domain.getUsernameSelected());
    }

    public void addShelfForUser(String shelfName, java.util.List<String> products) throws Exception {
        domain.addShelfForUser(shelfName, products);
    }

    public boolean addProductToShelf(String shelfName, String productName) throws Exception {
        java.util.List<String> productsInShelf = domain.getProductsInUserShelf(shelfName); // Asegúrate de que este método exista en CtrlDomain
        if (productsInShelf.contains(productName)) {
            throw new Exception("El producto ya está en la estantería.");
        }

        java.util.List<String> userProducts = domain.getProductNamesForUser(domain.getUsernameSelected()); // Valida que getUsernameSelected y getProductIdsForUser existan
        if (!userProducts.contains(productName)) {
            throw new Exception("El producto no está en su inventario.");
        }

        domain.addProductToShelfForUser(shelfName, productName);
        return true;
    }


    public void mostrarPantallaAddShelf() {
        String username = domain.getUsernameSelected();
        if (username == null) { // Asegúrate de que el usuario esté autenticado
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }
        PantallaAddShelf pantallaAddShelf = new PantallaAddShelf(this);
        mainPanel.add(pantallaAddShelf.getPanel(), "AddShelf");
        cardLayout.show(mainPanel, "AddShelf"); // Cambiar a la pantalla de Añadir Estantería

    }

    public void mostrarPantallaGetShelf() {
        String username = domain.getUsernameSelected();
        if (username == null) { // Asegúrate de que el usuario esté autenticado
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }
        PantallaGetShelf pantallaGetShelf = new PantallaGetShelf(this);
        mainPanel.add(pantallaGetShelf.getPanel(), "GetShelf");
        cardLayout.show(mainPanel, "GetShelf"); // Cambiar a la pantalla de Consultar Estantería
    }

    public void mostrarPantallaDeleteShelf() {
        String username = domain.getUsernameSelected();
        if (username == null) { // Asegúrate de que el usuario esté autenticado
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }
        PantallaDeleteShelf pantallaDeleteShelf = new PantallaDeleteShelf(this);
        mainPanel.add(pantallaDeleteShelf.getPanel(), "DeleteShelf");
        cardLayout.show(mainPanel, "DeleteShelf"); // Cambiar a la pantalla de Eliminar Estantería
    }

    public void mostrarPantallaAddProductsToShelf() {
        String username = domain.getUsernameSelected();
        if (username == null) { // Asegúrate de que el usuario esté autenticado
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }
        PantallaAddProductsToShelf pantallaAddProductsToShelf = new PantallaAddProductsToShelf(this);
        mainPanel.add(pantallaAddProductsToShelf.getPanel(), "AddProductsToShelf");
        cardLayout.show(mainPanel, "AddProductsToShelf"); // Cambiar a la pantalla de Añadir Producto a Estantería
    }

    public void mostrarPantallaDeleteProductsFromShelf() {
        String username = domain.getUsernameSelected();
        if (username == null) { // Asegúrate de que el usuario esté autenticado
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }
        PantallaDeleteProductsFromShelf pantallaDeleteProductsFromShelf = new PantallaDeleteProductsFromShelf(this);
        mainPanel.add(pantallaDeleteProductsFromShelf.getPanel(), "DeleteProductsFromShelf");
        cardLayout.show(mainPanel, "DeleteProductsFromShelf"); // Cambiar a la pantalla de Eliminar Producto de Estantería
    }

    public void mostrarPantallaOrganizeShelf() {
        String username = domain.getUsernameSelected();
        if (username == null) { // Asegúrate de que el usuario esté autenticado
            JOptionPane.showMessageDialog(mainPanel, "Por favor, inicie sesión primero.");
            return;
        }
        PantallaOrganizeShelf pantallaOrganizeShelf = new PantallaOrganizeShelf(this);
        mainPanel.add(pantallaOrganizeShelf.getPanel(), "OrganizeShelf");
        cardLayout.show(mainPanel, "OrganizeShelf"); // Cambiar a la pantalla de Eliminar Producto de Estantería
    }
}
