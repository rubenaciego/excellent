package vista;

import javax.swing.*;
import java.awt.*;

public class WindowTanca {
    private JDialog mainDialog;
    private JPanel mainPanel;
    private JPanel buttonsPanel;

    private JPanel spacer;
    private JButton tancaButton;
    private JButton cancelaButton;
    private JButton desaISurtButton;
    private JLabel tancaLabel;

    public WindowTanca(JFrame frame) {
        mainDialog = new JDialog(frame, "Tanca document", Dialog.ModalityType.DOCUMENT_MODAL);
        configuraUI();
        mainDialog.setContentPane(mainPanel);
        mainDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainDialog.pack();
        mainDialog.setVisible(false);
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
        desaISurtButton = new JButton();
        desaISurtButton.setText("Desa i tanca");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(desaISurtButton, gbc);
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
