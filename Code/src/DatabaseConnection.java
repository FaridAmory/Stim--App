import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;
import java.sql.*;

public class DatabaseConnection {
	
	public Connection connection;
	public Statement stt;
	public ResultSet rs;
	public ResultSetMetaData rsmd;
	public PreparedStatement ps;
	private int gamePrice;
	
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stim", "root", "");
			
			stt = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet execQuery (String query) {
		try {
			rs = stt.executeQuery(query);
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}
	
	
	public ResultSet getUserData() {
		try {
			ps = connection.prepareStatement("SELECT * FROM User");
			
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;

	}

	
	public boolean insertnewTransaction(String userId, String gameId, Integer quantity) {
		try {
			ps = connection.prepareStatement("INSERT INTO transaction (`TransactionId`, `UserId`, `gameId`, `gameQuantity`) "
					+ "VALUES (?,?,?,?)");
			
			Random r = new Random( System.currentTimeMillis() );
		    int transactionID = ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
			
		    ps.setInt(1, transactionID);
		    ps.setString(2, userId);
		    ps.setString(3, gameId);
		    ps.setInt(4, quantity);
			
		    ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void updateTransaction(int quantity, String gameId, int userId) {
		try {
			ps = connection.prepareStatement("UPDATE `transaction` SET `gameQuantity`=? "
					+ "WHERE gameId =? AND userId = ?");
			ps.setInt(1, quantity);
			ps.setString(2, gameId);
			ps.setInt(3, userId);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertBuyGames(int userId, String gameId, int gameQuantity) {
		try {
			ps = connection.prepareStatement("INSERT INTO `transaction`(`userId`, `gameId`, `gameQuantity`) "
					+ "VALUES (?, ?, ?)");

			ps.setInt(1, userId);
			ps.setString(2, gameId);
			ps.setInt(3, gameQuantity);

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateAfterBuy(int quantity, String gameId) {
		try {
			ps = connection.prepareStatement("UPDATE `game` SET `quantity`=? "
					+ "WHERE gameId =?");
			ps.setInt(1, quantity);
			ps.setString(2, gameId);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Vector<String> getGenreList() {
		Vector<String> genreList = new Vector<String>();
		
		try {
			ps = connection.prepareStatement("SELECT * FROM genre");
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			
			while (rs.next()) {
				genreList.add(rs.getString("genreName"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genreList;
	}
	public User getUserDataFromUsername(String username) {
		User userdata = null;
		try {
			ps = connection.prepareStatement("SELECT * FROM User WHERE Username = ?");
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			rsmd = ps.getMetaData();
		
			if(rs.next()) {
				userdata = new User();
				
				userdata.setUserId(rs.getInt("userId"));
				userdata.setUsername(rs.getString("username"));
				userdata.setPassword(rs.getString("password"));
				userdata.setGender(rs.getString("gender"));
				userdata.setCountry(rs.getString("country"));
				userdata.setRole(rs.getString("role"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return userdata;		
	}
	public boolean insertNewUser(User newUser) {
		try {
			ps = connection.prepareStatement("INSERT INTO User (`UserId`, `Username`, `Password`, `Gender`, `Country`, `Role`) "
					+ "VALUES (?,?,?,?,?,?)");
			
			ps.setInt(1, newUser.getUserId());
			ps.setString(2, newUser.getUsername());
			ps.setString(3, newUser.getPassword());
			ps.setString(4, newUser.getGender());
			ps.setString(5, newUser.getCountry());
			ps.setString(6, newUser.getRole());
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void updatePrepareStatement(String gameName, int gamePriceInt, String genreID, Integer gameQty,
			String gameID) {
		try {
			ps = connection.prepareStatement("UPDATE `game` SET `name`=?, `price`=?, `genreId`=?, `quantity`=? "
					+ "WHERE gameId =?");
			ps.setString(1, gameName);
			ps.setInt(2, gamePrice);
			ps.setString(3, genreID);
			ps.setInt(4, gameQty);
			ps.setString(5, gameID);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletePrepareStatement(String id) {
		try {
			ps = connection.prepareStatement("DELETE FROM `game` WHERE gameId = ?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertPrepareStatement(String newGameID, String newName, int newPrice, String newGenreID, int NewGameQty) {
			try {
				ps = connection.prepareStatement(
						"INSERT INTO `game`(`gameId`, `name`, `price`, `genreId`, `quantity`) " + "VALUES (?,?,?,?,?)");
				ps.setString(1, newGameID);
				ps.setString(2, newName);
				ps.setInt(3, gamePrice);
				ps.setString(4, newGenreID);
				ps.setInt(5, NewGameQty);

				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	

}
	
	



