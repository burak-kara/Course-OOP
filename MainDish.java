package Model;

public class MainDish extends Product {

	public MainDish(String name, double sellingPrice, double purchasePrice, double utilityCost) {

		super(name, sellingPrice, purchasePrice, utilityCost);

	}

	public double calculateExpense(){
		
		return this.getPurchasePrice() + this.getUtilityCost();
		
	} 
	
	
	
}
