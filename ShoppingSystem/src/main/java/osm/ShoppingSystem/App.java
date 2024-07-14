package osm.ShoppingSystem;
import java.util.ArrayList;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// creating products 
        Product p1 = new Product(1, "PS5", 45550, 400);
        Product p2 = new Product(2, "XBox", 42500, 268);
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        // creating a cart object to put products into cart
        Cart ct = new Cart();
        ct.addProduct(p1);
        ct.addProduct(p2);
        //
        List<Product> pord = ct.filterProductsAbovePrice(43000); // only ps5 will be shows
        System.out.println("products with price more that 43000 are - ");
        for(Product pd : pord) {
        	System.out.println("product name : " + pd.getName());
        }
        //getting total cost
        System.out.println("Total cost of all products : "+ ct.calculateTotalCost());
    }
}

class Product {
    private int id;
    private String name;
    private double price;
    private int quantityInStock;

    public Product(int id, String name, double price, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                '}';
    }
}


