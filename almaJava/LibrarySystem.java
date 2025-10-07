import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;

class BorrowRecord {
    String bookTitle;
    String borrowerName;
    String dateBorrowed;

    BorrowRecord(String bookTitle, String borrowerName, String dateBorrowed) {
        this.bookTitle = bookTitle;
        this.borrowerName = borrowerName;
        this.dateBorrowed = dateBorrowed;
    }

    @Override
    public String toString() {
        return "Book Title: " + bookTitle +
               " | Borrower: " + borrowerName +
               " | Date Borrowed: " + dateBorrowed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowRecord)) return false;
        BorrowRecord that = (BorrowRecord) o;
        return bookTitle.equalsIgnoreCase(that.bookTitle) &&
               borrowerName.equalsIgnoreCase(that.borrowerName) &&
               dateBorrowed.equalsIgnoreCase(that.dateBorrowed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle.toLowerCase(),
                            borrowerName.toLowerCase(),
                            dateBorrowed.toLowerCase());
    }
}

public class LibrarySystem {
    private static List<BorrowRecord> currentList;
    private static ArrayList<BorrowRecord> arrayList = new ArrayList<>();
    private static LinkedList<BorrowRecord> linkedList = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);
    private static String currentImplementation;

    public static void main(String[] args) {
        selectImplementation();
        
        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: addRecord(); break;
                case 2: removeRecord(); break;
                case 3: displayRecords(); break;
                case 4: changeImplementation(); break;
                case 5: 
                    System.out.println("Exiting program..."); 
                    break;
                default: System.out.println("Invalid choice!");
            }

            if (choice != 5) {
                System.out.println("\nPress ENTER to return to the menu...");
                sc.nextLine(); // wait for Enter key
            }
        } while (choice != 5);
    }

    private static void selectImplementation() {
        System.out.println("=== Library System Implementation Selection ===");
        System.out.println("1. ArrayList");
        System.out.println("2. LinkedList");
        System.out.print("Select list implementation (1 or 2): ");
        
        int choice = Integer.parseInt(sc.nextLine());
        
        switch (choice) {
            case 1:
                currentList = arrayList;
                currentImplementation = "ArrayList";
                System.out.println("\nUsing ArrayList implementation");
                break;
            case 2:
                currentList = linkedList;
                currentImplementation = "LinkedList";
                System.out.println("\nUsing LinkedList implementation");
                break;
            default:
                System.out.println("Invalid choice! Defaulting to ArrayList");
                currentList = arrayList;
                currentImplementation = "ArrayList";
        }
        
        System.out.println("Press ENTER to continue to main menu...");
        sc.nextLine();
    }

    private static void showMenu() {
        System.out.println("\n--- Library Borrowing System (" + currentImplementation + ") ---");
        System.out.println("1. Add a borrowing record");
        System.out.println("2. Remove a borrowing record");
        System.out.println("3. Display all borrowing records");
        System.out.println("4. Change List Implementation");
        System.out.println("5. Exit");
    }

    private static void addRecord() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter borrower name: ");
        String borrower = sc.nextLine();
        System.out.print("Enter date borrowed: ");
        String date = sc.nextLine();

        BorrowRecord record = new BorrowRecord(title, borrower, date);
        
        // Add to both lists to keep them synchronized
        arrayList.add(record);
        linkedList.add(record);
        
        System.out.println("\nRecord added successfully to both ArrayList and LinkedList!");
    }

    private static void removeRecord() {
        System.out.print("Enter borrower name or book title to remove: ");
        String keyword = sc.nextLine();

        // Remove from both lists to keep them synchronized
        boolean removedFromArrayList = arrayList.removeIf(r ->
                r.bookTitle.equalsIgnoreCase(keyword) ||
                r.borrowerName.equalsIgnoreCase(keyword));
                
        boolean removedFromLinkedList = linkedList.removeIf(r ->
                r.bookTitle.equalsIgnoreCase(keyword) ||
                r.borrowerName.equalsIgnoreCase(keyword));

        if (removedFromArrayList || removedFromLinkedList) {
            System.out.println("\nRecord removed successfully from both lists!");
        } else {
            System.out.println("\nNo record found with that name or title.");
        }
    }

    private static void displayRecords() {
        System.out.println("\n=== Display Borrowing Records ===");
        System.out.println("1. Display ArrayList Records");
        System.out.println("2. Display LinkedList Records");
        System.out.println("3. Display Both Lists");
        System.out.print("Select display option (1, 2, or 3): ");
        
        int choice = Integer.parseInt(sc.nextLine());
        
        switch (choice) {
            case 1:
                displayArrayListRecords();
                break;
            case 2:
                displayLinkedListRecords();
                break;
            case 3:
                displayBothLists();
                break;
            default:
                System.out.println("Invalid choice! Displaying current implementation: " + currentImplementation);
                displayCurrentList();
        }
    }

    private static void displayArrayListRecords() {
        if (arrayList.isEmpty()) {
            System.out.println("\n--- ArrayList Records ---");
            System.out.println("No records found in ArrayList.");
            return;
        }

        System.out.println("\n--- ArrayList Records ---");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("Index " + i + ": " + arrayList.get(i));
        }
        System.out.println("Total records in ArrayList: " + arrayList.size());
    }

    private static void displayLinkedListRecords() {
        if (linkedList.isEmpty()) {
            System.out.println("\n--- LinkedList Records ---");
            System.out.println("No records found in LinkedList.");
            return;
        }

        System.out.println("\n--- LinkedList Records ---");
        int index = 0;
        for (BorrowRecord record : linkedList) {
            System.out.println("Position " + index + ": " + record);
            index++;
        }
        System.out.println("Total records in LinkedList: " + linkedList.size());
    }

    private static void displayBothLists() {
        System.out.println("\n=== Comparison of Both Lists ===");
        
        // Display ArrayList records
        System.out.println("\n--- ArrayList Records (" + arrayList.size() + " records) ---");
        if (arrayList.isEmpty()) {
            System.out.println("No records in ArrayList");
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println("ArrayList Index " + i + ": " + arrayList.get(i));
            }
        }
        
        // Display LinkedList records
        System.out.println("\n--- LinkedList Records (" + linkedList.size() + " records) ---");
        if (linkedList.isEmpty()) {
            System.out.println("No records in LinkedList");
        } else {
            int index = 0;
            for (BorrowRecord record : linkedList) {
                System.out.println("LinkedList Position " + index + ": " + record);
                index++;
            }
        }
        
        // Verify both lists have the same content
        System.out.println("\n--- List Comparison ---");
        System.out.println("Both lists contain same records: " + (arrayList.equals(linkedList) ? "YES" : "NO"));
        System.out.println("ArrayList size: " + arrayList.size());
        System.out.println("LinkedList size: " + linkedList.size());
    }

    private static void displayCurrentList() {
        if (currentList.isEmpty()) {
            System.out.println("\n--- " + currentImplementation + " Records ---");
            System.out.println("No records found.");
            return;
        }

        System.out.println("\n--- " + currentImplementation + " Records ---");
        if (currentImplementation.equals("ArrayList")) {
            for (int i = 0; i < currentList.size(); i++) {
                System.out.println("Index " + i + ": " + currentList.get(i));
            }
        } else {
            int index = 0;
            for (BorrowRecord record : currentList) {
                System.out.println("Position " + index + ": " + record);
                index++;
            }
        }
        System.out.println("Total records in " + currentImplementation + ": " + currentList.size());
    }

    private static void changeImplementation() {
        System.out.println("\n=== Change List Implementation ===");
        System.out.println("Current implementation: " + currentImplementation);
        System.out.println("1. Switch to ArrayList");
        System.out.println("2. Switch to LinkedList");
        System.out.print("Select new implementation (1 or 2): ");
        
        int choice = Integer.parseInt(sc.nextLine());
        
        String previousImplementation = currentImplementation;
        
        switch (choice) {
            case 1:
                currentList = arrayList;
                currentImplementation = "ArrayList";
                System.out.println("\nSwitched to ArrayList implementation");
                break;
            case 2:
                currentList = linkedList;
                currentImplementation = "LinkedList";
                System.out.println("\nSwitched to LinkedList implementation");
                break;
            default:
                System.out.println("Invalid choice! Keeping current implementation: " + currentImplementation);
                return;
        }
        
        if (!previousImplementation.equals(currentImplementation)) {
            System.out.println("Successfully changed from " + previousImplementation + " to " + currentImplementation);
        }
        
        // Display current record count for both lists
        System.out.println("Records in ArrayList: " + arrayList.size());
        System.out.println("Records in LinkedList: " + linkedList.size());
    }
}