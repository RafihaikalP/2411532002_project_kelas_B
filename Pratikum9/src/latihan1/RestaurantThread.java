package latihan1;

public class RestaurantThread {
    public static void main(String[] args) {
        ChefThread c1 = new ChefThread("Koki A");
        ChefThread c2 = new ChefThread("Koki B");
        ChefThread c3 = new ChefThread("Koki C");

        c1.start();
        c2.start();
        c3.start();
    }
}



