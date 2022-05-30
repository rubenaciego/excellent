package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Classe corresponent a la vista que apareix en pitjar el botó corresponent a l’operació CERCA_OCURRENCIES.
 */
public class WindowCerca extends WindowSecundaria {
    /**
     * Etiqueta del panell de cerca.
     */
    protected JLabel cercaLabel;
    /**
     * Panell que conté els elements relacionats amb la introducció del text a cercar.
     */
    protected JPanel cercaPanel;
    /**
     * Camp per poder entrar el text a cercar.
     */
    protected JTextField entradaCerca;

    /**
     * Constructora principal
     * @param frame el contenidor de la WindowSecundaria
     */
    public WindowCerca(JFrame frame) {
        super(frame, "Cerca");
        destiPanel.setVisible(false);
    }

    /**
     * Constructora principal amb afegir títol
     * @param frame el contenidor de la WindowCerca
     * @param title el títol de la WindowCerca
     */
    protected WindowCerca(JFrame frame, String title) {
        super(frame, title);
    }

    public String getStringCercada() {return entradaCerca.getText();}

    /**
     * {@inheritDoc}
     */

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
