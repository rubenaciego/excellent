package vista;

import javax.swing.*;
import java.awt.*;

public class WindowTruncar extends WindowSecundaria {

    private JLabel xifresLabel;
    private JPanel xifresPanel;
    private JTextField entradaXifres;

    public WindowTruncar() {
        mainFrame.setTitle("Truncar");
        configuraUI();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    protected void configuraUI() {
        super.configuraUI();
        // Panell xifres
        xifresPanel = new JPanel();
        xifresPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(xifresPanel, gbc);

        //Xifres label
        xifresLabel = new JLabel();
        xifresLabel.setEnabled(true);
        xifresLabel.setText("# Xifres:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 10, 3);
        xifresPanel.add(xifresLabel, gbc);
        //Entrada xifres
        entradaXifres = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 20);
        xifresPanel.add(entradaXifres, gbc);
    }
}
