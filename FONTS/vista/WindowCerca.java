package vista;

import javax.swing.*;
import java.awt.*;

public class WindowCerca extends WindowSecundaria {
    private JLabel cercaLabel;
    private JPanel cercaPanel;
    private JTextField entradaCerca;

    public WindowCerca() {
        mainFrame.setTitle("Cerca");
        configuraUI();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    protected void configuraUI() {
        super.configuraUI();
        // Panell xifres
        cercaPanel = new JPanel();
        cercaPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(cercaPanel, gbc);
        //Xifres label
        cercaLabel = new JLabel();
        cercaLabel.setEnabled(true);
        cercaLabel.setText("Cerca:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(15, 10, 10, 17);
        cercaPanel.add(cercaLabel, gbc);
        //Entrada xifres
        entradaCerca = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 20);
        cercaPanel.add(entradaCerca, gbc);
    }
}
