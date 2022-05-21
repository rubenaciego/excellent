package vista;

import javax.swing.*;
import java.awt.*;

public class WindowReemplaca extends WindowCerca {
    private JLabel reemplacaLabel;
    private JPanel reemplPanel;
    private JTextField reemplacaEntrada;

    public WindowReemplaca() {
        mainFrame.setTitle("Reemplaca");
        configuraUI();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
@Override
    protected void configuraUI() {
       super.configuraUI();

        // Panell reemplaca
        reemplPanel = new JPanel();
        reemplPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(reemplPanel, gbc);

        //Reemplaca label
        reemplacaLabel = new JLabel();
        reemplacaLabel.setEnabled(true);
        reemplacaLabel.setText("Reemplaca:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(15, 4, 10, 3);
        reemplPanel.add(reemplacaLabel, gbc);
        //Entrada reemplaca
        reemplacaEntrada = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 20);
        reemplPanel.add(reemplacaEntrada, gbc);
        cercaPanel.setVisible(false);
        reemplPanel.setVisible(true);
        cercaPanel.setVisible(true);
        System.out.println("hola");
    }
}
