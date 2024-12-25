package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaAddProductUser {
    private JPanel panelAddProduct;

    public PantallaAddProductUser(CtrlPresentacio ctrlPresentacio) {
        panelAddProduct = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Añadir Producto", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JLabel productNameLabel = new JLabel("Nombre del Producto:");
        JTextField productNameField = new JTextField(15);

        JLabel productPriceLabel = new JLabel("Precio del Producto:");
        JTextField productPriceField = new JTextField(15);

        panelAddProduct.setBackground(new Color(230, 240, 255));

        JButton anadirButton = new JButton("Añadir");
        anadirButton.addActionListener(e -> {
            String productName = productNameField.getText().trim();
            String productPriceString = productPriceField.getText().trim();

            if (productName.isEmpty() || productPriceString.isEmpty()) {
                JOptionPane.showMessageDialog(panelAddProduct, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                productNameField.setText("");
                productPriceField.setText("");
                return;
            }
            if(!productName.matches("[a-zA-Z]+")){
                productNameField.setText("");
                productPriceField.setText("");
                JOptionPane.showMessageDialog(panelAddProduct, "El nombre del producto solo debe contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int productPrice = Integer.parseInt(productPriceString);

                boolean success = ctrlPresentacio.addProductToUser(productName, productPrice);
                if (success) {
                    JOptionPane.showMessageDialog(panelAddProduct, "Producto añadido exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    productNameField.setText("");
                    productPriceField.setText("");
                } else {
                    JOptionPane.showMessageDialog(panelAddProduct, "No se pudo añadir el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    productNameField.setText("");
                    productPriceField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panelAddProduct, "El precio debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                productNameField.setText("");
                productPriceField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panelAddProduct, "Error al añadir el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                productNameField.setText("");
                productPriceField.setText("");
            }

            productNameField.setText("");
            productPriceField.setText("");
        });

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaProductos());

        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);
        estilizarBoton(anadirButton, new Color(0, 150, 136), Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelAddProduct.add(productNameLabel, gbc);

        gbc.gridx = 1;
        panelAddProduct.add(productNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelAddProduct.add(productPriceLabel, gbc);

        gbc.gridx = 1;
        panelAddProduct.add(productPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelAddProduct.add(anadirButton, gbc);

        gbc.gridy = 4;
        panelAddProduct.add(backButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelAddProduct.add(titleLabel, gbc);
    }

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

    public JPanel getPanel() {
        return panelAddProduct;
    }
}