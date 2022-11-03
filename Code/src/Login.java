import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener {
	
	JPanel mainPanel, titlePanel, usernamePanel, passwordPanel, buttonPanel;
	JLabel titleLabel, usernameLabel, passwordLabel;
	JTextField usernameField;
	JPasswordField passwordField;
	JButton loginButton, registrasiButton;
	
	DatabaseConnection con = new DatabaseConnection();
	
	
	public Login() {
		
		frameSettings();
		initComponents();
		setVisible(true);
	}

	private void frameSettings() {
		setSize(600,350);
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
	        
	        titleLabel = new JLabel("Login");
	        titleLabel.setFont(new Font("Times new Roman", Font.PLAIN, 25));
	        titlePanel.setSize(100, 100);
	        titlePanel.setBorder(new EmptyBorder(15,1,15,1));
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
	        
	        
	        // Button
	        buttonPanel = new JPanel(new FlowLayout());
	        buttonPanel.setPreferredSize(new Dimension(490,70));
	        mainPanel.add(buttonPanel);
	        
	        loginButton = new JButton("  Login  ");
	        registrasiButton = new JButton(" Register");
	        
	        buttonPanel.add(loginButton);
	        buttonPanel.add(registrasiButton);
	        
	        //Action Listener
	        registrasiButton.addActionListener(this);	
	        loginButton.addActionListener(this);
	        	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == registrasiButton) {
			this.dispose();
			new Registrasi();
		
		}else if(e.getSource() == loginButton) {
			validation();
			
		}
	}

	private void validation() {
		String usernameValidation = usernameField.getText().toString();
		String passwordValidation = passwordField.getText().toString();
		Integer userId = 0;
		
	boolean validation = true;
	String role = "";
	try {
		String query = "SELECT * FROM `user` WHERE username ='" + usernameValidation + "' and password='" + passwordValidation + "'";
		ResultSet res = con.execQuery(query);
		if (res.next()) {
			role = res.getString("role");
			validation = true;
		}else {
			validation = false;
		}
	} catch (Exception e) {
		
	}
	
	if (usernameField.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Username cannot be empty", "Error", JOptionPane.WARNING_MESSAGE);
	}else if(passwordField.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Password cannot be empty", "Error", JOptionPane.WARNING_MESSAGE);
	}else if(validation == false) {
		JOptionPane.showMessageDialog(null, "Username / Password is wrong", "Error", JOptionPane.WARNING_MESSAGE);
	
	}else {
		
		User user = new User();
		user.setUserId(userId);
		if(role.equals("Player")) {
			this.dispose();
			new MainForm();
			
		}else {
			this.dispose();
			new MainFormDev();
		}
		
	
	}
	
  }

}
