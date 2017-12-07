package GUI;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import Model.*;

public class RestaurantPanel extends JPanel {

	private Restaurant restaurant;
	private JButton listEmployees;
	private JButton addCook;
	private JButton addWaiter;
	private JButton calculateExpense;

	public RestaurantPanel(Restaurant restaurant) {

		this.restaurant = restaurant;
		this.setLayout(new BorderLayout());
		this.createButtons();

	}

	public void createButtons() {

		listEmployees 	 = new JButton("List Employees");
		addCook 		 = new JButton("Add Cook");
		addWaiter		 = new JButton("Add Waiter");
		calculateExpense = new JButton("Calculate Expense");

		JPanel buttonPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		buttonPanel.add(listEmployees);
		buttonPanel.add(addCook);
		buttonPanel.add(addWaiter);
		buttonPanel.add(calculateExpense);

		Border border = BorderFactory.createTitledBorder("What do you want to do ? ");
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBorder(border);
		buttonPanel.setPreferredSize(new Dimension(this.getWidth(), 75));

		add(buttonPanel, BorderLayout.NORTH);
		add(bottomPanel);

		listEmployeeAction(bottomPanel);										      // actionlisteners of all buttons on Restaurant tab
		addCookAction(bottomPanel);
		addWaiterAction(bottomPanel);
		calculateExpenseAction(bottomPanel);

	}

	public void listEmployeeAction(JPanel bottomPanel) {

		listEmployees.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				updateBottomPanel(bottomPanel, 1, 3);

				JPanel idPanel   = new JPanel();
				JPanel namePanel = new JPanel();
				JPanel jobPanel  = new JPanel();

				idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.Y_AXIS));
				namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
				jobPanel.setLayout(new BoxLayout(jobPanel, BoxLayout.Y_AXIS));

				bottomPanel.add(idPanel);
				bottomPanel.add(namePanel);
				bottomPanel.add(jobPanel);

				JLabel id   = new JLabel("ID");
				JLabel name = new JLabel("Name");
				JLabel job  = new JLabel("Job");

				id.setForeground(Color.RED);
				name.setForeground(Color.RED);
				job.setForeground(Color.RED);

				ArrayList<JLabel> idArray   = new ArrayList<JLabel>();
				ArrayList<JLabel> nameArray = new ArrayList<JLabel>();
				ArrayList<JLabel> jobArray  = new ArrayList<JLabel>();

				for (Employee emp : restaurant.getEmployees()) {					  // to list all employees in restaurant
																					  // with their IDs, Names and jobs
					idArray.add(new JLabel("" + emp.getId()));
					nameArray.add(new JLabel(emp.getName()));

					if (emp instanceof Waiter) {

						jobArray.add(new JLabel("Waiter"));

					} else

						jobArray.add(new JLabel("Cook"));

				}

				addLabels(idPanel, idArray, id);
				addLabels(namePanel, nameArray, name);
				addLabels(jobPanel, jobArray, job);

			}

		});

	}
	

	public void addCookAction(JPanel bottomPanel) {

		addCook.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				updateBottomPanel(bottomPanel, 1, 2);

				JPanel labelPanel = new JPanel();
				JPanel fieldPanel = new JPanel();

				labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
				fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));

				JTextField nameField   = new JTextField();
				JTextField salaryField = new JTextField();
				JButton    addButton   = new JButton("Add");
				
				nameField.setMaximumSize(new Dimension(getWidth(), 20));
				salaryField.setMaximumSize(new Dimension(getWidth(), 20));
				addButton.setMaximumSize(new Dimension(getWidth(), 20));

				labelPanel.add(new JLabel("Name :"));
				labelPanel.add(new JLabel("Salary: "));
				fieldPanel.add(nameField);
				fieldPanel.add(salaryField);
				fieldPanel.add(addButton);
				bottomPanel.add(labelPanel);
				bottomPanel.add(fieldPanel);
				
				revalidate();

				addButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {

						tryAddCook(nameField, salaryField);							  // trying to add new Cooker

					}

				});

			}

		});

	}

	public void addWaiterAction(JPanel bottomPanel) {

		addWaiter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				updateBottomPanel(bottomPanel, 1, 2);

				JPanel labelPanel = new JPanel();
				JPanel fieldPanel = new JPanel();

				labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
				fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));

				bottomPanel.add(labelPanel);
				bottomPanel.add(fieldPanel);

				JTextField nameField = new JTextField();
				JButton addButton    = new JButton("Add");
				
				nameField.setMaximumSize(new Dimension(getWidth(), 20));
				addButton.setMaximumSize(new Dimension(getWidth(), 20));
				
				labelPanel.add(new JLabel("Name :"));
				fieldPanel.add(nameField);
				fieldPanel.add(addButton);
				revalidate();

				addButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {

						tryAddWaiter(nameField);								      // trying to add new Waiter
						
					}

				});

			}

		});

	}

	public void calculateExpenseAction(JPanel bottomPanel) {

		calculateExpense.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				updateBottomPanel(bottomPanel, 1, 2);

				JPanel typePanel   = new JPanel();
				JPanel figurePanel = new JPanel();

				typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
				figurePanel.setLayout(new BoxLayout(figurePanel, BoxLayout.Y_AXIS));

				double expenseFig = restaurant.calculateExpenses();
				double revenueFig = restaurant.calculateRevenue();
				double profitFig  = revenueFig - expenseFig;

				JLabel expense 		= new JLabel("Expenses:");
				JLabel revenue 		= new JLabel("Revenue:");
				JLabel profit  		= new JLabel("Profit:");
				JLabel expenseLabel = new JLabel("" + new DecimalFormat("##.##").format(expenseFig) + " TL");
				JLabel revenueLabel = new JLabel("" + new DecimalFormat("##.##").format(revenueFig) + " TL");
				JLabel profitLabel  = new JLabel("" + new DecimalFormat("##.##").format(profitFig)  + " TL");

				typePanel.add(expense);
				typePanel.add(revenue);
				typePanel.add(profit);
				figurePanel.add(expenseLabel);
				figurePanel.add(revenueLabel);
				figurePanel.add(profitLabel);
				bottomPanel.add(typePanel);
				bottomPanel.add(figurePanel);
				
				revalidate();

			}

		});

	}

	public void addLabels(JPanel panel, ArrayList<JLabel> labelArray, JLabel title) { // to add labels on list employee part

		panel.add(title, BorderLayout.NORTH);
		for (JLabel label : labelArray) {

			panel.add(label);

		}

		revalidate();

	}

	public void updateBottomPanel(JPanel bottomPanel, int row, int column) {          // to update the bottom panel depends on what user clicked

		bottomPanel.setLayout(new GridLayout(row, column));
		bottomPanel.removeAll();
		bottomPanel.repaint();

	}

	public void confirmMessage(String employee) { 									  // to show a message when user add employee successfully

		String message = employee + " added successfully";

		JOptionPane.showMessageDialog(null, message);

	}

	public void errorMessage(String type) { 										  // to show a message when user failed while adding new employee

		String message = "Error: Enter a "+ type;

		JOptionPane.showMessageDialog(null, message);

	}

	public void tryAddCook(JTextField nameField, JTextField salaryField) { 			  // try catch part of addCook button 

		try {

			if(nameField.getText().length() == 0){

					throw new Exception();                                            //god bless stack over flow
					
			}
			
			restaurant.addCook(nameField.getText(), Double.parseDouble(salaryField.getText()));
			confirmMessage("Cook");

		} catch (NumberFormatException e) {

			errorMessage("salary");
			salaryField.setText(null);

		} catch(Exception e){
			
			errorMessage("name");
			
		}

	}

	public void tryAddWaiter(JTextField nameField){									  // try catch part of addWaiter button
		
		try{
			
			if(nameField.getText().length() == 0){

				throw new Exception();
				
			}
			
			restaurant.addWaiter(nameField.getText());
			confirmMessage("Waiter");
			
		} catch(Exception e){
			
			errorMessage("name");
			
		}
		
	}
}
