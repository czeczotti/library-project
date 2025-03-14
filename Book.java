public class Book {

    // class that represents a book in system (title, author, status..)
    // Book has a status -> Enums (AVAILABLE, BORROWED)
    // elo
    private final int id;
    private final String title;
    private final String author;
    private Status status;
    
    public Book(int id, String title, String author) { // 8. assign the values for every created book object
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = Status.AVAILABLE;
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
    
    public Status getStatus() {
        return status; // returning status of a book -> AVAILABLE or BORROWED
    }
    
    public void setStatus (Status status) {
        this.status = status;
    }
    
    @Override
    public String toString() { // text representation, every Book object calls toString() method by default and display info in this format below
        return title + " by " + author + " (" + status + ") - [Book ID: " + id + "]";
    }
}
