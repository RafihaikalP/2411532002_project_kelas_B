package latihan1;

public class RestaurantNoThread {

    public static void main(String[] args) {
        prepareFood("Koki A");
        prepareFood("Koki B");
        prepareFood("Koki C");
    }

    public static void prepareFood(String chef) {
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
