package service;

import dao.PlayStationDAO;
import dao.RentalTransactionDAO;
import factory.TransactionFactory;
import helper.TimeCalculator;
import model.Customer;
import model.PlayStation;
import model.RentalTransaction;

import java.time.LocalDateTime;

public class RentalService {
    private final RentalTransactionDAO trxDAO;
    private final PlayStationDAO psDAO;
    private final PaymentService paymentService;

    public RentalService(RentalTransactionDAO trxDAO, PlayStationDAO psDAO, PaymentService paymentService) {
        this.trxDAO = trxDAO;
        this.psDAO = psDAO;
        this.paymentService = paymentService;
    }

    public RentalTransaction rent(Customer c, PlayStation ps) throws Exception {
        RentalTransaction t = TransactionFactory.create(c, ps);

        // update status PS -> rented
        ps.setStatus("rented");
        psDAO.update(ps);

        trxDAO.create(t);
        return t;
    }

    public RentalTransaction returnPlayStation(String trxId) throws Exception {
        RentalTransaction t = trxDAO.findById(trxId);
        if (t == null) throw new IllegalArgumentException("Transaksi tidak ditemukan!");

        if (t.getEndTime() != null)
            throw new IllegalStateException("Transaksi sudah ditutup (sudah dikembalikan).");

        LocalDateTime end = LocalDateTime.now();
        long hours = TimeCalculator.hoursBetween(t.getStartTime(), end);

        double cost = paymentService.calculateCost(t.getPlayStation().getType(), hours);

        t.setEndTime(end);
        t.setCost(cost);
        trxDAO.update(t);

        // update status PS -> available
        PlayStation psObj = t.getPlayStation();
        psObj.setStatus("available");
        psDAO.update(psObj);

        return t;
    }
}
