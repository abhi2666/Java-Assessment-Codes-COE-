package osm.ShoppingSystem;

import java.util.ArrayList;
import java.util.List;

//cart class to add a product into cart
public class Cart {
 private List<Product> products;

 public Cart() {
     products = new ArrayList<>();
 }

 public void addProduct(Product product) {
     products.add(product);
 }

 public void removeProduct(Product product) {
     products.remove(product);
 }

 public double calculateTotalCost() {
     double total = 0;
     for (Product product : products) {
         total += product.getPrice();
     }
     return total;
 }

//  Filter products based on price and returns a list of such products 
 public List<Product> filterProductsAbovePrice(double price) {
	 System.out.println("products with price >" + price);
     return products.stream()
                    .filter(product -> product.getPrice() > price)
                    .toList();
 }

 public List<Product> getProducts() {
     return products;
 }
}

// generic class for classItem
class CartItem<T> {
 private T item;
 private int quantity;

 public CartItem(T item, int quantity) {
     this.item = item;
     this.quantity = quantity;
 }

 public T getItem() {
     return item;
 }

 public int getQuantity() {
     return quantity;
 }

 @Override
 public String toString() {
     return "CartItem{" +
             "item=" + item +
             ", quantity=" + quantity +
             '}';
 }
}
