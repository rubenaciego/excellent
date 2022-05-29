package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowSpinner {
    private JDialog mainDialog;
    private JPanel mainPanel;
    private JPanel botonsPanel;
    private JPanel spacer1;
    private JPanel spacer2;
    private JPanel opPanel;
    private JButton cancelaButton;
    private JButton dAcordButton;
    private JLabel opLabel;
    private JSpinner spinnerOp;
    protected boolean success;

    int maxSpin;


    public WindowSpinner(JFrame frame, String titol, String accio, int nmax) {
        mainDialog = new JDialog(frame, titol,Dialog.ModalityType.DOCUMENT_MODAL);
        maxSpin = nmax;
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

    public int getValue() {return (int)spinnerOp.getValue();}

    public void setDefault(int n) {spinnerOp.setValue(n);}

    public boolean mostra() {
        mainDialog.setVisible(true);
        return success;
    }

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

        SpinnerNumberModel m_numberSpinnerModel;
        int current = 1;
        int min = 1;
        int max = maxSpin;
        int step = 1;
        m_numberSpinnerModel = new SpinnerNumberModel(current, min, max, step);
        spinnerOp = new JSpinner(m_numberSpinnerModel);

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
