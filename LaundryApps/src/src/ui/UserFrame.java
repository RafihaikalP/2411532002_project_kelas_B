package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UserRepo;
import Model.User;
import Table.TableUser;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import error1.ValidationException1;
import util1.ValidationUtilUser1;

 

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTable tableUsers;
	UserRepo usr = new UserRepo();
	List<User> ls;
	public String id = null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(214, 60, 469, 38);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(214, 129, 469, 38);
		contentPane.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(214, 198, 469, 38);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(61, 60, 87, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(61, 129, 87, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(61, 198, 87, 38);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(128, 255, 128));
		
		 
		btnSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        User user = new User("", "");
		        user.setNama(txtName.getText());
		        user.setUsername(txtUsername.getText());
		        user.setPassword(txtPassword.getText());

		        try {
		            ValidationUtilUser1.validate(user); 
		            usr.save(user);
		            reset();
		            loadTable();
		            JOptionPane.showMessageDialog(null, "User saved successfully!");
		        } 
		        catch (ValidationException1 | NullPointerException ex) {
		            JOptionPane.showMessageDialog(null, "Validation Error:\n" + ex.getMessage());
		        }
		    }
		});



		btnSave.setBounds(214, 266, 108, 29);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(128, 255, 255));
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        User user = new User("", "");
		        user.setId(id);
		        user.setNama(txtName.getText());
		        user.setUsername(txtUsername.getText());
		        user.setPassword(txtPassword.getText());

		        try {
		            ValidationUtilUser1.validateForUpdate(user); 
		            usr.update(user);
		            reset();
		            loadTable();
		            JOptionPane.showMessageDialog(null, "User updated successfully!");
		        } 
		        catch (ValidationException1 | NullPointerException ex) {
		            JOptionPane.showMessageDialog(null, "Update Failed:\n" + ex.getMessage());
		        }
		    }
		});


		btnUpdate.setBounds(332, 266, 105, 29);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 0, 128));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					usr.delete(id);
					reset();
					loadTable();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btnDelete.setBounds(447, 266, 104, 29);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(561, 266, 122, 31);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 372, 727, 202);
		contentPane.add(scrollPane);
		
		tableUsers = new JTable();
		scrollPane.setViewportView(tableUsers);
		tableUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableUsers.getValueAt(tableUsers.getSelectedRow(), 0).toString();
				txtName.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 1).toString());
				txtUsername.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 2).toString());
				txtPassword.setText(tableUsers.getValueAt(tableUsers.getSelectedRow(), 3).toString());
			}
		});
		
		
		
		
	}
	public void reset() {
		txtName.setText("");
		txtUsername.setText("");
		txtPassword.setText("");
	}
	
	public void loadTable() {
		ls = usr.show();
		TableUser tu = new TableUser(ls);
		tableUsers.setModel(tu);
		tableUsers.getTableHeader().setVisible(true);
	}
}