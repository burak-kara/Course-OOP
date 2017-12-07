package Model;

import java.util.ArrayList;

public class Waiter extends Employee {
	
	private double orderRate;
	private ArrayList<Order> ordersReceived;
	
	public Waiter(int id, String name){
		
		super(id, name);
		this.orderRate = 0.1;
		this.ordersReceived = new ArrayList<Order>();
		
	}
	
	public double calculateExpense(){
		
		double expense = 0;
		
		for(Order order : this.ordersReceived){
			
			expense += order.calculateTotalPrice();
			
		}
		
		return expense * orderRate;
		
	}

	public void createOrder(Order order){
		
		this.ordersReceived.add(order);
		
	}
	
	public ArrayList<Order> getOrdersReceived(){
		
		return this.ordersReceived;
		
	}

}
