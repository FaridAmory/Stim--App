import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Registrasi extends JFrame implements ActionListener {
	JPanel mainPanel, southPanel, titlePanel, usernamePanel, passwordPanel,genderPanel, genderPanels, countryPanel, rolePanel, rolePanels, buttonPanel;
	JLabel titleLabel, usernameLabel, passwordLabel, genderLabel, countryLabel, roleLabel;
	JTextField usernameField;
	JPasswordField passwordField;
	JRadioButton maleButton, femaleButton, playerButton, devButton;
	JComboBox countryBox;
	JButton backButton, registrasiButton;
	ButtonGroup genderGroup, roleGroup;
	
	DatabaseConnection db = new DatabaseConnection();

	public Registrasi() {
		frameSettings();
		initComponents();
		setVisible(true);
		
	}
	
	private void frameSettings() {
		setSize(600,450);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
	}

	private void initComponents() {
		
		// Main Panel
		mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		
		// Title 
		titlePanel = new JPanel();
		add(titlePanel, BorderLayout.NORTH);
		
		titleLabel = new JLabel("Create An Account");
		titleLabel.setFont(new Font("Times new Roman", Font.PLAIN, 25));
		titlePanel.setSize(100, 100);		
		titlePanel.setBorder(new EmptyBorder(20, 0, 20, 0));	
		titlePanel.add(titleLabel);
		
		// Username
		usernamePanel = new JPanel(new FlowLayout());
		mainPanel.add(usernamePanel);
		
		// Label
		usernameLabel = new JLabel("Username");
		usernameLabel.setPreferredSize(new Dimension(90, 30));
		usernameLabel.setFont(new Font("Times new Roman", Font.PLAIN, 15));
		
		// Field
		usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(340, 30));
		
		 // Panel
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		usernamePanel.setPreferredSize(new Dimension(510, 42));
		
		// Password
		passwordPanel = new JPanel(new FlowLayout());
		mainPanel.add(passwordPanel);
		
		// Label
		passwordLabel = new JLabel("Password");
		passwordLabel.setPreferredSize(new Dimension(90, 30));
		passwordLabel.setFont(new Font("Times new Roman", Font.PLAIN, 15));
		
		// Field
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(340, 30));
		
		// Panel
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		passwordPanel.setPreferredSize(new Dimension(510, 42));
		
		// Gender
		genderPanel = new JPanel(new FlowLayout());
		genderPanels = new JPanel(new FlowLayout());
		mainPanel.add(genderPanel);
		
		// Label
		genderLabel = new JLabel("Gender");
		genderLabel.setPreferredSize(new Dimension(90, 30));
		genderLabel.setFont(new Font("Times new Roman", Font.PLAIN, 15));
		
		// Panel
		genderPanel.setPreferredSize(new Dimension(510, 42));
		genderPanel.add(genderLabel);
		
		// Radio
		maleButton = new JRadioButton("Male");
		femaleButton = new JRadioButton("Female");
		genderPanels.add(maleButton);
		genderPanels.add(femaleButton);
		genderPanels.setPreferredSize(new Dimension(340, 30));
		genderPanel.add(genderPanels);
		
		// Group
		genderGroup = new ButtonGroup();
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		
		// Button
		maleButton.setActionCommand("Male");
		femaleButton.setActionCommand("Female");	
		
		// Country
		countryPanel = new JPanel(new FlowLayout());		
		mainPanel.add(countryPanel);	
		
		// Label
		countryLabel = new JLabel("Country");
		countryLabel.setPreferredSize(new Dimension(90, 30));
		countryLabel.setFont(new Font("Times new Roman", Font.PLAIN, 15));
		
		
		// Box 
		String[] country = {"Indonesia", "America", "England", "Malaysia", "Singapore", "South Korea", "German"};
		countryBox = new JComboBox(country);
		countryBox.setPreferredSize(new Dimension(340, 30));
		
		// Panel
		countryPanel.add(countryLabel);
		countryPanel.add(countryBox);	
		countryPanel.setPreferredSize(new Dimension(510, 40));
		
		// Role
		rolePanel = new JPanel(new FlowLayout());
		rolePanels = new JPanel(new FlowLayout());
		mainPanel.add(rolePanel);
		
		//Label
		roleLabel = new JLabel("Choose a role");
		roleLabel.setPreferredSize(new Dimension(90, 30));
		roleLabel.setFont(new Font("Times new Roman", Font.PLAIN, 15));
		
		//Panel
		rolePanel.setPreferredSize(new Dimension(510, 40));
		rolePanel.add(roleLabel);
	
		//Radio
		playerButton = new JRadioButton("Player");
		devButton = new JRadioButton("Developer");
		rolePanels.add(playerButton);
		rolePanels.add(devButton);
		rolePanels.setPreferredSize(new Dimension(340,50));	
		rolePanel.add(rolePanels);
		
		//Group
		roleGroup = new ButtonGroup();
		roleGroup.add(playerButton);
		roleGroup.add(devButton);
		
		//Button
		playerButton.setActionCommand("Player");
		devButton.setActionCommand("Developer");
		
		//Button back and Register
		southPanel = new JPanel(new GridLayout(1, 3, 15, 0));
		mainPanel.add(southPanel);
		
		//Button
		backButton = new JButton("Back");
		registrasiButton = new JButton("Register");
		
		//Panel
		southPanel.add(backButton);
		southPanel.add(registrasiButton);
		southPanel.setPreferredSize(new Dimension(500, 35));
				
		//Action Listener		
		backButton.addActionListener(this);
		registrasiButton.addActionListener(this);
				
		}
	private void showWarning(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Random rand = new Random();
		int upperbound = 25;
		int id = rand.nextInt(upperbound);
		
		if (e.getSource() == registrasiButton) {
			if(validation()) {
				User User = db.getUserDataFromUsername(usernameField.getText());
				if(User != null) {
					showWarning("Username is already exists", "Error");
				}else {
					User newUser = new User(usernameField.getText(), passwordField.getText(), genderGroup.getSelection().getActionCommand(), countryBox.getSelectedItem().toString(), roleGroup.getSelection().getActionCommand(), id);
					
					try {
						db.insertNewUser(newUser);
						JOptionPane.showMessageDialog(null, "Successfully registered user", "Success", JOptionPane.WARNING_MESSAGE);
						this.dispose();
						new Login();
						
					}catch (Exception wrongInput) {
						JOptionPane.showConfirmDialog(null, "Smth Wrong", "Error", JOptionPane.WARNING_MESSAGE);
					}
					
				}
				
			}
		
		}
		else {
			this.dispose();
			new Login();
		}
		
	}
	
	private boolean validation() {
		if(usernameField.getText().isEmpty()) {
			showWarning("Username cannot be empty", "Empty");
			return false;
		} else if(usernameField.getText().length() < 5 || usernameField.getText().length() > 15) {			
			showWarning("Username Length Must be at least 5-15 chars", "Error");						
			return false;
		} else if(passwordField.getText().length() < 3 || passwordField.getText().length() > 10) {
			showWarning("Password Length Must be at least 5-10 characters", "Error");						
			return false;
		}else if (passwordField.getText().isEmpty()) {
			showWarning("Password cannot be empty", "Empty");
			return false;
		}else if(countryBox.getSelectedItem() == null) {
			showWarning("Please select a Country", "Empty");
			return false;
		}else if (genderGroup.getSelection() == null) {
			showWarning("Please select a gender", "Empty");
			return false;
		}else if (roleGroup.getSelection() == null) {
			showWarning("Please select a role", "Empty");
			return false;
		}
		return true;
	}


	public void backButton() {
		this.dispose();
		new Login();
		
	}

}
