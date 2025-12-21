package factory;

import helper.IdGenerator;
import model.Customer;
import model.PlayStation;
import model.RentalTransaction;

import java.time.LocalDateTime;

public final class TransactionFactory {
    private TransactionFactory() {}

    public static RentalTransaction create(Customer c, PlayStation ps) {
        if (c == null) throw new IllegalArgumentException("Customer tidak ditemukan!");
        if (ps == null) throw new IllegalArgumentException("PlayStation tidak ditemukan!");
        if (!"available".equalsIgnoreCase(ps.getStatus()))
            throw new IllegalStateException("PlayStation sedang tidak tersedia (status rented)!");

        String id = IdGenerator.newId("TRX");
        return new RentalTransaction(id, LocalDateTime.now(), null, 0.0, c, ps);
    }
}
