package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaAddProductsToShelf {
    private final JPanel panelAddProductsToShelf;

    public PantallaAddProductsToShelf(CtrlPresentacio ctrlPresentacio) {
        panelAddProductsToShelf = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Añadir Producto a Estantería", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelAddProductsToShelf.setBackground(new Color(230, 240, 255));

        JLabel shelfLabel = new JLabel("Nombre de la Estantería:");
        JTextField shelfNameField = new JTextField(20);

        JLabel productNameLabel = new JLabel("Nombre del Producto:");
        JTextField productNameField = new JTextField(10);

        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");
        estilizarBoton(acceptButton, new Color(0, 150, 136), Color.WHITE);
        estilizarBoton(cancelButton, new Color(255, 87, 34), Color.WHITE);

        acceptButton.addActionListener(e -> {
            String shelfName = shelfNameField.getText().trim();
            String productNameText = productNameField.getText().trim();

            if (shelfName.isEmpty()) {
                JOptionPane.showMessageDialog(panelAddProductsToShelf, "Debe ingresar el nombre de la estantería.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (productNameText.isEmpty()) {
                JOptionPane.showMessageDialog(panelAddProductsToShelf, "Debe ingresar el nombre del producto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                boolean result = ctrlPresentacio.addProductToShelf(shelfName, productNameText);
                if (result) {
                    JOptionPane.showMessageDialog(panelAddProductsToShelf, "Producto añadido exitosamente.");
                    ctrlPresentacio.mostrarPantallaShelves();
                } else {
                    JOptionPane.showMessageDialog(panelAddProductsToShelf, "No se pudo añadir el producto a la estantería.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(panelAddProductsToShelf, "El ID del producto debe ser un valor numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(panelAddProductsToShelf, "Error al añadir el producto: " + exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaShelves());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelAddProductsToShelf.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelAddProductsToShelf.add(shelfLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelAddProductsToShelf.add(shelfNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelAddProductsToShelf.add(productNameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelAddProductsToShelf.add(productNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelAddProductsToShelf.add(acceptButton, gbc);

        gbc.gridx = 1;
        panelAddProductsToShelf.add(cancelButton, gbc);
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
        return panelAddProductsToShelf;
    }
}
