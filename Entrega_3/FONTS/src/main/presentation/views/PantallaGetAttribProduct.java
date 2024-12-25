package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaGetAttribProduct {
    private JPanel panelUpdateProduct;

    public PantallaGetAttribProduct(CtrlPresentacio ctrlPresentacio) {
        panelUpdateProduct = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Ver Atributos", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelUpdateProduct.setBackground(new Color(230, 240, 255));

        JLabel productNameLabel = new JLabel("Nombre del Producto:");
        JTextField productNameField = new JTextField(15);

        JButton consultarButton = new JButton("Consultar");
        consultarButton.addActionListener(e -> {
            String productName = productNameField.getText().trim();

            if (productName.isEmpty()) {
                JOptionPane.showMessageDialog(panelUpdateProduct, "El nombre del producto no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String attribs = ctrlPresentacio.getProductAttributesUser(productName);
                JOptionPane.showMessageDialog(panelUpdateProduct, attribs, "Atributos", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception exce) {
                JOptionPane.showMessageDialog(panelUpdateProduct, exce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            productNameField.setText("");
        });

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaProductos());
        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);
        estilizarBoton(consultarButton, new Color(0, 150, 136), Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelUpdateProduct.add(productNameLabel, gbc);

        gbc.gridx = 1;
        panelUpdateProduct.add(productNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelUpdateProduct.add(consultarButton, gbc);

        gbc.gridy = 3;
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