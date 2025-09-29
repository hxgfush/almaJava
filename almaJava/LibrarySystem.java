import java.util.ArrayList;
import java.util.LinkedList;
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

    // ✅ Proper equality check so contains() and remove() work correctly
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
    private static ArrayList<BorrowRecord> arrayList = new ArrayList<>();
    private static LinkedList<BorrowRecord> linkedList = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: addRecord(); break;
                case 2: removeRecord(); break;
                case 3: displayRecords(); break;
                case 4: compareLists(); break;
                case 5: System.out.println("Exiting program..."); break;
                default: System.out.println("Invalid choice!");
            }

            if (choice != 5) {
                System.out.println("\nPress ENTER to return to the menu...");
                sc.nextLine(); // wait for Enter key
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n--- Library Borrowing System ---");
        System.out.println("1. Add a borrowing record");
        System.out.println("2. Remove a borrowing record");
        System.out.println("3. Display all borrowing records");
        System.out.println("4. Compare ArrayList vs LinkedList");
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
        arrayList.add(record);
        linkedList.add(record);

        System.out.println("\nRecord added successfully!");
    }

    private static void removeRecord() {
        System.out.print("Enter borrower name or book title to remove: ");
        String keyword = sc.nextLine();

        boolean removedFromArray = arrayList.removeIf(r ->
                r.bookTitle.equalsIgnoreCase(keyword) ||
                r.borrowerName.equalsIgnoreCase(keyword));

        boolean removedFromLinked = linkedList.removeIf(r ->
                r.bookTitle.equalsIgnoreCase(keyword) ||
                r.borrowerName.equalsIgnoreCase(keyword));

        if (removedFromArray || removedFromLinked) {
            System.out.println("\nRecord removed successfully!");
        } else {
            System.out.println("\nNo record found with that name or title.");
        }
    }

    private static void displayRecords() {
        if (arrayList.isEmpty()) {
            System.out.println("\nNo records found.");
            return;
        }

        System.out.println("\n--- Borrowing Records ---");
        for (BorrowRecord r : arrayList) {
            System.out.println(r);
        }
    }

    private static void compareLists() {
        long start, end;

        BorrowRecord tempRecord = new BorrowRecord("Temp Book", "Temp Borrower", "Today");

        // ArrayList add timing
        start = System.nanoTime();
        arrayList.add(tempRecord);
        end = System.nanoTime();
        long arrayAdd = end - start;

        // LinkedList add timing
        start = System.nanoTime();
        linkedList.add(tempRecord);
        end = System.nanoTime();
        long linkedAdd = end - start;

        // ArrayList search timing
        start = System.nanoTime();
        arrayList.contains(tempRecord);
        end = System.nanoTime();
        long arraySearch = end - start;

        // LinkedList search timing
        start = System.nanoTime();
        linkedList.contains(tempRecord);
        end = System.nanoTime();
        long linkedSearch = end - start;

        // ArrayList remove timing
        start = System.nanoTime();
        arrayList.remove(tempRecord);
        end = System.nanoTime();
        long arrayRemove = end - start;

        // LinkedList remove timing
        start = System.nanoTime();
        linkedList.remove(tempRecord);
        end = System.nanoTime();
        long linkedRemove = end - start;

        System.out.println("\n--- Performance Comparison ---");
        System.out.println("ArrayList add time: " + arrayAdd + " ns");
        System.out.println("LinkedList add time: " + linkedAdd + " ns");
        System.out.println("ArrayList search time: " + arraySearch + " ns");
        System.out.println("LinkedList search time: " + linkedSearch + " ns");
        System.out.println("ArrayList remove time: " + arrayRemove + " ns");
        System.out.println("LinkedList remove time: " + linkedRemove + " ns");
    }
}
