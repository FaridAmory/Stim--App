
public class Game {
	String gameId, name, genreId, genreName;
	Integer price, quantity;
	public Game(String gameId, String name, Integer price, String genderId, Integer quantity, String genreId) {
		super();
		this.gameId = gameId;
		this.name = name;
		this.price = price;
		this.genreId = genreId;
		this.quantity = quantity;
	
	}
	
	public Game() {
		
	}
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenreId() {
		return genreId;
	}
	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
