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
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class BuyGameForm extends JFrame implements ActionListener,  MouseListener {
	
	JPanel mainPanel, northPanel, IdPanel, namePanel, pricePanel, genrePanel, qtyPanel, checkPanel, buttonPanel;
	JLabel idLabel, nameLabel, priceLabel, genreLabel, qtyLabel, checkLabel;
	JTextField idField, nameField, priceField, genreField;
	JSpinner qtySpinner;
	JCheckBox refundBox;
	JButton backButton, buyButton;
	
	DatabaseConnection con = new DatabaseConnection();
	
	User loggedUser;
	JTable table;
	DefaultTableModel dtm;
	
	int id;
	String username;
	String role;
	String gameQuantityMax;

	public BuyGameForm(User loggedUser) {
		this.loggedUser = loggedUser;
		framesettings();
		initComponents();
		setVisible(true);
		revalidate();
	
	}
	
	
	public BuyGameForm() {
		
	}

	private void framesettings() {
		setSize(900,900);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
	}

	private void initComponents() {
		String[] columnNames = {"Game ID", "Game Name", "Game Price", "Genre", "Quantity" };
		
		dtm = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
		}
		};
		
		

		northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		
		refreshData();
		table = new JTable(dtm);
		table.setPreferredSize(new Dimension(600, 450));
		table.setRowHeight(30);
//		table.setRowSelectionInterval(0, 0);
		JScrollPane scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);
		mainPanel.add(scroll);
		
		
		// ID
		IdPanel = new JPanel(new FlowLayout());
		mainPanel.add(IdPanel);
		
		
		
		// LabeL
		idLabel = new JLabel("Game ID");
		idLabel.setPreferredSize(new Dimension(340,30));
		idLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		// Field
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(310,30));
		
		// Panel 
		IdPanel.add(idLabel);
		IdPanel.add(idField);
		
		// name
		namePanel = new JPanel(new FlowLayout());
		mainPanel.add(namePanel);
		
		// Label
		nameLabel = new JLabel("Game Name");
		nameLabel.setPreferredSize(new Dimension(340,30));
		nameLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		// Field
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
		qtyLabel = new JLabel("How many do you want to buy?");
		qtyLabel.setPreferredSize(new Dimension(340,30));
		qtyLabel.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		
		// Spinner
		qtySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		qtySpinner.setPreferredSize(new Dimension(310,30));
		
		
		// Panel
		qtyPanel.add(qtyLabel);
		qtyPanel.add(qtySpinner);
		
		// Check
		checkPanel = new JPanel(new FlowLayout());
		mainPanel.add(checkPanel);
		
		// Label
		checkLabel = new JLabel("Once bought game cannot be returned!");
		checkLabel.setPreferredSize(new Dimension(340,30));
		
		// Box
		refundBox = new JCheckBox();
		refundBox.setPreferredSize(new Dimension(310,30));
		
		// Panel
		checkPanel.add(checkLabel);
		checkPanel.add(refundBox);
		
		
		// Button
		buttonPanel = new JPanel(new GridLayout(1, 3, 15, 0));
		mainPanel.add(buttonPanel);
		
		// Label button
		backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(90, 40));
		buyButton = new JButton("Buy Game");
		buyButton.setPreferredSize(new Dimension(90, 40));
		
		
		
		buttonPanel.add(backButton);
		buttonPanel.add(buyButton);
		buttonPanel.setPreferredSize(new Dimension(600, 40));

		
		
		// ActionListener
		backButton.addActionListener(this);
		buyButton.addActionListener(this);
		table.addMouseListener(this);
		
		// Set editable
		idField.setEditable(false);
		nameField.setEditable(false);
		priceField.setEditable(false);
		genreField.setEditable(false);
		System.out.println("berhasil");
		
		
		
	}
	
	private void refreshData() {
		dtm.setRowCount(0);
		String query  = "SELECT `gameId`, `name`, `price`, `genreName`, `quantity`" + "FROM `game` JOIN `genre` ON genre.genreId = game.genreId ";	
		ResultSet res = con.execQuery(query);
		
		try {
			while (res.next()) {
				dtm.addRow(new Object[] {res.getObject(1), res.getObject(2), res.getObject(3), res.getObject(4), res.getObject(5)});
				
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		
		try {
			idField.setText(dtm.getValueAt(row, 0).toString());
			  nameField.setText(dtm.getValueAt(row, 1).toString());
			  priceField.setText(dtm.getValueAt(row, 2).toString());
			  genreField.setText(dtm.getValueAt(row, 3).toString());
			  gameQuantityMax = dtm.getValueAt(row, 4).toString();
		} catch (Exception e2) {
			clear();
		}
		  
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

	public void itemStateChanged(ItemEvent arg0) {
		
	}
	
	void clear() {
		idField.setText("");
		nameField.setText("");
		genreField.setText("");
		priceField.setText("");
		refundBox.setSelected(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String valueSpin = qtySpinner.getValue().toString();
		int valueSpinIntMax = Integer.parseInt(gameQuantityMax);
		int valueSpinInt = Integer.parseInt(valueSpin);
		int row = table.getSelectedRow();
		int gameQty = Integer.parseInt(qtySpinner.getValue().toString());
		String gameId = dtm.getValueAt(row, 0).toString();
		boolean bool = true;
		
		if(valueSpinInt == 0 || valueSpinInt > valueSpinIntMax) {
			JOptionPane.showMessageDialog(null, "Game Quantity cannot be less than 0 or more than stock",
					"Warning", JOptionPane.WARNING_MESSAGE);
		}else if (!refundBox.isSelected()) {
			JOptionPane.showMessageDialog(null, "Check box must be checked", "Warning",
					JOptionPane.WARNING_MESSAGE);
		
		}else{
			int updateGameQuantity = Integer.parseInt(dtm.getValueAt(row, 4).toString()) - gameQty;
			int updateTransactionQty = 0;
			
			String queryDuplicate = "SELECT game.gameId, gameQuantity FROM game "
					+ "JOIN transaction ON game.gameId = transaction.gameId "
					+ "WHERE userId = '" + id + "' "
					+ "AND game.gameId = '" + gameId + "'";
			ResultSet rs = con.execQuery(queryDuplicate);
			
			try {
				while (rs.next()) {
					
					if (rs.getString("gameId").equals(gameId)) {
						updateTransactionQty = rs.getInt("gameQuantity") + gameQty;
						con.updateTransaction(updateTransactionQty, gameId, id);
						
						bool = false;
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			if (bool) {
				con.insertBuyGames(id, gameId, gameQty);
			}
			con.updateAfterBuy(updateGameQuantity, gameId);
			JOptionPane.showMessageDialog(null, "Game Bought", "Success", JOptionPane.WARNING_MESSAGE);

			clear();
			refreshData();
			
			}
		if (e.getSource() == backButton ) {
			this.dispose();
			new MainForm();
		}
		}
		

	}
