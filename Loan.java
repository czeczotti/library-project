import java.util.Date;

public class Loan {

    // class that represents a single book loan by user
    
    private final Book book;
    private final User user;
    private final Date loanDate;
    private Date returnDate;
    
    public Loan(Book book, User user) { // set the default values for Loan object, constructor set the correct values to give basic information about a single loan
        this.book = book;
        this.user = user;
        this.loanDate = new Date();
        this.returnDate = null;
    }
    
    public Book getBook() {
        return book;
    }
    
    public User getUser() {
        return user;
    }
    
    public Date getLoanDate() {
        return loanDate;
    }
    
    public Date getReturnDate() {
        return returnDate;
    }
    
    public void returnBook() {
        this.returnDate = new Date();
        book.setStatus(Status.AVAILABLE);
    }
    
    @Override
    public String toString() {
        return book.getTitle() + " borrowed by " + user.getName() + " on " + loanDate;
    }
}
