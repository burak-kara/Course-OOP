package Model;

import java.util.ArrayList;

public class MenuProduct extends Product {

	private ArrayList<Product> products = new ArrayList<Product>();
	private final double MAIN_DISH = 0.9;
	private final double DESSERT   = 0.8;
	private final double BEVERAGE  = 0.5;
	
 	public MenuProduct(String name, ArrayList<Product> products) {

		super(name);
		this.products = products;
		this.setSellingPrice(calculateSellingPrice());

	}

	public double calculateExpense() {

		double expense = 0;

		for (Product product : this.products) {

			expense += (double) product.getPurchasePrice() + (double) product.getUtilityCost();

		}

		return expense;

	}

	private double calculateSellingPrice() {

		double price = 0;

		for (Product product : this.products) {

			if (product instanceof MainDish) {

				price += (double) product.getSellingPrice() * MAIN_DISH;
			
			} else if(product instanceof Dessert){
				
				price += (double) product.getSellingPrice() * DESSERT; 
				
			} else if(product instanceof Beverage){
				
				price += (double) product.getSellingPrice() * BEVERAGE;
				
			}
		}
		
		return price;

	}

}
