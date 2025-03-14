import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanService {

    // responsible for: loaning books, returning books, checking which books are loaned
    // yo
    private final List<Loan> loans;
    
    public LoanService() {
        this.loans = new ArrayList<>(); // 5. creating a new ArrayList for loans
    }
    
    // borrowing a book
    public boolean borrowBook(User user, Book book) {
        if (book.getStatus() == Status.BORROWED) { // checking if book is borrowed
            System.out.println("The book is currently borrowed."); // if book is borrowed, display the info
            return false;
        }
        
        Loan loan = new Loan(book, user); // composition? creating a new Loan object, going into constructor
        loans.add(loan); // adding a loan to list of loans in formatted text by toString() method
        user.addLoan(loan); // adding a loan to list of user loans in formatted text by toString() method
        book.setStatus(Status.BORROWED); // change status to a borrowed
        System.out.println("Book " + book.getTitle() + " is now borrowed by " + user.getName()); // display info
        return true;
    }
    
    // returning a book
    public boolean returnBook(User user, Book book) {
        Optional<Loan> loan = loans.stream() // if a loan that does match these conditions exist -> then loan contains Optional<Loan>, if a loan does not match the conditions -> loan = Optional.empty()
                .filter(l -> l.getUser().equals(user) && l.getBook().equals(book) && l.getReturnDate() == null).findFirst();
        // searching a loans list in order to find matching loan for user and book
        
        if (loan.isPresent()) { // if we found a loan, we set book as a returned
            loan.get().returnBook(); // we get the Loan object from Optional and do certain operation with this object, basically, loan.get() means - if loan contains value,
                                     // we found a loan, then return this object
                                     // get() - get the value from Optional
            user.removeLoan(loan.get());
            System.out.println("Book " + book.getTitle() + " has been returned by " + user.getName());
            return true;
        } else {
            System.out.println("The book can't be returned because is not currently borrowed by this user!");
            return false;
        }
    }
    
    // display info about borrowed books by the user
    public void listBorrowedBooks(User user) {
        //System.out.println("Books borrowed by " + user.getName() + ": ");
        user.getLoans().forEach(System.out::println);
    }
}
