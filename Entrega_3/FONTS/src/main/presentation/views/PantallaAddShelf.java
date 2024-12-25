package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PantallaAddShelf {
    private final JPanel panelAddShelf;

    public PantallaAddShelf(CtrlPresentacio ctrlPresentacio) {
        panelAddShelf = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Añadir Estantería", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelAddShelf.setBackground(new Color(230, 240, 255));

        JLabel nameLabel = new JLabel("Nombre de la estantería:");
        JTextField nameField = new JTextField(20);

        JLabel productLabel = new JLabel("Seleccionar productos:");
        JList<String> productList = new JList<>();
        productList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        try {
            List<String> productsNames = ctrlPresentacio.getProductNamesForUser();
            if (productsNames != null) {
                productList.setListData(productsNames.toArray(new String[0]));
            }
        } catch (Exception exc) {
            // Si no hay productos o hay un error, dejar el JList vacío
            productList.setListData(new String[0]);
        }

        JButton acceptButton = new JButton("Aceptar");
        estilizarBoton(acceptButton, new Color(0, 150, 136), Color.WHITE);

        JButton cancelButton = new JButton("Cancelar");
        estilizarBoton(cancelButton, new Color(255, 87, 34), Color.WHITE);

        acceptButton.addActionListener(ex -> {
            String shelfName = nameField.getText().trim();

            if (shelfName.isEmpty() || !shelfName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(panelAddShelf, "El nombre solo debe utilizar letras y no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<String> selectedProducts = productList.getSelectedValuesList();

            try {
                ctrlPresentacio.addShelfForUser(shelfName, selectedProducts);
                JOptionPane.showMessageDialog(panelAddShelf, "Estantería añadida exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                ctrlPresentacio.mostrarPantallaShelves();
            } catch (Exception exce) {
                JOptionPane.showMessageDialog(panelAddShelf, "Error al añadir la estantería: " + exce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaShelves());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelAddShelf.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelAddShelf.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelAddShelf.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        panelAddShelf.add(productLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panelAddShelf.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelAddShelf.add(acceptButton, gbc);

        gbc.gridx = 1;
        panelAddShelf.add(cancelButton, gbc);
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
        return panelAddShelf;
    }
}
