package GUI;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Model.*;

public class OrderPanel extends JPanel {

	private Restaurant restaurant;
	private Waiter waiter;
	private Order order;
	
	public OrderPanel(Restaurant restaurant) {

		this.restaurant = restaurant;
		this.newOrder();

	}

	public void newOrder() {

		this.setLayout(new FlowLayout());
		
		JButton newOrderButton = new JButton("New Order");
		this.add(newOrderButton);
		
		newOrderAction(newOrderButton);
		
	}
	
	public void update(){ 																// to update the panels when user clicked buttons
		
		removeAll();
		repaint();
		
	}
	
	public void newOrderAction(JButton newOrderButton){ 								// when user clicked the new order button
		
		newOrderButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event){
				
				setLayout(new GridLayout(2,1));
				update();
				newWaiterMessage();
				createOrder();
				
			}
			
		});

	}
	
	public void createOrder(){
		
		order = new Order();
		
		JPanel addProductPanel    = new JPanel();	
		JPanel listOrderPanel     = new JPanel();
		JPanel productLabelPanel  = new JPanel();
		JPanel productSelectPanel = new JPanel();
		JPanel productNameColumn  = new JPanel();
		JPanel countColumn 		  = new JPanel();
		JPanel priceColumn 		  = new JPanel();
		
		JLabel productLabel    = new JLabel("Product : ");
		JLabel countLabel  	   = new JLabel("Count :");
		JLabel priceLabel      = new JLabel("Price :");
		JLabel price 		   = new JLabel("0,00 TL");
		JButton addButton 	   = new JButton("Add");
		JButton finalizeButton = new JButton("Finalize");
		JSpinner spinner 	   = new JSpinner();
		JComboBox <String> comboBox = new JComboBox<>();
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
		
		ArrayList<JLabel> productNameArray = new ArrayList<JLabel>();
		ArrayList<JLabel> countArray 	   = new ArrayList<JLabel>();
		ArrayList<JLabel> priceArray 	   = new ArrayList<JLabel>();
		
		productNameArray.add((new JLabel("Product")));
		countArray.add(new JLabel("Count"));
		priceArray.add(new JLabel("Price"));
		
		spinnerModel.setMaximum(10);
		spinnerModel.setMinimum(1);
		spinner.setModel(spinnerModel);
		spinner.setValue(1);
		spinner.setEnabled(false);
		
		addProductPanel.setBorder(setTitle("Add Product"));
		listOrderPanel.setBorder(setTitle("Current Order"));
		addProductPanel.setLayout(new GridLayout(1,2));
		listOrderPanel.setLayout(new GridLayout(1,4));
		productLabelPanel.setLayout(new BoxLayout(productLabelPanel, BoxLayout.Y_AXIS));
		productSelectPanel.setLayout(new BoxLayout(productSelectPanel, BoxLayout.Y_AXIS));
		productNameColumn.setLayout(new BoxLayout(productNameColumn, BoxLayout.Y_AXIS));
		countColumn.setLayout(new BoxLayout(countColumn, BoxLayout.Y_AXIS));
		priceColumn.setLayout(new BoxLayout(priceColumn, BoxLayout.Y_AXIS));
		
		setComboBox(comboBox);
		comboBox.setMaximumSize(new Dimension(getWidth(),30));
		spinner.setMaximumSize(new Dimension(getWidth(),30));
		addButton.setMaximumSize(new Dimension(getWidth(),30));
		addButton.setEnabled(false);

		finalizeButton.setEnabled(false);
		finalizeButton.setMaximumSize(new Dimension(getWidth(),30));
	
		comboBox.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent event){
				
				String selected = (String) comboBox.getSelectedItem();
				
				for(Product product : restaurant.getProducts()){
					
					if(selected == null){
						
						price.setText("0,00 TL");
						
					} else if(selected.equals(product.getName())){
						
						price.setText(new DecimalFormat("##.##").format(product.getSellingPrice()) + " TL");
						addButton.setEnabled(true);
						spinner.setEnabled(true);
					}
					
				}
				
			}
			
		});

		addButton.addActionListener(new ActionListener(){ 								// when user clicked the add button
			
			public void actionPerformed(ActionEvent event){
				
				String selectedProduct = (String) comboBox.getSelectedItem();
				int selectedCount      = (int) spinner.getValue();
				Product ordered        = null;
				
				for(Product product : restaurant.getProducts()){
					
					if(product.getName().equals(selectedProduct)){
						
						ordered = product;
						
					}
					
				}
				
				for(int i = 0; i < selectedCount; i++ ){
					
					order.addProduct(ordered);
					
				}
				
				productNameArray.add(new JLabel(selectedProduct));
				countArray.add(new JLabel("" + selectedCount));
				priceArray.add(new JLabel("" + new DecimalFormat("##.##").format(ordered.getSellingPrice() * selectedCount)));
				
				addLabels(productNameColumn, productNameArray );
				addLabels(countColumn, countArray );
				addLabels(priceColumn, priceArray );
		
				finalizeButton.setEnabled(true);
				
			}
			
		});
		
		finalizeButton.addActionListener(new ActionListener(){ 							// when user clicked the finalize button
			
			public void actionPerformed(ActionEvent event){
				
				waiter.createOrder(order);
				update();
				completedMessage();
				newOrder();
				
			}
			
		});
		
		addLabels(productNameColumn, productNameArray );
		addLabels(countColumn, countArray );
		addLabels(priceColumn, priceArray );
		productLabelPanel.add(productLabel);
		productLabelPanel.add(Box.createVerticalStrut(30)); 							// this creates a gap in vertically
		productLabelPanel.add(countLabel);
		productLabelPanel.add(Box.createVerticalStrut(30));
		productLabelPanel.add(priceLabel);
		productSelectPanel.add(comboBox);
		productSelectPanel.add(Box.createVerticalStrut(20));
		productSelectPanel.add(spinner);
		productSelectPanel.add(Box.createVerticalStrut(20));
		productSelectPanel.add(price);
		productSelectPanel.add(Box.createVerticalStrut(20));
		productSelectPanel.add(addButton);
		addProductPanel.add(productLabelPanel);
		addProductPanel.add(productSelectPanel);
		listOrderPanel.add(productNameColumn);
		listOrderPanel.add(countColumn);
		listOrderPanel.add(priceColumn);
		listOrderPanel.add(finalizeButton);
		add(addProductPanel);
		add(listOrderPanel);
		
		revalidate();
		
	}

	public void setComboBox(JComboBox<String> comboBox ){  								// to set the products in the comboBox
		
		comboBox.addItem(null);
		for(Product product : restaurant.getProducts()){
			
			comboBox.addItem(product.getName());
			
		}
		
	}
	
	public Border setTitle(String title){
		
		Border border = BorderFactory.createTitledBorder(title);
		
		return border;
		
	}
	
	public void addLabels(JPanel panel, ArrayList<JLabel> labelArray){  				// to add the labels on panels in current order part
		
		for (JLabel label : labelArray) {

			panel.add(label);

		}

		revalidate();

	}
	
	public void newWaiterMessage(){ 													// to show the message when user clicked the new order Button
		
		this.waiter = restaurant.assignWaiter();
		String message = "Hi, I am "+ waiter.getName() + 
				". \nWhat would you like to order?";
		JOptionPane.showMessageDialog(null, message);
		
	}
	
	public void completedMessage(){ 													// to show the message when user clicked the finalize button
		
		String message = "Your order is completed\n"+
				"Total price is " +  new DecimalFormat("##.##").format(order.calculateTotalPrice()) + " TL";
		JOptionPane.showMessageDialog(null, message);
		
	}
	
}
