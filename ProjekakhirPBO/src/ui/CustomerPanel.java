package ui;

import factory.CustomerFactory;
import model.Customer;
import service.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerPanel extends JPanel {

    private final CustomerService customerService;

    private final DefaultTableModel model;
    private final JTable table;

    private final JTextField tfId = new JTextField();
    private final JTextField tfName = new JTextField();
    private final JTextField tfPhone = new JTextField();
    private final JTextField tfAddress = new JTextField();

    public CustomerPanel(CustomerService customerService) {
        this.customerService = customerService;

        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 16, 16, 16));

        model = new DefaultTableModel(new Object[]{"ID", "Name", "Phone", "Address"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(26);
        table.getSelectionModel().addListSelectionListener(e -> fillFormFromSelected());

        add(makeForm(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refresh();
    }

    private JPanel makeForm() {
        JPanel form = new JPanel(new BorderLayout(10, 10));

        // kiri: input
        JPanel grid = new JPanel(new GridLayout(2, 4, 10, 8));
        tfId.setEditable(false);

        grid.add(labeled("ID (auto)", tfId));
        grid.add(labeled("Name", tfName));
        grid.add(labeled("Phone", tfPhone));
        grid.add(labeled("Address", tfAddress));

        // kanan: tombol
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton btnAdd = UIUtil.primaryButton("Add");
        JButton btnUpdate = UIUtil.normalButton("Update");
        JButton btnDelete = UIUtil.normalButton("Delete");
        JButton btnClear = UIUtil.normalButton("Clear");
        JButton btnRefresh = UIUtil.normalButton("Refresh");

        btnAdd.addActionListener(e -> onAdd());
        btnUpdate.addActionListener(e -> onUpdate());
        btnDelete.addActionListener(e -> onDelete());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> refresh());

        actions.add(btnAdd);
        actions.add(btnUpdate);
        actions.add(btnDelete);
        actions.add(btnClear);
        actions.add(btnRefresh);

        form.add(grid, BorderLayout.CENTER);
        form.add(actions, BorderLayout.SOUTH);

        return form;
    }

    private JPanel labeled(String label, JComponent field) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        JLabel l = new JLabel(label);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 12f));
        p.add(l, BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        return p;
    }

    private void refresh() {
        try {
            model.setRowCount(0);
            List<Customer> list = customerService.getAll();
            for (Customer c : list) {
                model.addRow(new Object[]{c.getId(), c.getName(), c.getPhone(), c.getAddress()});
            }
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void fillFormFromSelected() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        tfId.setText(String.valueOf(model.getValueAt(row, 0)));
        tfName.setText(String.valueOf(model.getValueAt(row, 1)));
        tfPhone.setText(String.valueOf(model.getValueAt(row, 2)));
        tfAddress.setText(String.valueOf(model.getValueAt(row, 3)));
    }

    private void clearForm() {
        tfId.setText("");
        tfName.setText("");
        tfPhone.setText("");
        tfAddress.setText("");
        table.clearSelection();
    }

    private void onAdd() {
        try {
            Customer c = CustomerFactory.create(tfName.getText(), tfPhone.getText(), tfAddress.getText());
            customerService.addCustomer(c);
            UIUtil.showInfo(this, "Customer berhasil ditambahkan: " + c.getId());
            clearForm();
            refresh();
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void onUpdate() {
        try {
            String id = tfId.getText().trim();
            if (id.isEmpty()) throw new IllegalArgumentException("Pilih customer di tabel dulu.");

            Customer existing = customerService.getById(id);
            if (existing == null) throw new IllegalArgumentException("Customer tidak ditemukan.");

            existing.setName(tfName.getText().trim());
            existing.setPhone(tfPhone.getText().trim());
            existing.setAddress(tfAddress.getText().trim());

            customerService.update(existing);
            UIUtil.showInfo(this, "Customer berhasil diupdate.");
            refresh();
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void onDelete() {
        try {
            String id = tfId.getText().trim();
            if (id.isEmpty()) throw new IllegalArgumentException("Pilih customer di tabel dulu.");

            if (UIUtil.confirm(this, "Hapus customer " + id + "?") != JOptionPane.YES_OPTION) return;

            customerService.delete(id);
            UIUtil.showInfo(this, "Customer berhasil dihapus.");
            clearForm();
            refresh();
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }
}
