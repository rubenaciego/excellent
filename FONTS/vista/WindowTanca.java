package vista;

import javax.swing.*;
import java.awt.*;

public class WindowTanca {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private JPanel spacer;
    private JButton tancaButton;
    private JButton cancelaButton;
    private JButton desaTancaButton;
    private JLabel tancaLabel;

    public WindowTanca() {
        mainFrame = new JFrame("Tanca document");
        configuraUI();
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void configuraUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonsPanel, gbc);
        tancaButton = new JButton();
        tancaButton.setText("Tanca");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(tancaButton, gbc);
        cancelaButton = new JButton();
        cancelaButton.setText("Cancela");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(cancelaButton, gbc);
        desaTancaButton = new JButton();
        desaTancaButton.setText("Desa i tanca");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(desaTancaButton, gbc);
        tancaLabel = new JLabel();
        tancaLabel.setText("Estas segur que vols tancar?");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(tancaLabel, gbc);
        spacer = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer, gbc);
    }
}
