import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GameGUI {
	public static JFrame loginFrame;
	public static JFrame gameFrame;
	public static JPanel gridPanel;

	public GameGUI() {

	}

	public void createLoginGUI(ArrayList<Account> accounts) {
		loginFrame = new JFrame("League of Warriors - Login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(400, 300);
		loginFrame.setLayout(new GridLayout(4, 2));

		JLabel emailLabel = new JLabel("Email:");
		JTextField emailField = new JTextField();
		JLabel passwordLabel = new JLabel("Password:");
		JPasswordField passwordField = new JPasswordField();
		JButton loginButton = new JButton("Login");

		loginFrame.add(emailLabel);
		loginFrame.add(emailField);
		loginFrame.add(passwordLabel);
		loginFrame.add(passwordField);
		loginFrame.add(new JLabel()); // pt ca butonul de login sa fie fix sub
		loginFrame.add(loginButton);

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String password = new String(passwordField.getPassword());
				boolean loginSuccess = false;

				for (Account account : accounts) {
					if (email.equals(account.getInformation().getCredentials().getEmail()) &&
							password.equals(account.getInformation().getCredentials().getPassword())) {
						loginSuccess = true;
						Game.loggedInAccount = account;
						JOptionPane.showMessageDialog(loginFrame, "Login successful!");
						loginFrame.dispose();
						createCharacterSelectionGUI();
						break;
					}
				}

				if (!loginSuccess) {
					JOptionPane.showMessageDialog(loginFrame, "Invalid email or password. Please try again.");
				}
			}
		});

		loginFrame.setVisible(true);
	}

	public void createCharacterSelectionGUI() {
		loginFrame = new JFrame("League of Warriors - Select Character");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(400, 300);
		loginFrame.setLayout(new GridLayout(4, 1));

		JLabel selectLabel = new JLabel("Select your character:");
		loginFrame.add(selectLabel);

		ButtonGroup group = new ButtonGroup();
		for (Character character : Game.loggedInAccount.getCharacters()) {
			JRadioButton radioButton = new JRadioButton(character.getName() + " - " + character.getProfession());
			radioButton.setActionCommand(character.getProfession());
			group.add(radioButton);
			loginFrame.add(radioButton);
		}

		JButton selectButton = new JButton("Select");
		loginFrame.add(selectButton);

		selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedProfession = group.getSelection().getActionCommand();
				Game.createCharacter(selectedProfession);
				loginFrame.dispose();
				createGameGUI();
			}
		});

		loginFrame.setVisible(true);
	}

	private void updatePlayerInfo(JLabel healthLabel, JLabel manaLabel, JLabel strengthLabel, JLabel dexterityLabel, JLabel charismaLabel) {
		healthLabel.setText("Health: " + Game.currentCharacter.getCurrentHealth());
		manaLabel.setText("Mana: " + Game.currentCharacter.getCurrentMana());
		strengthLabel.setText("Strength: " + Game.currentCharacter.getStrength());
		dexterityLabel.setText("Dexterity: " + Game.currentCharacter.getDexterity());
		charismaLabel.setText("Charisma: " + Game.currentCharacter.getCharisma());
	}

	public void recreateGameFrame(int newWidth, int newHeight) {
		gameFrame.dispose(); // Închide fereastra curentă
		Game.gameGrid = Grid.createTheGrid(newWidth, newHeight, Game.currentCharacter); 
		createGameGUI();
	}

	private void usePortal() {
		Game.onPortal = false;
		Random rd = new Random();
		int width = rd.nextInt(7) + 4;
		int height = rd.nextInt(7) + 4;
		Game.currentCharacter.setCurrentHealth(100);
		recreateGameFrame(width, height); // Specifică noile dimensiuni ale gridului
	}

	public void createGameGUI() {
		gameFrame = new JFrame("League of Warriors - Game");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(1200, 1000);
		gameFrame.setLayout(new BorderLayout());

		// Panel pentru grid
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(Game.gameGrid.getHeight(), Game.gameGrid.getWidth()));
		gridPanel.setBackground(Color.LIGHT_GRAY); // Setează fundalul colorat al gridPanel
		gameFrame.add(gridPanel, BorderLayout.CENTER);

		// detalii despre jucător
		JPanel playerInfoPanel = new JPanel();
		playerInfoPanel.setBackground(Color.LIGHT_GRAY);
		playerInfoPanel.setLayout(new GridLayout(1, 6)); // 6 rânduri pentru informațiile suplimentare
		JLabel characterName = new JLabel("Profession: " + Game.currentCharacter.getProfession());
		JLabel healthLabel = new JLabel("Health: " + Game.currentCharacter.getCurrentHealth());
		JLabel manaLabel = new JLabel("Mana: " + Game.currentCharacter.getCurrentMana());
		JLabel strengthLabel = new JLabel("Strength: " + Game.currentCharacter.getStrength());
		JLabel dexterityLabel = new JLabel("Dexterity: " + Game.currentCharacter.getDexterity());
		JLabel charismaLabel = new JLabel("Charisma: " + Game.currentCharacter.getCharisma());
		playerInfoPanel.add(characterName);
		playerInfoPanel.add(healthLabel);
		playerInfoPanel.add(manaLabel);
		playerInfoPanel.add(strengthLabel);
		playerInfoPanel.add(dexterityLabel);
		playerInfoPanel.add(charismaLabel);
		gameFrame.add(playerInfoPanel, BorderLayout.NORTH);

		// Panel pentru butoanele de mișcare
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2, 2));

		JButton northButton = new JButton("Go North");
		northButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Game.gameGrid.goNorth()) {
						createBattleGUI();
						updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
					}
					if (Game.onPortal) {
						usePortal();
						updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
						return;
					}
					updateGrid();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(gameFrame, ex.getMessage());
				}
				updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
			}
		});
		controlPanel.add(northButton);

		JButton southButton = new JButton("Go South");
		southButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Game.gameGrid.goSouth()) {
						createBattleGUI();
						updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
					}
					if (Game.onPortal) {
						usePortal();
						updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
						return;
					}
					updateGrid();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(gameFrame, ex.getMessage());
				}
				updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
			}
		});
		controlPanel.add(southButton);

		JButton eastButton = new JButton("Go East");
		eastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Game.gameGrid.goEast()) {
						createBattleGUI();
						updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
					}
					if (Game.onPortal) {
						usePortal();
						updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
						return;
					}
					updateGrid();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(gameFrame, ex.getMessage());
				}
				updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
			}
		});
		controlPanel.add(eastButton);

		JButton westButton = new JButton("Go West");
		westButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Game.gameGrid.goWest()) {
						createBattleGUI();
						updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
					}
					if (Game.onPortal) {
						usePortal();
						updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
						return;
					}
					updateGrid();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(gameFrame, ex.getMessage());
				}
				updatePlayerInfo(healthLabel, manaLabel, strengthLabel, dexterityLabel, charismaLabel);
			}
		});
		controlPanel.add(westButton);

		gameFrame.add(controlPanel, BorderLayout.SOUTH);

		updateGrid(); // Inițializează grid-ul la început

		gameFrame.setVisible(true);
	}

	public static void useSpellGUI(Character currentCharacter, Enemy currentEnemy, JFrame gameFrame) {
		ArrayList<Spell> spells = currentCharacter.getSpells();

		if (spells.isEmpty()) {
			JOptionPane.showMessageDialog(gameFrame, "No spells available. Using default attack.");
			currentCharacter.defaultAttack(currentEnemy);
			return;
		}

		String[] spellOptions = new String[spells.size()];
		for (int i = 0; i < spells.size(); i++) {
			spellOptions[i] = spells.get(i).toString();
		}

		String chosenSpellName = (String) JOptionPane.showInputDialog(
				gameFrame,
				"Choose a spell to cast:",
				"Spell Selection",
				JOptionPane.PLAIN_MESSAGE,
				null,
				spellOptions,
				spellOptions[0]);

		if (chosenSpellName == null) {
			JOptionPane.showMessageDialog(gameFrame, "No spell selected. Using default attack.");
			currentCharacter.defaultAttack(currentEnemy);
			return;
		}

		Spell chosenSpell = null;
		for (Spell spell : spells) {
			if (spell.toString().equals(chosenSpellName)) {
				chosenSpell = spell;
				break;
			}
		}

		if (chosenSpell != null && currentCharacter.getCurrentMana() >= chosenSpell.getManaCost()) {
			chosenSpell.visit(currentEnemy); // Visitor Pattern
			spells.remove(chosenSpell); // Remove the spell after casting
		} else if (chosenSpell != null) {
			JOptionPane.showMessageDialog(gameFrame,
					"Not enough mana to cast " + chosenSpell + ". Using default attack.");
			currentCharacter.defaultAttack(currentEnemy);
		}
	}

	public void createBattleGUI() {
		JFrame battleFrame = new JFrame("League of Warriors - Battle");
		battleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		battleFrame.setSize(600, 400);
		battleFrame.setLayout(new BorderLayout());

		// Panel pentru informațiile despre caracter și inamic
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 1));

		JLabel characterInfo = new JLabel("Character: " + Game.currentCharacter.getName() + " - Health: "
				+ Game.currentCharacter.getCurrentHealth() + " - Mana: " + Game.currentCharacter.getCurrentMana(),
				SwingConstants.CENTER);
		JLabel enemyInfo = new JLabel("Enemy " + " - Health: " + Game.currentEnemy.getCurrentHealth(),
				SwingConstants.CENTER);

		infoPanel.add(characterInfo);
		infoPanel.add(enemyInfo);

		battleFrame.add(infoPanel, BorderLayout.NORTH);

		// panel pentru butoanele de atac si vraji
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new GridLayout(1, 2));

		JButton attackButton = new JButton("Attack");
		attackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.currentCharacter.defaultAttack(Game.currentEnemy);
				updateBattleInfo(characterInfo, enemyInfo);
				checkBattleOutcome(battleFrame);

				// Enemy attacks after player attack
				if (Game.currentEnemy.getCurrentHealth() > 0) {
					enemyAttack();
					updateBattleInfo(characterInfo, enemyInfo);
					checkBattleOutcome(battleFrame);
				}
				if (Game.currentCharacter.getCurrentHealth() <= 0) {
					JOptionPane.showMessageDialog(gameFrame,
							"You have lost the game. Your character has no health left.", "Game Over",
							JOptionPane.INFORMATION_MESSAGE);
					gameFrame.dispose();
					createCharacterSelectionGUI();
				}
			}
		});
		actionPanel.add(attackButton);

		JButton spellButton = new JButton("Use Spell");
		spellButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				useSpellGUI(Game.currentCharacter, Game.currentEnemy, battleFrame);
				updateBattleInfo(characterInfo, enemyInfo);
				checkBattleOutcome(battleFrame);

				// Enemy attacks after player uses a spell
				if (Game.currentEnemy.getCurrentHealth() > 0) {
					enemyAttack();
					updateBattleInfo(characterInfo, enemyInfo);
					checkBattleOutcome(battleFrame);
				}
				if (Game.currentCharacter.getCurrentHealth() <= 0) {
					JOptionPane.showMessageDialog(gameFrame,
							"You have lost the game. Your character has no health left.", "Game Over",
							JOptionPane.INFORMATION_MESSAGE);
					gameFrame.dispose();
				}
			}
		});
		actionPanel.add(spellButton);
		actionPanel.add(spellButton);

		battleFrame.add(actionPanel, BorderLayout.SOUTH);

		battleFrame.setVisible(true);
	}

	private void updateBattleInfo(JLabel characterInfo, JLabel enemyInfo) {
		characterInfo.setText("Character: " + Game.currentCharacter.getName() + " - Health: "
				+ Game.currentCharacter.getCurrentHealth() + " - Mana: " + Game.currentCharacter.getCurrentMana());
		enemyInfo.setText("Enemy " + " - Health: " + Game.currentEnemy.getCurrentHealth());
	}

	private void checkBattleOutcome(JFrame battleFrame) {
		if (Game.currentEnemy.getCurrentHealth() <= 0) {
			JOptionPane.showMessageDialog(battleFrame, "Enemy defeated!");
			battleFrame.dispose();
			Game.currentCharacter.spells = Game.generateRandomSpells();
			Game.currentCharacter.Charisma *= 1.3;
			Game.currentCharacter.Strength *= 1.3;
			Game.currentCharacter.Dexterity *= 1.3;
			Game.currentCharacter.setCurrentMana(1000);
		} else if (Game.currentCharacter.getCurrentHealth() <= 0) {
			JOptionPane.showMessageDialog(battleFrame, "You have been defeated!");
			battleFrame.dispose();
		}
	}

	private void enemyAttack() {
		Random rd = new Random();
		ArrayList<Spell> spells = Game.currentEnemy.getSpells();

		if (!spells.isEmpty() && rd.nextBoolean()) {
			// Enemy uses a spell
			int choice = rd.nextInt(spells.size());
			Spell chosenSpell = spells.get(choice);
			if (Game.currentEnemy.getCurrentMana() >= chosenSpell.getManaCost()) {
				chosenSpell.visit(Game.currentCharacter); // Visitor Pattern
				spells.remove(chosenSpell); // Remove the spell after casting
			} else {
				Game.currentEnemy.defaultAttack(Game.currentCharacter);
			}
		} else {
			// Enemy uses default attack
			Game.currentEnemy.defaultAttack(Game.currentCharacter);
		}
	}

	public void updateGrid() {
		System.out.println(Game.gameGrid.getHeight());
		System.out.println(Game.gameGrid.getWidth());
		gridPanel.removeAll();
		for (int i = 0; i < Game.gameGrid.getHeight(); i++) {
			for (int j = 0; j < Game.gameGrid.getWidth(); j++) {
				Cell cell = Game.gameGrid.getCell(i, j);
				JLabel cellLabel;
				switch (cell.getType()) {
					case VOID:
						cellLabel = new JLabel("*", SwingConstants.CENTER);
						break;
					case PLAYER:
						cellLabel = new JLabel("P", SwingConstants.CENTER);
						break;
					case ENEMY:
						cellLabel = new JLabel("E", SwingConstants.CENTER);
						break;
					case SANCTUARY:
						cellLabel = new JLabel("S", SwingConstants.CENTER);
						break;
					case PORTAL:
						cellLabel = new JLabel("O", SwingConstants.CENTER);
						break;
					default:
						cellLabel = new JLabel(" ", SwingConstants.CENTER);
						break;
				}
				cellLabel.setPreferredSize(new Dimension(50, 50));
				cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				gridPanel.add(cellLabel);
			}
		}
		gridPanel.revalidate();
		gridPanel.repaint();
	}
}
