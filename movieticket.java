import java.util.*;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Theatre {
    private String name;
    private List<Integer> availableSeats;

    public Theatre(String name, int capacity) {
        this.name = name;
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= capacity; i++) {
            availableSeats.add(i);
        }
    }

    public String getName() {
        return name;
    }

    public List<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeat(int seatNumber) {
        if (availableSeats.contains(seatNumber)) {
            availableSeats.remove(Integer.valueOf(seatNumber));
            return true;
        }
        return false;
    }

    public void cancelSeat(int seatNumber) {
        availableSeats.add(seatNumber);
    }
}

class BookingSystem {
    public List<User> users;
    public List<Theatre> theatres;

    public BookingSystem() {
        users = new ArrayList<>();
        theatres = new ArrayList<>();
    }

    public void registerUser(String username, String password) {
        User user = new User(username, password);
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addTheatre(String name, int capacity) {
        Theatre theatre = new Theatre(name, capacity);
        theatres.add(theatre);
        System.out.println("Theatre added successfully.");
    }

    public void displayTheatres() {
        for (int i = 0; i < theatres.size(); i++) {
            Theatre theatre = theatres.get(i);
            System.out.println((i + 1) + ". " + theatre.getName() + " (Available Seats: " + theatre.getAvailableSeats().size() + ")");
        }
    }

    public void bookTicket(User user, Theatre theatre, int seatNumber) {
        if (user == null) {
            System.out.println("Invalid user. Please log in.");
            return;
        }

        if (theatre.bookSeat(seatNumber)) {
            System.out.println("Booking confirmed at " + theatre.getName() +
                    ", Seat " + seatNumber);
        } else {
            System.out.println("Seat " + seatNumber + " is not available.");
        }
    }

    public void cancelTicket(User user, Theatre theatre, int seatNumber) {
        if (user == null) {
            System.out.println("Invalid user. Please log in.");
            return;
        }

        theatre.cancelSeat(seatNumber);
        System.out.println("Booking canceled at " + theatre.getName() +
                ", Seat " + seatNumber);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingSystem bookingSystem = new BookingSystem();
        User currentUser = null;

        while (true) {
            System.out.println("Welcome to the Movie Ticket Booking System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Add Theatre");
            System.out.println("4. Display Theatres");
            System.out.println("5. Book Ticket");
            System.out.println("6. Cancel Ticket");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                bookingSystem.registerUser(username, password);
            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                currentUser = bookingSystem.login(username, password);
                if (currentUser == null) {
                    System.out.println("Invalid username or password.");
                } else {
                    System.out.println("Login successful.");
                }
            } else if (choice == 3) {
                System.out.print("Enter theatre name: ");
                String name = scanner.nextLine();
                System.out.print("Enter theatre capacity: ");
                int capacity = scanner.nextInt();
                scanner.nextLine();
                bookingSystem.addTheatre(name, capacity);
            } else if (choice == 4) {
                bookingSystem.displayTheatres();
            } else if (choice == 5) {
                bookingSystem.displayTheatres();
                System.out.print("Enter the number of the theatre you want to book: ");
                int theatreChoice = scanner.nextInt() - 1;
                scanner.nextLine();
                if (theatreChoice >= 0 && theatreChoice < bookingSystem.theatres.size()) {
                    Theatre selectedTheatre = bookingSystem.theatres.get(theatreChoice);
                    System.out.print("Enter the seat number you want to book: ");
                    int seatNumber = scanner.nextInt();
                    scanner.nextLine();
                    bookingSystem.bookTicket(currentUser, selectedTheatre, seatNumber);
                } else {
                    System.out.println("Invalid theatre selection.");
                }
            } else if (choice == 6) {
                bookingSystem.displayTheatres();
                System.out.print("Enter the number of the theatre for ticket cancellation: ");
                int theatreChoice = scanner.nextInt() - 1;
                scanner.nextLine();
                if (theatreChoice >= 0 && theatreChoice < bookingSystem.theatres.size()) {
                    Theatre selectedTheatre = bookingSystem.theatres.get(theatreChoice);
                    System.out.print("Enter the seat number for ticket cancellation: ");
                    int seatNumber = scanner.nextInt();
                    scanner.nextLine();
                    bookingSystem.cancelTicket(currentUser, selectedTheatre, seatNumber);
                } else {
                    System.out.println("Invalid theatre selection.");
                }
            } else if (choice == 7) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}