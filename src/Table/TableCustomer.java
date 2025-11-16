package Table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Model.Customer;

public class TableCustomer extends AbstractTableModel  {
    List<Customer> ls;
    private String[] columnNames = {"ID", "Nama", "Email", "Alamat", "HP"};
    
    public TableCustomer(List<Customer> ls) {
        this.ls = ls;
    }

    @Override
    public int getRowCount() {
        return ls.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ls.get(rowIndex).getId();
            case 1:
                return ls.get(rowIndex).getNama();
            case 2:
                return ls.get(rowIndex).getemail();
            case 3:
                return ls.get(rowIndex).getalamat();
            case 4:
                return ls.get(rowIndex).gethp();
            default:
                return null;
        }
    }
}

