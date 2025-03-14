import java.util.ArrayList;
import java.util.List;

public class User {

    // class that represents the user of library, who can loan/return the books
    // User has a list of borrowed books -> List<Loan>
    
    private final int id;
    private final String name;
    private final String email;
    private final List<Loan> loans;
    
    public User(int id, String name, String email) { // 13. assign the values for every created user object
        this.id = id;
        this.name = name;
        this.email = email;
        this.loans = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public List<Loan> getLoans() {
        return loans;
    }
    
    public void addLoan(Loan loan) {
        loans.add(loan); //
    }
    
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }
    
    @Override
    public String toString() {
        return name + " (" + email + ") - [User ID: " + id + "]";
    }
}
