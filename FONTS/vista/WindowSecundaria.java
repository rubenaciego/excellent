package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowSecundaria {

    protected JDialog mainDialog;
    protected JPanel mainPanel;

    protected JPanel contPanel;
    protected JPanel origenPanel;
    protected JPanel destiPanel;
    protected JPanel botonsPanel;
    protected JPanel spacerSup;
    protected JPanel spacerInf;
    protected JLabel destiLabel;
    protected JTextField entradaOrigen;
    protected JButton cancelaButton;
    protected JButton dAcordButton;
    protected JLabel origenLabel;
    protected JTextField entradaDesti;

    protected boolean success;

    public WindowSecundaria(JFrame frame, String title) {
        mainDialog = new JDialog(frame, title, Dialog.ModalityType.DOCUMENT_MODAL);
        configuraUI();
        mainDialog.setContentPane(mainPanel);
        mainDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainDialog.pack();
        mainDialog.setVisible(false);
        success = false;

        dAcordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainDialog.setVisible(false);
                success = true;
            }
        });

        cancelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainDialog.setVisible(false);
            }
        });
    }

    public void setDefault(String cela) {
        entradaOrigen.setText(cela);
        entradaDesti.setText(cela);
    }

    public boolean mostra() {
        mainDialog.setVisible(true);
        return success;
    }

    public void amaga() {
        mainDialog.setVisible(false);
    }

    protected void configuraUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        //Panell contenidor
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        contPanel = new JPanel();
        contPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(contPanel, gbc);

        // Panell desti
        destiPanel = new JPanel();
        destiPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(destiPanel, gbc);
        // Label desti
        destiLabel = new JLabel();
        destiLabel.setText("Desti:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 10, 20);
        destiPanel.add(destiLabel, gbc);
        // Caixa text desti
        entradaDesti = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 20);
        destiPanel.add(entradaDesti, gbc);

        //Panell origen
        origenPanel = new JPanel();
        origenPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(origenPanel, gbc);
        //Label origen
        origenLabel = new JLabel();
        origenLabel.setText("Origen:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 10, 10);
        origenPanel.add(origenLabel, gbc);
        // Caixa text origen
        entradaOrigen = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 20);
        origenPanel.add(entradaOrigen, gbc);

        //Panell botons
        botonsPanel = new JPanel();
        botonsPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add(botonsPanel, gbc);
        //Boto cancela
        cancelaButton = new JButton();
        cancelaButton.setText("Cancela");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        botonsPanel.add(cancelaButton, gbc);
        //Boto d'acord
        dAcordButton = new JButton();
        dAcordButton.setText("D'acord");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        botonsPanel.add(dAcordButton, gbc);

        //Spacer de sobre
        spacerSup = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacerSup, gbc);

        //Spacer de sota
        spacerInf = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacerInf, gbc);
    }
}
