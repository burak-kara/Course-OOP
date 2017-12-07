package Model;

import java.util.*;

public class Restaurant {

	private ArrayList<Employee> employees = new ArrayList<>();
	private ArrayList<Product> products = new ArrayList<>();
	private static int id = 0;

	Random rgen = new Random();

	public Restaurant() {

		initEmployees();
		initProducts();

	}

	private void initEmployees() {

		addCook("Monica", 100);

		addWaiter("Ross");
		addWaiter("Phobe");
		addWaiter("Rachel");
		
	}

	private void initProducts() {

		// Parameters for Product Constructor:
		// Product Name, Selling Price, Purchase Price and Utility Cost

		products.add(new MainDish("Pizza", 6, 2, 2));
		products.add(new MainDish("Burger", 5, 1.5, 2));

		products.add(new Beverage("Coke", 2, 0.5));
		products.add(new Beverage("Lemonade", 2, 0.3));

		products.add(new Dessert("Tiramusu", 4, 1, 1));
		products.add(new Dessert("Cake", 3, 0.5, 1));
		products.add(new Dessert("Ice Cream", 3, 0.5, 0.5));

		ArrayList<Product> HGproducts = new ArrayList<>();
		HGproducts.add(new MainDish("Pizza", 6, 2, 2));
		HGproducts.add(new Beverage("Coke", 2, 0.5));
		HGproducts.add(new Dessert("Tiramusu", 4, 1, 1));
		products.add(new MenuProduct("Hunger Games Menu", HGproducts));

		ArrayList<Product> Kidsproducts = new ArrayList<>();
		Kidsproducts.add(new MainDish("Burger", 5, 1.5, 2));
		Kidsproducts.add(new Beverage("Lemonade", 2, 0.3));
		Kidsproducts.add(new Dessert("Ice Cream", 3, 0.5, 0.5));
		products.add(new MenuProduct("Kids Menu", Kidsproducts));
	}

	public void listEmployees() {

		if (this.employees.size() != 0) {

			for (Employee employee : this.employees) {

				System.out.println("Employee " + employee.getId() + ": " + employee.getName());

			}
		} else

			System.out.println("You do not have any employee yet!");

	}

	public void addCook(String name, double salary) {

		id++;
		employees.add(new Cook(id, name, salary));

	}

	public void addWaiter(String name) {
		
		id++;
		employees.add(new Waiter(id, name));

	}

	public Waiter assignWaiter() {

		while (true) {

			int waiterID = rgen.nextInt(id);

			for (Employee waiter : this.employees) {

				if (waiter instanceof Waiter) {

					if (waiter.getId() == waiterID) {

						return (Waiter) waiter;

					}
				}

			}

		}
	}

	public double calculateExpenses() {

		double employeeExpense = 0;
		double orderExpense    = 0;

		for (Employee employee : this.employees) {

			employeeExpense += employee.calculateExpense();
			
			if(employee instanceof Waiter){
				
				for(Order order :  ((Waiter) employee).getOrdersReceived()){
					
					for(Product product : order.getOrderedProduct()){
						
						orderExpense += product.calculateExpense();
					}
				}
			}
		}

		return orderExpense + employeeExpense;

	}

	public double calculateRevenue() {

		double revenue = 0;
		
		for(Employee employee : this.employees){
			
			if(employee instanceof Waiter){
				
				for(Order order :  ((Waiter) employee).getOrdersReceived()){
					
					for(Product product : order.getOrderedProduct()){
						
						revenue += product.getSellingPrice();
					}
					
				}
					
			}
			
		}
		
	
		return revenue;

	}

	public ArrayList<Product> getProducts() {

		return this.products;

	}

	public ArrayList<Employee> getEmployees() {

		return this.employees;

	}

}
