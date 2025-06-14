package model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private int copies;
    private int totalCopies;

    public Book(int id, String title, String author, String publisher, int year, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.copies = copies;
        this.totalCopies = copies; 
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public int getCopies() {
        return copies;
    }

    public void increaseCopies() {
        copies++;
    }

    public void decreaseCopies() {
        if (copies > 0) {
            copies--;

        }
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + publisher + " | Year: " + year + " | Copies: " + copies + "/" + totalCopies;
    }
}