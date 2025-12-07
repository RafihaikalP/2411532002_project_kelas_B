package latihan1;

public class ChefThread extends Thread {
    private String chef;

    public ChefThread(String chef) {
        this.chef = chef;
    }

    @Override
    public void run() {
        System.out.println(chef + " mulai memasak...");
        for (int i = 1; i <= 5; i++) {
            System.out.println(chef + " memasak tahap " + i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(chef + " selesai memasak!\n");
    }
}


