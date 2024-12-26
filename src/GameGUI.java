import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameGUI {
	private JFrame loginFrame;
	private JFrame gameFrame;
	private JPanel gridPanel;

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
		loginFrame.add(new JLabel()); // Placeholder
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

	public void createGameGUI() {
		gameFrame = new JFrame("League of Warriors - Game");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(800, 600);
		gameFrame.setLayout(new BorderLayout());

		// Panel pentru grid
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(Game.gameGrid.getHeight(), Game.gameGrid.getWidth()));
		gameFrame.add(gridPanel, BorderLayout.CENTER);

		// Panel pentru butoanele de mișcare
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2, 2));

		JButton northButton = new JButton("Go North");
		northButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Game.gameGrid.goNorth()) {
						Game.gameGrid.battle(Game.currentEnemy, Game.currentCharacter);
					}
					updateGrid();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(gameFrame, ex.getMessage());
				}
			}
		});
		controlPanel.add(northButton);

		JButton southButton = new JButton("Go South");
		southButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Game.gameGrid.goSouth()) {
						Game.gameGrid.battle(Game.currentEnemy, Game.currentCharacter);
					}
					updateGrid();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(gameFrame, ex.getMessage());
				}
			}
		});
		controlPanel.add(southButton);

		JButton eastButton = new JButton("Go East");
		eastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Game.gameGrid.goEast()) {
						Game.gameGrid.battle(Game.currentEnemy, Game.currentCharacter);
					}
					updateGrid();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(gameFrame, ex.getMessage());
				}
			}
		});
		controlPanel.add(eastButton);

		JButton westButton = new JButton("Go West");
		westButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Game.gameGrid.goWest()) {
						Game.gameGrid.battle(Game.currentEnemy, Game.currentCharacter);
					}
					updateGrid();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(gameFrame, ex.getMessage());
				}
			}
		});
		controlPanel.add(westButton);

		gameFrame.add(controlPanel, BorderLayout.SOUTH);

		updateGrid(); // Inițializează grid-ul la început

		gameFrame.setVisible(true);
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

		// Panel pentru butoanele de atac și vrăji
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new GridLayout(1, 2));

		JButton attackButton = new JButton("Attack");
		attackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.currentCharacter.defaultAttack(Game.currentEnemy);
				updateBattleInfo(characterInfo, enemyInfo);
				checkBattleOutcome(battleFrame);
			}
		});
		actionPanel.add(attackButton);

		JButton spellButton = new JButton("Use Spell");
		spellButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.useSpell(Game.currentCharacter, Game.currentEnemy);
				updateBattleInfo(characterInfo, enemyInfo);
				checkBattleOutcome(battleFrame);
			}
		});
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
		} else if (Game.currentCharacter.getCurrentHealth() <= 0) {
			JOptionPane.showMessageDialog(battleFrame, "You have been defeated!");
			battleFrame.dispose();
		}
	}

	public void updateGrid() {
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
				cellLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				gridPanel.add(cellLabel);
			}
		}
		gridPanel.revalidate();
		gridPanel.repaint();
	}
}