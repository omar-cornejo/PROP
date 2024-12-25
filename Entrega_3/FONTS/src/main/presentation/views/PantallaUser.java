package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class PantallaUser {
    private JPanel panelUser;

    public PantallaUser(CtrlPresentacio ctrlPresentacio) {
        panelUser = new JPanel(new GridBagLayout());
        panelUser.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200)),
                "Pantalla de Usuario",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD | Font.ITALIC, 18),
                new Color(50, 100, 150)
        ));
        panelUser.setBackground(new Color(230, 240, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton estanteriasButton = new JButton("Estanterías");
        JButton productosButton = new JButton("Productos");
        JButton modificarUserButton = new JButton("Modificar User");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        JButton similitudesButton = new JButton("Similitudes"); // Nuevo botón

        Dimension buttonSize = new Dimension(200, 30);
        estanteriasButton.setPreferredSize(buttonSize);
        productosButton.setPreferredSize(buttonSize);
        modificarUserButton.setPreferredSize(buttonSize);
        cerrarSesionButton.setPreferredSize(buttonSize);
        similitudesButton.setPreferredSize(buttonSize); // Tamaño del nuevo botón

        estilizarBoton(estanteriasButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(productosButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(modificarUserButton, new Color(92, 141, 211), Color.white);
        estilizarBoton(cerrarSesionButton, new Color(255, 87, 34), Color.WHITE);
        estilizarBoton(similitudesButton, new Color(92, 141, 211), Color.white);

        estanteriasButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaShelves());
        productosButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaProductos());
        modificarUserButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaModificarUsuario());
        cerrarSesionButton.addActionListener(e -> ctrlPresentacio.cerrarSesion());
        similitudesButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaSimilitudes()); // Acción para el nuevo botón

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelUser.add(estanteriasButton, gbc);

        gbc.gridy = 1;
        panelUser.add(productosButton, gbc);

        gbc.gridy = 2;
        panelUser.add(modificarUserButton, gbc);

        gbc.gridy = 3;
        panelUser.add(similitudesButton, gbc);

        gbc.gridy = 4;
        panelUser.add(cerrarSesionButton, gbc);
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
        return panelUser;
    }
}
