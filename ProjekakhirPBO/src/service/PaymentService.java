package service;

public class PaymentService {
    public double calculateCost(String psType, long hours) {
        // Tarif contoh (boleh kamu sesuaikan)
        double ratePerHour;
        switch (psType.toUpperCase()) {
            case "PS2": ratePerHour = 3000; break;
            case "PS3": ratePerHour = 5000; break;
            case "PS4": ratePerHour = 8000; break;
            case "PS5": ratePerHour = 12000; break;
            default: ratePerHour = 7000;
        }
        return ratePerHour * hours;
    }
}
