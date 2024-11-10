package orozco;
import java.io.Serializable;

// This class represents a textbook in the inventory
public class Textbook implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributes for each textbook
	private int sku; // Stock Keeping Unit (unique identifier)
	private String title; // Title of the book
	private double price; // Price of the book
	private int quantity; // Quantity in stock

	// Constructor to initialize a textbook
	public Textbook(int sku, String title, double price, int quantity) {
		this.sku = sku;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}

	// Getters to access the private attributes
	public int getSku() {
		return sku;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	// String representation of the textbook (for easy display)
	@Override
	public String toString() {
		return "SKU: " + sku + ", Title: " + title + ", Price: $" + price + ", Quantity: " + quantity;
	}
}
