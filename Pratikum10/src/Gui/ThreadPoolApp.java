package Gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolApp {
    private JFrame frame;
    private JTextArea logArea;
    private DefaultListModel<String> taskListModel;
    private JLabel statusLabel;
    private JTextField threadCountField, taskCountField;
    private JButton startButton, clearButton;
    private JPanel controlPanel;

    public ThreadPoolApp() {
        frame = new JFrame("Aplikasi ThreadPool dengan GUI");
        frame.setLayout(new BorderLayout());

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        threadCountField = new JTextField(5);
        taskCountField = new JTextField(5);
        startButton = new JButton("Mulai Proses");

        clearButton = new JButton("Bersihkan Log");
        clearButton.addActionListener(e -> clearLog());

        controlPanel.add(new JLabel("Jumlah Thread:"));
        controlPanel.add(threadCountField);
        controlPanel.add(new JLabel("Jumlah Tugas:"));
        controlPanel.add(taskCountField);
        controlPanel.add(startButton);
        controlPanel.add(clearButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        logArea = new JTextArea(15, 30);
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane taskScroll = new JScrollPane(taskList);
        frame.add(taskScroll, BorderLayout.WEST);

        statusLabel = new JLabel("Log dibersihkan. Siap untuk proses baru.");
        frame.add(statusLabel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startProcessing());

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ThreadPoolApp();
    }

    private void clearLog() {
        logArea.setText("");  
        taskListModel.clear(); 
        statusLabel.setText("Log dibersihkan. Siap untuk proses baru.");
    }

    private void startProcessing() {
        try {
            int threadCount = Integer.parseInt(threadCountField.getText());
            int taskCount = Integer.parseInt(taskCountField.getText());

            if (threadCount < 1 || taskCount < 1) {
                JOptionPane.showMessageDialog(frame, "Jumlah thread dan tugas harus lebih dari 0!", "Input Tidak Valid", JOptionPane.ERROR_MESSAGE);
                return;
            }

            startButton.setEnabled(false);
            taskListModel.clear();
            logArea.append("=== Memulai Proses Baru ===\n");
            logArea.append("ThreadPool dibuat dengan " + threadCount + " worker threads\n");
            statusLabel.setText("Memproses " + taskCount + " tugas dengan " + threadCount + " threads...");

            ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

            for (int i = 1; i <= taskCount; i++) {
                taskListModel.addElement("Task #" + i + " - Waiting");
            }

            for (int i = 1; i <= taskCount; i++) {
                Task task = new Task(i, logArea, taskListModel);
                threadPool.execute(task);
            }

            new Thread(() -> {
                threadPool.shutdown();
                try {
                    if (threadPool.awaitTermination(5, TimeUnit.MINUTES)) {
                        SwingUtilities.invokeLater(() -> {
                            logArea.append("\n=== Semua tugas selesai ===\n");
                            statusLabel.setText("Semua tugas selesai!");
                            startButton.setEnabled(true);
                        });
                    }
                } catch (InterruptedException e) {
                    threadPool.shutdownNow();
                }
            }).start();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!", "Input Tidak Valid", JOptionPane.ERROR_MESSAGE);
        }
    }

    class Task implements Runnable {
        private int taskId;
        private JTextArea logArea;
        private DefaultListModel<String> taskListModel;

        public Task(int taskId, JTextArea logArea, DefaultListModel<String> taskListModel) {
            this.taskId = taskId;
            this.logArea = logArea;
            this.taskListModel = taskListModel;
        }

        @Override
        public void run() {
            SwingUtilities.invokeLater(() -> {
                logArea.append("Task #" + taskId + " dimulai oleh " + Thread.currentThread().getName() + "\n");
                updateTaskList("Task #" + taskId + " - Running");
            });

            try {
                int processingTime = new Random().nextInt(3000) + 1000;  // Random 1-3 detik
                Thread.sleep(processingTime);

                SwingUtilities.invokeLater(() -> {
                    logArea.append("Task #" + taskId + " selesai oleh " + Thread.currentThread().getName() + " (waktu: " + processingTime + "ms)\n");
                    updateTaskList("Task #" + taskId + " - Completed");
                });
            } catch (InterruptedException e) {
                SwingUtilities.invokeLater(() -> {
                    logArea.append("Task #" + taskId + " terganggu!\n");
                    updateTaskList("Task #" + taskId + " - Interrupted");
                });
                Thread.currentThread().interrupt();
            }
        }

        private void updateTaskList(String status) {
            for (int i = 0; i < taskListModel.size(); i++) {
                if (taskListModel.get(i).startsWith("Task #" + taskId)) {
                    taskListModel.set(i, status);
                    break;
                }
            }
        }
    }
}

