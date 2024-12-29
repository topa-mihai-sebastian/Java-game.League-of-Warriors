public class CharacterFactory {
    public static Character createCharacter(String type, String name, int level, int experience) {
        Character character;
        switch (type) {
            case "Warrior":
                character = new Warrior(name, level, experience, 20, 45, 35);
                break;
            case "Mage":
                character = new Mage(name, level, experience, 20, 45, 35);
                break;
            case "Rogue":
                character = new Rogue(name, level, experience, 20, 45, 35);
                break;
            default:
                throw new IllegalArgumentException("Unknown character type: " + type);
        }
        character.setCurrentHealth(character.maxHealth);
        character.setCurrentMana(1000);
        character.spells = Game.generateRandomSpells();
        return character;
    }
}