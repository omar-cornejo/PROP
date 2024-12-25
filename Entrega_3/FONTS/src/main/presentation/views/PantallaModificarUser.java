package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaModificarUser {

    private JPanel PantallaModificarUser;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField actualpasswordField;

    public PantallaModificarUser(CtrlPresentacio ctrlPresentacio) {
        PantallaModificarUser = new JPanel(new GridBagLayout());
        PantallaModificarUser.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200)),
                "Modificar Usuario",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD | Font.ITALIC, 18),
                new Color(50, 100, 150)
        ));
        PantallaModificarUser.setBackground(new Color(230, 240, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Nuevo Nombre de Usuario:");
        usernameField = new JTextField(15);

        JLabel actualpasswordLabel = new JLabel("Contraseña Actual:");
        actualpasswordField = new JPasswordField(15);

        JLabel passwordLabel = new JLabel("Contraseña Nueva:");
        passwordField = new JPasswordField(15);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        usernameLabel.setFont(labelFont);
        actualpasswordLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);

        JButton saveButton = new JButton("Guardar");
        estilizarBoton(saveButton, new Color(0, 150, 136), Color.WHITE);

        saveButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String actualPassword = new String(actualpasswordField.getPassword()).trim();

            if (actualPassword.isEmpty()) {
                JOptionPane.showMessageDialog(PantallaModificarUser, "El campo 'Contraseña Actual' es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                usernameField.setText("");
                actualpasswordField.setText("");
                passwordField.setText("");
                return;
            }

            if (username.isEmpty() && password.isEmpty()) {
                JOptionPane.showMessageDialog(PantallaModificarUser, "Debe completar al menos uno de los campos: 'Nuevo Nombre de Usuario' o 'Contraseña Nueva'.", "Error", JOptionPane.ERROR_MESSAGE);
                usernameField.setText("");
                actualpasswordField.setText("");
                passwordField.setText("");
                return;
            }

            try {
                boolean result = ctrlPresentacio.modificarUsuario(username, actualPassword, password);

                if (result) {
                    JOptionPane.showMessageDialog(PantallaModificarUser, "Usuario modificado correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    usernameField.setText("");
                    actualpasswordField.setText("");
                    passwordField.setText("");
                    ctrlPresentacio.mostrarPantallaUsuario();
                } else {
                    JOptionPane.showMessageDialog(PantallaModificarUser, "Error al modificar el usuario: ", "Error", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    actualpasswordField.setText("");
                    passwordField.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(PantallaModificarUser, "Error al modificar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            usernameField.setText("");
            actualpasswordField.setText("");
            passwordField.setText("");
        });

        JButton volverButton = new JButton("Volver");
        estilizarBoton(volverButton, new Color(255, 87, 34), Color.WHITE);

        volverButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaUsuario());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        PantallaModificarUser.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PantallaModificarUser.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        PantallaModificarUser.add(actualpasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PantallaModificarUser.add(actualpasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        PantallaModificarUser.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PantallaModificarUser.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PantallaModificarUser.add(new JSeparator(), gbc);

        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        botonesPanel.setBackground(new Color(230, 240, 255));
        botonesPanel.add(saveButton);
        botonesPanel.add(volverButton);

        PantallaModificarUser.add(botonesPanel, gbc);
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
        return PantallaModificarUser;
    }
}
