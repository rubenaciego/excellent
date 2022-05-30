package vista;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Classe corresponent a la vista que apareix en pitjar a l’opció de AFEGEIX_FULL.
 */
public class WindowAfegeixFull {
    /**
     * Botó per cancel·lar l’operació.
     */
    private JButton cancelaButton;
    /**
     * Botó per acceptar l’operació.
     */
    private JButton dAcordButton;
    /**
     * Camp per introduir el número de files.
     */
    private JSpinner spinnerFiles;
    /**
     * Etiqueta del panell de número de files.
     */
    private JLabel numFilesLabel;
    /**
     * Etiqueta del panell de número de columnes.
     */
    private JLabel numColumnesLabel;
    /**
     * Camp per introduir el número de columnes.
     */
    private JSpinner spinnerCols;
    /**
     * Espaiador superior
     */
    private JPanel spacerSup;
    /**
     * Espaiador inferior
     */
    private JPanel spacerInf;
    /**
     * Panell que conté els elements per introduir el número de columnes.
     */
    private JPanel colsPanel;
    /**
     * Panell que conté els elements per introduir el número de files.
     */
    private JPanel filesPanel;
    /**
     * Panell que conté els elements principals de la finestra.
     */
    private JPanel contPanel;
    /**
     * Panell que conté els botons per triar l’operació pertinent.
     */
    private JPanel buttonsPanel;
    /**
     * Panell contenidor de mainFrame.
     */
    private JPanel mainPanel;
    /**
     * Contenidor principal de la vista.
     */
    private JDialog mainDialog;
    /**
     * Indicador si s'ha sortit cancel·lant
     */
    private boolean success;

    /**
     * Constructora principal
     * @param frame el contenidor de la WindowAfegeixFull
     */
    public WindowAfegeixFull(JFrame frame) {
        mainDialog = new JDialog(frame, "Afegeix full", Dialog.ModalityType.DOCUMENT_MODAL);
        configuraUI();
        mainDialog.setContentPane(mainPanel);
        mainDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainDialog.pack();
        mainDialog.setVisible(false);
        success = false;

        cancelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainDialog.setVisible(false);
            }
        });

        dAcordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainDialog.setVisible(false);
                success = true;
            }
        });
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
     * @return retorna el nombre de files
     */
    public int getNumFiles() {
        return (Integer)spinnerFiles.getValue();
    }

    /**
     * @return retorna el nombre de columnes
     */
    public int getNumCols() {
        return (Integer)spinnerCols.getValue();
    }

    /**
     * Configura tots els elements de UI per tal que se situïn a les seves posicions
     * corresponents i tinguin la mida, marges, alineacions… Correctes.
     */
    public void configuraUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(buttonsPanel, gbc);

        cancelaButton = new JButton();
        cancelaButton.setText("Cancela");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        buttonsPanel.add(cancelaButton, gbc);

        dAcordButton = new JButton();
        dAcordButton.setText("D'acord");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        buttonsPanel.add(dAcordButton, gbc);

        spacerInf = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacerInf, gbc);

        spacerSup = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacerSup, gbc);

        contPanel = new JPanel();
        contPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(contPanel, gbc);

        filesPanel = new JPanel();
        filesPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(filesPanel, gbc);

        numFilesLabel = new JLabel();
        numFilesLabel.setText("Num. Files:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        filesPanel.add(numFilesLabel, gbc);

        spinnerFiles = new JSpinner(new SpinnerNumberModel(100, 1, Integer.MAX_VALUE, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 15);
        filesPanel.add(spinnerFiles, gbc);

        colsPanel = new JPanel();
        colsPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(colsPanel, gbc);

        numColumnesLabel = new JLabel();
        numColumnesLabel.setText("Num. Columnes:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 0);
        colsPanel.add(numColumnesLabel, gbc);

        spinnerCols = new JSpinner(new SpinnerNumberModel(100, 1, Integer.MAX_VALUE, 1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 15);
        colsPanel.add(spinnerCols, gbc);
    }

}
