import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OwnedGame extends JFrame implements ItemListener, ActionListener, MouseListener {
	JPanel mainPanel, northPanel, idPanel, namePanel, pricePanel, genrePanel, qtyPanel, totalPanel, buttonPanel;
	JLabel idLabel, nameLabel, priceLabel, genreLabel, qtyLabel, totalLabel;
	JTextField idField, nameField, priceField, genreField, qtyField , totalField;
	JButton backButton;
	JTable table;
	

	DatabaseConnection con = new DatabaseConnection();
	DefaultTableModel dtm;
	JScrollPane scroll;
	
	public OwnedGame(User loggedUser) {
		frameSettings();
		initComponents();
		setVisible(true);
	}

	private void frameSettings() {
		
		setSize(800,500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}
	private void initComponents() {
		
		//Main Panel
		northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		
		// ID
		idPanel = new JPanel(new FlowLayout());
		mainPanel.add(idPanel);
		
		//LabeL
		idLabel = new JLabel("Game ID");
		idLabel.setPreferredSize(new Dimension(340,30));
		idLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		// Field 
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(310,30));
		
		// Panel
		idPanel.add(idLabel);
		idPanel.add(idField);
		
		// Name
		namePanel = new JPanel(new FlowLayout());
		mainPanel.add(namePanel);
		
		// Label
		nameLabel = new JLabel("Game Name");
		nameLabel.setPreferredSize(new Dimension(340,30));
		nameLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		//Field
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(310,30));
		
		// Panel
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		
		// Price
		pricePanel = new JPanel(new FlowLayout());
		mainPanel.add(pricePanel);
		
		// Label
		priceLabel = new JLabel("Game Price");
		priceLabel.setPreferredSize(new Dimension(340,30));
		priceLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		// Field
		priceField = new JTextField();
		priceField.setPreferredSize(new Dimension(310,30));
		
		// Panel
		pricePanel.add(priceLabel);
		pricePanel.add(priceField);
		
		// Genre
		genrePanel = new JPanel(new FlowLayout());
		mainPanel.add(genrePanel);
		
		// Label
		genreLabel = new JLabel("Game Genre");
		genreLabel.setPreferredSize(new Dimension(340,30));
		genreLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		// Field
		genreField = new JTextField();
		genreField.setPreferredSize(new Dimension(310,30));
		
		// Panel
		genrePanel.add(genreLabel);
		genrePanel.add(genreField);
		
		// Quantity
		qtyPanel = new JPanel(new FlowLayout());
		mainPanel.add(qtyPanel);
		
		// Label
		qtyLabel = new JLabel("Owned Quantity");
		qtyLabel.setPreferredSize(new Dimension(340,30));
		qtyLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		// Field
		qtyField = new JTextField();
		qtyField.setPreferredSize(new Dimension(310,30));
		
		// Panel
		qtyPanel.add(qtyLabel);
		qtyPanel.add(qtyField);
		
		// Total
		totalPanel = new JPanel(new FlowLayout());
		mainPanel.add(totalPanel);
		
		// Label
		totalLabel = new JLabel("Total Spent on Games");
		totalLabel.setPreferredSize(new Dimension(340,30));
		totalLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		// Field
		totalField = new JTextField();
		totalField.setPreferredSize(new Dimension(310,30));
		
		// Panel
		totalPanel.add(totalLabel);
		totalPanel.add(totalField);
	
		// Button
		buttonPanel = new JPanel(new GridLayout(1, 3, 15, 0));
		mainPanel.add(buttonPanel);
		
		// Label
		backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(100, 40));
		backButton.setFont(new Font("Times new Roman", Font.PLAIN, 15));
		
		// Panel
		buttonPanel.add(backButton);
		buttonPanel.setPreferredSize(new Dimension(700, 40));
		
		Object[] columnNames = new Object[] { "Game ID", "Game Name", "Genre", "Quantity", "Price" };
		Object[][] Data = new Object[][] {  
			
			{"GAME002", "World of Warcraft", 9999,"Mystery" , 5 },
			{"GAME004", "Dota 2", 123, "Moba", 6 },
			{"GAME005", "ARK : Survival Evolved",986 , "Adventure", 149999 },
		    {"Game006", "Valorant", 999, "FPS", 1 }};
	  
		// Table
        dtm = new DefaultTableModel(Data, columnNames);
		table = new JTable(dtm);
		northPanel.add(table);
		
		// Action Listener
		backButton.addActionListener(this);
		table.addMouseListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = table.getSelectedRow();
		  idField.setText(dtm.getValueAt(row, 0).toString());
		  nameField.setText(dtm.getValueAt(row, 1).toString());
		  priceField.setText(dtm.getValueAt(row, 2).toString());
		  genreField.setText(dtm.getValueAt(row, 3).toString());
		  qtyField.setText(dtm.getValueAt(row, 4).toString()) ;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			this.dispose();
			new MainForm();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}

}
