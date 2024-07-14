package osm.ShoppingSystem;

import junit.framework.TestCase;

public class CartTest extends TestCase {
	
	private Product pd = new Product(01, "Shampoo", 69.69, 420);
	private Product pd2 = new Product(02, "Chocolate", 120, 240);
	Cart c = new Cart();
	public void testAddProduct () {
		c.addProduct(pd);
		// checking if the product is actually added into the list
		assertTrue(c.getProducts().contains(pd)); 
		c.addProduct(pd2);
		assertTrue(c.getProducts().contains(pd2));
		System.out.println("Tests #1 passed");
	}
	
	public void testRemoveProduct() {
		// check if removing of a product is working or not
		c.addProduct(pd);
//		System.out.println("product added !!");
		c.removeProduct(pd);
		assertFalse(c.getProducts().contains(pd)); // should return false 
		System.out.println("Tests #2 passed");
	}
	
	public void testTotalCost() {
		c.addProduct(pd);
		c.addProduct(pd2);
		double actualValue = 120 + 69.69;
		assertEquals(actualValue, c.calculateTotalCost()); // both values should match
		System.out.println("Test #3 passed");
	}
}
