package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.enums.AlgorithmType;

import javax.swing.*;
import java.awt.*;

public class PantallaOrganizeShelf extends JFrame {

    private final CtrlPresentacio ctrlPresentacio;
    private final JPanel panelOrganize;

    public PantallaOrganizeShelf(CtrlPresentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        this.panelOrganize = new JPanel(new GridBagLayout());
        initializePanel();
    }

    private void initializePanel() {
        panelOrganize.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Organizar Estantería", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelOrganize.add(titleLabel, gbc);
        panelOrganize.setBackground(new Color(230, 240, 255));

        JLabel shelfLabel = new JLabel("Nombre de la estantería:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelOrganize.add(shelfLabel, gbc);

        JTextField shelfField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelOrganize.add(shelfField, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10);

        JButton twoApproxButton = new JButton("Two-Approximation");
        JButton bruteForceButton = new JButton("Brute Force");
        estilizarBoton(twoApproxButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(bruteForceButton, new Color(92, 141, 211), Color.white);

        buttonPanel.setBackground(new Color(230, 240, 255));
        Dimension buttonSize = new Dimension(200, 40);
        twoApproxButton.setPreferredSize(buttonSize);
        bruteForceButton.setPreferredSize(buttonSize);

        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(twoApproxButton, buttonGbc);

        buttonGbc.gridy = 1;
        buttonPanel.add(bruteForceButton, buttonGbc);

        JButton backButton = new JButton("Volver");
        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);
        buttonGbc.gridy = 2;
        buttonPanel.add(backButton, buttonGbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelOrganize.add(buttonPanel, gbc);

        panelOrganize.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        twoApproxButton.addActionListener(e -> {
            String shelfName = shelfField.getText().trim();
            if (shelfName.isEmpty()) {
                JOptionPane.showMessageDialog(panelOrganize, "El nombre de la estantería no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    boolean respuesta = ctrlPresentacio.organizeShelf(shelfName, AlgorithmType.TWO_APPROXIMATION);
                    if (respuesta) JOptionPane.showMessageDialog(panelOrganize, "La estantería " + shelfName + " se ha organizado con éxito", "Estantería organizada", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(panelOrganize, "Error al organizar la estantería: ", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelOrganize, "Error al organizar la estantería: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bruteForceButton.addActionListener(e -> {
            String shelfName = shelfField.getText().trim();
            if (shelfName.isEmpty()) {
                JOptionPane.showMessageDialog(panelOrganize, "El nombre de la estantería no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    boolean respuesta = ctrlPresentacio.organizeShelf(shelfName, AlgorithmType.BRUTE_FORCE);
                    if (respuesta) JOptionPane.showMessageDialog(panelOrganize, "La estantería " + shelfName + " se ha organizado con éxito", "Estantería organizada", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(panelOrganize, "Error al organizar la estantería: ", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelOrganize, "Error al organizar la estantería: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaShelves());
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
        return panelOrganize;
    }
}