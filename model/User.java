package model;

import java.util.*;

public class User {
    private int userId;
    private String name;
    private String username;
    private String password;
    private boolean isAdmin;
    private List<Integer> borrowedBookIds = new ArrayList<>();

    public User(int userId, String name, String username, String password, boolean isAdmin) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isAdmin() { return isAdmin; }

    public void borrowBook(int bookId) { borrowedBookIds.add(bookId); }
    public void returnBook(int bookId) { borrowedBookIds.remove(Integer.valueOf(bookId)); }
    public List<Integer> getBorrowedBookIds() { return borrowedBookIds; }

    public void showBorrowedBooks() {
        if (borrowedBookIds.isEmpty()) {
            System.out.println("No borrowed books.");
        } else {
            System.out.println("Borrowed Book IDs: " + borrowedBookIds);
        }
    }
}