package tester;

import java.util.Scanner;

import orozco.TextbookStore;

public class TextbookStoreTest {
	public static void main(String[] args) {
		TextbookStore store = new TextbookStore();
		Scanner scanner = new Scanner(System.in);

		// Test Case 1: Adding textbooks
		System.out.println("\n[Test Case 1] Adding textbooks...");
		store.addTextbook(scanner);

		// Test Case 2: Removing a textbook
		System.out.println("\n[Test Case 2] Removing a textbook...");
		store.removeTextbook(scanner);

		// Test Case 3: Displaying a textbook by SKU
		System.out.println("\n[Test Case 3] Displaying a textbook...");
		store.displayTextbook(scanner);

		// Test Case 4: Displaying all inventory
		System.out.println("\n[Test Case 4] Displaying all inventory...");
		store.displayInventory();

		// Cleanup
		scanner.close();
	}
}
