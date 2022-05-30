package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.*;

public class WindowSpinner {
    /**
     * Contenidor principal de la vista.
     */
    private JDialog mainDialog;
    /**
     * Es tracta del panell contenidor de mainFrame.
     */
    private JPanel mainPanel;
    /**
     * Panell que conté els botons d’acceptar o cancel·lar l’operació.
     */
    private JPanel botonsPanel;
    /**
     * Espaiador superior.
     */
    private JPanel spacer1;
    /**
     * Espaiador inferior.
     */
    private JPanel spacer2;
    /**
     * Panell que conté els elements relacionats amb l’operació a executar.
     */
    private JPanel opPanel;
    /**
     * Botó per cancel·lar l’operació.
     */
    private JButton cancelaButton;
    /**
     * Botó per acceptar l’operació.
     */
    private JButton dAcordButton;
    /**
     * Etiqueta del panell d’operacions.
     */
    private JLabel opLabel;
    /**
     * Camp per introduir l’enter sobre el que s’executarà la corresponent operació.
     */
    private JSpinner spinnerOp;
    /**
     * Indica èxit d'algun resultat
     */
    protected boolean success;
    /**
     * Indica si s'estan usant lletres
     */
    boolean usaLletres;

    /**
     * El nombre màxim que arriba el Spinner
     */
    int maxSpin;

    /**
     * Contructora principal
     * @param frame el contenidor de la WindowSpinner
     * @param titol el títol de la WindowSpinner
     * @param accio indica l'acció que es realitza
     * @param nmax el número màxim que pot arribar el Spinner
     * @param lletres indica si s'usen lletres en el Spinner
     */
    public WindowSpinner(JFrame frame, String titol, String accio, int nmax,
                         boolean lletres) {
        mainDialog = new JDialog(frame, titol,Dialog.ModalityType.DOCUMENT_MODAL);
        maxSpin = nmax;
        usaLletres = lletres;
        configuraUI(accio);
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
     * Getter del valor del Spinner
     * @return el valor del Spinner
     */
    public int getValue() {
        if (!usaLletres) return (int)spinnerOp.getValue();
        else return Utilitats.convertirBase26(spinnerOp.getValue().toString());
    }

    /**
     * Setter del valor per defecte
     * @param n el valor per defecte
     */
    public void setDefault(int n) {
        if (!usaLletres)  spinnerOp.setValue(n);
        else spinnerOp.setValue(Utilitats.convertirABase26(n - 1));
    }

    /**
     * Es mostra la finestra
     * @return true
     */
    public boolean mostra() {
        mainDialog.setVisible(true);
        return success;
    }

    /**
     * configura tots els elements de UI per tal que se situïn a les seves
     * posicions corresponents i tinguin la mida, marges, alineacions… Correctes.
     * A més, afegeix com a text de l’etiqueta opLabel la String accio.
     * @param accio el text corresponent a l'acció que s'executa
     */
    private void configuraUI(String accio) {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        botonsPanel = new JPanel();
        botonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        mainPanel.add(botonsPanel, gbc);

        cancelaButton = new JButton();
        cancelaButton.setText("Cancela");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        botonsPanel.add(cancelaButton, gbc);
        dAcordButton = new JButton();
        dAcordButton.setText("D'acord");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        botonsPanel.add(dAcordButton, gbc);

        spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer1, gbc);
        opPanel = new JPanel();
        opPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(opPanel, gbc);
        opLabel = new JLabel();
        opLabel.setText(accio);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        opPanel.add(opLabel, gbc);

        if (!usaLletres){
            SpinnerNumberModel m_numberSpinnerModel;

            int current = 1;
            int min = 1;
            int max = maxSpin;
            int step = 1;
            m_numberSpinnerModel = new SpinnerNumberModel(current, min, max, step);
            spinnerOp = new JSpinner(m_numberSpinnerModel);
        } else {
            SpinnerListModel m_stringSpinnerModel;
            String[] llistaCols = new String[maxSpin];

            for (int i = 0; i < maxSpin; ++i)
                llistaCols[i] = Utilitats.convertirABase26(i);

            m_stringSpinnerModel = new SpinnerListModel(llistaCols);
            spinnerOp = new JSpinner(m_stringSpinnerModel);
        }

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.insets = new Insets(10, 10, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        opPanel.add(spinnerOp, gbc);
        spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer2, gbc);
    }
}
