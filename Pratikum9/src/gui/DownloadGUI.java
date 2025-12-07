package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class DownloadGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JProgressBar bar1, bar2, bar3;
    private JButton startBtn;
    private JTextField txtDownloadManagerApp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DownloadGUI frame = new DownloadGUI();
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
	 public DownloadGUI() {
	        setTitle("Simulasi Download File");
	        setSize(477, 400);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        getContentPane().setLayout(null);

	        JLabel lblFile = new JLabel("File-1");
	        lblFile.setFont(new Font("Tahoma", Font.BOLD, 15));
	        lblFile.setBounds(20, 54, 368, 37);
	        getContentPane().add(lblFile);
	        bar1 = new JProgressBar(0, 100);
	        bar1.setBounds(10, 91, 445, 37);
	        getContentPane().add(bar1);

	        JLabel lblFile_1 = new JLabel("File-2");
	        lblFile_1.setFont(new Font("Tahoma", Font.BOLD, 15));
	        lblFile_1.setBounds(20, 139, 368, 37);
	        getContentPane().add(lblFile_1);
	        bar2 = new JProgressBar(0, 100);
	        bar2.setBounds(10, 173, 445, 37);
	        getContentPane().add(bar2);

	        JLabel lblFile_2 = new JLabel("File-3");
	        lblFile_2.setFont(new Font("Tahoma", Font.BOLD, 15));
	        lblFile_2.setBounds(20, 217, 368, 37);
	        getContentPane().add(lblFile_2);
	        bar3 = new JProgressBar(0, 100);
	        bar3.setBounds(10, 252, 445, 37);
	        getContentPane().add(bar3);

	        startBtn = new JButton("Start Download");
	        startBtn.setBounds(261, 317, 204, 37);
	        getContentPane().add(startBtn);
	        
	        txtDownloadManagerApp = new JTextField();
	        txtDownloadManagerApp.setFont(new Font("Tahoma", Font.BOLD, 15));
	        txtDownloadManagerApp.setHorizontalAlignment(SwingConstants.CENTER);
	        txtDownloadManagerApp.setText("Download Manager App");
	        txtDownloadManagerApp.setBounds(92, 11, 271, 32);
	        getContentPane().add(txtDownloadManagerApp);
	        txtDownloadManagerApp.setColumns(10);

	        startBtn.addActionListener(e -> startDownload());
	    }

	    private void startDownload() {
	        new Thread(() -> runDownload(bar1)).start();
	        new Thread(() -> runDownload(bar2)).start();
	        new Thread(() -> runDownload(bar3)).start();
	    }

	    private void runDownload(JProgressBar bar) {
	        for (int i = 0; i <= 100; i += 10) {
	            bar.setValue(i);
	            try {
	                Thread.sleep(500);
	            } catch (Exception ignored) {}
	        }
	    }

	    public static void main1(String[] args) {
	        SwingUtilities.invokeLater(() -> new DownloadGUI().setVisible(true));
	    }
	}
