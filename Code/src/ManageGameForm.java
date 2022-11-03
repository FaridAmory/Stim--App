import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ManageGameForm extends JFrame implements ActionListener, MouseListener {
	
	JPanel northPanel, southPanel, leftPanel, centerPanel, centerMiddlePanel, buttonPanel;
	JLabel idLabel, nameLabel, priceLabel, genreLabel, qtyLabel, newNameLabel, newPriceLabel, newGenreLabel, newQtyLabel, emptyLabel, emptyLabels;
	JTextField idField, nameField , priceField, newGameField, newPriceField;
	JButton backButton, deleteButton, updateButton, insertButton;
	JComboBox<String> genreBox, newGenreBox;
	JSpinner qtySpinner, newQtySpinner;
	JScrollPane scroll;
	
	JTable tabel;
	DefaultTableModel dtm;
	
	DatabaseConnection con = new DatabaseConnection();
	
	int selectedIndex = -1;
	int id;
	String username;
	String role;
	User loggedUser;


	public ManageGameForm(User loggedUser) {
		this.loggedUser = loggedUser;
		frameSettings();
		initComponents();
		setVisible(true);
	
	}
	private void frameSettings() {
		setResizable(false);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
		
	}

	private void initComponents() {
		
		
		// Panel Container
		northPanel = makeContainer(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		centerPanel = makeContainer(new BorderLayout());
		add(centerPanel, BorderLayout.CENTER);
		centerMiddlePanel = makeContainer(new GridBagLayout());
		centerPanel.add(centerMiddlePanel);
		southPanel = makeContainer(new BorderLayout());
		add(southPanel, BorderLayout.SOUTH);
		buttonPanel = makeContainer(new GridBagLayout());
		
	
		Object [] header = new Object [] {"Game ID", "Game Name", "Game Price", "Genre", "Quantity"};
		Object [][] data = new Object [][] {
		{"GAME002", "World of Warcraft", 9999,"Mystery" , 5 },
		{"GAME004", "Dota 2", 123, "Moba", 6 },
		{"GAME005", "ARK : Survival Evolved",986 , "Adventure", 149999 },
		{"Game006", "Valorant", 999, "FPS", 1 }};
		
		
		//Table
		dtm = new DefaultTableModel(data, header);
		tabel = new JTable(dtm);
		tabel.setRowHeight(20);
		
		tabel.addMouseListener(this);
		
		refreshTable();
		
		// Scroll
		scroll = new JScrollPane(tabel);
		northPanel.add(scroll);
		northPanel.setPreferredSize(new Dimension(600, 300));
		
		
		tabel.setPreferredSize(new Dimension(600, 300));
		tabel.getTableHeader().setReorderingAllowed(false);
		
		backButton = new JButton("Back");
		deleteButton = new JButton("Delete");
		updateButton = new JButton("Update");
		insertButton = new JButton("Insert");

		// ActionListener
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		insertButton.addActionListener(this);
		backButton.addActionListener(this);
		
		//Labels
		idLabel = makeLabel("Game ID");
		nameLabel = makeLabel("Game Name");
		priceLabel = makeLabel("Game Price");
		genreLabel = makeLabel("Game Genre");
		qtyLabel = makeLabel("Game Quantity");
		newNameLabel = makeLabel("New Game Name");
		newPriceLabel = makeLabel("New Game Price");
		genreLabel = makeLabel("New Game Genre");
		newQtyLabel = makeLabel("New Game Quantity");
		emptyLabel = makeLabel("");
		emptyLabels = makeLabel("");
		
		
		//Fields
		idField = makeTextfield();
		idField.setEditable(false);
	
		nameField = makeTextfield();
		priceField = makeTextfield();
		newPriceField = makeTextfield();
		newGameField = makeTextfield();
		
		Vector<Genre> Genres = new Vector<>();
		Vector<String> genreName = new Vector<>();
		
		String queryGenreName = "SELECT genreName FROM genre";
		ResultSet res1 = con.execQuery(queryGenreName);
		try {
			while (res1.next()) {
				Genres.add(new Genre(res1.getString("genreName")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Genre g : Genres) {
			genreName.add(g.getGenreName());
		}
				
		
		// Genre
		genreBox = new JComboBox(genreName);
		genreBox.setPreferredSize(new Dimension(170, 20));
		
		newGenreBox = new JComboBox(genreName);
		newGenreBox.setPreferredSize(new Dimension(170, 20));
		
		
		// Quantity
		qtySpinner = new JSpinner();
		qtySpinner.setPreferredSize(new Dimension(170, 20));
		
		newQtySpinner = new JSpinner();
		newQtySpinner.setPreferredSize(new Dimension(170, 20));
		
		
		//All Grid Bag
		GridBagConstraints in = new GridBagConstraints();
		in.insets = new Insets(50,80,5,5);
		in.anchor = GridBagConstraints.WEST;

	
		centerMiddlePanel.add(idLabel, in);
		in.insets = new Insets(5,80,5,5);
		in.gridy = 1;
		centerMiddlePanel.add(nameLabel, in);
		in.gridy = 2;
		centerMiddlePanel.add(priceLabel, in);
		in.gridy = 3;
		centerMiddlePanel.add(genreLabel, in);
		in.gridy = 4;
		centerMiddlePanel.add(qtyLabel, in);
	
		in.insets = new Insets(50, 5, 5, 80);
		in.gridx = 1;
		in.gridy = 0;
		centerMiddlePanel.add(idField, in);
		in.insets = new Insets(5, 5, 5, 80);
		in.gridy = 1;
		centerMiddlePanel.add(nameField, in);	
		in.gridy = 2;
		centerMiddlePanel.add(priceField, in);
		in.gridy = 3;
		centerMiddlePanel.add(genreBox, in);
		in.gridy = 4;
		centerMiddlePanel.add(qtySpinner, in);
		
		in.insets = new Insets(50, 80, 5, 5);
		in.gridx = 2;
		in.gridy = 0;

		centerMiddlePanel.add(newNameLabel, in);
		in.insets = new Insets(5,80,5,5);
		in.gridy = 1;
		centerMiddlePanel.add(newPriceLabel, in);
		in.gridy = 2;
		centerMiddlePanel.add(genreLabel, in);
		in.gridy = 3;
		centerMiddlePanel.add(newQtyLabel, in);
		in.gridy = 4;
		centerMiddlePanel.add(emptyLabel, in);
		in.insets = new Insets(50, 5, 5, 80);
		in.gridx = 3;
		in.gridy = 0;
		centerMiddlePanel.add(newGameField, in);
		in.insets = new Insets(5, 5, 5, 80);
		in.gridy = 1;
		centerMiddlePanel.add(newPriceField, in);
		in.gridy = 2;
		centerMiddlePanel.add(newGenreBox, in);
		in.gridy = 3;
		centerMiddlePanel.add(newQtySpinner, in);
		in.gridy = 4;
		centerMiddlePanel.add(emptyLabels, in);

		
		in.insets = new Insets(4, 4, 60, 4);
		in.gridx = 0;
		in.gridy = 0;
		in.anchor = GridBagConstraints.FIRST_LINE_START;
		buttonPanel.add(backButton, in);
		
		in.gridx = 1;
		in.anchor = GridBagConstraints.PAGE_START;
		buttonPanel.add(deleteButton, in);
		
		in.insets = new Insets(4, 4, 60, 200);
		in.gridx = 2;
		in.anchor = GridBagConstraints.FIRST_LINE_END;
		buttonPanel.add(updateButton, in);
		
		in.insets = new Insets(4, 190, 60, 4);
		in.gridx = 3;
		in.anchor = GridBagConstraints.LINE_END;
		buttonPanel.add(insertButton, in);
		
	
		buttonPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		southPanel.add(buttonPanel);
		
		
		pack();
		revalidate();
	}
	
	public JPanel makeContainer(LayoutManager layout) {
		JPanel panel = new JPanel(layout);
		return panel;
	}
	
	public JLabel makeLabel(String txt) {
		JLabel label = new JLabel(txt);
		label.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		return label;	
	}
	
	public JTextField makeTextfield() {
		JTextField txtField = new JTextField(15);
		return txtField;
	}
	

	
	void refreshTable() {
		dtm.setRowCount(0);
		String query = "SELECT `gameId`, `name`, `price`, `genreName`, `quantity` "
				+ "FROM `game` JOIN `genre` ON genre.genreId = game.genreId";
		ResultSet res = con.execQuery(query);
		
		try {
			while (res.next()) {
				dtm.addRow(new Object[] {res.getObject(1), 
						res.getObject(2), res.getObject(3), 
						res.getObject(4), res.getObject(5)});
							
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void updateData() {
		
		int[] p = {0, 0, 0, 0, 0};
		
		if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(null, "Select a game first!", "Warning", 
					JOptionPane.WARNING_MESSAGE);
		}
		else {
			String gameID = idField.getText();
			String gameName = nameField.getText();
			String gamePrice = priceField.getText();
			String gameGenre = genreBox.getSelectedItem().toString();
			Integer gameQty = Integer.parseInt(qtySpinner.getValue().toString());
			
			if (gameName.length() < 5 || gameName.length() > 35) {
				p[0] = 1;
				
			}

			
			try {
				Integer.parseInt(gamePrice);
			} catch (NumberFormatException e) {
				p[2] = 3;		
				
			}
			
			if (p[2] == 0) {
				if (Integer.parseInt(gamePrice) <= 0) {
					p[3] = 4;
				}
			}
			
			if (gameQty <= 0) {
				p[4] = 5;
			}
			
			boolean b = true;
			for (int i = 0; i < p.length && b; i++) {
				if (p[i] == 1) {
					JOptionPane.showMessageDialog(null, "Name must be between 5-30 Characters!", "Warning", 
							JOptionPane.WARNING_MESSAGE);					
					b = false;
				}
				else if (p[i] == 2) {
					JOptionPane.showMessageDialog(null, "Game name already exists", "Warning", 
							JOptionPane.WARNING_MESSAGE);
					b = false;
				}
				else if (p[i] == 3) {
					JOptionPane.showMessageDialog(null, "Price must be numeric", "Warning", 
							JOptionPane.WARNING_MESSAGE);
					b = false;
				}
				else if (p[i] == 4) {
					JOptionPane.showMessageDialog(null, "Price must be > 0", "Warning", 
							JOptionPane.WARNING_MESSAGE);
				}
				else if (p[i] == 5) {
					JOptionPane.showMessageDialog(null, "Quantity must be > 0", "Warning", 
							JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
			boolean t = true;
			if (p[0] == 0 && p[1] == 0 && p[2] == 0 && p[3] == 0 && p[4] == 0) {				
				for (int i = 0; i < gamePrice.length() && t; i++) {
					char index = gamePrice.charAt(i);
					if (!(index == '0')) {
						gamePrice = gamePrice.substring(i);					
						t = false;
					}				
				}
				
				String genreID = "";
				String queryIDcheck = "SELECT genreId from genre WHERE genreName = '" + gameGenre + "'";
				ResultSet rs = con.execQuery(queryIDcheck);				
				try {
					while (rs.next()) {
						genreID = rs.getObject(1).toString();
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				int gamePriceInt = Integer.parseInt(gamePrice);
				con.updatePrepareStatement(gameName, gamePriceInt, genreID, gameQty, gameID);
				refreshTable();
				JOptionPane.showMessageDialog(null, "Update Success", "Success", 
						JOptionPane.WARNING_MESSAGE);
				selectedIndex = -1;
			}
		}
		clearField();
	}
	
	void deleteData() {		
		if (selectedIndex == -1) {
			JOptionPane.showMessageDialog(null, "Select a game first!", "Warning", 
					JOptionPane.WARNING_MESSAGE);
		}
		else {			
			String id = idField.getText();
			con.deletePrepareStatement(id);
			refreshTable();
			selectedIndex = -1;
			JOptionPane.showMessageDialog(null, "Delete Success", "Success", 
					JOptionPane.WARNING_MESSAGE);
		}
		clearField();
	}
	
	void insertDataNew() {
		Random rand = new Random();
		String newGameID = "";
		String newName = newGameField.getText();
		int gamePrice = Integer.parseInt(newPriceField.getText());
		String newPrice = newPriceField.getText();
		String newGenre = newGenreBox.getSelectedItem().toString();
		int newQty = (Integer)newQtySpinner.getValue();
		String newGenreID = "";
		
		int[] p = {0, 0, 0, 0, 0};
		
		if (newName.hashCode() == 0 || newPrice.hashCode() == 0 || newGenre.hashCode() == 0) {
			JOptionPane.showMessageDialog(null, "All fields must be filled", "Warning", 
					JOptionPane.WARNING_MESSAGE);	
		}
			
			boolean b = true;
			for (int i = 0; i < p.length && b; i++) {
				if (p[i] == 1) {
					JOptionPane.showMessageDialog(null, "Name must be between 5-30 Characters!", "Warning", JOptionPane.WARNING_MESSAGE);					
					b = false;
				}
				else if (p[i] == 2) {
					JOptionPane.showMessageDialog(null, "Game name already exists", "Warning", JOptionPane.WARNING_MESSAGE);
					b = false;
				}
				else if (p[i] == 3) {
					JOptionPane.showMessageDialog(null, "Price must be numeric", "Warning", JOptionPane.WARNING_MESSAGE);
					b = false;
				}
				else if (p[i] == 4) {
					JOptionPane.showMessageDialog(null, "Price must be > 0", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if (p[i] == 5) {
					JOptionPane.showMessageDialog(null, "Quantity must be > 0", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
				
		}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
			
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			insertDataNew();
		}
		else if (e.getSource() == updateButton) {	
			updateData();
		}
		else if (e.getSource() == deleteButton) {
			deleteData();
		}
		else if (e.getSource() == backButton) {						
			dispose();
			new MainForm(id, username, role);
			
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tabel) {
			int row = tabel.getSelectedRow();
			selectedIndex = row;
			
			try {
				idField.setText(dtm.getValueAt(row, 0).toString());
				nameField.setText(dtm.getValueAt(row, 1).toString());
				priceField.setText(dtm.getValueAt(row, 2).toString());
				genreBox.setSelectedItem(dtm.getValueAt(row, 3).toString());
				qtySpinner.setValue(Integer.parseInt(dtm.getValueAt(row, 4).toString()));
			} catch (Exception e1) {
				clearField();
			}
			

		}
	}
	
	void clearField() {
		idField.setText("");
		nameField.setText("");
		priceField.setText("");
		genreBox.setSelectedIndex(0);
		qtySpinner.setValue(0);
		
		newGameField.setText("");
		newPriceField.setText("");
		newGenreBox.setSelectedIndex(0);
		newQtySpinner.setValue(0);
	}


}

