public class Credentials {
	private String email;
	private String password;

	// Constructor pentru inițializarea câmpurilor
	public Credentials(String email, String password) {
		this.email = email;
		this.password = password;
	}

	// Getter pentru email
	public String getEmail() {
		return email;
	}

	// Setter pentru email (opțional, dacă este nevoie să se modifice ulterior)
	public void setEmail(String email) {
		this.email = email;
	}

	// Getter pentru password
	public String getPassword() {
		return password;
	}

	// Setter pentru password (opțional)
	public void setPassword(String password) {
		this.password = password;
	}
}
