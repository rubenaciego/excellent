package vista;

import javax.swing.*;
import java.awt.*;

public class WindowCerca extends WindowSecundaria {
    protected JLabel cercaLabel;
    protected JPanel cercaPanel;
    protected JTextField entradaCerca;

    public WindowCerca() {
        mainFrame.setTitle("Cerca");
        configuraUI();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
@Override
    protected void configuraUI() {
        super.configuraUI();

        // Panell cerca
        cercaPanel = new JPanel();
        cercaPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(cercaPanel, gbc);
        //Cerca label
        cercaLabel = new JLabel();
        cercaLabel.setEnabled(true);
        cercaLabel.setText("Cerca:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 10, 17);
        cercaPanel.add(cercaLabel, gbc);
        //Entrada cerca
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