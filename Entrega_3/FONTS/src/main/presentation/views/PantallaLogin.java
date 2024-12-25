package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class PantallaLogin {
    private JPanel panelLogin;

    public PantallaLogin(CtrlPresentacio ctrlPresentacio) {
        panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200)),
                "Iniciar Sesión",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD | Font.ITALIC, 18),
                new Color(50, 100, 150)
        ));
        panelLogin.setBackground(new Color(230, 240, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Nombre de Usuario:");
        JTextField usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField(15);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        JButton entryButton = new JButton("Entrar");
        estilizarBoton(entryButton, new Color(0, 150, 136), Color.WHITE);
        entryButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (ctrlPresentacio.loginUser(username, password)) {
                JOptionPane.showMessageDialog(panelLogin, "Inicio de sesión exitoso.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                ctrlPresentacio.mostrarPantallaUsuario();
            } else {
                JOptionPane.showMessageDialog(panelLogin, "Error: Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            usernameField.setText("");
            passwordField.setText("");
        });

        JButton volverButton = new JButton("Volver");
        estilizarBoton(volverButton, new Color(255, 87, 34), Color.WHITE);
        volverButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaInicio());

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLogin.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panelLogin.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelLogin.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panelLogin.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelLogin.add(entryButton, gbc);

        gbc.gridy = 3;
        panelLogin.add(volverButton, gbc);
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
        return panelLogin;
    }
}
