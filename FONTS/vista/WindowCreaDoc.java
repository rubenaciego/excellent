package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe corresponent a la vista que apareix en pitjar a l’opció de CREA_DOCUMENT.
 */
public class WindowCreaDoc {
    /**
     * Contenidor principal de la vista.
     */
    private JDialog mainDialog;
    /**
     * Panell contenidor de mainFrame.
     */
    private JPanel mainPanel;
    /**
     * Panell que conté els botons per triar l’operació pertinent.
     */
    private JPanel buttonsPanel;
    /**
     * Panell que conté els elements relacionats amb l'entrada del títol.
     */
    private JPanel nomDocPanel;
    /**
     * Espaiador superior
     */
    private JPanel spacerSup;
    /**
     * Espaiador inferior
     */
    private JPanel spacerInf;
    /**
     * Botó per cancel·lar l’operació.
     */
    private JButton cancelaButton;
    /**
     * Botó per acceptar l’operació.
     */
    private JButton dAcordButton;
    /**
     * Camp per poder entrar el nom del nou document.
     */
    private JTextField entradaDoc;
    /**
     * Etiqueta del panell del nom del nou document.
     */
    private JLabel nomDocumentLabel;

    /**
     * Indica si hi ha hagut èxit en l'operació
     */
    private boolean success;

    /**
     * Constructora principal
     * @param frame el contenidor de la WindowCreaDoc
     * @param title el títol de la finestra
     */
    public WindowCreaDoc(JFrame frame, String title) {
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
     * Mostra la WindowCreaDoc actual
     * @return true si hi ha hagut èxit, false altrament
     */
    public boolean mostra() {
        mainDialog.setVisible(true);
        return success;
    }

    /**
     * Getter del nom del document
     * @return el nom del document
     */
    public String getDocumentName() {
        return entradaDoc.getText();
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

        spacerSup = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacerSup, gbc);

        spacerInf = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacerInf, gbc);

        nomDocPanel = new JPanel();
        nomDocPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(nomDocPanel, gbc);

        nomDocumentLabel = new JLabel();
        nomDocumentLabel.setText("Nom document:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        nomDocPanel.add(nomDocumentLabel, gbc);

        entradaDoc = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 15);
        gbc.ipadx = 100;
        nomDocPanel.add(entradaDoc, gbc);
    }
}
