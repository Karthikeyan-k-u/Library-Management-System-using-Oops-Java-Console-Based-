import java.util.*;

// =======================
// Book Class
// =======================
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void setIssued(boolean issued) {
        this.isIssued = issued;
    }

    public void displayBook() {
        System.out.println("ID: " + id +
                " | Title: " + title +
                " | Author: " + author +
                " | Status: " + (isIssued ? "Issued" : "Available"));
    }
}

// =======================
// Library Class
// =======================
class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    // Add Book (with duplicate check)
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getId() == book.getId()) {
                System.out.println(" Book ID already exists!");
                return;
            }
        }
        books.add(book);
        System.out.println(" Book added successfully!");
    }

    // Remove Book
    public void removeBook(int id) {
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book b = it.next();
            if (b.getId() == id) {
                it.remove();
                System.out.println(" Book removed!");
                return;
            }
        }
        System.out.println(" Book not found!");
    }

    // Search Book (partial match)
    public void searchBook(String title) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                b.displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found!");
        }
    }

    // Issue Book
    public void issueBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (!b.isIssued()) {
                    b.setIssued(true);
                    System.out.println("Book issued!");
                } else {
                    System.out.println("Book already issued!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Return Book
    public void returnBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (b.isIssued()) {
                    b.setIssued(false);
                    System.out.println("Book returned!");
                } else {
                    System.out.println("Book was not issued!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    // Display All Books
    public void displayAll() {
        if (books.isEmpty()) {
            System.out.println("No books in library!");
            return;
        }
        for (Book b : books) {
            b.displayBook();
        }
    }
}

// =======================
// Main Class
// =======================
public class Fulllength1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Display All Books");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            
            // Input validation
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // FIX: consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    library.addBook(new Book(id, title, author));
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    library.removeBook(sc.nextInt());
                    sc.nextLine();
                    break;

                case 3:
                    System.out.print("Enter Title: ");
                    library.searchBook(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    library.issueBook(sc.nextInt());
                    sc.nextLine();
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    library.returnBook(sc.nextInt());
                    sc.nextLine();
                    break;

                case 6:
                    library.displayAll();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}