package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Classe corresponent a la vista que apareix en pitjar el botó corresponent a l’operació REEMPLACA.
 */
public class WindowReemplaca extends WindowCerca {
    /**
     * Etiqueta del panell de reemplaçar.
     */
    private JLabel reemplacaLabel;
    /**
     * Panell que conté els elements relacionats amb la introducció del text a reemplaçar.
     */
    private JPanel reemplPanel;
    /**
     * Camp per poder entrar el text a reemplaçar.
     */
    private JTextField reemplacaEntrada;

    /**
     * Constructora principal
     * @param frame el contenidor de la WindowReemplaca
     */
    public WindowReemplaca(JFrame frame) {
        super(frame, "Reemplaça");
        destiPanel.setVisible(true);
    }

    /**
     * Getter del text reemplaçador
     * @return el text reemplaçador
     */
    public String getStringReemplacadora() {return reemplacaEntrada.getText();}

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configuraUI() {
        super.configuraUI();

        // Panell reemplaca
        reemplPanel = new JPanel();
        reemplPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(reemplPanel, gbc);

        //Reemplaca label
        reemplacaLabel = new JLabel();
        reemplacaLabel.setEnabled(true);
        reemplacaLabel.setText("Reemplaca:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 10, 10);
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
    }
}
