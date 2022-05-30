package vista;

import javafx.util.Pair;
import util.Utilitats;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class WindowPrincipal {
    private final ControladorVista controladorVista;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel opPanel;
    private JPanel opAritPanel;
    private JPanel opAritButtPanel;
    private JPanel opEstPanel;
    private JPanel opEstButtPanel;
    private JPanel opTextPanel;
    private JPanel opTextButtPanel;
    private JPanel opDataPanel;
    private JPanel opDataButtPanel;
    private JPanel opConvPanel;
    private JPanel opConvButtPanel;
    private JPanel spacer;
    private JPanel buttFullsPanel;
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
    private JComboBox comboBox;
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
    private JButton convertirButton;
    private JLabel operacionsAritmetiquesLabel;
    private JLabel operacionsEstadistiquesLabel;
    private JLabel operacionsTextualsLabel;
    private JLabel operacionsDeDatesLabel;
    private JTextField entradaInput;
    private JLabel inputLabel;
    private JPanel barraInput;

    private JPanel espaiadorInput;

    //Menu
    private JMenuBar menuBarVista = new JMenuBar();

    private JMenu menuFile = new JMenu("Fitxer");
    private JMenuItem menuItemCrear = new JMenuItem("Crea document");
    private JMenuItem menuItemCarregar = new JMenuItem("Carrega document");
    private JMenuItem menuItemReanomena = new JMenuItem("Reanomena document");
    private JMenuItem menuItemTancar = new JMenuItem("Tanca document");
    private JMenuItem menuItemDesar = new JMenuItem("Desa document");

    private JMenu menuEditar = new JMenu("Editar");
    private JMenuItem menuItemSelectAll = new JMenuItem("Selecciona-ho tot");
    private JMenuItem menuItemSelectFila = new JMenuItem("Selecciona fila...");
    private JMenuItem menuItemSelectCol = new JMenuItem("Selecciona columna...");

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
    private JMenuItem menuItemAfegirFull = new JMenuItem("Afegeix full");

    private JMenu menuAjuda = new JMenu("Ajuda");
    private JMenuItem menuItemDocu = new JMenuItem("Documentació");
    private JMenuItem menuItemSobre = new JMenuItem("Sobre Excellent...");

    //Menu Clic dret
    private JPopupMenu menuClicDret;
    private JMenuItem itAfFila = new JMenuItem("Afegeix fila");
    private JMenuItem itAfCol = new JMenuItem("Afegeix columna");
    private JMenuItem itElFila = new JMenuItem("Elimina fila/es");
    private JMenuItem itElCol = new JMenuItem("Elimina columna/es");
    private JMenuItem itSelFila = new JMenuItem("Selecciona fila/es");
    private JMenuItem itSelCol = new JMenuItem("Selecciona columna/es");
    private JMenuItem itBuida = new JMenuItem("Buida bloc");
    private JMenuItem itCopia = new JMenuItem("Copia bloc");
    private JMenuItem itMou = new JMenuItem("Mou bloc");
    private JMenuItem itOrd = new JMenuItem("Ordena bloc");
    private JMenuItem itTransp = new JMenuItem("Transposa bloc");
    //Fulls
    private ArrayList<DefaultTableModel> fullTables;
    private boolean documentObert;

    public WindowPrincipal(ControladorVista controlador) {
        this.controladorVista = controlador;
        fullTables = new ArrayList<DefaultTableModel>();
        mainFrame = new JFrame("Excellent");

        configuraUI();
        inicialitzar_menuBar();
        inicialitzar_menuContextual();

        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);

        documentObert = false;
        deshabilitaControls();

        afegirFullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.afegeixFull();

                if (!fullTables.isEmpty())
                    habilitaControls();
            }
        });

        elimFullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.esborraFull(getFocusedFull());

                if (fullTables.isEmpty())
                    deshabilitaControls();
            }
        });

        menuItemCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WindowCreaDoc w = new WindowCreaDoc(mainFrame, "Crea document");

                if (w.mostra()) {
                    controladorVista.creaDocument(w.getDocumentName());

                    if (documentObert)
                        habilitaControls();
                }
            }
        });

        menuItemCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileDialog fd = new FileDialog(mainFrame, "Escull un arxiu", FileDialog.LOAD);
                fd.setFilenameFilter(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String s) {
                        return s.endsWith(".json") || s.endsWith(".csv");
                    }
                });

                fd.setVisible(true);

                String file = fd.getFile();
                if (file != null) {
                    controladorVista.carregaDocument(fd.getDirectory() + "/" + file);

                    if (documentObert)
                        habilitaControls();
                }
            }
        });

        menuItemReanomena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WindowCreaDoc w = new WindowCreaDoc(mainFrame, "Reanomena document");
                if (w.mostra())
                    controladorVista.reanomenaDocument(w.getDocumentName());
            }
        });

        menuItemDesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.desaDocument();
            }
        });

        menuItemTancar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                WindowTanca w = new WindowTanca(mainFrame);
                w.mostra();

                if (w.getTanca()) {
                    if (w.getDesa())
                        controladorVista.desaDocument();

                    controladorVista.tancaDocument();

                    if (!documentObert)
                        deshabilitaControls();
                }
            }
        });

        menuItemSelectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JPanel panel = (JPanel) tabFulls.getSelectedComponent();
                JTable table = (JTable) (((JScrollPane) panel.getComponent(0)).getViewport().getView());
                table.selectAll();
            }
        });

        menuItemSelectFila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int nfiles = fullTables.get(getFocusedFull()).getRowCount();
                int ncols = fullTables.get(getFocusedFull()).getColumnCount();
                WindowSpinner w = new WindowSpinner(mainFrame, "",
                        "Selecciona l'índex de la fila a seleccionar", nfiles
                        , false);

                SeleccioTaula s = getCurrentSelection();

                if (s.fila >= 0) w.setDefault(s.fila + 1);
                else w.setDefault(1);

                if (w.mostra()) {
                    int f = w.getValue() - 1;
                    JPanel panel = (JPanel) tabFulls.getSelectedComponent();
                    JTable table = (JTable) (((JScrollPane) panel.getComponent(0)).getViewport().getView());
                    table.setRowSelectionInterval(f, f);
                    table.setColumnSelectionInterval(0, ncols - 1);
                }
            }
        });

        menuItemSelectCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int nfiles = fullTables.get(getFocusedFull()).getRowCount();
                int ncols = fullTables.get(getFocusedFull()).getColumnCount();
                WindowSpinner w = new WindowSpinner(mainFrame, "",
                        "Selecciona l'índex de la columna a seleccionar",
                        ncols, true);

                SeleccioTaula s = getCurrentSelection();

                if (s.col >= 0) w.setDefault(s.col + 1);
                else w.setDefault(1);

                if (w.mostra()) {
                    int c = w.getValue();
                    JPanel panel = (JPanel) tabFulls.getSelectedComponent();
                    JTable table = (JTable) (((JScrollPane) panel.getComponent(0)).getViewport().getView());
                    table.setRowSelectionInterval(0, nfiles - 1);
                    table.setColumnSelectionInterval(c, c);
                }
            }
        });

        absButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Valor absolut");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.valorAbsolut(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        incrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Incrementar");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.incrementar(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        decrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Decrementar");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.decrementar(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        expButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Exponencial");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.exponencial(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        cosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Cosinus");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.cosinus(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        sinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Sinus");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.sinus(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        coshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Cosh");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.cosinusHiperbolic(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        sinhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Sinh");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.sinusHiperbolic(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        tanhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Tanh");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.tangentHiperbolic(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        truncarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowTruncar w = new WindowTruncar(mainFrame);

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    int digitsTruncar = w.getDigitsTruncar();
                    controladorVista.truncar(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue(), digitsTruncar);
                }
            }
        });

        longButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Longitud del text");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.longitudText(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        majButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Convertir a majúscules");

                if (!s.empty()) w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.majuscules(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        minuscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Convertir a minúscules");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.minuscules(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        cercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowCerca w = new WindowCerca(mainFrame);

                if (!s.empty()) w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }

                    String stringCercada = w.getStringCercada();
                    controladorVista.cerca(getFocusedFull(), s.fila,
                            s.col, s.nfiles, s.ncols, stringCercada);
                }
            }
        });

        reemplacaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowReemplaca w = new WindowReemplaca(mainFrame);

                if (!s.empty()) w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    String stringCercada = w.getStringCercada();
                    String stringReemplacadora = w.getStringReemplacadora();

                    controladorVista.reemplaca(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue(), stringCercada,
                            stringReemplacadora);
                }
            }
        });

        mitjanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Mitjana");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.mitjana(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        medianaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Mediana");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.mediana(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        varianciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Variància");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.variancia(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        desvEstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Desviació estàndard");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.desviacioEstandard(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        covarianciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Covariància");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.covariancia(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        coefCorrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Coeficient de correlació");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.coeficientPearson(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        diaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Extreu dia");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.extreureDia(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        mesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Extreu mes");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.extreureMes(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        anyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Extreu any");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.extreureAny(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        diaSetmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Extreu dia de la setmana");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.extreureDiaSetmana(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        horoscopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Extreu horòscop");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.extreureHoroscop(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols, desti.getKey(), desti.getValue());
                }
            }
        });

        convertirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String conv = comboBox.getSelectedItem().toString();
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Convertir unitats");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.convertirUnitats(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue(), conv);
                }
            }
        });

        menuItemTransposarBloc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Transposa bloc");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.transposaBloc(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue());
                }
            }
        });

        menuItemOrdenarBloc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowOrdena w = new WindowOrdena(mainFrame);

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else {
                    w.setDefault("A1");
                    s.col = 1;
                }

                w.setEntradesColumna(s.col, fullTables.get(getFocusedFull()).getColumnCount());

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    String criteri = w.getCriteri().toUpperCase();
                    int colOrd = w.getColOrd();

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.ordenaBloc(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue(), criteri, colOrd - origen.getValue());
                }
            }
        });

        menuItemMoureBloc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Mou bloc");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.mouBloc(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue());
                }
            }
        });

        menuItemCopiarBloc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Copia bloc");

                if (!s.empty())
                    w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else
                    w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.copiaBloc(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue());
                }
            }
        });

        menuItemBuidarBloc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();

                if (!s.empty())
                    controladorVista.buidaBloc(getFocusedFull(), s.fila, s.col, s.nfiles, s.ncols);
            }
        });

        menuItemAfegirFila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.afegeixFila(getFocusedFull());
            }
        });

        menuItemAfegirCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.afegeixColumna(getFocusedFull());
            }
        });

        menuItemElimCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();

                int ncols = fullTables.get(getFocusedFull()).getColumnCount();

                WindowSpinner w = new WindowSpinner(mainFrame, "",
                        "Selecciona l'índex de la columna a eliminar", ncols,
                        true);

                if (!s.empty()) w.setDefault(s.col + 1);
                else w.setDefault(1);

                if (w.mostra()) {
                    int col = w.getValue();
                    controladorVista.eliminaColumna(getFocusedFull(), col - 1);
                }
            }
        });

        menuItemElimFila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();

                int nfiles = fullTables.get(getFocusedFull()).getRowCount();

                WindowSpinner w = new WindowSpinner(mainFrame, "",
                        "Selecciona l'índex de la fila a eliminar", nfiles,
                        false);

                if (!s.empty()) w.setDefault(s.fila + 1);
                else w.setDefault(1);

                if (w.mostra()) {
                    int fila = w.getValue();
                    controladorVista.eliminaFila(getFocusedFull(), fila - 1);
                }
            }
        });

        menuItemAfegirFull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.afegeixFull();

                if (!fullTables.isEmpty())
                    habilitaControls();
            }
        });

        itAfFila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.afegeixFila(getFocusedFull());
            }
        });

        itAfCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controladorVista.afegeixColumna(getFocusedFull());
            }
        });

        itSelFila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int ncols = fullTables.get(getFocusedFull()).getColumnCount();
                SeleccioTaula s = getCurrentSelection();

                if (!s.empty()) {
                    JPanel panel = (JPanel) tabFulls.getSelectedComponent();
                    JTable table = (JTable) (((JScrollPane) panel.getComponent(0)).getViewport().getView());
                    table.setRowSelectionInterval(s.fila, s.fila + s.nfiles - 1);
                    table.setColumnSelectionInterval(0, ncols - 1);
                }
            }
        });

        itSelCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int nfiles = fullTables.get(getFocusedFull()).getRowCount();
                SeleccioTaula s = getCurrentSelection();

                if (!s.empty()) {
                    JPanel panel = (JPanel) tabFulls.getSelectedComponent();
                    JTable table = (JTable) (((JScrollPane) panel.getComponent(0)).getViewport().getView());
                    table.setRowSelectionInterval(0, nfiles - 1);
                    table.setColumnSelectionInterval(s.col, s.col + s.ncols - 1);
                }
            }
        });

        itElFila.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();

                if (!s.empty()) {
                    int fila = s.fila;
                    for (int i = 0; i < s.nfiles; ++i)
                        controladorVista.eliminaFila(getFocusedFull(), fila);
                }
            }
        });

        itElCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();

                if (!s.empty()) {
                    int col = s.col;
                    for (int i = 0; i < s.ncols; ++i)
                        controladorVista.eliminaColumna(getFocusedFull(), col);
                }
            }
        });

        itBuida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();

                if (!s.empty())
                    controladorVista.buidaBloc(getFocusedFull(), s.fila, s.col, s.nfiles, s.ncols);
            }
        });

        itCopia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Copia bloc");

                if (!s.empty()) w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.copiaBloc(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue());
                }
            }
        });

        itMou.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Mou bloc");

                if (!s.empty()) w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.mouBloc(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue());
                }
            }
        });

        itOrd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowOrdena w = new WindowOrdena(mainFrame);

                if (!s.empty()) w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else w.setDefault("A1");

                w.setEntradesColumna(s.col, fullTables.get(getFocusedFull()).getColumnCount());

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    String criteri = w.getCriteri().toUpperCase();
                    int colOrd = w.getColOrd();

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.ordenaBloc(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue(), criteri, colOrd - origen.getValue());
                }
            }
        });

        itTransp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SeleccioTaula s = getCurrentSelection();
                WindowSecundaria w = new WindowSecundaria(mainFrame, "Transposa bloc");

                if (!s.empty()) w.setDefault(Utilitats.convertirATextCela(s.fila, s.col));
                else w.setDefault("A1");

                if (w.mostra()) {
                    Pair<Integer, Integer> origen = Utilitats.convertirAIndexs(w.getOrigen());
                    Pair<Integer, Integer> desti = Utilitats.convertirAIndexs(w.getDesti());

                    if (origen == null) {
                        errorMessage("Error en la cel·la d'origen " + w.getOrigen());
                        return;
                    }
                    if (desti == null) {
                        errorMessage("Error en la cel·la de destí " + w.getDesti());
                        return;
                    }

                    controladorVista.transposaBloc(getFocusedFull(), origen.getKey(),
                            origen.getValue(), s.nfiles, s.ncols,
                            desti.getKey(), desti.getValue());
                }
            }
        });

        menuItemDocu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO: Canviar el fixter pel manual
                final String docFile = "../DOCS/index.html";
                try {
                    Desktop.getDesktop().open(new File(docFile));
                } catch (NullPointerException | IOException | IllegalArgumentException ignored) {
                    errorMessage("Error obrint l'arxiu de documentació " + docFile +
                            "\nPotser no s'està executant el programa amb l'script run.sh");
                }
            }
        });

        menuItemSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final String about = "Software desenvolupat per:" + "\nJofre Costa" +
                        "\nMariona Jaramillo" + "\nFrancesc Pifarré" + "\nRubén Aciego";
                missatge("Sobre Excellent", about);
            }
        });
    }

    private void habilitaControls() {
        if (documentObert) {
            afegirFullButton.setEnabled(true);
            elimFullButton.setEnabled(true);
            menuItemReanomena.setEnabled(true);
            menuItemTancar.setEnabled(true);
            menuItemDesar.setEnabled(true);
            menuItemAfegirFull.setEnabled(true);

            if (!fullTables.isEmpty()) {
                absButton.setEnabled(true);
                expButton.setEnabled(true);
                incrButton.setEnabled(true);
                decrButton.setEnabled(true);
                truncarButton.setEnabled(true);
                cosButton.setEnabled(true);
                sinButton.setEnabled(true);
                coshButton.setEnabled(true);
                sinhButton.setEnabled(true);
                tanhButton.setEnabled(true);
                covarianciaButton.setEnabled(true);
                desvEstButton.setEnabled(true);
                coefCorrButton.setEnabled(true);
                mitjanaButton.setEnabled(true);
                varianciaButton.setEnabled(true);
                medianaButton.setEnabled(true);
                reemplacaButton.setEnabled(true);
                longButton.setEnabled(true);
                minuscButton.setEnabled(true);
                majButton.setEnabled(true);
                diaButton.setEnabled(true);
                mesButton.setEnabled(true);
                anyButton.setEnabled(true);
                diaSetmButton.setEnabled(true);
                horoscopButton.setEnabled(true);
                cercaButton.setEnabled(true);
                convertirButton.setEnabled(true);
                menuItemSelectAll.setEnabled(true);
                menuItemSelectFila.setEnabled(true);
                menuItemSelectCol.setEnabled(true);
                menuItemAfegirFila.setEnabled(true);
                menuItemAfegirCol.setEnabled(true);
                menuItemElimFila.setEnabled(true);
                menuItemElimCol.setEnabled(true);
                menuItemCopiarBloc.setEnabled(true);
                menuItemMoureBloc.setEnabled(true);
                menuItemBuidarBloc.setEnabled(true);
                menuItemOrdenarBloc.setEnabled(true);
                menuItemTransposarBloc.setEnabled(true);
                entradaInput.setEnabled(true);
            }
        }
    }

    private void deshabilitaControls() {
        absButton.setEnabled(false);
        expButton.setEnabled(false);
        incrButton.setEnabled(false);
        decrButton.setEnabled(false);
        truncarButton.setEnabled(false);
        cosButton.setEnabled(false);
        sinButton.setEnabled(false);
        coshButton.setEnabled(false);
        sinhButton.setEnabled(false);
        tanhButton.setEnabled(false);
        covarianciaButton.setEnabled(false);
        desvEstButton.setEnabled(false);
        coefCorrButton.setEnabled(false);
        mitjanaButton.setEnabled(false);
        varianciaButton.setEnabled(false);
        medianaButton.setEnabled(false);
        reemplacaButton.setEnabled(false);
        longButton.setEnabled(false);
        minuscButton.setEnabled(false);
        majButton.setEnabled(false);
        diaButton.setEnabled(false);
        mesButton.setEnabled(false);
        anyButton.setEnabled(false);
        diaSetmButton.setEnabled(false);
        horoscopButton.setEnabled(false);
        cercaButton.setEnabled(false);
        convertirButton.setEnabled(false);
        menuItemSelectAll.setEnabled(false);
        menuItemSelectFila.setEnabled(false);
        menuItemSelectCol.setEnabled(false);
        menuItemAfegirFila.setEnabled(false);
        menuItemAfegirCol.setEnabled(false);
        menuItemElimFila.setEnabled(false);
        menuItemElimCol.setEnabled(false);
        menuItemCopiarBloc.setEnabled(false);
        menuItemMoureBloc.setEnabled(false);
        menuItemBuidarBloc.setEnabled(false);
        menuItemOrdenarBloc.setEnabled(false);
        menuItemTransposarBloc.setEnabled(false);
        entradaInput.setEnabled(false);

        if (!documentObert) {
            afegirFullButton.setEnabled(false);
            elimFullButton.setEnabled(false);
            menuItemReanomena.setEnabled(false);
            menuItemTancar.setEnabled(false);
            menuItemDesar.setEnabled(false);
            menuItemAfegirFull.setEnabled(false);
        }
    }

    private void inicialitzar_menuContextual() {
        menuClicDret = new JPopupMenu();
        menuClicDret.add(itAfFila);
        menuClicDret.add(itAfCol);
        menuClicDret.add(itElFila);
        menuClicDret.add(itElCol);
        menuClicDret.addSeparator();
        menuClicDret.add(itSelFila);
        menuClicDret.add(itSelCol);
        menuClicDret.addSeparator();
        menuClicDret.add(itBuida);
        menuClicDret.add(itCopia);
        menuClicDret.add(itMou);
        menuClicDret.add(itOrd);
        menuClicDret.add(itTransp);
    }

    private void inicialitzar_menuBar() {
        //Fitxer
        menuFile.add(menuItemCrear);
        menuFile.add(menuItemCarregar);
        menuFile.add(menuItemReanomena);
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
        menuFull.add(menuItemAfegirFull);

        //Ajuda
        menuAjuda.add(menuItemDocu);
        menuAjuda.add(menuItemSobre);

        //Barra Menu
        menuBarVista.add(menuFile);
        menuBarVista.add(menuEditar);
        menuBarVista.add(menuFull);
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
        final JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);
        table.setAutoResizeMode(0);
        table.setColumnSelectionAllowed(true);
        table.setDropMode(DropMode.USE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);

        TableCellListener listener = new TableCellListener(table, new Action() {
            @Override
            public Object getValue(String s) {
                return null;
            }

            @Override
            public void putValue(String s, Object o) {
            }

            @Override
            public void setEnabled(boolean b) {
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
            }

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TableCellListener l = (TableCellListener) actionEvent.getSource();
                controladorVista.modificaCela((String) l.getNewValue(),
                        getFocusedFull(), l.getRow(), l.getColumn());
            }
        });

        RowNumberTable rowNumberTable = new RowNumberTable(table);
        scrollPane.setRowHeaderView(rowNumberTable);

        table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent ME) {
                if (SwingUtilities.isRightMouseButton(ME) && ME.getClickCount() == 1) {
                    menuClicDret.show(ME.getComponent(), ME.getX(), ME.getY());
                }
            }
        });

        fullTables.add(model);
        setFocusedFull(fullTables.size() - 1);
    }

    public void esborraFulls() {
        tabFulls.removeAll();
        fullTables.clear();
    }

    public void esborraFull(int index) {
        if (index >= 0 && index < fullTables.size()) {
            tabFulls.remove(index);
            fullTables.remove(index);

            for (int i = index; i < fullTables.size(); ++i)
                tabFulls.setTitleAt(i, "Full " + (i + 1));
        }
    }

    public int getFocusedFull() {
        return tabFulls.getSelectedIndex();
    }

    public void setDocument(String titol) {
        mainFrame.setTitle("Excellent - " + titol);
        documentObert = true;
    }

    public void tancaDocument() {
        mainFrame.setTitle("Excellent");
        documentObert = false;
    }

    public SeleccioTaula getCurrentSelection() {
        JPanel panel = (JPanel) tabFulls.getSelectedComponent();
        JTable table = (JTable) (((JScrollPane) panel.getComponent(0)).getViewport().getView());

        return new SeleccioTaula(table.convertRowIndexToModel(table.getSelectedRow()),
                table.convertColumnIndexToModel(table.getSelectedColumn()),
                table.getSelectedRowCount(), table.getSelectedColumnCount());
    }

    public ArrayList<EntradaTaula> getCurrentEntradesFull(SeleccioTaula seleccio) {
        ArrayList<EntradaTaula> entrades = new ArrayList<EntradaTaula>();
        return entrades;
    }

    public void buidaSeleccio(int full, SeleccioTaula seleccio) {
        TableModel model = fullTables.get(full);

        for (int i = 0; i < seleccio.nfiles; ++i)
            for (int j = 0; j < seleccio.ncols; ++j)
                model.setValueAt("", i + seleccio.fila, j + seleccio.col);
    }

    public void setEntradesFull(int full, ArrayList<EntradaTaula> entrades) {
        if (full >= 0 && full < fullTables.size()) {
            TableModel model = fullTables.get(full);

            for (EntradaTaula e : entrades)
                model.setValueAt(e.valor, e.fila, e.columna);
        }
    }

    public void setFocusedFull(int full) {
        if (full >= 0 && full < fullTables.size())
            tabFulls.setSelectedIndex(full);
    }

    public void afegeixFila(int full) {
        DefaultTableModel m = fullTables.get(full);
        m.setRowCount(m.getRowCount() + 1);
    }

    public void afegeixColumna(int full) {
        DefaultTableModel m = fullTables.get(full);
        m.setColumnCount(m.getColumnCount() + 1);
    }

    public void eliminaFila(int full) {
        DefaultTableModel m = fullTables.get(full);
        m.setRowCount(m.getRowCount() - 1);
    }

    public void eliminaColumna(int full) {
        DefaultTableModel m = fullTables.get(full);
        m.setColumnCount(m.getColumnCount() - 1);
    }

    public void missatge(String titol, String msg) {
        JOptionPane.showMessageDialog(mainFrame, msg, titol, JOptionPane.PLAIN_MESSAGE);
    }

    public void errorMessage(String error) {
        JOptionPane.showMessageDialog(mainFrame, error, "Error!", JOptionPane.ERROR_MESSAGE);
    }

    private boolean algunFull() {
        return fullTables.size() > 0;
    }

    private void configuraUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        opPanel = new JPanel();
        opPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(opPanel, gbc);
        opAritPanel = new JPanel();
        opAritPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        opPanel.add(opAritPanel, gbc);
        opAritPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));

        // Panell operacions aritmetiques
        opAritButtPanel = new JPanel();
        opAritButtPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        opAritPanel.add(opAritButtPanel, gbc);

        cosButton = new JButton();
        cosButton.setText("Cos");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(cosButton, gbc);

        sinButton = new JButton();
        sinButton.setText("Sin");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(sinButton, gbc);

        coshButton = new JButton();
        coshButton.setText("Cosh");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(coshButton, gbc);

        sinhButton = new JButton();
        sinhButton.setText("Sinh");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(sinhButton, gbc);

        tanhButton = new JButton();
        tanhButton.setText("Tanh");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(tanhButton, gbc);

        absButton = new JButton();
        absButton.setText("Abs");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(absButton, gbc);

        decrButton = new JButton();
        decrButton.setText("Decr");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(decrButton, gbc);

        expButton = new JButton();
        expButton.setText("Exp");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(expButton, gbc);

        incrButton = new JButton();
        incrButton.setText("Incr");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(incrButton, gbc);

        truncarButton = new JButton();
        truncarButton.setText("Truncar");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritButtPanel.add(truncarButton, gbc);

        operacionsAritmetiquesLabel = new JLabel();
        operacionsAritmetiquesLabel.setText("Operacions aritmètiques");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        opAritPanel.add(operacionsAritmetiquesLabel, gbc);

        // Panell operacions estadistiques
        opEstPanel = new JPanel();
        opEstPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        opPanel.add(opEstPanel, gbc);
        opEstPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));

        opEstButtPanel = new JPanel();
        opEstButtPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        opEstPanel.add(opEstButtPanel, gbc);

        covarianciaButton = new JButton();
        covarianciaButton.setText("Covariància");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opEstButtPanel.add(covarianciaButton, gbc);

        desvEstButton = new JButton();
        desvEstButton.setText("DesvEst");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opEstButtPanel.add(desvEstButton, gbc);

        coefCorrButton = new JButton();
        coefCorrButton.setText("CoefCorr");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opEstButtPanel.add(coefCorrButton, gbc);

        mitjanaButton = new JButton();
        mitjanaButton.setText("Mitjana");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opEstButtPanel.add(mitjanaButton, gbc);

        varianciaButton = new JButton();
        varianciaButton.setText("Variància");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opEstButtPanel.add(varianciaButton, gbc);

        medianaButton = new JButton();
        medianaButton.setText("Mediana");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opEstButtPanel.add(medianaButton, gbc);

        operacionsEstadistiquesLabel = new JLabel();
        operacionsEstadistiquesLabel.setText("Operacions estadístiques");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        opEstPanel.add(operacionsEstadistiquesLabel, gbc);

        opTextPanel = new JPanel();
        opTextPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        opPanel.add(opTextPanel, gbc);
        opTextPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));

        opTextButtPanel = new JPanel();
        opTextButtPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        opTextPanel.add(opTextButtPanel, gbc);

        reemplacaButton = new JButton();
        reemplacaButton.setText("Reemplaça");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opTextButtPanel.add(reemplacaButton, gbc);

        longButton = new JButton();
        longButton.setText("Long");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opTextButtPanel.add(longButton, gbc);

        minuscButton = new JButton();
        minuscButton.setText("Minúsc");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opTextButtPanel.add(minuscButton, gbc);

        majButton = new JButton();
        majButton.setText("Majúsc");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opTextButtPanel.add(majButton, gbc);

        cercaButton = new JButton();
        cercaButton.setText("Cerca");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opTextButtPanel.add(cercaButton, gbc);

        operacionsTextualsLabel = new JLabel();
        operacionsTextualsLabel.setText("Operacions textuals");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        opTextPanel.add(operacionsTextualsLabel, gbc);

        opDataPanel = new JPanel();
        opDataPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        opPanel.add(opDataPanel, gbc);
        opDataPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));

        opDataButtPanel = new JPanel();
        opDataButtPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        opDataPanel.add(opDataButtPanel, gbc);

        diaSetmButton = new JButton();
        diaSetmButton.setText("DiaSetm");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opDataButtPanel.add(diaSetmButton, gbc);

        horoscopButton = new JButton();
        horoscopButton.setText("Horòscop");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opDataButtPanel.add(horoscopButton, gbc);

        diaButton = new JButton();
        diaButton.setText("Dia");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opDataButtPanel.add(diaButton, gbc);

        anyButton = new JButton();
        anyButton.setText("Any");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opDataButtPanel.add(anyButton, gbc);

        mesButton = new JButton();
        mesButton.setText("Mes");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opDataButtPanel.add(mesButton, gbc);

        operacionsDeDatesLabel = new JLabel();
        operacionsDeDatesLabel.setText("Operacions de dates");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        opDataPanel.add(operacionsDeDatesLabel, gbc);

        opConvPanel = new JPanel();
        opConvPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        opPanel.add(opConvPanel, gbc);
        opConvPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-4473925)));

        opConvButtPanel = new JPanel();
        opConvButtPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        opConvPanel.add(opConvButtPanel, gbc);
        comboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("km -> mi");
        defaultComboBoxModel1.addElement("mi -> km");
        defaultComboBoxModel1.addElement("km -> nmi");
        defaultComboBoxModel1.addElement("nmi -> km");
        defaultComboBoxModel1.addElement("m -> yd");
        defaultComboBoxModel1.addElement("yd -> m");
        defaultComboBoxModel1.addElement("km2 -> hac");
        defaultComboBoxModel1.addElement("hac -> km2");
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
        defaultComboBoxModel1.addElement("ºF -> ºK");
        defaultComboBoxModel1.addElement("ºK -> ºF");
        comboBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opConvButtPanel.add(comboBox, gbc);

        convertirButton = new JButton();
        convertirButton.setText("Convertir");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        opConvButtPanel.add(convertirButton, gbc);

        convUniComboBox = new JLabel();
        convUniComboBox.setText("Conversió d'unitats");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        opConvPanel.add(convUniComboBox, gbc);

        spacer = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        opPanel.add(spacer, gbc);

        tabFulls = new JTabbedPane();
        tabFulls.setTabPlacement(3);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipady = 500;
        mainPanel.add(tabFulls, gbc);

        buttFullsPanel = new JPanel();
        buttFullsPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(buttFullsPanel, gbc);

        afegirFullButton = new JButton();
        afegirFullButton.setText("+");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        buttFullsPanel.add(afegirFullButton, gbc);
        elimFullButton = new JButton();
        elimFullButton.setText("-");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        buttFullsPanel.add(elimFullButton, gbc);

        barraInput = new JPanel();
        barraInput.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(barraInput, gbc);
        barraInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        entradaInput = new JTextField();
        entradaInput.setMinimumSize(new Dimension(1699, 130));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 2.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        barraInput.add(entradaInput, gbc);
        inputLabel = new JLabel();
        inputLabel.setText("Input: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        barraInput.add(inputLabel, gbc);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
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
        operacionsAritmetiquesLabel = new JLabel();
        operacionsAritmetiquesLabel.setText("Operacions aritmètiques");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel2.add(operacionsAritmetiquesLabel, gbc);
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
        covarianciaButton.setText("Covariància");
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
        varianciaButton.setText("Variància");
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
        operacionsEstadistiquesLabel = new JLabel();
        operacionsEstadistiquesLabel.setText("Operacions estadístiques");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel4.add(operacionsEstadistiquesLabel, gbc);
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
        reemplacaButton.setText("Reemplaça");
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
        minuscButton.setText("Minúsc");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel7.add(minuscButton, gbc);
        majButton = new JButton();
        majButton.setText("Majúsc");
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
        operacionsTextualsLabel = new JLabel();
        operacionsTextualsLabel.setText("Operacions textuals");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel6.add(operacionsTextualsLabel, gbc);
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
        horoscopButton.setText("Horòscop");
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
        operacionsDeDatesLabel = new JLabel();
        operacionsDeDatesLabel.setText("Operacions de dates");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel8.add(operacionsDeDatesLabel, gbc);
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
        comboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("km -> mi");
        defaultComboBoxModel1.addElement("mi -> km");
        defaultComboBoxModel1.addElement("km -> nmi");
        defaultComboBoxModel1.addElement("nmi -> km");
        defaultComboBoxModel1.addElement("m -> yd");
        defaultComboBoxModel1.addElement("yd -> m");
        defaultComboBoxModel1.addElement("km2 -> hac");
        defaultComboBoxModel1.addElement("hac -> km2");
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
        defaultComboBoxModel1.addElement("ºF -> ºK");
        defaultComboBoxModel1.addElement("ºK -> ºF");
        comboBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel11.add(comboBox, gbc);
        convertirButton = new JButton();
        convertirButton.setText("Convertir");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel11.add(convertirButton, gbc);
        convUniComboBox = new JLabel();
        convUniComboBox.setText("Conversió d'unitats");
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
        barraInput = new JPanel();
        barraInput.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(barraInput, gbc);
        barraInput.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        entradaInput = new JTextField();
        entradaInput.setMinimumSize(new Dimension(1699, 130));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 2.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        barraInput.add(entradaInput, gbc);
        inputLabel = new JLabel();
        inputLabel.setText("Input: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        barraInput.add(inputLabel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
