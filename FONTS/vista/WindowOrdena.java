package vista;

import javax.swing.*;
import java.awt.*;
import util.Utilitats;

public class WindowOrdena extends WindowSecundaria {
    private JComboBox<String> columnaOrdenaSpin;
    private JLabel columnaOrdenacioLabel;
    private JLabel criteriLabel;
    private JComboBox<String> criteriCombo;
    private JPanel columnaPanel;
    private JPanel criteriPanel;

    public WindowOrdena(JFrame frame) {
        super(frame, "Ordena");
    }

    public String getCriteri() {
        return criteriCombo.getSelectedItem().toString();
    }

    public int getColOrd() {
        return Utilitats.convertirBase26(columnaOrdenaSpin.getSelectedItem().toString());
    }

    public void setEntradesColumna(int col, int ncols) {
        DefaultComboBoxModel<String> ordenaModel = new DefaultComboBoxModel<String>();
        for (int i = 0; i < ncols; ++i)
            ordenaModel.addElement(Utilitats.convertirABase26(i));

        columnaOrdenaSpin.setModel(ordenaModel);
        columnaOrdenaSpin.setSelectedIndex(col);
        mainDialog.pack();
    }

    @Override
    protected void configuraUI() {
        super.configuraUI();
        columnaPanel = new JPanel();
        columnaPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(columnaPanel, gbc);
        columnaOrdenacioLabel = new JLabel();
        columnaOrdenacioLabel.setText("Columna Ordenacio:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 10, 10);
        columnaPanel.add(columnaOrdenacioLabel, gbc);
        columnaOrdenaSpin = new JComboBox<String>();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 20);
        columnaPanel.add(columnaOrdenaSpin, gbc);
        columnaPanel.setVisible(true);

        criteriPanel = new JPanel();
        criteriPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        contPanel.add(criteriPanel, gbc);
        criteriLabel = new JLabel();
        criteriLabel.setText("Criteri:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 10, 10, 10);
        criteriPanel.add(criteriLabel, gbc);
        criteriCombo = new JComboBox<String>();
        final DefaultComboBoxModel<String> defaultComboBoxModel1 = new DefaultComboBoxModel<String>();
        defaultComboBoxModel1.addElement("Ascendent");
        defaultComboBoxModel1.addElement("Descendent");
        criteriCombo.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 20);
        criteriPanel.add(criteriCombo, gbc);
    }
}
