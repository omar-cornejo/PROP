package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class PantallaRegistro {

    private JPanel panelRegistro;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField nameField;

    public PantallaRegistro(CtrlPresentacio ctrlPresentacio) {
        panelRegistro = new JPanel(new GridBagLayout());
        panelRegistro.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200)),
                "Registro de Usuario",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD | Font.ITALIC, 18),
                new Color(50, 100, 150)
        ));
        panelRegistro.setBackground(new Color(230, 240, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Nombre Completo:");
        nameField = new JTextField(15);

        JLabel usernameLabel = new JLabel("Nombre de Usuario:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField(15);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        nameLabel.setFont(labelFont);
        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        JButton saveButton = new JButton("Guardar");
        estilizarBoton(saveButton, new Color(0, 150, 136), Color.WHITE);
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panelRegistro, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                nameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
                return;
            }
            else if(username.length()>12){
                JOptionPane.showMessageDialog(panelRegistro, "Por favor, introduzca 12 caracteres como máximo.", "Error", JOptionPane.ERROR_MESSAGE);
                nameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
                return;
            }
            else if(!username.matches("[a-zA-Z0-9]+")){
                JOptionPane.showMessageDialog(panelRegistro, "Por favor, utilize solo letras y números (sin espacios).", "Error", JOptionPane.ERROR_MESSAGE);
                nameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
                return;
            }

            try {
                boolean success = ctrlPresentacio.registerUser(name, username, password);
                if (success) {
                    JOptionPane.showMessageDialog(panelRegistro, "Usuario registrado exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    nameField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");
                    ctrlPresentacio.mostrarPantallaInicio();
                } else {
                    JOptionPane.showMessageDialog(panelRegistro, "El nombre de usuario ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    nameField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panelRegistro, "Error al registrar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            nameField.setText("");
            usernameField.setText("");
            passwordField.setText("");
        });

        JButton volverButton = new JButton("Volver");
        estilizarBoton(volverButton, new Color(255, 87, 34), Color.WHITE);
        volverButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaInicio());

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRegistro.add(nameLabel, gbc);

        gbc.gridx = 1;
        panelRegistro.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRegistro.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panelRegistro.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelRegistro.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panelRegistro.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelRegistro.add(saveButton, gbc);

        gbc.gridy = 4;
        panelRegistro.add(volverButton, gbc);
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
        return panelRegistro;
    }
}
