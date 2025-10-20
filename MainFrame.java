package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 176, 40);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Pesanan");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 62, 132, 54);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Pengguna");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 139, 132, 54);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Keluar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(152, 212, 132, 54);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Layanan");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(152, 62, 132, 54);
		contentPane.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Laporan");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBounds(152, 139, 132, 54);
		contentPane.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Pelanggan");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_5.setBounds(294, 62, 134, 54);
		contentPane.add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("Profil");
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_6.setBounds(296, 139, 132, 54);
		contentPane.add(btnNewButton_6);
	}
}
