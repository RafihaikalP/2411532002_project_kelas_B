package Model;

public class Service {
	    private int id;
	    private String jenis;
	    private double harga;
	    private String status;

	    public int getId() {
	        return id;
	    }
	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getJenis() {
	        return jenis;
	    }
	    public void setJenis(String jenis) {
	        this.jenis = jenis;
	    }

	    public double getHarga() {
	        return harga;
	    }
	    public void setHarga(double harga) {
	        this.harga = harga;
	    }

	    public String getStatus() {
	        return status;
	    }
	    public void setStatus(String status) {
	        this.status = status;
	    }
	

	   public class ser {
		public static void main(String[] args) {
			Service ser = new Service();
	        ser.setId(101);
	        ser.setJenis("Cuci Setrika");
	        ser.setHarga(15000);
	        ser.setStatus("Tersedia");
	        
	        System.out.println("Service dengan ID: " + ser.getId()
            + " Jenis: " + ser.getJenis()
            + " Harga: " + ser.getHarga()
            + " Status: " + ser.getStatus());
	        
		}
	}

}			
		
		

