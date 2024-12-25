package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PantallaGetShelf {
    private final JPanel panelGetShelf;

    public PantallaGetShelf(CtrlPresentacio ctrlPresentacio) {
        panelGetShelf = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Consultar Estantería", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelGetShelf.setBackground(new Color(230, 240, 255));

        JLabel nameLabel = new JLabel("Nombre de la estantería:");
        JTextField nameField = new JTextField(20);

        JLabel productsLabel = new JLabel("Productos asociados:");
        JTextArea productsArea = new JTextArea(10, 20);
        productsArea.setEditable(false);
        JScrollPane productsScrollPane = new JScrollPane(productsArea);

        JButton acceptButton = new JButton("Consultar");
        estilizarBoton(acceptButton, new Color(0, 150, 136), Color.WHITE);

        JButton cancelButton = new JButton("Cancelar");
        estilizarBoton(cancelButton, new Color(255, 87, 34), Color.WHITE);

        acceptButton.addActionListener(ex -> {
            String shelfName = nameField.getText().trim();

            if (shelfName.isEmpty()) {
                JOptionPane.showMessageDialog(panelGetShelf, "El nombre de la estantería no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                List<String> productNames = ctrlPresentacio.getProductsInShelf(shelfName);

                if (productNames == null || productNames.isEmpty()) {
                    JOptionPane.showMessageDialog(panelGetShelf, "La estantería no existe o no tiene productos asociados.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    productsArea.setText("");
                    for (String productName : productNames) {
                        productsArea.append(productName + "\n");
                    }
                }
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(panelGetShelf, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaShelves());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelGetShelf.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelGetShelf.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelGetShelf.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelGetShelf.add(productsLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelGetShelf.add(productsScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelGetShelf.add(acceptButton, gbc);

        gbc.gridx = 1;
        panelGetShelf.add(cancelButton, gbc);
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
        return panelGetShelf;
    }
}