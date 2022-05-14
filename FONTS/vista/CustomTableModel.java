package vista;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {

    @Override
    public int getColumnCount() {
        return super.getColumnCount() + 1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) return row;
        return super.getValueAt(row, col - 1);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 0) return false;
        return super.isCellEditable(row, col - 1);
    }
}
