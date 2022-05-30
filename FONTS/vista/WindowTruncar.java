package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe corresponent a la vista que apareix en pitjar el botó corresponent a l’operació TRUNCA_NUMERO.
 */
public class WindowTruncar extends WindowSecundaria {

    /**
     * Etiqueta del panell d’introducció de xifres.
     */
    private JLabel xifresLabel;
    /**
     * Panell que conté els elements relacionats amb la introducció del número de xifres a truncar.
     */
    private JPanel xifresPanel;
    /**
     * Camp per poder entrar el número de xifres a truncar.
     */
    private JSpinner entradaXifres;

    /**
     * Constructora principal
     * @param frame el contenidor de la WindowSecundaria
     */
    public WindowTruncar(JFrame frame) {
        super(frame, "Truncar");
    }

    /**
     * Getter dels dígits a truncar
     * @return els nombre de dígits a truncar
     */
    public int getDigitsTruncar() {
        return (int)entradaXifres.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
