package SportsClub;

public class ClubMember {
	private String name;
	private String address;
	private int yearOfRegistration;
	private String email;

	public ClubMember(String name, String address, int yearOfRegistration, String email)
	{
		this.name = name; 
		this.address = address;
		this.yearOfRegistration = yearOfRegistration;
		this.email = email;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getYearOfRegistration() {
		return yearOfRegistration;
	}

	public void setYearOfRegistration(int yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}