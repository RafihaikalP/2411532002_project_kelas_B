package Pratikum4;

public class Main {
    public static void main(String[] args) {
        // Membuat objek buku dari berbagai jenis
        Book novel = new Novel("Laskar Pelangi", "Andrea Hirata", "Drama");
        Book magazine = new Magazine("National Geographic", "Various Authors", "Science");
        Book textbook = new Textbook("Pemrograman Java", "Anonymous", "Informatika");

        // Membuat objek user
        User user = new User();

        // Menampilkan detail buku menggunakan polymorphism
        System.out.println("=== Detail Buku ===");
        user.viewBookDetails(novel);
        System.out.println();
        user.viewBookDetails(magazine);
        System.out.println();
        user.viewBookDetails(textbook);
        System.out.println();


            // Meminjam buku
            System.out.println("=== Proses Peminjaman Buku ===");
            user.borrowBook(novel);
            user.borrowBook(magazine);

            // Menampilkan status ketersediaan
            System.out.println("\nStatus Buku Setelah Dipinjam:");
            System.out.println(novel.getTitle() + " tersedia: " + novel.getisAvailable());
            System.out.println(magazine.getTitle() + " tersedia: " + magazine.getisAvailable());

            // Mengembalikan buku
            System.out.println("\n=== Proses Pengembalian Buku ===");
            user.returnBook(novel);

            // Menampilkan status ketersediaan setelah pengembalian
            System.out.println("\nStatus Buku Setelah Dikembalikan:");
            System.out.println(novel.getTitle() + " tersedia: " + novel.getisAvailable());
        }
    }



