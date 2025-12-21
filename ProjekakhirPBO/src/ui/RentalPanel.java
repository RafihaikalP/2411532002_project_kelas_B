package ui;

import dao.PlayStationDAO;
import model.Customer;
import model.PlayStation;
import model.RentalTransaction;
import service.CustomerService;
import service.RentalService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RentalPanel extends JPanel {

    private final CustomerService customerService;
    private final PlayStationDAO playStationDAO;
    private final RentalService rentalService;

    private final JComboBox<String> cbCustomer = new JComboBox<>();
    private final JComboBox<String> cbPlayStation = new JComboBox<>();
    private final JTextField tfTransactionId = new JTextField();

    private final DefaultTableModel psModel;
    private final JTable psTable;

    public RentalPanel(CustomerService customerService, PlayStationDAO playStationDAO, RentalService rentalService) {
        this.customerService = customerService;
        this.playStationDAO = playStationDAO;
        this.rentalService = rentalService;

        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 16, 16, 16));

        psModel = new DefaultTableModel(new Object[]{"ID", "Type", "Status"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        psTable = new JTable(psModel);
        psTable.setRowHeight(26);

        add(makeTopActions(), BorderLayout.NORTH);
        add(new JScrollPane(psTable), BorderLayout.CENTER);

        refreshAll();
    }

    private JPanel makeTopActions() {
        JPanel wrapper = new JPanel(new GridLayout(2, 1, 10, 10));

        // bar sewa
        JPanel rentBar = new JPanel(new GridLayout(1, 4, 10, 8));
        JButton btnRent = UIUtil.primaryButton("Sewa");
        JButton btnRefresh = UIUtil.normalButton("Refresh");

        rentBar.add(labeled("Customer", cbCustomer));
        rentBar.add(labeled("PlayStation (available)", cbPlayStation));
        rentBar.add(btnRent);
        rentBar.add(btnRefresh);

        btnRent.addActionListener(e -> onRent());
        btnRefresh.addActionListener(e -> refreshAll());

        // bar kembali
        JPanel returnBar = new JPanel(new GridLayout(1, 4, 10, 8));
        JButton btnReturn = UIUtil.primaryButton("Kembalikan");
        tfTransactionId.setToolTipText("Masukkan ID transaksi (TRX-XXXXXXX)");

        returnBar.add(labeled("Transaction ID", tfTransactionId));
        returnBar.add(new JLabel()); // spacer
        returnBar.add(btnReturn);
        returnBar.add(new JLabel()); // spacer

        btnReturn.addActionListener(e -> onReturn());

        wrapper.add(rentBar);
        wrapper.add(returnBar);
        return wrapper;
    }

    private JPanel labeled(String label, JComponent field) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        JLabel l = new JLabel(label);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 12f));
        p.add(l, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        return p;
    }

    private void refreshAll() {
        refreshCustomers();
        refreshAvailablePS();
        refreshPSTable();
    }

    private void refreshCustomers() {
        try {
            cbCustomer.removeAllItems();
            List<Customer> customers = customerService.getAll();
            for (Customer c : customers) {
                cbCustomer.addItem(c.getId() + " | " + c.getName());
            }
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void refreshAvailablePS() {
        try {
            cbPlayStation.removeAllItems();
            List<PlayStation> list = playStationDAO.findAvailable();
            for (PlayStation ps : list) {
                cbPlayStation.addItem(ps.getId() + " | " + ps.getType());
            }
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void refreshPSTable() {
        try {
            psModel.setRowCount(0);
            List<PlayStation> list = playStationDAO.findAll();
            for (PlayStation ps : list) {
                psModel.addRow(new Object[]{ps.getId(), ps.getType(), ps.getStatus()});
            }
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void onRent() {
        try {
            if (cbCustomer.getSelectedItem() == null) throw new IllegalArgumentException("Customer masih kosong.");
            if (cbPlayStation.getSelectedItem() == null) throw new IllegalArgumentException("PS available masih kosong.");

            String custId = cbCustomer.getSelectedItem().toString().split("\\|")[0].trim();
            String psId = cbPlayStation.getSelectedItem().toString().split("\\|")[0].trim();

            Customer c = customerService.getById(custId);
            PlayStation ps = playStationDAO.findById(psId);

            RentalTransaction t = rentalService.rent(c, ps);
            UIUtil.showInfo(this, "Sewa berhasil! ID Transaksi: " + t.getId());

            refreshAll();
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void onReturn() {
        try {
            String trxId = tfTransactionId.getText().trim();
            if (trxId.isEmpty()) throw new IllegalArgumentException("Transaction ID wajib diisi.");

            RentalTransaction closed = rentalService.returnPlayStation(trxId);
            UIUtil.showInfo(this, "Pengembalian berhasil.\nBiaya: " + closed.getCost());

            tfTransactionId.setText("");
            refreshAll();
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }
}
