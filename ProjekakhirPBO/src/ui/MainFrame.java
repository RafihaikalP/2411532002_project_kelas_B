package ui;

import dao.CustomerDAO;
import dao.PlayStationDAO;
import dao.RentalTransactionDAO;
import service.CustomerService;
import service.PaymentService;
import service.RentalService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final CustomerService customerService;
    private final PlayStationDAO playStationDAO;
    private final RentalTransactionDAO rentalTransactionDAO;
    private final RentalService rentalService;

    public MainFrame() {
        // service & DAO (reuse kode yang sudah ada)
        CustomerDAO customerDAO = new CustomerDAO();
        playStationDAO = new PlayStationDAO();
        rentalTransactionDAO = new RentalTransactionDAO();

        customerService = new CustomerService(customerDAO);
        rentalService = new RentalService(rentalTransactionDAO, playStationDAO, new PaymentService());

        setTitle("PlayStation Rental Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 650);
        setLocationRelativeTo(null);

        setUIStyle();

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Customers", new CustomerPanel(customerService));
        tabs.addTab("PlayStations", new PlayStationPanel(playStationDAO));
        tabs.addTab("Rentals", new RentalPanel(customerService, playStationDAO, rentalService));
        tabs.addTab("Transactions", new TransactionPanel(rentalTransactionDAO));

        setLayout(new BorderLayout());
        add(makeHeader(), BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
    }

    private JPanel makeHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JLabel title = new JLabel("PlayStation Rental Manager");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));

        JLabel subtitle = new JLabel("CRUD Customer & PS • Sewa & Pengembalian • Riwayat Transaksi");
        subtitle.setFont(subtitle.getFont().deriveFont(13f));

        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.setOpaque(false);
        text.add(title);
        text.add(Box.createVerticalStrut(2));
        text.add(subtitle);

        header.add(text, BorderLayout.WEST);
        return header;
    }

    private void setUIStyle() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("TabbedPane.focus", new Color(0,0,0,0));
        } catch (Exception ignored) {}
    }
}

