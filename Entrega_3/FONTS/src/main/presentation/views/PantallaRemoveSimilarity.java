package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaRemoveSimilarity {
    private JPanel panelRemoveSimilarity;

    public PantallaRemoveSimilarity(CtrlPresentacio ctrlPresentacio) {
        this.panelRemoveSimilarity = new JPanel(new GridBagLayout());
        initializePanel(ctrlPresentacio);
    }

    private void initializePanel(CtrlPresentacio ctrlPresentacio) {
        panelRemoveSimilarity.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Eliminar Similitud", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelRemoveSimilarity.add(titleLabel, gbc);
        panelRemoveSimilarity.setBackground(new Color(230, 240, 255));

        JLabel product1Label = new JLabel("Producto 1:");
        JTextField product1Field = new JTextField(15);
        JLabel product2Label = new JLabel("Producto 2:");
        JTextField product2Field = new JTextField(15);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRemoveSimilarity.add(product1Label, gbc);

        gbc.gridx = 1;
        panelRemoveSimilarity.add(product1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRemoveSimilarity.add(product2Label, gbc);

        gbc.gridx = 1;
        panelRemoveSimilarity.add(product2Field, gbc);

        JButton submitButton = new JButton("Eliminar");
        JButton backButton = new JButton("Volver");

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelRemoveSimilarity.add(submitButton, gbc);

        gbc.gridx = 1;
        panelRemoveSimilarity.add(backButton, gbc);

        estilizarBoton(submitButton, new Color(0, 150, 136), Color.WHITE);
        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);

        submitButton.addActionListener(e -> {
            String product1 = product1Field.getText().trim();
            String product2 = product2Field.getText().trim();

            try {
                if (!ctrlPresentacio.productExists(product1) || !ctrlPresentacio.productExists(product2)) {
                    JOptionPane.showMessageDialog(null, "Uno o ambos productos no existen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean result = ctrlPresentacio.removeProductSimilarityForUser(product1, product2);

                if (result) {
                    JOptionPane.showMessageDialog(null, "Similitud eliminada exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar la similitud. Verifique los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        return panelRemoveSimilarity;
    }
}
