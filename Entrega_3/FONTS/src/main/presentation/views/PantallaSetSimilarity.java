package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaSetSimilarity {
    private JPanel panelSetSimilarity;

    public PantallaSetSimilarity(CtrlPresentacio ctrlPresentacio) {
        this.panelSetSimilarity = new JPanel(new GridBagLayout());
        initializePanel(ctrlPresentacio);
    }

    private void initializePanel(CtrlPresentacio ctrlPresentacio) {
        panelSetSimilarity.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Modificar Similitud", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelSetSimilarity.add(titleLabel, gbc);
        panelSetSimilarity.setBackground(new Color(230, 240, 255));

        JLabel product1Label = new JLabel("Producto 1:");
        JTextField product1Field = new JTextField(15);
        JLabel product2Label = new JLabel("Producto 2:");
        JTextField product2Field = new JTextField(15);
        JLabel similarityLabel = new JLabel("Similitud (0-1):");
        JTextField similarityField = new JTextField(5);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelSetSimilarity.add(product1Label, gbc);

        gbc.gridx = 1;
        panelSetSimilarity.add(product1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelSetSimilarity.add(product2Label, gbc);

        gbc.gridx = 1;
        panelSetSimilarity.add(product2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelSetSimilarity.add(similarityLabel, gbc);

        gbc.gridx = 1;
        panelSetSimilarity.add(similarityField, gbc);

        JButton submitButton = new JButton("Modificar");
        JButton backButton = new JButton("Volver");

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelSetSimilarity.add(submitButton, gbc);

        gbc.gridx = 1;
        panelSetSimilarity.add(backButton, gbc);

        estilizarBoton(submitButton, new Color(0, 150, 136), Color.WHITE);
        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);

        submitButton.addActionListener(e -> {
            String product1 = product1Field.getText().trim();
            String product2 = product2Field.getText().trim();
            String similarityText = similarityField.getText().trim();

            try {
                if (!ctrlPresentacio.productExists(product1) || !ctrlPresentacio.productExists(product2)) {
                    JOptionPane.showMessageDialog(null, "Uno o ambos productos no existen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double similarity = Double.parseDouble(similarityText);
                if (similarity < 0 || similarity > 1) {
                    JOptionPane.showMessageDialog(null, "La similitud debe estar entre 0 y 1.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean result = ctrlPresentacio.setProductSimilarityForUser(product1, product2, similarity);

                if (result) {
                    JOptionPane.showMessageDialog(null, "Similitud modificada exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar la similitud. Verifique los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor válido para la similitud.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaSimilitudes());
    }

    private void estilizarBoton(JButton boton, Color colorFondo, Color colorTexto) {
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createLineBorder(colorFondo.darker()));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorFondo.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorFondo);
            }
        });
    }

    public JPanel getPanel() {
        return panelSetSimilarity;
    }
}
