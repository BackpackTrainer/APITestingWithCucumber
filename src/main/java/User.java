import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private String login;
	private String name;
	private String location;
	private String company;
	private int public_repos;
	
	public User()  {}
	
	public User(String name, String location, String company)  {
		this.name = name;
		this.location = location;
		this.company = company;
	}
	
	@Override
	public boolean equals(Object o)  {
		boolean result = false;
		
		if(!(o instanceof User)) {
			return result;
		}
		
		User user  = (User) o;
		if(user.getName().equals(this.name) && user.getLocation().equals(this.location) && user.getCompany().equals(this.company))  {
			result = true;
		}
		
		return result;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getPublic_repos() {
		return public_repos;
	}
	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}

}
