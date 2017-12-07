package Model;

public abstract class Product implements Expense {
	
	private String name;
	private double purchasePrice;
	private double sellingPrice;
	private double utilityCost;
	
	public Product(String name, double sellingPrice, double purchasePrice, double utilityCost){
		
		this.name 		   = name;
		this.purchasePrice = purchasePrice;
		this.sellingPrice  = sellingPrice;
		this.utilityCost   = utilityCost;
		
	}
	
	public Product(String name){
		
		this.name = name;
		
	}
	
	public String getName(){
		
		return this.name;
		
	}
	
	public void setName(String name){
		
		this.name = name;
		
	}
	
	public double getPurchasePrice(){
		
		return this.purchasePrice;
		
	}
	
	public double getSellingPrice(){
		
		return this.sellingPrice;
		
	}
	
	public void setSellingPrice(double sellingPrice){
		
		this.sellingPrice = sellingPrice;
		
	}
	
	public double getUtilityCost(){
		
		return this.utilityCost;
		
	}
	
	public String toString(){
		
		return this.name;
	}
	
}
