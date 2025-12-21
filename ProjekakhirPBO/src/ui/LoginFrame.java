package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final JTextField tfUsername = new JTextField();
    private final JPasswordField pfPassword = new JPasswordField();
    private final JCheckBox cbShowPassword = new JCheckBox("Show Password");

    public LoginFrame() {
        setTitle("Login - PlayStation Rental Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 340);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        setLayout(new BorderLayout());
        add(makeHeader(), BorderLayout.NORTH);
        add(makeForm(), BorderLayout.CENTER);
        add(makeFooter(), BorderLayout.SOUTH);
    }

    private JPanel makeHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(16, 18, 10, 18));

        JLabel title = new JLabel("PlayStation Rental Manager");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        JLabel subtitle = new JLabel("Silakan login untuk melanjutkan");
        subtitle.setFont(subtitle.getFont().deriveFont(13f));

        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.setOpaque(false);
        text.add(title);
        text.add(Box.createVerticalStrut(4));
        text.add(subtitle);

        header.add(text, BorderLayout.WEST);
        return header;
    }

    private JPanel makeForm() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(new EmptyBorder(10, 18, 10, 18));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(8, 8, 8, 8);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1;

        JLabel lbUser = new JLabel("Username");
        lbUser.setFont(lbUser.getFont().deriveFont(Font.BOLD));

        JLabel lbPass = new JLabel("Password");
        lbPass.setFont(lbPass.getFont().deriveFont(Font.BOLD));

        // Username
        gc.gridx = 0; gc.gridy = 0;
        form.add(lbUser, gc);

        gc.gridx = 0; gc.gridy = 1;
        tfUsername.setColumns(20);
        form.add(tfUsername, gc);

        // Password
        gc.gridx = 0; gc.gridy = 2;
        form.add(lbPass, gc);

        gc.gridx = 0; gc.gridy = 3;
        pfPassword.setColumns(20);
        form.add(pfPassword, gc);

        // Show password
        gc.gridx = 0; gc.gridy = 4;
        cbShowPassword.setOpaque(false);
        cbShowPassword.addActionListener(e -> togglePassword());
        form.add(cbShowPassword, gc);

        wrapper.add(form, BorderLayout.NORTH);

        // Enter untuk login
        pfPassword.addActionListener(e -> doLogin());

        return wrapper;
    }

    private JPanel makeFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBorder(new EmptyBorder(0, 18, 16, 18));

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton btnClear = new JButton("Clear");
        JButton btnLogin = new JButton("Login");

        styleButton(btnLogin, true);
        styleButton(btnClear, false);

        btnClear.addActionListener(e -> clearForm());
        btnLogin.addActionListener(e -> doLogin());

        actions.add(btnClear);
        actions.add(btnLogin);

        footer.add(actions, BorderLayout.EAST);

        JLabel hint = new JLabel("Tip: Tekan Enter pada password untuk login");
        hint.setFont(hint.getFont().deriveFont(12f));
        footer.add(hint, BorderLayout.WEST);

        return footer;
    }

    private void styleButton(JButton btn, boolean primary) {
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setFont(btn.getFont().deriveFont(Font.BOLD, 12.5f));
        if (primary) {
            btn.setBackground(new Color(30, 136, 229));
            btn.setForeground(Color.WHITE);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
        }
    }

    private void togglePassword() {
        if (cbShowPassword.isSelected()) {
            pfPassword.setEchoChar((char) 0);
        } else {
            // default echo char
            pfPassword.setEchoChar('•');
        }
    }

    private void clearForm() {
        tfUsername.setText("");
        pfPassword.setText("");
        cbShowPassword.setSelected(false);
        pfPassword.setEchoChar('•');
        tfUsername.requestFocus();
    }

    private void doLogin() {
        String username = tfUsername.getText().trim();
        String password = new String(pfPassword.getPassword());

        // ✅ LOGIN SEDERHANA (hardcode)
        // Kamu boleh ganti jadi cek database nanti.
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username & Password wajib diisi!",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isValidLogin(username, password)) {
            JOptionPane.showMessageDialog(this, "Login berhasil!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // buka main app
            MainFrame main = new MainFrame();
            main.setVisible(true);

            // tutup login
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah!",
                    "Login gagal", JOptionPane.ERROR_MESSAGE);
            pfPassword.setText("");
            pfPassword.requestFocus();
        }
    }

    private boolean isValidLogin(String username, String password) {
        // contoh akun:
        // admin / admin123
        // kasir / kasir123
        return (username.equals("admin") && password.equals("123"))
                || (username.equals("kasir") && password.equals("123"));
    }
}
