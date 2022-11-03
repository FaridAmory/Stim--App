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

public class MainFormDev extends JFrame implements ActionListener{
	
	JPanel mainPanel;
	JLabel titleLabel;
	JMenuBar menuBar;
	JMenu accountMenu, manageMenu ;
	JMenuItem logout, manageGame, manageGenres;
	
	User loggedUser;
	public MainFormDev() {
		frameSettings();
		initComponents();
		setVisible(true);
		this.loggedUser = loggedUser;
	
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
		
		// Manage Menu
		manageMenu = new JMenu("Manage");
		manageGame = new JMenuItem("Manage Games");
		manageGenres = new JMenuItem("Manage Genres");
		
		// Account Menu
		accountMenu = new JMenu("Account");
		logout = new JMenuItem("Log Out");
		accountMenu.add(logout);
		
		manageMenu.add(manageGame);
		manageMenu.add(manageGenres);
		
		
		//MenuBar
		menuBar.add(accountMenu);
		menuBar.add(manageMenu);
		
		//Action Listener
		logout.addActionListener(this);
		manageGame.addActionListener(this);
		manageGenres.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == logout) {
			this.dispose();
			new Login();
			
		}else if(e.getSource() == manageGame) {
			this.dispose();
			new ManageGameForm(loggedUser);
		}else if(e.getSource() == manageGenres ) {
			this.dispose();
			new ManageGenreForm();
		}
	}
	}
