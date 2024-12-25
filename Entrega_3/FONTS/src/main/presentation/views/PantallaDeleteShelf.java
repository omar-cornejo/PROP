package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaDeleteShelf {
    private JPanel panelDeleteShelf;

    public PantallaDeleteShelf(CtrlPresentacio ctrlPresentacio) {
        panelDeleteShelf = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Eliminar Estantería", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelDeleteShelf.setBackground(new Color(230, 240, 255));

        JLabel nameLabel = new JLabel("Nombre de la estantería:");
        JTextField nameField = new JTextField(20);

        JButton acceptButton = new JButton("Aceptar");
        estilizarBoton(acceptButton, new Color(0, 150, 136), Color.WHITE);

        JButton cancelButton = new JButton("Cancelar");
        estilizarBoton(cancelButton, new Color(255, 87, 34), Color.WHITE);

        acceptButton.addActionListener(ex -> {
            String shelfName = nameField.getText().trim();

            if (shelfName.isEmpty()) {
                JOptionPane.showMessageDialog(panelDeleteShelf, "El nombre de la estantería no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                ctrlPresentacio.removeShelfForUser(shelfName);
                JOptionPane.showMessageDialog(panelDeleteShelf, "Estantería eliminada exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                ctrlPresentacio.mostrarPantallaShelves();
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(panelDeleteShelf, "La estantería no existe o no se pudo eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaShelves());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelDeleteShelf.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelDeleteShelf.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelDeleteShelf.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelDeleteShelf.add(acceptButton, gbc);

        gbc.gridx = 1;
        panelDeleteShelf.add(cancelButton, gbc);
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
        return panelDeleteShelf;
    }
}
