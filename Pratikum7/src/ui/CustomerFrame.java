package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import DAO.CustomerRepo; 
import Model.Customer;
import Model.CustomerBuilder;
import Table.TableCustomer; 

public class CustomerFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtCustomerName;
    private JTextField txtCustomerAlamat;
    private JTextField txtCostumerPhone;
    private JTextField txtCustomerEmail;
    private JButton btnSimpan, btnBatal;
    private JTable tableCustomers;
    private TableCustomer tableModel; 

    private CustomerRepo repo = new CustomerRepo();
    private List<Customer> listCustomer;
    private String id = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerFrame frame = new CustomerFrame();
                frame.setVisible(true);
                frame.loadTable(); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CustomerFrame() {
        setTitle("DATA PELANGGAN"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 550); 

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BorderLayout(0, 15));
        setContentPane(contentPane);

        contentPane.add(createFormPanel(), BorderLayout.NORTH);
        contentPane.add(createTablePanel(), BorderLayout.CENTER);
        
        setLocationRelativeTo(null); 
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setBorder(new LineBorder(Color.GRAY, 1, true));
        formPanel.setBackground(Color.WHITE);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{90, 280, 0};
        gbl.rowHeights = new int[]{30, 30, 30, 30, 45, 0};
        gbl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        formPanel.setLayout(gbl);


        JLabel lblNama = new JLabel("Nama"); 
        GridBagConstraints gbc_lblNama = new GridBagConstraints();
        gbc_lblNama.anchor = GridBagConstraints.WEST;
        gbc_lblNama.insets = new Insets(15, 20, 8, 15);
        gbc_lblNama.gridx = 0;
        gbc_lblNama.gridy = 0;
        formPanel.add(lblNama, gbc_lblNama);

        txtCustomerName = new JTextField(); 
        GridBagConstraints gbc_txtNama = new GridBagConstraints();
        gbc_txtNama.insets = new Insets(15, 0, 8, 20);
        gbc_txtNama.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNama.gridx = 1;
        gbc_txtNama.gridy = 0;
        formPanel.add(txtCustomerName, gbc_txtNama);

        JLabel lblAlamat = new JLabel("Alamat"); 
        GridBagConstraints gbc_lblAlamat = new GridBagConstraints();
        gbc_lblAlamat.anchor = GridBagConstraints.WEST;
        gbc_lblAlamat.insets = new Insets(0, 20, 8, 15);
        gbc_lblAlamat.gridx = 0;
        gbc_lblAlamat.gridy = 1;
        formPanel.add(lblAlamat, gbc_lblAlamat);

        txtCustomerAlamat = new JTextField(); 
        GridBagConstraints gbc_txtAlamat = new GridBagConstraints();
        gbc_txtAlamat.insets = new Insets(0, 0, 8, 20);
        gbc_txtAlamat.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtAlamat.gridx = 1;
        gbc_txtAlamat.gridy = 1;
        formPanel.add(txtCustomerAlamat, gbc_txtAlamat);

        JLabel lblHp = new JLabel("No HP"); 
        GridBagConstraints gbc_lblHp = new GridBagConstraints();
        gbc_lblHp.anchor = GridBagConstraints.WEST;
        gbc_lblHp.insets = new Insets(0, 20, 8, 15);
        gbc_lblHp.gridx = 0;
        gbc_lblHp.gridy = 2;
        formPanel.add(lblHp, gbc_lblHp);

        txtCostumerPhone = new JTextField(); 
        GridBagConstraints gbc_txtHp = new GridBagConstraints();
        gbc_txtHp.insets = new Insets(0, 0, 8, 20);
        gbc_txtHp.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtHp.gridx = 1;
        gbc_txtHp.gridy = 2;
        formPanel.add(txtCostumerPhone, gbc_txtHp);

        JLabel lblEmail = new JLabel("Email"); 
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.anchor = GridBagConstraints.WEST;
        gbc_lblEmail.insets = new Insets(0, 20, 8, 15);
        gbc_lblEmail.gridx = 0;
        gbc_lblEmail.gridy = 3;
        formPanel.add(lblEmail, gbc_lblEmail);

        txtCustomerEmail = new JTextField(); 
        GridBagConstraints gbc_txtEmail = new GridBagConstraints();
        gbc_txtEmail.insets = new Insets(0, 0, 8, 20);
        gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtEmail.gridx = 1;
        gbc_txtEmail.gridy = 3;
        formPanel.add(txtCustomerEmail, gbc_txtEmail);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
        gbc_buttonPanel.gridwidth = 2;
        gbc_buttonPanel.fill = GridBagConstraints.HORIZONTAL;
        gbc_buttonPanel.insets = new Insets(0, 15, 15, 15);
        gbc_buttonPanel.gridx = 0;
        gbc_buttonPanel.gridy = 4;
        formPanel.add(buttonPanel, gbc_buttonPanel);

        btnSimpan = new JButton("Simpan"); 
        btnSimpan.setBackground(new Color(192, 192, 192));
        btnSimpan.setForeground(Color.BLACK);
        btnSimpan.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(btnSimpan);

        btnBatal = new JButton("Batal"); 
        btnBatal.setBackground(Color.LIGHT_GRAY);
        btnBatal.setPreferredSize(new Dimension(90, 30));
        buttonPanel.add(btnBatal);

        btnSimpan.addActionListener(e -> saveData());
        btnBatal.addActionListener(e -> batalAction());

        return formPanel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.GRAY, 1, true));
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        listCustomer = repo.show();
        tableModel = new TableCustomer(listCustomer);

        tableCustomers = new JTable(tableModel); 
        tableCustomers.setRowHeight(22);
        tableCustomers.getTableHeader().setVisible(true);

        tableCustomers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int row = tableCustomers.getSelectedRow();
                    if (row == -1) return;
                    

                    id = tableCustomers.getValueAt(row, 0).toString();
                    txtCustomerName.setText(tableCustomers.getValueAt(row, 1).toString());
                    txtCustomerEmail.setText(tableCustomers.getValueAt(row, 2).toString());
                    txtCustomerAlamat.setText(tableCustomers.getValueAt(row, 3).toString());
                    txtCostumerPhone.setText(tableCustomers.getValueAt(row, 4).toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        panel.add(new JScrollPane(tableCustomers), BorderLayout.CENTER);
        return panel;
    }

    private void saveData() {
        if (txtCustomerName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama wajib diisi!");
            return;
        }

        if (id == null) {

            String newId = String.valueOf(System.currentTimeMillis());
            Customer c = new CustomerBuilder()
                    .setId(newId)
                    .setNama(txtCustomerName.getText())
                    .setEmail(txtCustomerEmail.getText())
                    .setAlamat(txtCustomerAlamat.getText())
                    .setHp(txtCostumerPhone.getText())
                    .build();
            repo.save(c);
        } else {

            Customer c = new CustomerBuilder()
                    .setId(id) 
                    .setNama(txtCustomerName.getText())
                    .setEmail(txtCustomerEmail.getText())
                    .setAlamat(txtCustomerAlamat.getText())
                    .setHp(txtCostumerPhone.getText())
                    .build();
            repo.update(c); 
        }
        
        loadTable();
        resetForm();
    }

    private void batalAction() {
        if (id == null) {
            resetForm();
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            repo.delete(id);
            loadTable();
        }
        resetForm();
    }

    private void resetForm() {
        txtCustomerName.setText("");
        txtCustomerAlamat.setText("");
        txtCostumerPhone.setText("");
        txtCustomerEmail.setText("");
        id = null;
        tableCustomers.clearSelection();
    }

    public void loadTable() {
        listCustomer = repo.show();
        tableModel = new TableCustomer(listCustomer); 
        tableCustomers.setModel(tableModel);
        tableCustomers.getTableHeader().setVisible(true);
    }
}