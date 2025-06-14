package manager;

import java.util.ArrayList;
import model.Book;
import model.User;

public class LibraryManager {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();



    // Add a new book to the list
    public void addBook(Book book) {
        books.add(book);
    }

    // Add a new user to the list
    public void addUser(User user) {
        users.add(user);
    }

    // Get list of all users
    public ArrayList<User> getAllUsers() {
        return users;
    }

    // Get a user by user ID
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    // Get a book by book ID
    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // View all books
    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Search for a book by its title
    public void searchBookByTitle(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }
    // Borrow a book
    public void borrowBook(int userId, int bookId) {
        User user = getUserById(userId);
        Book book = getBookById(bookId);

        if (user != null && book != null && book.getCopies() > 0) {
            book.decreaseCopies();
            user.borrowBook(bookId);
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Unable to borrow book.");
        }
    }
    // Delete a book by book ID
public boolean deleteBookById(int bookId) {
    for (Book book : books) {
        if (book.getId() == bookId) {
            books.remove(book);
            System.out.println("Book with ID " + bookId + " has been deleted.");
            return true;
        }
    }
    System.out.println("Book with ID " + bookId + " not found.");
    return false;
}

    // Return a book
    public void returnBook(int userId, int bookId) {
        User user = getUserById(userId);
        Book book = getBookById(bookId);

        if (user != null && book != null) {
            book.increaseCopies();
            user.returnBook(bookId);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Unable to return book.");
        }
    }
}