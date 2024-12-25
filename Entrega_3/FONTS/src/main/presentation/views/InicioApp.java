package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class InicioApp {

    private JPanel mainPanel; // Panel principal de la pantalla de inicio
    private JButton registerButton;
    private JButton loginButton;
    private JButton exitButton;

    public InicioApp(CtrlPresentacio ctrlPresentacio) {
        // Crear el panel principal con borde y título
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200)),
                "Inicio de la Aplicación",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD | Font.ITALIC, 18), // Fuente en negrita y cursiva
                new Color(50, 100, 150)
        ));
        mainPanel.setBackground(new Color(230, 240, 255)); // Fondo de la pantalla

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado alrededor de los componentes

        // Botón para ir a la pantalla de registro
        registerButton = new JButton("Registrarse");
        estilizarBoton(registerButton, new Color(92, 141, 211), Color.white);
        registerButton.setPreferredSize(new Dimension(150, 40));
        registerButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaRegistro()); // Navegar a pantalla de registro

        // Botón para ir a la pantalla de login
        loginButton = new JButton("Iniciar Sesión");
        estilizarBoton(loginButton, new Color(92, 141, 211), Color.white);
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaLogin()); // Navegar a pantalla de login

        // Botón para salir de la aplicación
        exitButton = new JButton("Salir");
        estilizarBoton(exitButton, new Color(255, 87, 34), Color.WHITE);
        exitButton.setPreferredSize(new Dimension(150, 40));
        exitButton.addActionListener(e -> System.exit(0)); // Salir de la aplicación

        // Colocar los botones en el panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(registerButton, gbc);

        gbc.gridy = 1; // Nueva fila
        mainPanel.add(loginButton, gbc);

        gbc.gridy = 2; // Nueva fila
        mainPanel.add(exitButton, gbc);
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

    /**
     * Método para obtener el panel principal de la pantalla de inicio.
     *
     * @return El panel principal.
     */
    public JPanel getPanel() {
        return mainPanel;
    }
}
