package latihan2;

public class DownloadTask extends Thread {
    private String fileName;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        System.out.println("Mulai download: " + fileName);
        for (int i = 10; i <= 100; i += 10) {
            System.out.println(fileName + " : " + i + "%");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Download selesai: " + fileName + "\n");
    }
}
