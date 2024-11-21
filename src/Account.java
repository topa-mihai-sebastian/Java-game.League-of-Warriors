import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Account {
    private Information information;
    private List<Character> characters;
    private int gamesPlayed;

    // Constructor al clasei Account
    public Account(Information information, int gamesPlayed) {
        this.information = information;
        this.characters = new ArrayList<>();
        this.gamesPlayed = gamesPlayed;
    }

    // Metode pentru a adăuga și a obține informații despre jucător
    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    // Metode pentru a adăuga și a obține personaje
    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    // Metode pentru a obține și a seta numărul de jocuri jucate
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    // Clasa Information
    static class Information {
        private Credentials credentials;
        private TreeSet<String> favoriteGames;
        private String name;
        private String country;

        // Constructor al clasei Information
        public Information(Credentials credentials, String name, String country) {
            this.credentials = credentials;
            this.favoriteGames = new TreeSet<String>();
            this.name = name;
            this.country = country;
        }

        // Metode getter și setter pentru Information
        public Credentials getCredentials() {
            return credentials;
        }

        public void setCredentials(Credentials credentials) {
            this.credentials = credentials;
        }

        public TreeSet<String> getFavoriteGames() {
            return favoriteGames;
        }

        public void addFavoriteGame(String game) {
            this.favoriteGames.add(game);
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
    }

    // Clasa Credentials
    static class Credentials {
        private String username;
        private String password;

        // Constructor al clasei Credentials
        public Credentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // Metode getter și setter pentru Credentials
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
    }
}
