public class User {
	Integer userId;
	String username, password, gender, country, role;
	
	public User(String username, String password, String gender, String country, String role, Integer userId) 
	{
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.country = country;
		this.role = role;
		
	}

	public User() {
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
