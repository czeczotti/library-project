import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in); // 1. creating a scanner
        LibraryService libraryService = new LibraryService(); // 2. creating a new object libraryService -> going into constructor, currently books and users are size 0
        LoanService loanService = new LoanService(); // 4. creating a new object loanService -> going into constructor
    
        // creating a list of books
        List<Book> booksToAdd = List.of( // 6. creating a list of books to add, Book data type
                new Book(1, "Harry Potter", "J. K. Rowling"), // 7. creating a new Book objects data type, and going into constructor for each book
                new Book(2, "A Game of Thrones", "George R. R. Martin"), // Java calls toString() method for every created Book object
                new Book(3, "The Hobbit or There and Back Again", "John Ronald Reuel Tolkien"),
                new Book(4, "Clean Code", "Robert C. Martin"),
                new Book(5, "History of a Rapper (Biography)", "Patryk Czeczot")
        );
        
        // adding books to library
        for (Book book : booksToAdd) { // 9. enhanced for loop to iterate every book in booksToAdd list in order to add them into books ArrayList
            libraryService.addBook(book);
        }
        
        // creating many users
        List<User> usersToAdd = List.of( // 11. creating a list of users to add, User data type
                new User(1, "Patryk Świetny Czeczot", "czeczotpatryk@gmail.com"), // 12. creating a new User objects data type, and going into constructor for each user
                new User(2, "Patrick The Notorious Czeczorri", "7patrick10@gmail.com") // Java calls toString() method for every created user
        );
        
        // registration of users
        for (User user : usersToAdd) { // 14. enhanced for loop to iterate every user in usersToAdd list in order to add them into users ArrayList
            libraryService.registerUser(user);
        }
        
        // 16. stepping into main loop of interactive menu
        boolean running = true;
        while (running) {
            System.out.println("\n📚 Welcome in our library! Choose the option below:");
            System.out.println("1. Display available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Show user's borrowed books");
            System.out.println("5. Quit");
            
            System.out.print("Choice: ");
            // int choice = scanner.nextInt(); // 17. making a choice -> ZABEZPIECZENIE TRY CATCH
            // scanner.nextLine(); // buffer cleaning
            
            int choice = getValidInt(scanner);
            
            switch (choice) {
                case 1 -> {
                    System.out.println("\n📖 Available books:");
                    // getAvailableBooks() is searching through list of books and returning only these with AVAILABLE status
                    List<Book> availableBooks = libraryService.getAvailableBooks(); // program gets available books from LibraryService class
                    // the result is saved with availableBooks variable
                    if (availableBooks.isEmpty()) { // checking if availableBooks list is empty, then print the display, if it isn't, display availableBooks
                        System.out.println("Currently every book is borrowed :(");
                    } else {
                        availableBooks.forEach(System.out::println); // display availableBooks
                        // forEach(System.out::println) means that for every book in availableBooks we calls System.out.println(book)
                        // println(book) automatically calls toString() method in Book class to format text
                        
//                        for (Book book : availableBooks) {
//                            System.out.println(book + " - [Book ID: " + book.getId() + "]");
//                        }
                    }
                }
                
                case 2 -> {
                    /*
                    System.out.print("\n🔹 Enter user ID: "); // PO ID CZY PO IMIENIU I NAZWISKU?
                    int userId = scanner.nextInt(); // stores user ID
                    scanner.nextLine();
                    
                    // ifPresentOrElse() -> if user or book is found, the code is executed, if not, we will get the display info
                    // CZY MOZNA PROSCIEJ?
                    libraryService.findUserById(userId).ifPresentOrElse(user -> { // calling findUserById(userId) (int id) method in class LibraryService
                        System.out.print("🔹 Enter book ID to borrow: ");
                        int bookId = scanner.nextInt(); // stores book ID
                        scanner.nextLine();
                        
                        libraryService.findBookById(bookId).ifPresentOrElse(book -> { // calling findBookById(bookId) (int id) method in class LibraryService
                            loanService.borrowBook(user, book); // if book is found by entered id, we call borrowBook(user, book) (User user, Book book) method in class LoanService
                        }, () -> System.out.println("❌ Book not found."));
                    }, () -> System.out.println("❌ User not found."));
                     */
                    
                    borrowBook(scanner, libraryService, loanService);
                }
                
                case 3 -> {
                    /*
                    System.out.print("\n🔹 Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    
                    libraryService.findUserById(userId).ifPresentOrElse(user -> {
                        System.out.print("🔹 Enter book ID to return: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();
                        
                        libraryService.findBookById(bookId).ifPresentOrElse(book -> {
                            loanService.returnBook(user, book);
                        }, () -> System.out.println("❌ Book not found."));
                    }, () -> System.out.println("❌ User not found."));
                     */
                    
                    returnBook(scanner, libraryService, loanService);
                }
                
                case 4 -> {
                    /*
                    System.out.print("\n🔹 Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    
                    libraryService.findUserById(userId).ifPresentOrElse(user -> {
                        System.out.println("📌 Books borrowed by " + user.getName() + ":");
                        loanService.listBorrowedBooks(user);
                    }, () -> System.out.println("❌ User not found."));
                     */
                    
                    showUserBorrowedBooks(scanner, libraryService, loanService);
                }
                
                case 5 -> {
                    System.out.println("\n📚 Thanks for using our library!");
                    running = false;
                }
                
                default -> System.out.println("\n❌ Invalid choice. Please enter valid option!");
            }
        }
        
        scanner.close();
    }
    
    private static int getValidInt(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number: ");
                scanner.nextLine();
            }
        }
    }
    
    private static void borrowBook(Scanner scanner, LibraryService libraryService, LoanService loanService) {
        System.out.print("\n🔹 Enter user ID: ");
        int userId = getValidInt(scanner);
        
        // ifPresentOrElse() -> if user or book is found, the code is executed, if not, we will get the display info
        // CZY MOZNA PROSCIEJ?
        libraryService.findUserById(userId).ifPresentOrElse(user -> { // calling findUserById(userId) (int id) method in class LibraryService
            System.out.print("🔹 Enter book ID to borrow: ");
            int bookId = getValidInt(scanner);
            
            libraryService.findBookById(bookId).ifPresentOrElse(book -> { // calling findBookById(bookId) (int id) method in class LibraryService
                loanService.borrowBook(user, book); // if book is found by entered id, we call borrowBook(user, book) (User user, Book book) method in class LoanService
            }, () -> System.out.println("❌ Book not found."));
        }, () -> System.out.println("❌ User not found."));
    }
    
    private static void returnBook(Scanner scanner, LibraryService libraryService, LoanService loanService) {
        System.out.print("\n🔹 Enter user ID: ");
        int userId = getValidInt(scanner);
        
        libraryService.findUserById(userId).ifPresentOrElse(user -> {
            System.out.print("🔹 Enter book ID to return: ");
            int bookId = getValidInt(scanner);
            
            libraryService.findBookById(bookId).ifPresentOrElse(book -> {
                loanService.returnBook(user, book);
            }, () -> System.out.println("❌ Book not found."));
        }, () -> System.out.println("❌ User not found."));
    }
    
    private static void showUserBorrowedBooks(Scanner scanner, LibraryService libraryService, LoanService loanService) {
        System.out.print("\n🔹 Enter user ID: ");
        int userId = getValidInt(scanner);
        
        libraryService.findUserById(userId).ifPresentOrElse(user -> {
            System.out.println("📌 Books borrowed by " + user.getName() + ":");
            loanService.listBorrowedBooks(user);
        }, () -> System.out.println("❌ User not found."));
    }
}