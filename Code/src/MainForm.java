import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainForm extends JFrame implements ActionListener{
	
	JPanel mainPanel;
	JLabel titleLabel;
	JMenuBar menuBar;
	JMenu accountMenu, manageMenu, gameMenu;
	JMenuItem logout, buyGame, ownedGame;
	User loggedUser;
	String username;
	int id;
	String role;
	
	
	public MainForm() {
		frameSettings();
		initComponents();
		setVisible(true);
	}

	public MainForm(int id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
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
		add(mainPanel,  BorderLayout.CENTER);
		
		// Title 
		titleLabel = new JLabel("Stim");
		titleLabel.setFont(new Font("Times new Roman", Font.PLAIN, 40));
		mainPanel.add(titleLabel);
		
		// Menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Game Menu
		gameMenu = new JMenu("Games");
		buyGame = new JMenuItem("Buy Games");
		ownedGame = new JMenuItem("Owned Games");
		
		// Account Menu
		accountMenu = new JMenu("Account");
		logout = new JMenuItem("Log Out");
		accountMenu.add(logout);
		
		//gameMenu
		gameMenu.add(buyGame);
		gameMenu.add(ownedGame);
		
		//MenuBar
		menuBar.add(accountMenu);
		menuBar.add(gameMenu);
		
		
		//Action Listener
		logout.addActionListener(this);
		buyGame.addActionListener(this);
		ownedGame.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logout) {
			this.dispose();
			new Login();
			
		}else if(e.getSource() == buyGame) {
			this.dispose();
			new BuyGameForm(loggedUser);
		}else if(e.getSource() == ownedGame) {
			this.dispose();
			new OwnedGame(loggedUser);
		}
	}
	}
