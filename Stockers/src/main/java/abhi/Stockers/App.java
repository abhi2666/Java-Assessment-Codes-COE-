package abhi.Stockers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Functionalities required - 
 * Display the companies stock prices in ascending order
2. Display the companies stock prices in descending order
3. Display the total no of companies for which stock prices rose today
4. Display the total no of companies for which stock prices declined today
 *
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	int choice = 0;
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	Stockers st = new Stockers();
    	st.starter();
    	while(true) {
    		System.out.println("\n\n");
    		System.out.println("1. Display the companies stock prices in ascending order");
    		System.out.println("2. Display the companies stock prices in descending order");
    		System.out.println("3. Display the total no of companies for which stock prices rose today");
    		System.out.println("4. Display the total no of companies for which stock prices declined today");
    		System.out.println("5. Search a specific stock price");
    		System.out.println("6. press 0 to exit");
    		System.out.println("Enter the operation that you want to perform-->");
    		choice = sc.nextInt();
    		switch (choice) {
			case 1:
				st.getStocksAsc();
				break;
			case 2:
				st.getStockDes();
				break;
			case 3:
				st.totalWinnerStocks();
				break;
			case 4:
				st.totalLoserStocks();
			case 5:
				System.out.println("Enter stock price to be checked -->");
				Double value = sc.nextDouble();
				boolean res = st.getStock(value);
//				System.out.println(res);
				if(res==true) {
					System.out.println("Stock is present !!");
				}
				else {
					System.out.println("stock is not present !!");
				}
				break;
			case 6:
				System.out.println("Exiting the program !!");
				break;
			default:
				System.out.println("Invalid choice please select right choice");
				break;
			}
    		if(choice == 0) break;
    	}
//    	st.getAllStocks();
    	
    }
}
// collections does not support primitive data types..you will have to use there wrapper class

class Stockers {
	// will hold all stocks in a treemap to maintain the values 
	// treemap keeps values in sorted order
	TreeMap<Double, Boolean> stocks = null;
	Scanner sc = null;
	
	Stockers () {
		sc = new Scanner(System.in);
		stocks = new TreeMap<>(); 
	}
	
	// intialize the stocks map
	public void starter() {
    	System.out.println("Enter no. of companies - ");
    	int totalCompanies = sc.nextInt();
    	//take data of all the companies
    	Double stkPrice =  0.0;
    	Boolean b = false;
    	
    	for(int i = 0; i < totalCompanies; i++) {
    		System.out.println("Enter stock price and bool value for "+ (i + 1) + " stock -->");
    		stkPrice = sc.nextDouble();
    		b = sc.nextBoolean();
    		stocks.put(stkPrice, b);
    	}
	}
	
	public void getStocksAsc() {
		System.out.println("Stock price in ascending order are - ");
		for(Map.Entry<Double, Boolean> entry : stocks.entrySet()) {
			System.out.println("Stock price : " + entry.getKey() + " stock bool : " + entry.getValue());
		}
	}
	
	public void getStockDes() {
		System.out.println("Stock price in descending order are - ");
		for(Map.Entry<Double, Boolean> entry : stocks.descendingMap().entrySet()) {
			System.out.println("Stock price : " + entry.getKey() + " stock bool : " + entry.getValue());
		}
	}
	
	public void totalWinnerStocks() {
		List<Double> winnerStocks = new ArrayList<>();
		for(Map.Entry<Double, Boolean> entry : stocks.entrySet()) {
			if(entry.getValue()==true) {
				//this is a winner stock
				winnerStocks.add(entry.getKey());
			}
		}
		System.out.println("Total winning stocks are -->");
		for(Double data : winnerStocks) {
			System.out.println(data);
		}
	}
	
	public void totalLoserStocks() {
		List<Double> loserStocks = new ArrayList<>();
		for(Map.Entry<Double, Boolean> entry : stocks.entrySet()) {
			if(entry.getValue()==false) {
				//this is a winner stock
				loserStocks.add(entry.getKey());
			}
		}
		System.out.println("Total loser stocks are -->");
		for(Double data : loserStocks) {
			System.out.println(data);
		}
	}
	
	public boolean getStock(Double stockPrice) {
		/// using the containskey method of treemap to check if stockPrice is present or not
		return stocks.containsKey(stockPrice);
	}
	
	public void getAllStocks() {
		//print all the stocks stored in tree map
		System.out.println("Available stocks are -->");
		for(Map.Entry<Double, Boolean> entry : stocks.entrySet()) {
			System.out.println("stock value : " +entry.getKey() + " stock bool : " + entry.getValue());
		}
	}
}
