package Pratikum4;

public class Book {
	private String title;
	private String author;
	private boolean isAvailable;
	
	public Book ( String title, String author) {
		this.title = title;
		this.author = author;
		this.isAvailable = true;
		
	}
	
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public boolean getisAvailable() { return isAvailable; }
	
	public void borrowBook () { this.isAvailable = false; }
	public void returnBook () { this.isAvailable = true; }
}
