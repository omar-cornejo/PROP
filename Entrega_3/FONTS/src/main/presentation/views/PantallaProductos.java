package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaProductos {
    private JPanel panelProducts;

    public PantallaProductos(CtrlPresentacio ctrlPresentacio) {

        panelProducts = new JPanel(new BorderLayout());

        // Panel para el título
        JLabel titleLabel = new JLabel("Gestión de Productos", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelProducts.add(titleLabel, BorderLayout.NORTH);

        // Panel central para botones con GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre botones
        gbc.gridx = 0;

        panelProducts.setBackground(new Color(230, 240, 255)); // Fondo de la pantalla
        buttonPanel.setBackground(new Color(230, 240, 255)); // Fondo de la pantalla

        // Crear los botones
        JButton addProductButton = new JButton("Añadir Producto");
        JButton deleteProductButton = new JButton("Eliminar Producto");
        JButton updateProductButton = new JButton("Modificar Productos");
        JButton getAttributesProductButton = new JButton("Ver Atributos de Producto");
        JButton getNamesProductsButton = new JButton("Ver Nombres de Productos");
        JButton backButton = new JButton("Volver");
        // Estilizar los botones
        estilizarBoton(addProductButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(deleteProductButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(updateProductButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(getAttributesProductButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(getNamesProductsButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);

        Dimension buttonSize = new Dimension(200, 30); // Tamaño de los botones
        addProductButton.setPreferredSize(buttonSize);
        deleteProductButton.setPreferredSize(buttonSize);
        updateProductButton.setPreferredSize(buttonSize);
        getAttributesProductButton.setPreferredSize(buttonSize);
        getNamesProductsButton.setPreferredSize(buttonSize);



        // Acciones de los botones

        // Botón de Añadir Producto
        addProductButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaAddProduct());

        // Botón de Eliminar Producto
        deleteProductButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaDeleteProduct());

        // Botón de Modificar Producto
        updateProductButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaUpdateProduct());

        // Botón de Consultar Atributos de Producto
        getAttributesProductButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaGetProductAttributes());

        // Botón de Consultar Nombres de Productos
        getNamesProductsButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaGetProductNamesForUser());

        // Botón de Volver
        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaUsuario());


        // Colocar los botones en el panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addProductButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(deleteProductButton, gbc);

        gbc.gridy = 2;
        buttonPanel.add(updateProductButton, gbc);

        gbc.gridy = 3;
        buttonPanel.add(getAttributesProductButton, gbc);

        gbc.gridy = 4;
        buttonPanel.add(getNamesProductsButton, gbc);

        gbc.gridy = 5;
        buttonPanel.add(backButton, gbc);

        panelProducts.add(buttonPanel, BorderLayout.CENTER);
    }
    // Método para estilizar los botones con color de fondo y texto, y efectos al pasar el ratón
    private void estilizarBoton(JButton boton, Color colorFondo, Color colorTexto) {
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createLineBorder(colorFondo.darker()));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(colorFondo.brighter());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setBackground(colorFondo);
            }
        });
    }
    // Método para obtener el panel de productos
    public JPanel getPanel() {
        return panelProducts;
    }
}