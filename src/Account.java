import java.util.ArrayList;
import java.util.List;

public class Account {
	public Information information;
	private ArrayList<Character> characters;
	private int gamesPlayed;

	public Account(ArrayList<Character> characters, int gamesPlayed, Information information) {
		this.information = information;
		this.characters = characters;
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
}
