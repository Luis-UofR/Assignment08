package orozco;
import java.io.*;
import java.util.*;

public class TextbookStore {
    private static final String FILE_NAME = "inventory.dat"; // File to save inventory data
    private Map<Integer, Textbook> inventory; // Using a map to store textbooks by their SKU

    // Constructor to initialize the inventory
    public TextbookStore() {
        inventory = new HashMap<>();
        loadInventory(); // Load existing data from file if available
    }

    // Main method to run the program
    public static void main(String[] args) {
        TextbookStore store = new TextbookStore(); // Create a store instance
        store.displayMenu(); // Show menu to the user
    }

    // Method to display menu options
    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Loop to keep showing menu until the user decides to exit
        do {
            System.out.println("\n--- Campus Textbook Store ---");
            System.out.println("1. Add a textbook");
            System.out.println("2. Remove a textbook");
            System.out.println("3. Display a textbook");
            System.out.println("4. Display all inventory");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Getting user's choice
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character

            switch (choice) {
                case 1 -> addTextbook(scanner);
                case 2 -> removeTextbook(scanner);
                case 3 -> displayTextbook(scanner);
                case 4 -> displayInventory();
                case 5 -> {
                    saveInventory(); // Save the inventory before exiting
                    System.out.println("Thank you for using the Textbook Store!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    // Method to add a new textbook to the inventory
    public void addTextbook(Scanner scanner) {
        try {
            System.out.print("Enter SKU (unique integer): ");
            int sku = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Check if the SKU is already in use
            if (inventory.containsKey(sku)) {
                System.out.println("Error: A textbook with this SKU already exists.");
                return;
            }

            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter price (must be positive): ");
            double price = scanner.nextDouble();

            if (price <= 0) {
                System.out.println("Error: Price must be greater than 0.");
                return;
            }

            System.out.print("Enter quantity (0 or more): ");
            int quantity = scanner.nextInt();

            if (quantity < 0) {
                System.out.println("Error: Quantity cannot be negative.");
                return;
            }

            // Create a new textbook and add it to the inventory
            Textbook textbook = new Textbook(sku, title, price, quantity);
            inventory.put(sku, textbook);
            System.out.println("Textbook added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please try again.");
            scanner.nextLine(); // Clear bad input
        }
    }

    // Method to remove a textbook by SKU
    public void removeTextbook(Scanner scanner) {
        System.out.print("Enter SKU to remove: ");
        int sku = scanner.nextInt();
        if (inventory.remove(sku) != null) {
            System.out.println("Textbook removed successfully.");
        } else {
            System.out.println("Error: No textbook found with this SKU.");
        }
    }

    // Method to display details of a single textbook by SKU
    public void displayTextbook(Scanner scanner) {
        System.out.print("Enter SKU to display: ");
        int sku = scanner.nextInt();
        Textbook textbook = inventory.get(sku);
        if (textbook != null) {
            System.out.println(textbook);
        } else {
            System.out.println("Error: No textbook found with this SKU.");
        }
    }

    // Method to display all textbooks in inventory
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("The inventory is empty.");
        } else {
            System.out.println("\n--- Inventory ---");
            for (Textbook textbook : inventory.values()) {
                System.out.println(textbook);
            }
        }
    }

    // Save the inventory to a file using serialization
    private void saveInventory() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Load the inventory from a file if it exists
    private void loadInventory() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            inventory = (Map<Integer, Textbook>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous inventory found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading inventory.");
        }
    }
}
