package Pratikum4;

public class Magazine extends Book {
	private String kategori;
	
	public Magazine (String title, String author, String kategori) {
		super (title, author);
		this.kategori= kategori;
	}
	public String getKategori() {return kategori; }
}
