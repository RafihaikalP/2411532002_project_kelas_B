package latihan1;

public class RestaurantRunnable {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ChefRunnable("Koki A"));
        Thread t2 = new Thread(new ChefRunnable("Koki B"));
        Thread t3 = new Thread(new ChefRunnable("Koki C"));

        t1.start();
        t2.start();
        t3.start();
    }
}

