package Model;

import java.util.Date;

public class Order {
    private int id;
    private int id_costumer;
    private int id_service;
    private int id_user;
    private double total;
    private Date tanggal;
    private Date tanggal_selesai;
    private String status;
    private String status_pembayaran;



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_costumer() {
        return id_costumer;
    }
    public void setId_costumer(int id_costumer) {
        this.id_costumer = id_costumer;
    }

    public int getId_service() {
        return id_service;
    }
    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getId_user() {
        return id_user;
    }
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Date getTanggal_selesai() {
        return tanggal_selesai;
    }
    public void setTanggal_selesai(Date tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_pembayaran() {
        return status_pembayaran;
    }
    public void setStatus_pembayaran(String status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }


    public static void main(String[] args) {
        Order ord = new Order();

        ord.setId(5001);
        ord.setId_costumer(1001);
        ord.setId_service(2001);
        ord.setId_user(999);
        ord.setTotal(30000);
        ord.setTanggal(new Date());
        ord.setTanggal_selesai(new Date(System.currentTimeMillis() + 2L * 24 * 60 * 60 * 1000));
        ord.setStatus("Proses");
        ord.setStatus_pembayaran("Belum Lunas");

        System.out.println("Order dengan ID: " + ord.getId()+ " CostumerID: " + ord.getId_costumer()+ " ServiceID: " + ord.getId_service()
                + " UserID: " + ord.getId_user()+ " | Total: " + ord.getTotal()+ " Tanggal: " + ord.getTanggal() + " Tanggal Selesai: " + ord.getTanggal_selesai()
                + " Status: " + ord.getStatus() + " Pembayaran: " + ord.getStatus_pembayaran());
    }
}
