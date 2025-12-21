package ui;

import dao.PlayStationDAO;
import helper.IdGenerator;
import model.PlayStation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PlayStationPanel extends JPanel {

    private final PlayStationDAO playStationDAO;

    private final DefaultTableModel model;
    private final JTable table;

    private final JTextField tfId = new JTextField();
    private final JComboBox<String> cbType = new JComboBox<>(new String[]{"PS2", "PS3", "PS4", "PS5"});
    private final JComboBox<String> cbStatus = new JComboBox<>(new String[]{"available", "rented"});

    public PlayStationPanel(PlayStationDAO playStationDAO) {
        this.playStationDAO = playStationDAO;

        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 16, 16, 16));

        model = new DefaultTableModel(new Object[]{"ID", "Type", "Status"}, 0) {
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

        JPanel grid = new JPanel(new GridLayout(1, 3, 10, 8));
        tfId.setEditable(false);

        grid.add(labeled("ID (auto)", tfId));
        grid.add(labeled("Type", cbType));
        grid.add(labeled("Status", cbStatus));

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
            List<PlayStation> list = playStationDAO.findAll();
            for (PlayStation ps : list) {
                model.addRow(new Object[]{ps.getId(), ps.getType(), ps.getStatus()});
            }
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void fillFormFromSelected() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        tfId.setText(String.valueOf(model.getValueAt(row, 0)));
        cbType.setSelectedItem(String.valueOf(model.getValueAt(row, 1)));
        cbStatus.setSelectedItem(String.valueOf(model.getValueAt(row, 2)));
    }

    private void clearForm() {
        tfId.setText("");
        cbType.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        table.clearSelection();
    }

    private void onAdd() {
        try {
            String id = IdGenerator.newId("PS");
            String type = String.valueOf(cbType.getSelectedItem());
            String status = "available";

            PlayStation psObj = new PlayStation(id, type, status);
            playStationDAO.create(psObj);

            UIUtil.showInfo(this, "PlayStation berhasil ditambahkan: " + id);
            clearForm();
            refresh();
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void onUpdate() {
        try {
            String id = tfId.getText().trim();
            if (id.isEmpty()) throw new IllegalArgumentException("Pilih PlayStation di tabel dulu.");

            PlayStation psObj = playStationDAO.findById(id);
            if (psObj == null) throw new IllegalArgumentException("PlayStation tidak ditemukan.");

            psObj.setType(String.valueOf(cbType.getSelectedItem()));
            psObj.setStatus(String.valueOf(cbStatus.getSelectedItem()));

            playStationDAO.update(psObj);
            UIUtil.showInfo(this, "PlayStation berhasil diupdate.");
            refresh();
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }

    private void onDelete() {
        try {
            String id = tfId.getText().trim();
            if (id.isEmpty()) throw new IllegalArgumentException("Pilih PlayStation di tabel dulu.");

            if (UIUtil.confirm(this, "Hapus PlayStation " + id + "?") != JOptionPane.YES_OPTION) return;

            playStationDAO.delete(id);
            UIUtil.showInfo(this, "PlayStation berhasil dihapus.");
            clearForm();
            refresh();
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }
}
