package Model;

public class Customer {
    private String id;
    private String nama;
    private String email;
    private String alamat;
    private String hp;
    
    public Customer(String id, String nama, String email, String alamat, String hp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.hp = hp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getalamat() {
        return alamat;
    }

    public void setalamat(String alamat) {
        this.alamat = alamat;
    }

    public String gethp() {
        return hp;
    }

    public void sethp(String hp) {
        this.hp = hp;
    }
    public String email() {
        return email;
    }

    public void email(String email) {
        this.email = email;

    }

	public String getemail() {
		return email;
	}

		
	
}

