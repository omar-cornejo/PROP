package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

public class PantallaShelves {
    private JPanel panelShelves;
    private CtrlPresentacio ctrlPresentacio;

    public PantallaShelves(CtrlPresentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        this.panelShelves = new JPanel(new GridBagLayout());
        initializePanel();
    }

    private void initializePanel() {
        panelShelves.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel para el título
        JLabel titleLabel = new JLabel("Gestión de Estanterías", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Toma dos columnas
        panelShelves.add(titleLabel, gbc);
        panelShelves.setBackground(new Color(230, 240, 255)); // Fondo de la pantalla
        // Panel central para botones con GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(0, 10, 14, 10);
        buttonPanel.setBackground(new Color(230, 240, 255)); // Fondo de la pantalla
        // Botones
        JButton addShelfButton = new JButton("Añadir Estantería");
        JButton getShelfButton = new JButton("Consultar Estantería");
        JButton deleteShelfButton = new JButton("Borrar Estantería");
        JButton addProductsButton = new JButton("Añadir Productos");
        JButton deleteProductsButton = new JButton("Eliminar Productos");
        JButton organizeShelf = new JButton("Organizar Estantería");

        // Estilizar los botones
        estilizarBoton(addShelfButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(getShelfButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(deleteShelfButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(addProductsButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(deleteProductsButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(organizeShelf, new Color(92, 141, 211), Color.white);


        // Establecer el tamaño uniforme para los botones
        Dimension buttonSize = new Dimension(200, 25);
        addShelfButton.setPreferredSize(buttonSize);
        getShelfButton.setPreferredSize(buttonSize);
        deleteShelfButton.setPreferredSize(buttonSize);
        addProductsButton.setPreferredSize(buttonSize);
        deleteProductsButton.setPreferredSize(buttonSize);
        organizeShelf.setPreferredSize(buttonSize);

        // Añadir botones al panel
        buttonGbc.gridy = 0;
        buttonPanel.add(addShelfButton, buttonGbc);

        buttonGbc.gridy = 1;
        buttonPanel.add(getShelfButton, buttonGbc);

        buttonGbc.gridy = 2;
        buttonPanel.add(deleteShelfButton, buttonGbc);

        buttonGbc.gridy = 3;
        buttonPanel.add(addProductsButton, buttonGbc);

        buttonGbc.gridy = 4;
        buttonPanel.add(deleteProductsButton, buttonGbc);

        buttonGbc.gridy = 5;
        buttonPanel.add(organizeShelf, buttonGbc);

        // Botón de volver
        JButton backButton = new JButton("Volver");
        buttonGbc.gridy = 6;
        backButton.setBackground(new Color(255, 102, 102)); // Rojo suave
        backButton.setForeground(Color.WHITE); // Texto blanco
        buttonPanel.add(backButton, buttonGbc);

        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);
        // Añadir el panel de botones al principal
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelShelves.add(buttonPanel, gbc);

        // Ajustar el borde general del panel principal
        panelShelves.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Configurar acciones de los botones
        addShelfButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaAddShelf());
        getShelfButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaGetShelf());
        deleteShelfButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaDeleteShelf());
        addProductsButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaAddProductsToShelf());
        deleteProductsButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaDeleteProductsFromShelf());
        organizeShelf.addActionListener(e -> ctrlPresentacio.mostrarPantallaOrganizeShelf());
        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaUsuario());
    }

    // Método para estilizar los botones con color de fondo y texto, y efectos al pasar el ratón
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

    // Metodo para obtener el panel
    public JPanel getPanel() {
        return panelShelves;
    }
}
