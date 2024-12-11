import java.util.*;

class Destination {
    private String name;
    private String startDate;
    private String endDate;
    private String preferences;
    private double dailyBudget;

    public Destination(String name, String startDate, String endDate, String preferences, double dailyBudget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.preferences = preferences;
        this.dailyBudget = dailyBudget;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPreferences() {
        return preferences;
    }

    public double getDailyBudget() {
        return dailyBudget;
    }

    @Override
    public String toString() {
        return "Destination: " + name + "\n" +
                "Start Date: " + startDate + "\n" +
                "End Date: " + endDate + "\n" +
                "Preferences: " + preferences + "\n" +
                "Daily Budget: $" + dailyBudget + "\n";
    }
}

public class codealpha_task2 {
    private List<Destination> destinations;
    private double totalBudget;

    public codealpha_task2() {
        destinations = new ArrayList<>();
        totalBudget = 0.0;
    }

    public void addDestination(String name, String startDate, String endDate, String preferences, double dailyBudget) {
        Destination destination = new Destination(name, startDate, endDate, preferences, dailyBudget);
        destinations.add(destination);
        totalBudget += dailyBudget;
    }

    public void displayItinerary() {
        if (destinations.isEmpty()) {
            System.out.println("No destinations added yet!");
            return;
        }

        System.out.println("\n--- Travel Itinerary ---");
        for (Destination destination : destinations) {
            System.out.println(destination);
        }
        System.out.println("Total Budget: $" + totalBudget);
    }

    public static void main(String[] args) {
        codealpha_task2 planner = new codealpha_task2();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Travel Itinerary Planner ---");
            System.out.println("1. Add Destination");
            System.out.println("2. View Itinerary");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter destination name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter start date (yyyy-MM-dd): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Enter end date (yyyy-MM-dd): ");
                    String endDate = scanner.nextLine();
                    System.out.print("Enter preferences (e.g., sightseeing, beach, etc.): ");
                    String preferences = scanner.nextLine();
                    System.out.print("Enter daily budget: ");
                    double dailyBudget = Double.parseDouble(scanner.nextLine());

                    planner.addDestination(name, startDate, endDate, preferences, dailyBudget);
                    break;
                case 2:
                    planner.displayItinerary();
                    break;
                case 3:
                    System.out.println("Thank you for using the Travel Itinerary Planner!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
