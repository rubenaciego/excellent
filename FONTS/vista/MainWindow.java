package vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MainWindow {
    private ControladorVista controladorVista;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JTabbedPane tabFulls;
    private JButton absButton;
    private JButton expButton;
    private JButton incrButton;
    private JButton decrButton;
    private JButton truncarButton;
    private JButton cosButton;
    private JButton sinButton;
    private JButton coshButton;
    private JButton sinhButton;
    private JButton tanhButton;
    private JComboBox ComboBox;
    private JButton covarianciaButton;
    private JButton desvEstButton;
    private JButton coefCorrButton;
    private JButton mitjanaButton;
    private JButton varianciaButton;
    private JButton medianaButton;
    private JButton reemplacaButton;
    private JButton longButton;
    private JButton minuscButton;
    private JButton majButton;
    private JButton diaButton;
    private JButton mesButton;
    private JButton anyButton;
    private JButton diaSetmButton;
    private JButton horoscopButton;
    private JLabel convUniComboBox;
    private JButton cercaButton;
    private JButton afegirFullButton;
    private JButton elimFullButton;

    //Menu
    private JMenuBar menuBarVista = new JMenuBar();

    private JMenu menuFile = new JMenu("Fitxer");
    private JMenuItem menuItemCrear = new JMenuItem("Crea document");
    private JMenuItem menuItemCarregar = new JMenuItem("Carrega document");
    private JMenuItem menuItemTancar = new JMenuItem("Tanca document");
    private JMenuItem menuItemDesar = new JMenuItem("Desa document");

    //TODO: vista secundaria
    private JMenu menuEditar = new JMenu("Editar");
    private JMenuItem menuItemSelectAll = new JMenuItem("Selecciona-ho tot");
    private JMenuItem menuItemSelectFila = new JMenuItem("Selecciona fila...");
    private JMenuItem menuItemSelectCol = new JMenuItem("Selecciona columna...");

    //TODO: vista secundaria
    private JMenu menuFull = new JMenu("Full");
    private JMenuItem menuItemAfegirFila = new JMenuItem("Afegeix fila");
    private JMenuItem menuItemAfegirCol = new JMenuItem("Afegeix columna");
    private JMenuItem menuItemElimFila = new JMenuItem("Elimina fila...");
    private JMenuItem menuItemElimCol = new JMenuItem("Elimina columna...");
    private JMenuItem menuItemCopiarBloc = new JMenuItem("Copia bloc...");
    private JMenuItem menuItemMoureBloc = new JMenuItem("Mou bloc...");
    private JMenuItem menuItemBuidarBloc = new JMenuItem("Buidar bloc...");
    private JMenuItem menuItemOrdenarBloc = new JMenuItem("Ordena bloc...");
    private JMenuItem menuItemTransposarBloc = new JMenuItem("Transposa bloc...");

    //TODO: vista secundaria
    private JMenu menuVista = new JMenu("Vista");
    private JMenuItem menuItemNightMode = new JMenuItem("Activa el mode nocturn");
    private JMenuItem menuItemCanviarEstil = new JMenuItem("Canvia l'estil...");

    //TODO: vista secundaria
    private JMenu menuAjuda = new JMenu("Ajuda");
    private JMenuItem menuItemDocu = new JMenuItem("Documentacio");
    private JMenuItem menuItemSobre = new JMenuItem("Sobre Excellent...");

    //Fulls
    private ArrayList<TableModel> fullTables;

    public MainWindow(ControladorVista controlador) {
        this.controladorVista = controlador;
        fullTables = new ArrayList<TableModel>();
        mainFrame = new JFrame("Excellent");

        $$$setupUI$$$();
        inicialitzar_menuBar();

        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);


        afegirFullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.afegeixFull();
            }
        });

        elimFullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selected = tabFulls.getSelectedIndex();
                controladorVista.esborraFull(selected);
            }
        });

        menuItemCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileDialog fd = new FileDialog(mainFrame, "Escull un arxiu", FileDialog.LOAD);
                // filename filter fd.setFile("*.json;*.csv");
                fd.setVisible(true);
                controladorVista.carregaDocument(fd.getFile());
            }
        });
    }

    private void inicialitzar_menuBar() {
        //Fitxer
        menuFile.add(menuItemCrear);
        menuFile.add(menuItemCarregar);
        menuFile.add(menuItemTancar);
        menuFile.add(menuItemDesar);

        //Editar
        menuEditar.add(menuItemSelectAll);
        menuEditar.add(menuItemSelectFila);
        menuEditar.add(menuItemSelectCol);

        //Full
        menuFull.add(menuItemAfegirFila);
        menuFull.add(menuItemAfegirCol);
        menuFull.add(menuItemElimFila);
        menuFull.add(menuItemElimCol);
        menuFull.add(menuItemCopiarBloc);
        menuFull.add(menuItemMoureBloc);
        menuFull.add(menuItemBuidarBloc);
        menuFull.add(menuItemOrdenarBloc);
        menuFull.add(menuItemTransposarBloc);

        //Vista
        menuVista.add(menuItemNightMode);
        menuVista.add(menuItemCanviarEstil);

        //Ajuda
        menuAjuda.add(menuItemDocu);
        menuAjuda.add(menuItemSobre);

        //Barra Menu
        menuBarVista.add(menuFile);
        menuBarVista.add(menuEditar);
        menuBarVista.add(menuFull);
        menuBarVista.add(menuVista);
        menuBarVista.add(menuAjuda);

        mainFrame.setJMenuBar(menuBarVista);
    }

    public void afegeixFull(int nrows, int ncols) {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.setNumRows(nrows);
        model.setColumnCount(ncols);

        int numFull = fullTables.size() + 1;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        tabFulls.addTab("Full " + numFull, panel);
        final JScrollPane scrollPane1 = new JScrollPane();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane1, gbc);
        table.setAutoResizeMode(0);
        table.setColumnSelectionAllowed(true);
        table.setDropMode(DropMode.USE_SELECTION);
        table.setRowSelectionAllowed(true);
        scrollPane1.setViewportView(table);
        table.setAutoResizeMode(0);
        table.setColumnSelectionAllowed(true);
        table.setDropMode(DropMode.USE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane1.setViewportView(table);

        RowNumberTable rowNumberTable = new RowNumberTable(table);
        scrollPane1.setRowHeaderView(rowNumberTable);

        fullTables.add(model);
        focusFull(fullTables.size() - 1);
    }

    public void esborraFull(int index) {
        if (index >= 0 && index < fullTables.size()) {
            tabFulls.remove(index);
            fullTables.remove(index);

            for (int i = index; i < fullTables.size(); ++i)
                tabFulls.setTitleAt(i, "Full " + (i + 1));
        }
    }

    public void setEntradesFull(int full, ArrayList<EntradaTaula> entrades)
    {
        if (full >= 0 && full < fullTables.size()) {
            TableModel model = fullTables.get(full);

            for (EntradaTaula e : entrades)
                model.setValueAt(e.valor, e.fila, e.columna);
        }
    }

    public void focusFull(int full)
    {
        if (full >= 0 && full < fullTables.size())
            tabFulls.setSelectedIndex(full);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(panel1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel1.add(panel2, gbc);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(panel3, gbc);
        cosButton = new JButton();
        cosButton.setText("Cos");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(cosButton, gbc);
        sinButton = new JButton();
        sinButton.setText("Sin");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(sinButton, gbc);
        coshButton = new JButton();
        coshButton.setText("Cosh");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(coshButton, gbc);
        sinhButton = new JButton();
        sinhButton.setText("Sinh");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(sinhButton, gbc);
        tanhButton = new JButton();
        tanhButton.setText("Tanh");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(tanhButton, gbc);
        absButton = new JButton();
        absButton.setText("Abs");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(absButton, gbc);
        decrButton = new JButton();
        decrButton.setText("Decr");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(decrButton, gbc);
        expButton = new JButton();
        expButton.setText("Exp");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(expButton, gbc);
        incrButton = new JButton();
        incrButton.setText("Incr");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(incrButton, gbc);
        truncarButton = new JButton();
        truncarButton.setText("Truncar");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel3.add(truncarButton, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Operacions aritmetiques");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel2.add(label1, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel1.add(panel4, gbc);
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(panel5, gbc);
        covarianciaButton = new JButton();
        covarianciaButton.setText("Covariancia");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel5.add(covarianciaButton, gbc);
        desvEstButton = new JButton();
        desvEstButton.setText("DesvEst");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel5.add(desvEstButton, gbc);
        coefCorrButton = new JButton();
        coefCorrButton.setText("CoefCorr");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel5.add(coefCorrButton, gbc);
        mitjanaButton = new JButton();
        mitjanaButton.setText("Mitjana");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel5.add(mitjanaButton, gbc);
        varianciaButton = new JButton();
        varianciaButton.setText("Variancia");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel5.add(varianciaButton, gbc);
        medianaButton = new JButton();
        medianaButton.setText("Mediana");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel5.add(medianaButton, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Operacions estadistiques");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel4.add(label2, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel1.add(panel6, gbc);
        panel6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel6.add(panel7, gbc);
        reemplacaButton = new JButton();
        reemplacaButton.setText("Reemplaca");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel7.add(reemplacaButton, gbc);
        longButton = new JButton();
        longButton.setText("Long");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel7.add(longButton, gbc);
        minuscButton = new JButton();
        minuscButton.setText("Minusc");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel7.add(minuscButton, gbc);
        majButton = new JButton();
        majButton.setText("Majusc");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel7.add(majButton, gbc);
        cercaButton = new JButton();
        cercaButton.setText("Cerca");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel7.add(cercaButton, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Operacions textuals");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel6.add(label3, gbc);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel1.add(panel8, gbc);
        panel8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel8.add(panel9, gbc);
        diaSetmButton = new JButton();
        diaSetmButton.setText("DiaSetm");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel9.add(diaSetmButton, gbc);
        horoscopButton = new JButton();
        horoscopButton.setText("Horoscop");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel9.add(horoscopButton, gbc);
        diaButton = new JButton();
        diaButton.setText("Dia");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel9.add(diaButton, gbc);
        anyButton = new JButton();
        anyButton.setText("Any");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel9.add(anyButton, gbc);
        mesButton = new JButton();
        mesButton.setText("Mes");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel9.add(mesButton, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Operacions de dates");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel8.add(label4, gbc);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel1.add(panel10, gbc);
        panel10.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-4473925)));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel10.add(panel11, gbc);
        ComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("km -> mi");
        defaultComboBoxModel1.addElement("mi -> km");
        defaultComboBoxModel1.addElement("km -> nmi");
        defaultComboBoxModel1.addElement("nmi -> km");
        defaultComboBoxModel1.addElement("m -> yd");
        defaultComboBoxModel1.addElement("yd -> m");
        defaultComboBoxModel1.addElement("km2 -> acre");
        defaultComboBoxModel1.addElement("acre -> km2");
        defaultComboBoxModel1.addElement("km2 -> sqmi");
        defaultComboBoxModel1.addElement("sqmi -> km2");
        defaultComboBoxModel1.addElement("l -> gal");
        defaultComboBoxModel1.addElement("gal -> l");
        defaultComboBoxModel1.addElement("g -> oz");
        defaultComboBoxModel1.addElement("oz -> g");
        defaultComboBoxModel1.addElement("kg -> lb");
        defaultComboBoxModel1.addElement("lb -> kg");
        defaultComboBoxModel1.addElement("kg -> t");
        defaultComboBoxModel1.addElement("t -> kg");
        defaultComboBoxModel1.addElement("grad -> rad");
        defaultComboBoxModel1.addElement("rad -> grad");
        defaultComboBoxModel1.addElement("ºF -> ºC");
        defaultComboBoxModel1.addElement("ºC -> ºF");
        defaultComboBoxModel1.addElement("ºC -> ºK");
        defaultComboBoxModel1.addElement("ºK -> ºC");
        ComboBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel11.add(ComboBox, gbc);
        convUniComboBox = new JLabel();
        convUniComboBox.setText("Conversio unitats");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel10.add(convUniComboBox, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        tabFulls = new JTabbedPane();
        tabFulls.setTabPlacement(3);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 500;
        mainPanel.add(tabFulls, gbc);
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(panel12, gbc);
        afegirFullButton = new JButton();
        afegirFullButton.setText("+");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel12.add(afegirFullButton, gbc);
        elimFullButton = new JButton();
        elimFullButton.setText("-");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel12.add(elimFullButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
