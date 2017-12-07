package Model;

import java.util.ArrayList;

public class Order {

	private ArrayList<Product> products = new ArrayList<Product>();

	public Order() {

	}

	public void addProduct(Product newProduct) {

		this.products.add(newProduct);

	}

	public void listOrder() {

		if (this.products.size() != 0) {

			for (Product product : this.products) {

				System.out.println(product.getName() + " : "+product.getSellingPrice());

			}
		} else
			
			System.out.println("You have not ordered anything yet!");

	}

	public ArrayList<Product> getOrderedProduct() {

		return this.products;

	}

	public double calculateTotalPrice() {

		double price = 0;
		
		for(Product product : this.products){
			
			price += product.getSellingPrice();
			
		}
		
		return price;

	}

}
