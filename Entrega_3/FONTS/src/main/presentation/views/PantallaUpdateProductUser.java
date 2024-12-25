package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaUpdateProductUser {
    private JPanel panelUpdateProduct;

    public PantallaUpdateProductUser(CtrlPresentacio ctrlPresentacio) {
        panelUpdateProduct = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Actualizar Producto", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelUpdateProduct.setBackground(new Color(230, 240, 255));

        JLabel productNameLabel = new JLabel("Nombre del Producto:");
        JTextField productNameField = new JTextField(15);

        JLabel newPriceLabel = new JLabel("Nuevo Precio del Producto:");
        JTextField newPriceField = new JTextField(15);

        JButton actualizarButton = new JButton("Actualizar");
        actualizarButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String newPriceString = newPriceField.getText();

            try {
                int newPrice = Integer.parseInt(newPriceString);
                ctrlPresentacio.updateProductUser(productName, newPrice);
                JOptionPane.showMessageDialog(panelUpdateProduct, "Producto actualizado exitosamente.","Mensaje", JOptionPane.INFORMATION_MESSAGE);
                productNameField.setText("");
                newPriceField.setText("");
                ctrlPresentacio.mostrarPantallaProductos();
            } catch (NumberFormatException nfe) {
                productNameField.setText("");
                newPriceField.setText("");
                JOptionPane.showMessageDialog(panelUpdateProduct, "El precio debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception exce) {
                productNameField.setText("");
                newPriceField.setText("");
                JOptionPane.showMessageDialog(panelUpdateProduct, exce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            productNameField.setText("");
            newPriceField.setText("");
        });

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaProductos());

        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);
        estilizarBoton(actualizarButton, new Color(0, 150, 136), Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelUpdateProduct.add(productNameLabel, gbc);

        gbc.gridx = 1;
        panelUpdateProduct.add(productNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelUpdateProduct.add(newPriceLabel, gbc);

        gbc.gridx = 1;
        panelUpdateProduct.add(newPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelUpdateProduct.add(actualizarButton, gbc);

        gbc.gridy = 4;
        panelUpdateProduct.add(backButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelUpdateProduct.add(titleLabel, gbc);
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
        return panelUpdateProduct;
    }
}
