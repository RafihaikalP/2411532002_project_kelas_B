package Model;

public class Costumer {
    private int id;
    private String nama;
    private String alamat;
    private String noHp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String no_hp) {
        this.noHp = noHp;
    }

    public class cos{
		public static void main(String[] args) {
			Costumer cos = new Costumer();
			cos.setId(1);
			cos.setNama("Ahmad");
			cos.setAlamat("Padang");
			cos.setNoHp("08123456789");

			System.out.println("Customer dengan ID = " + cos.getId() + 
                           " Nama = " + cos.getNama() + 
                           " Alamat = " + cos.getAlamat() + 
                           " No HP = " + cos.getNoHp());
    }
	}
}
