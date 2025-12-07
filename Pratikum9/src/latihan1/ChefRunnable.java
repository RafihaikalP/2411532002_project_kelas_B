package latihan1;

public class ChefRunnable implements Runnable {
    private String chef;

    public ChefRunnable(String chef) {
        this.chef = chef;
    }

    @Override
    public void run() {
        System.out.println(chef + " mulai memasak...");
        for (int i = 1; i <= 5; i++) {
            System.out.println(chef + " memasak tahap " + i);
            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
        System.out.println(chef + " selesai memasak!\n");
    }
}
