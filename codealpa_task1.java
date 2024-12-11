import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Room class
class Room {
    private int roomNumber;
    private String roomType; // Single, Double, Suite
    private double price;
    private boolean isAvailable;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// Hotel class
class Hotel {
    private List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchRooms(String roomType) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}

// Booking class
class Booking {
    private Room room;
    private String userName;
    private Date checkInDate;
    private Date checkOutDate;

    public Booking(Room room, String userName, Date checkInDate, Date checkOutDate) {
        this.room = room;
        this.userName = userName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "roomNumber=" + room.getRoomNumber() +
                ", roomType='" + room.getRoomType() + '\'' +
                ", userName='" + userName + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}

// PaymentProcessor class
class PaymentProcessor {
    public boolean processPayment(String userName, double amount) {
        // Simulate payment processing
        System.out.println("Processing payment of $" + amount + " for " + userName);
        return true; // Assume payment is successful
    }
}

// Main Hotel Reservation System
public class codealpa_task1 {
    private Hotel hotel;
    private List<Booking> bookings;
    private PaymentProcessor paymentProcessor;

    public codealpa_task1() {
        hotel = new Hotel();
        bookings = new ArrayList<>();
        paymentProcessor = new PaymentProcessor();
    }

    public void initializeRooms() {
        hotel.addRoom(new Room(101, "Single", 50.0));
        hotel.addRoom(new Room(102, "Double", 80.0));
        hotel.addRoom(new Room(103, "Suite", 120.0));
        hotel.addRoom(new Room(104, "Single", 55.0));
    }

    public void searchAndBookRoom(Scanner scanner) {
        System.out.print("Enter room type (Single/Double/Suite): ");
        String roomType = scanner.nextLine();

        List<Room> availableRooms = hotel.searchRooms(roomType);
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available for type: " + roomType);
            return;
        }

        System.out.println("Available rooms:");
        for (Room room : availableRooms) {
            System.out.println("Room Number: " + room.getRoomNumber() + ", Price: $" + room.getPrice());
        }

        System.out.print("Enter room number to book: ");
        int roomNumber = Integer.parseInt(scanner.nextLine());

        Room room = null;
        for (Room r : availableRooms) {
            if (r.getRoomNumber() == roomNumber) {
                room = r;
                break;
            }
        }

        if (room == null) {
            System.out.println("Invalid room number.");
            return;
        }

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter check-in date (yyyy-MM-dd): ");
        Date checkInDate = parseDate(scanner.nextLine());

        System.out.print("Enter check-out date (yyyy-MM-dd): ");
        Date checkOutDate = parseDate(scanner.nextLine());

        if (paymentProcessor.processPayment(userName, room.getPrice())) {
            room.setAvailable(false);
            Booking booking = new Booking(room, userName, checkInDate, checkOutDate);
            bookings.add(booking);
            System.out.println("Booking successful! " + booking);
        } else {
            System.out.println("Payment failed! Booking not completed.");
        }
    }

    public void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    public static void main(String[] args) {
        codealpa_task1 system = new codealpa_task1();
        system.initializeRooms();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. Search and Book Room");
            System.out.println("2. View Bookings");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    system.searchAndBookRoom(scanner);
                    break;
                case 2:
                    system.viewBookings();
                    break;
                case 3:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
