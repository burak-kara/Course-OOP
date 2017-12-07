package Model;

public class Beverage extends Product {

	public Beverage(String name, double sellingPrice , double purchasePrice) {

		super(name, sellingPrice, purchasePrice, 0);

	}
	
	public double calculateExpense(){
		
		return this.getPurchasePrice();
		
	}
	
}
