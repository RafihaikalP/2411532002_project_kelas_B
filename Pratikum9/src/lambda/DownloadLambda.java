package lambda;

public class DownloadLambda {

    public static void main(String[] args) {

        Runnable d1 = () -> download("File-A.iso");
        Runnable d2 = () -> download("File-B.rar");
        Runnable d3 = () -> download("File-C.mp4");

        new Thread(d1).start();
        new Thread(d2).start();
        new Thread(d3).start();
    }

    public static void download(String fileName) {
        System.out.println("Mulai download: " + fileName);
        for (int i = 10; i <= 100; i += 10) {
            System.out.println(fileName + " : " + i + "%");
            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
        System.out.println("Download selesai: " + fileName + "\n");
    }
}

