package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaDeleteProductUser {
    private JPanel panelDelProduct;

    public PantallaDeleteProductUser(CtrlPresentacio ctrlPresentacio) {
        panelDelProduct = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado alrededor de los componentes

        JLabel titleLabel = new JLabel("Eliminar Producto", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelDelProduct.setBackground(new Color(230, 240, 255));

        JLabel productNameLabel = new JLabel("Nombre del Producto:");
        JTextField productNameField = new JTextField(15);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(e -> {
            String productName = productNameField.getText().trim();

            if (productName.isEmpty()) {
                JOptionPane.showMessageDialog(panelDelProduct, "El nombre del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                productNameField.setText("");
                return;
            }

            try {
                ctrlPresentacio.removeProductUser(productName);
                JOptionPane.showMessageDialog(panelDelProduct, "Producto eliminado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exce) {
                productNameField.setText("");
                JOptionPane.showMessageDialog(panelDelProduct, "Error al eliminar el producto: " + exce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            productNameField.setText("");
        });

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaProductos());

        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);
        estilizarBoton(eliminarButton, new Color(0, 150, 136), Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelDelProduct.add(productNameLabel, gbc);

        gbc.gridx = 1;
        panelDelProduct.add(productNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelDelProduct.add(eliminarButton, gbc);

        gbc.gridy = 3;
        panelDelProduct.add(backButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelDelProduct.add(titleLabel, gbc);
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
        return panelDelProduct;
    }
}
