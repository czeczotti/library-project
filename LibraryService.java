import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryService {
    
    // responsible for: adding books to a library; user registration; searching books and users; checking if book is available or not
    // elo
    private final List<Book> books; // list of books
    private final List<User> users;
    
    public LibraryService() { // 3. creating new ArrayList for books and users to store data
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    
    // adding a book to a system -> books list
    public void addBook(Book book) { // 10. adding a book from list booksToAdd in formatted text representation by toString()
        books.add(book);
    }
    
    // registration of a user -> users list
    public void registerUser(User user) { // 15. adding a user from list usersToAdd in formatted text representation by toString()
        users.add(user);
    }
    
    // finding a book by ID
    public Optional<Book> findBookById (int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst();
    }
    
    // finding a user by ID
    public Optional<User> findUserById (int id) {
        // searching list of users in library (users.stream())
        // filtration by using filter(...) to find user with matching id
        // if user existing we return Optional<User>
        // if user does not exist we return empty Optional (Optiona.empty())
        // by using optional we don't have to use null and else-if
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }
    
    // checking available books
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>(); // creating an empty list named availableBooks, in which we store available books
        
        for (Book book : books) { // iterating through every book in books ArrayList, this mean through every added book we store in library
            if (book.getStatus() == Status.AVAILABLE) { // checking if book's status is AVAILABLE, at start we set every book as a AVAILABLE in constructor
                availableBooks.add(book); // if book is available, we add it to our list availableBooks
            }
        }
        return availableBooks; // return the list availableBooks to case 1
    }
    
    // display a list of all books
    public void listAllBooks() {
        books.forEach(System.out::println);
    }
    
    // display a list of all users
    public void listAllUsers() {
        users.forEach(System.out::println);
    }
    
}
