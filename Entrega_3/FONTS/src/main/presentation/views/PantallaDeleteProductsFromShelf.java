package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaDeleteProductsFromShelf {

    private final JPanel panelDeleteProductsFromShelf;

    public PantallaDeleteProductsFromShelf(CtrlPresentacio ctrlPresentacio) {
        this.panelDeleteProductsFromShelf = new JPanel();
        initializePanel(ctrlPresentacio);
    }

    private void initializePanel(CtrlPresentacio ctrlPresentacio) {
        panelDeleteProductsFromShelf.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Eliminar Producto de Estantería", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelDeleteProductsFromShelf.setBackground(new Color(230, 240, 255));

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panelDeleteProductsFromShelf.add(titleLabel, c);

        JLabel shelfLabel = new JLabel("Nombre de la estantería:");
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panelDeleteProductsFromShelf.add(shelfLabel, c);

        JTextField shelfField = new JTextField(20);
        c.gridx = 1;
        c.gridy = 1;
        panelDeleteProductsFromShelf.add(shelfField, c);

        JLabel productLabel = new JLabel("Nombre del producto:");
        c.gridx = 0;
        c.gridy = 2;
        panelDeleteProductsFromShelf.add(productLabel, c);

        JTextField productField = new JTextField(20);
        c.gridx = 1;
        c.gridy = 2;
        panelDeleteProductsFromShelf.add(productField, c);

        JButton acceptButton = new JButton("Aceptar");
        estilizarBoton(acceptButton, new Color(0, 150, 136), Color.WHITE);
        c.gridx = 0;
        c.gridy = 4;
        panelDeleteProductsFromShelf.add(acceptButton, c);

        JButton cancelButton = new JButton("Cancelar");
        estilizarBoton(cancelButton, new Color(255, 87, 34), Color.WHITE);
        c.gridx = 1;
        panelDeleteProductsFromShelf.add(cancelButton, c);

        acceptButton.addActionListener(e -> {
            String shelfName = shelfField.getText().trim();
            String productNameText = productField.getText().trim();

            if (shelfName.isEmpty()) {
                JOptionPane.showMessageDialog(panelDeleteProductsFromShelf, "El nombre de la estantería no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                ctrlPresentacio.removeProductFromShelf(shelfName, productNameText);
                JOptionPane.showMessageDialog(panelDeleteProductsFromShelf, "Producto eliminado correctamente de la estantería.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                shelfField.setText("");
                productField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panelDeleteProductsFromShelf, "Error al eliminar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaShelves());
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
        return panelDeleteProductsFromShelf;
    }
}