package main.presentation.views;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PantallaGetNamesProducts {
    private JPanel panelNameProducts;

    public PantallaGetNamesProducts(CtrlPresentacio ctrlPresentacio) {
        panelNameProducts = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelNameProducts.setBackground(new Color(230, 240, 255));

        JLabel titleLabel = new JLabel("Productos de Usuario", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        try {
            List<String> productsNames = ctrlPresentacio.getProductNamesForUser();
            JList<String> productList = new JList<>(productsNames.toArray(new String[0]));
            JScrollPane scrollPane = new JScrollPane(productList);
            scrollPane.setPreferredSize(new Dimension(300, 150));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            panelNameProducts.add(scrollPane, gbc);
        } catch (Exception exce) {
            JOptionPane.showMessageDialog(panelNameProducts, exce.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton backButton = new JButton("Volver");
        backButton.addActionListener(e -> ctrlPresentacio.mostrarPantallaProductos());
        estilizarBoton(backButton, new Color(255, 87, 34), Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelNameProducts.add(titleLabel, gbc);

        gbc.gridy = 3;
        panelNameProducts.add(backButton, gbc);
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
        return panelNameProducts;
    }
}
