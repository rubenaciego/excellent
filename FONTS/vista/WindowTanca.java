package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe estructural corresponent a la vista que apareix en executar accions que requereixen només d’un enter per executar-se.
 */
public class WindowTanca {
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
     * Espaiador superior
     */
    private JPanel spacer;
    /**
     * Espaiador esquerre
     */
    private JPanel spacerE;
    /**
     * Espaiador dret
     */
    private JPanel spacerD;
    /**
     * Botó que confirma el tancament del document.
     */
    private JButton tancaButton;
    /**
     * Botó per cancel·lar l’operació.
     */
    private JButton cancelaButton;
    /**
     * Botó per primer desar i llavors tancar el document actual.
     */
    private JButton desaTancaButton;
    /**
     * Etiqueta contenidora del missatge de tancament.
     */
    private JLabel tancaLabel;
    /**
     * Indica si es tanca l'aplicació
     */
    private boolean tanca;
    /**
     * Indica si es desa l'aplicació
     */
    private boolean desa;

    /**
     * Constructora principal
     * @param frame el contenidor de la WindowTanca
     */
    public WindowTanca(JFrame frame) {
        mainDialog = new JDialog(frame, "Tanca document", Dialog.ModalityType.DOCUMENT_MODAL);
        configuraUI();
        mainDialog.setContentPane(mainPanel);
        mainDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainDialog.pack();
        mainDialog.setVisible(false);
        tanca = desa = false;

        desaTancaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainDialog.setVisible(false);
                tanca = desa = true;
            }
        });

        tancaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainDialog.setVisible(false);
                tanca = true;
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
     * Es mostra la finestra
     */
    public void mostra() {
        mainDialog.setVisible(true);
    }

    /**
     * Getter de la variable tanca
     * @return el booleà tanca
     */
    public boolean getTanca() {
        return tanca;
    }
    /**
     * Getter de la variable desa
     * @return el booleà desa
     */
    public boolean getDesa() {
        return desa;
    }

    /**
     * Configura tots els elements de UI per tal que se situïn a les seves posicions
     * corresponents i tinguin la mida, marges, alineacions… Correctes.
     */
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
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        buttonsPanel.add(tancaButton, gbc);
        cancelaButton = new JButton();
        cancelaButton.setText("Cancela");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        buttonsPanel.add(cancelaButton, gbc);
        desaTancaButton = new JButton();
        desaTancaButton.setText("Desa i tanca");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        buttonsPanel.add(desaTancaButton, gbc);
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

        spacerE = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(spacerE, gbc);

        spacerD = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(spacerD, gbc);
    }
}
