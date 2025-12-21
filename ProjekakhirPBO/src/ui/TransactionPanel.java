package ui;

import dao.RentalTransactionDAO;
import model.RentalTransaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionPanel extends JPanel {

    private final RentalTransactionDAO rentalTransactionDAO;

    private final DefaultTableModel model;
    private final JTable table;

    public TransactionPanel(RentalTransactionDAO rentalTransactionDAO) {
        this.rentalTransactionDAO = rentalTransactionDAO;

        setLayout(new BorderLayout(12, 12));
        setBorder(BorderFactory.createEmptyBorder(12, 16, 16, 16));

        model = new DefaultTableModel(new Object[]{
                "ID", "Customer", "PS", "Start", "End", "Cost"
        }, 0) {
            @Override 
        public boolean isCellEditable(int r, int c) { return false; }
        };

        table = new JTable(model);
        table.setRowHeight(26);

        JButton btnRefresh = UIUtil.normalButton("Refresh");
        btnRefresh.addActionListener(e -> refresh());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        top.add(btnRefresh);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refresh();
    }

    private void refresh() {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            model.setRowCount(0);

            List<RentalTransaction> list = rentalTransactionDAO.findAll();
            for (RentalTransaction t : list) {
                String start = t.getStartTime() == null ? "" : t.getStartTime().format(fmt);
                String end = t.getEndTime() == null ? "" : t.getEndTime().format(fmt);

                model.addRow(new Object[]{
                        t.getId(),
                        t.getCustomer().getName(),
                        t.getPlayStation().getType(),
                        start,
                        end,
                        t.getCost()
                });
            }
        } catch (Exception e) {
            UIUtil.showError(this, e);
        }
    }
}
