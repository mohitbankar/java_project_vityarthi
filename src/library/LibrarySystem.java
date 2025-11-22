package library;

import java.util.Date;
import java.util.Scanner;

public class LibrarySystem {
    private UserManager userManager;
    private BookManager bookManager;
    private TransactionManager transactionManager;

    public LibrarySystem() {
        userManager = new UserManager();
        bookManager = new BookManager();
        transactionManager = new TransactionManager();
    }

    public void addUser(User user) {
        userManager.addUser(user);
    }

    public void addBook(Book book) {
        bookManager.addBook(book);
    }

    public boolean authenticateUser(String userId, String password) {
        return userManager.authenticate(userId, password);
    }

    public boolean issueBook(String userId, String bookId) {
        if (bookManager.isAvailable(bookId)) {
            String transactionId = "TXN" + System.currentTimeMillis();
            Transaction txn = new Transaction(transactionId, userId, bookId, new Date());
            transactionManager.addTransaction(txn);
            bookManager.setAvailability(bookId, false);
            return true;
        }
        return false;
    }

    public boolean returnBook(String userId, String bookId) {
        for (Transaction txn : transactionManager.getUserTransactions(userId)) {
            if (txn.getBookId().equals(bookId) && !txn.isReturned()) {
                txn.setReturned(new Date());
                bookManager.setAvailability(bookId, true);
                return true;
            }
        }
        return false;
    }

    public void showIssuedBooks() {
        System.out.println("\nIssued Books:");
        boolean found = false;
        for (Transaction txn : transactionManager.getAllTransactions()) {
            if (!txn.isReturned()) {
                User user = userManager.getUser(txn.getUserId());
                Book book = bookManager.getBook(txn.getBookId());
                String userName = (user != null) ? user.getName() : txn.getUserId();
                String bookTitle = (book != null) ? book.getTitle() : txn.getBookId();
                System.out.println("Book: " + bookTitle + " (ID: " + txn.getBookId() + ") issued to " + userName
                        + " (ID: " + txn.getUserId() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are currently issued.");
        }
    }

    public void runCLI() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "\n1. Add User\n2. Add Book\n3. Issue Book\n4. Return Book\n5. Show Issued Books\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter user type (Student/S/Librarian/L/Admin/A): ");
                    String type = scanner.nextLine().trim().toLowerCase();
                    System.out.print("User ID: ");
                    String uid = scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Password: ");
                    String pwd = scanner.nextLine();
                    User user = null;
                    if (type.equals("student") || type.equals("s"))
                        user = new Student(uid, name, pwd);
                    else if (type.equals("librarian") || type.equals("l"))
                        user = new Librarian(uid, name, pwd);
                    else if (type.equals("admin") || type.equals("a"))
                        user = new Admin(uid, name, pwd);
                    if (user != null)
                        addUser(user);
                    else
                        System.out.println("Invalid user type. Please enter Student/S, Librarian/L, or Admin/A.");
                    System.out.println("User added.");
                    break;
                case 2:
                    System.out.print("Book ID: ");
                    String bid = scanner.nextLine();
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    addBook(new Book(bid, title, author));
                    System.out.println("Book added.");
                    break;
                case 3:
                    System.out.print("User ID: ");
                    String issueUid = scanner.nextLine();
                    System.out.print("Book ID: ");
                    String issueBid = scanner.nextLine();
                    if (issueBook(issueUid, issueBid))
                        System.out.println("Book issued.");
                    else
                        System.out.println("Issue failed. Book may not be available.");
                    break;
                case 4:
                    System.out.print("User ID: ");
                    String returnUid = scanner.nextLine();
                    System.out.print("Book ID: ");
                    String returnBid = scanner.nextLine();
                    if (returnBook(returnUid, returnBid))
                        System.out.println("Book returned.");
                    else
                        System.out.println("Return failed. No such transaction.");
                    break;
                case 5:
                    showIssuedBooks();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
