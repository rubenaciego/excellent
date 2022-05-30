package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Classe corresponent a la vista que apareix en pitjar la gran majoria dels botons corresponents a operacions de WindowPrincipal.
 */
public class WindowSecundaria {

    /**
     * Contenidor principal de la vista.
     */
    protected JDialog mainDialog;
    /**
     * Panell contenidor de mainFrame.
     */
    protected JPanel mainPanel;
    /**
     * Panell que conté els principals elements de la vista.
     */
    protected JPanel contPanel;
    /**
     * Panell que conté els elements relacionats amb la introducció de l’origen.
    */
    protected JPanel origenPanel;
    /**
     * Panell que conté els elements relacionats amb la introducció del destí.
     */
    protected JPanel destiPanel;
    /**
     * Panell que conté els botons d’acceptar o cancel·lar l’operació.
     */
    protected JPanel botonsPanel;
    /**
     * Espaiador superior
     */
    protected JPanel spacerSup;
    /**
     * Espaiador inferior
     */
    protected JPanel spacerInf;
    /**
     * Etiqueta del panell de destí.
     */
    protected JLabel destiLabel;
    /**
     * Camp per poder entrar el punt d’origen de l’operació.
     */
    protected JTextField entradaOrigen;
    /**
     * Botó per cancel·lar l’operació.
     */
    protected JButton cancelaButton;
    /**
     * Botó per acceptar l’operació.
     */
    protected JButton dAcordButton;
    /**
     * Etiqueta del panell d'origen.
    */
    protected JLabel origenLabel;
    /**
     * Camp per poder entrar el destí de l’operació.
     */
    protected JTextField entradaDesti;
    /**
     * Indicador si s'ha sortit cancel·lant
     */
    protected boolean success;

    /**
     * Getter del text del camp entradaOrigen
     * @return el contingut de entradaOrigen
     */
    public String getOrigen() {
        return entradaOrigen.getText();
    }

    /**
     * Getter del text del camp entradaDesti
     * @return el contigut de entradaDesti
     */
    public String getDesti() {
        return entradaDesti.getText();
    }

    /**
     * Constructora principal
     * @param frame el contenidor de la nova WindowSecundaria
     * @param title el títol de la WindowSecundaria
     */
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

    /**
     * Setter del contingut per defecte
     * @param cela el contingut per defecte
     */
    public void setDefault(String cela) {
        entradaOrigen.setText(cela);
        entradaDesti.setText(cela);
    }

    /**
     * Fa visible la WindowSecundaria
     * @return retorna si s'ha sortit cancel·lant
     */
    public boolean mostra() {
        mainDialog.setVisible(true);
        return success;
    }

    /**
     * Amaga la WindowSecundaria
     */
    public void amaga() {
        mainDialog.setVisible(false);
    }

    /**
     * Configura tots els elements de UI per tal que se situïn a les seves posicions corresponents
     * i tinguin la mida, marges, alineacions… Correctes.
     */
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
