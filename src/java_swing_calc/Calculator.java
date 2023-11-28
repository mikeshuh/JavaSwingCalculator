package java_swing_calc;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Calculator application using Java Swing libraries
 * <p>
 * utilizes code from calculator application (AWT libraries) by Yulia Newton
 * @author Michael Huh
 */
public class Calculator extends JFrame {
	
	//private fields
	private JTextField outputField;
	private ArrayList<JButton> digits;
	private JButton add;
	private JButton subtract;
	private JButton multiply;
	private JButton divide;
	private JButton equals;
	private String currentOperation;
	private Double firstOperand;
	
	/**
	 * Calculator constructor
	 */
	public Calculator() {
		this.currentOperation = "";
		this.firstOperand = 0.0;
		
		//instantiate display panel with flow layout
		JPanel displayPanel = new JPanel(new FlowLayout());
		
		//instantiate menu bar
		JMenuBar menuBar = new JMenuBar();
		
		//instantiate menu
		JMenu menu = new JMenu("Menu");
		
		//instantiate menu items
		JMenuItem menuAdd = new JMenuItem("+");
		JMenuItem menuSubtract = new JMenuItem("-");
		JMenuItem menuMultiply = new JMenuItem("x");
		JMenuItem menuDivide = new JMenuItem("/");
		JMenuItem menuAC = new JMenuItem("AC");
		
		//menu action listener
		ActionListener menuListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JMenuItem source = (JMenuItem)event.getSource();
				if(source == menuAdd) {
					currentOperation = "+";
				}
				else if(source == menuSubtract) {
					currentOperation = "-";
				}
				else if(source == menuMultiply) {
					currentOperation = "x";
				}
				else if(source == menuDivide) {
					currentOperation = "/";
				}
				else if(source == menuAC) {
					resetValues();
				}
				String currentText = outputField.getText();
				try{
					Double currentTextDouble = Double.parseDouble(currentText);
					firstOperand = currentTextDouble;
					outputField.setText("0");
				}
				catch(NumberFormatException e){
					resetValues();
				}
			}
		};
		
		//add menu action listeners
		menuAdd.addActionListener(menuListener);
		menuSubtract.addActionListener(menuListener);
		menuMultiply.addActionListener(menuListener);
		menuDivide.addActionListener(menuListener);
		menuAC.addActionListener(menuListener);
		
		//add menu items to menu
		menu.add(menuAdd);
		menu.add(menuSubtract);
		menu.add(menuMultiply);
		menu.add(menuDivide);
		menu.add(menuAC);
		
		//add menu to menu bar
		menuBar.add(menu);
		
		//set menu bar
		this.setJMenuBar(menuBar);
		
		//instantiate result panel
		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));
		
		outputField = new JTextField("0", 20);
		
		//add output field to result panel
		resultPanel.add(outputField);
		
		//add result panel to display panel
		displayPanel.add(resultPanel);
		
		//instantiate button panel with 1x2 grid layout
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		
		//instantiate digit panel with 4x3 grid layout
		JPanel digitPanel = new JPanel(new GridLayout(4, 3));
		digitPanel.setBorder(BorderFactory.createTitledBorder("Digits"));
		
		digits = new ArrayList<JButton>();
		digits.add(new JButton("7"));
		digits.add(new JButton("8"));
		digits.add(new JButton("9"));
		digits.add(new JButton("4"));
		digits.add(new JButton("5"));
		digits.add(new JButton("6"));
		digits.add(new JButton("1"));
		digits.add(new JButton("2"));
		digits.add(new JButton("3"));
		digits.add(new JButton("0"));
		digits.add(new JButton("."));
		digits.add(new JButton("AC"));
		
		//add digit buttons to digit panel
		digitPanel.add(digits.get(0));
		digitPanel.add(digits.get(1));
		digitPanel.add(digits.get(2));
		digitPanel.add(digits.get(3));
		digitPanel.add(digits.get(4));
		digitPanel.add(digits.get(5));
		digitPanel.add(digits.get(6));
		digitPanel.add(digits.get(7));
		digitPanel.add(digits.get(8));
		digitPanel.add(digits.get(9));
		digitPanel.add(digits.get(10));
		digitPanel.add(digits.get(11));
		
		//add digit panel to button panel
		buttonPanel.add(digitPanel);
		
		//instantiate operator panel with 5x1 grid layout
		JPanel opPanel = new JPanel(new GridLayout(5, 1));
		opPanel.setBorder(BorderFactory.createTitledBorder("Operators"));
		
		//scale operator image icons
		ImageIcon addIcon = new ImageIcon("add_icon.jpg");
		Image img = addIcon.getImage();
		Image newImg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		addIcon = new ImageIcon(newImg);
		
		ImageIcon subtractIcon = new ImageIcon("subtract_icon.jpg");
		img = subtractIcon.getImage();
		newImg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		subtractIcon = new ImageIcon(newImg);
		
		ImageIcon multiplyIcon = new ImageIcon("multiply_icon.jpg");
		img = multiplyIcon.getImage();
		newImg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		multiplyIcon = new ImageIcon(newImg);
		
		ImageIcon divideIcon = new ImageIcon("divide_icon.jpg");
		img = divideIcon.getImage();
		newImg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		divideIcon = new ImageIcon(newImg);
		
		ImageIcon equalsIcon = new ImageIcon("equals_icon.jpg");
		img = equalsIcon.getImage();
		newImg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		equalsIcon = new ImageIcon(newImg);
		
		add = new JButton(addIcon);
		subtract = new JButton(subtractIcon);
		multiply = new JButton(multiplyIcon);
		divide = new JButton(divideIcon);
		equals = new JButton(equalsIcon);
		
		//add operator buttons to operator panel
		opPanel.add(add);
		opPanel.add(subtract);
		opPanel.add(multiply);
		opPanel.add(divide);
		opPanel.add(equals);
		
		//add operator panel to button panel
		buttonPanel.add(opPanel);
		
		//add button panel to display panel
		displayPanel.add(buttonPanel);
		
		//add displayPanel
		add(displayPanel);
		
		//all clear action listener
		digits.get(11).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				resetValues();
			}
		});
		
		//decimal action listener
		digits.get(10).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String currentText = outputField.getText();
				if(currentText.indexOf(".") < 0){
					outputField.setText(currentText+".");
				}
			}
		});
		
		//instantiate operator listener
		OperatorListener opListener = new OperatorListener();
		
		//add operator listeners
		add.addActionListener(opListener);
		subtract.addActionListener(opListener);
		multiply.addActionListener(opListener);
		divide.addActionListener(opListener);
		
		//digit action listener
		for(int i = 0; i <= 9; i ++){
			digits.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					String currentText = outputField.getText();
					JButton source = (JButton)event.getSource();
					String newDigit = "";
					if(source == digits.get(0)) {
						newDigit = "7";
					}
					else if(source == digits.get(1)) {
						newDigit = "8";
					}
					else if(source == digits.get(2)) {
						newDigit = "9";
					}
					else if(source == digits.get(3)) {
						newDigit = "4";
					}
					else if(source == digits.get(4)) {
						newDigit = "5";
					}
					else if(source == digits.get(5)) {
						newDigit = "6";
					}
					else if(source == digits.get(6)) {
						newDigit = "1";
					}
					else if(source == digits.get(7)) {
						newDigit = "2";
					}
					else if(source == digits.get(8)) {
						newDigit = "3";
					}
					else if(source == digits.get(9)) {
						newDigit = "0";
					}
					currentText = currentText + newDigit;
					currentText = currentText.replaceFirst("^0+(?!$)", "");
					outputField.setText(currentText);
				}
			});
		}
		
		//equals action listener
		equals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Double result = 0.0;
				String currentText = outputField.getText();
				try {
					Double secondOperand = Double.parseDouble(currentText);
					if(currentOperation == "+") {
						result = firstOperand + secondOperand;
					}
					else if(currentOperation == "-") {
						result = firstOperand - secondOperand;
					}
					else if(currentOperation == "x") {
						result = firstOperand * secondOperand;
					}
					else if(currentOperation == "/") {
						if(secondOperand != 0.0) {
							result = firstOperand / secondOperand;
						}
						else {
							resetValues();
							outputField.setBackground(Color.PINK);
						}
					}
					outputField.setText(result.toString());
					firstOperand = result;
				}
				catch(NumberFormatException e) {
					resetValues();
				}
			}
		});
		
		//window listener
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
		
		setTitle("Calculator");
		setSize(400, 375);
		setVisible(true);
	}
	
	//resets calculator
	private void resetValues(){
		currentOperation = "";
		firstOperand = 0.0;
		outputField.setText("0");
		outputField.setBackground(Color.WHITE);
	}

	//operator listener
	private class OperatorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton source = (JButton)event.getSource();
			if(source == add) {
				currentOperation = "+";
			}
			else if(source == subtract) {
				currentOperation = "-";
			}
			else if(source == multiply) {
				currentOperation = "x";
			}
			else if(source == divide) {
				currentOperation = "/";
			}
			String currentText = outputField.getText();
			try{
				Double currentTextDouble = Double.parseDouble(currentText);
				firstOperand = currentTextDouble;
				outputField.setText("0");
			}
			catch(NumberFormatException e){
				resetValues();
			}
		}
	}

	/**
	 * Run application
	 */
	public static void main(String[] args) {
		new Calculator();
	}
}
