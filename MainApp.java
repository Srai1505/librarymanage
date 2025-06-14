import java.util.*;
import manager.*;
import model.*;

public class MainApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryManager library = new LibraryManager();
        LoginManager loginManager = new LoginManager();

        library.addUser(new User(1, "Admin", "admin", "admin123", true));
        library.addUser(new User(2, "Abhishek", "abhi", "pass123", false));
        library.addBook(new Book(101, "Java Basics", "Sharma", "TechBooks", 2021, 4));
        library.addBook(new Book(102, "OOP in Java", "Kumar", "CodePress", 2022, 2));

        User currentUser = null;
        while (currentUser == null) {
            System.out.println("\n1. Login\n2. Register\n3. Exit");
            int opt = sc.nextInt();
            sc.nextLine();

            if (opt == 1) {
                System.out.print("Username: ");
                String uname = sc.nextLine();
                System.out.print("Password: ");
                String pwd = sc.nextLine();
                currentUser = loginManager.login(uname, pwd, library.getAllUsers());

                if (currentUser == null) {
                    System.out.println("❌ Login failed. Try again.");
                }

            } else if (opt == 2) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Username: ");
                String uname = sc.nextLine();
                System.out.print("Password: ");
                String pwd = sc.nextLine();

                // Generate new user ID
                int newUserId = 1000 + new Random().nextInt(9000);
                User newUser = new User(newUserId, name, uname, pwd, false);
                library.addUser(newUser);
                System.out.println("User registered. You can now log in.");

            } else if (opt == 3) {
                System.out.println("Goodbye!");
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        System.out.println("\nWelcome " + currentUser.getName() + (currentUser.isAdmin() ? " (Admin)" : ""));
        int choice;
        do {
            System.out.println("\n=== MENU ===");
            if (currentUser.isAdmin()) {
                System.out.println("1. Add Book\n2. View Books\n3. View Users\n4. Logout");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Author: ");
                        String author = sc.nextLine();
                        System.out.print("Publisher: ");
                        String pub = sc.nextLine();
                        System.out.print("Year: ");
                        int year = sc.nextInt();
                        System.out.print("Copies: ");
                        int cop = sc.nextInt();
                        int newId = 100 + new Random().nextInt(900);
                        library.addBook(new Book(newId, title, author, pub, year, cop));
                        System.out.println("Book added.");
                        break;
                    case 2:
                        library.viewAllBooks();
                        break;
                    case 3:
                        for (User u : library.getAllUsers()) {
                            System.out.println(u.getUserId() + ": " + u.getName() + " | Role: " + (u.isAdmin() ? "Admin" : "User"));
                        }
                        break;
                    case 4:
                        System.out.println("Logged out.");
                        break;
                }
            } else {
                System.out.println("1. View Books\n2. Search Book\n3. Borrow Book\n4. Return Book\n5. My Books\n6. Logout");
                choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        library.viewAllBooks();
                        break;
                    case 2:
                        System.out.print("Keyword: ");
                        String keyword = sc.nextLine();
                        library.searchBookByTitle(keyword);
                        break;
                    case 3:
                        System.out.print("Book ID: ");
                        int bid = sc.nextInt();
                        library.borrowBook(currentUser.getUserId(), bid);
                        break;
                    case 4:
                        System.out.print("Book ID: ");
                        int rid = sc.nextInt();
                        library.returnBook(currentUser.getUserId(), rid);
                        break;
                    case 5:
                        currentUser.showBorrowedBooks();
                        break;
                    case 6:
                        System.out.println("Logged out.");
                        break;
                }
            }
        } while (choice != (currentUser.isAdmin() ? 4 : 6));

        sc.close();
    }
}
