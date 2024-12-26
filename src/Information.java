import java.util.List;

public class Information {
	private Credentials credentials;
	private String name;
	private String country;
	private List<String> favoriteGames;
	private int mapsCompleted;

	private Information(Builder builder) {
		this.credentials = builder.credentials;
		this.name = builder.name;
		this.country = builder.country;
		this.favoriteGames = builder.favoriteGames;
		this.mapsCompleted = builder.mapsCompleted;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public List<String> getFavoriteGames() {
		return favoriteGames;
	}

	public int getMapsCompleted() {
		return mapsCompleted;
	}

	public static class Builder {
		private Credentials credentials;
		private String name;
		private String country;
		private List<String> favoriteGames;
		private int mapsCompleted;

		public Builder setCredentials(Credentials credentials) {
			this.credentials = credentials;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}

		public Builder setFavoriteGames(List<String> favoriteGames) {
			this.favoriteGames = favoriteGames;
			return this;
		}

		public Builder setMapsCompleted(int mapsCompleted) {
			this.mapsCompleted = mapsCompleted;
			return this;
		}

		public Information build() {
			return new Information(this);
		}
	}
}
