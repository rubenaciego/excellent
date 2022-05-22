package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowTruncar extends WindowSecundaria {

    private JLabel xifresLabel;
    private JPanel xifresPanel;
    private JSpinner entradaXifres;
    String origen;
    String desti;
    int digitsTruncar;

    public WindowTruncar() {
        mainFrame.setTitle("Truncar");
        configuraUI();
    }

    public void oculta() {
        mainFrame.setVisible(false);
    }

    public void mostra(int filaOrigen, int colOrigen) {

        mainFrame.setVisible(true);

        dAcordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.setVisible(false);
                origen = entradaOrigen.getText();
                entradaOrigen.setText("");
                desti = entradaDesti.getText();
                entradaDesti.setText("");
                digitsTruncar = (Integer) entradaXifres.getValue();
                entradaXifres.setValue(0);
            }
        });

        cancelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.setVisible(false);
                entradaOrigen.setText("");
                entradaDesti.setText("");
                entradaXifres.setValue(0);
            }
        });
    }

    public String getOrigen() {
        return origen;
    }

    public String getDesti() {
        return desti;
    }

    public int getDigitsTruncar() {
        return digitsTruncar;
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
        entradaXifres = new JSpinner();
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
