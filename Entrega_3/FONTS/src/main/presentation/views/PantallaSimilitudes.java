package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaSimilitudes {
    private JPanel panelSimilitudes;

    public PantallaSimilitudes(CtrlPresentacio ctrlPresentacio) {
        this.panelSimilitudes = new JPanel(new GridBagLayout());
        initializePanel(ctrlPresentacio);
    }

    private void initializePanel(CtrlPresentacio ctrlPresentacio) {
        panelSimilitudes.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel para el título
        JLabel titleLabel = new JLabel("Gestión de Similitudes", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Toma dos columnas
        panelSimilitudes.add(titleLabel, gbc);
        panelSimilitudes.setBackground(new Color(230, 240, 255)); // Fondo de la pantalla

        // Panel central para botones con GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(0, 10, 14, 10);
        buttonPanel.setBackground(new Color(230, 240, 255)); // Fondo de la pantalla


        // Botones
        JButton setSimilarityButton = new JButton("Modificar Similitud");
        JButton removeSimilarityButton = new JButton("Eliminar Similitud");
        JButton getSimilarityButton = new JButton("Consultar Similitud");

        // Estilizar los botones
        estilizarBoton(setSimilarityButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(removeSimilarityButton, new Color(92, 141, 211), Color.WHITE);
        estilizarBoton(getSimilarityButton, new Color(92, 141, 211), Color.white);

        // Establecer el tamaño uniforme para los botones
        Dimension buttonSize = new Dimension(250, 30);
        setSimilarityButton.setPreferredSize(buttonSize);
        removeSimilarityButton.setPreferredSize(buttonSize);
        getSimilarityButton.setPreferredSize(buttonSize);

        // Añadir botones al panel
        buttonGbc.gridy = 0;
        buttonPanel.add(setSimilarityButton, buttonGbc);

        buttonGbc.gridy = 1;
        buttonPanel.add(removeSimilarityButton, buttonGbc);

        buttonGbc.gridy = 2;
        buttonPanel.add(getSimilarityButton, buttonGbc);

        // Botón de volver
        JButton backButton = new JButton("Volver");
        buttonGbc.gridy = 3;
        backButton.setBackground(new Color(255, 102, 102)); // Rojo suave
        backButton.setForeground(Color.WHITE); // Texto blanco
        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);
        buttonPanel.add(backButton, buttonGbc);

        // Añadir el panel de botones al principal
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelSimilitudes.add(buttonPanel, gbc);

        // Ajustar el borde general del panel principal
        panelSimilitudes.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Configurar acciones de los botones
        setSimilarityButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaSetSimilarity());
        removeSimilarityButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaRemoveSimilarity());
        getSimilarityButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaGetSimilarity());
        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaUsuario());
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
        return panelSimilitudes;
    }
}
