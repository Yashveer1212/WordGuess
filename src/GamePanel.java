import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	JTextArea background;
	JLabel instruct;
	JLabel bounds;
	JLabel upper;
	JLabel lower;
	JLabel score;
	JLabel info;
	JLabel error;
	String lWord;
	String uWord;
	JTextField field;
	JButton submit;
	JRadioButton yes;
	JRadioButton no;
	int scoreNum, upperNum, lowerNum = 0;
	private String answer;
	int answerNum;
	private Map<String, Object> map;
	String[] words;
	public static Library mlib = new Library();
	public static int size = mlib.getmWords().length;
	public static Random rand = new Random(size);
	ButtonGroup group = new ButtonGroup();


	public GamePanel(String upperWord, String lowerWord, String realWord,
			Map<String, Object> wordMap) {
		answer = realWord;
		words = mlib.getmWords();
		map = wordMap;
		answerNum = (Integer) map.get(answer);

		lWord = lowerWord;
		uWord = upperWord;
		upperNum = (Integer) map.get(uWord);
		lowerNum = (Integer) map.get(lWord);
		setBackground(Color.white);
		setPreferredSize(new Dimension(570, 210));
		setFont(new Font("Courier", Font.PLAIN, 20));

		background = new JTextArea("Guess a 5 letter m-word!" + "\n"
				+ "The word will be between the given bounds." + "\n"
				+ "You may guess as many times as you like." + "\n");
		background.setFont(new Font("Courier", Font.PLAIN, 20));

		upper = new JLabel("Current Bounds Upper:" + uWord + "   ");
		upper.setFont(new Font("Courier", Font.PLAIN, 20));

		lower = new JLabel("Lower:" + lWord);
		lower.setFont(new Font("Courier", Font.PLAIN, 20));

		instruct = new JLabel("What is your guess?");
		instruct.setFont(new Font("Courier", Font.PLAIN, 20));

		score = new JLabel("Score: " + scoreNum);

		info = new JLabel("");

		error = new JLabel("");
		error.setFont(new Font("Courier", Font.PLAIN, 20));

		field = new JTextField(5);

		submit = new JButton("Submit");
		submit.setFont(new Font("Courier", Font.PLAIN, 20));

		submit.addActionListener(new ButtonListener());

		yes = new JRadioButton("Yes", true);
		yes.addActionListener(new ButtonListener());
		yes.setBackground(Color.cyan);
		yes.setVisible(false);
		no = new JRadioButton("No");
		no.addActionListener(new ButtonListener());
		no.setBackground(Color.cyan);
		no.setVisible(false);

		group.add(yes);
		group.add(no);
		group.clearSelection();

		add(background);
		add(upper);
		add(lower);
		add(instruct);

		add(field);
		add(submit);
		add(score);
		add(info);
		add(error);
		add(yes);
		add(no);

	}

	@SuppressWarnings("unused")
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent ae) {

			String guess;
			int guessNum = 0;
			boolean real;

			if (ae.getSource() == submit) {
				error.setText("");
				info.setText("");

				guess = field.getText();
				field.setText("");

				if (map.get(guess) != null) {
					guessNum = (Integer) map.get(guess);
					if (guessNum != -1)
						info.setText("You guessed: " + guess);
				} else {
					real = false;
				}

				// error checking
				if (guess == null) {
					error.setText("You have to guess something!");
				} else if (guess.length() > 5) {
					error.setText("Your guess is too long!");

				} else if (guess.equals("quit")) {
					// reveal the answers if quit
					System.exit(0);
				}

				if (map.get(guess) == null) {
					error.setText("That is not a  m-word");
					real = false;
				} else {
					real = true;
				}

				if (guess.equals(answer)) {
					score.setText("Score: " + ++scoreNum);
					info.setText("That is right! Congrats! Do you want to play again? Click Yes or No!"+ "\n");

					yes.setVisible(true);
					no.setVisible(true);
					/* reset the game */
					/*
					 * if (choice.equals("No") | choice.equals("N") |
					 * choice.equals("no") | choice.equals("n")) { play = false;
					 * System.out.println("Thank you for playing!"); break; }
					 * else { System.out.println("Starting a new game!");
					 * newGame(words); }
					 */
				} else {
					// error.setText("That is incorrect. Please guess Again!");

				}

				// update bounds
				if (real) {
					updateBounds(guessNum, upperNum, lowerNum, answerNum);
				}
				upper.setText("Current Bounds Upper:" + uWord + "   ");
				lower.setText("Lower:" + lWord);

				System.out.println("The word is between " + uWord + " and "
						+ lWord);

			}
			
			if(ae.getSource() == yes){
				resetGame();

			}
			
			if(ae.getSource() == no){
				System.exit(0);
			}
		}
	}

	public void setUpper(String newUpper, int guessedNum) {

		uWord = newUpper;
		upperNum = guessedNum;
	}

	public void setLower(String newLower, int guessedNum) {

		lWord = newLower;
		lowerNum = guessedNum;
	}

	public void updateBounds(int guess, int oldUpper, int oldLower, int answer) {

		// bounds should only update if the work makes the bounds smaller!

		if (guess < answer && guess > oldUpper) {
			setUpper(words[guess], guess);
		}

		if (guess > answer && guess < oldLower) {
			setLower(words[guess], guess);
		}

	}

	public void resetGame() {
		answerNum = rand.nextInt(mlib.getSize() + 1); // choice location
		answer = words[answerNum];

		// pick the random bounds
		upperNum = rand.nextInt(answerNum - 0 + 1); // 0 to center
		lowerNum = answerNum + rand.nextInt(size - answerNum + 1); // center to size
		uWord = words[upperNum];
		lWord = words[lowerNum];
		
		upper.setText("Current Bounds Upper:" + uWord + "   ");
		lower.setText("Lower:" + lWord);
		group.clearSelection();
		yes.setVisible(false);
		no.setVisible(false);
		error.setText("");
		info.setText("");






	}
}
