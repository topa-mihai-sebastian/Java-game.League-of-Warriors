import java.util.List;

public class Information {
	private Credentials credentials;
	private String name;
	private String country;
	private List<String> favoriteGames;
	private int mapsCompleted;

	public Information(Credentials credentials, String name, String country, List<String> favoriteGames,
			int mapsCompleted) {
		this.credentials = credentials;
		this.name = name;
		this.country = country;
		this.favoriteGames = favoriteGames;
		this.mapsCompleted = mapsCompleted;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getFavoriteGames() {
		return favoriteGames;
	}

	public void setFavoriteGames(List<String> favoriteGames) {
		this.favoriteGames = favoriteGames;
	}

	public int getMapsCompleted() {
		return mapsCompleted;
	}

	public void setMapsCompleted(int mapsCompleted) {
		this.mapsCompleted = mapsCompleted;
	}
}
