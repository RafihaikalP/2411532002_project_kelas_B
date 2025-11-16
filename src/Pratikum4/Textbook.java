package Pratikum4;

public class Textbook extends Book {
	private String Bidangstudi;
	
	public Textbook (String title, String author, String Bidangstudi) {
		super (title, author);
		this.Bidangstudi = Bidangstudi;
	}
	public String getBidangstudi() {return Bidangstudi; }
}
