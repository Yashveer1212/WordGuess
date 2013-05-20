import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

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
	int scoreNum = 0;
	private String answer;
	private Map<String, Object> map;

	public GamePanel(String upperWord, String lowerWord, String realWord, Map<String, Object> wordMap) {
		answer = realWord;
		map = wordMap;
		lWord = lowerWord;
		uWord = upperWord;
		setBackground(Color.white);
		setPreferredSize(new Dimension(570, 210));
		setFont(new Font("Courier", Font.BOLD, 20));

		background = new JTextArea("Guess a 5 letter m-word!" + "\n"
				+ "The word will be between the given bounds." + "\n"
				+ "You may guess as many times as you like."+ "\n");
		background.setFont(new Font("Courier", Font.PLAIN, 20));

		upper = new JLabel("Current Bounds Upper:" + upperWord + "   ");
		upper.setFont(new Font("Courier", Font.PLAIN, 20));

		lower = new JLabel("Lower:" + lowerWord);
		lower.setFont(new Font("Courier", Font.PLAIN, 20));

		instruct = new JLabel("What is your guess?");
		instruct.setFont(new Font("Courier", Font.BOLD, 20));
		
		score = new JLabel("Score: "+scoreNum);

		info = new JLabel("");


		error = new JLabel("");
		instruct.setFont(new Font("Courier", Font.BOLD, 20));

		field = new JTextField(5);

		submit = new JButton("Submit");
		submit.setFont(new Font("Courier", Font.BOLD, 20));

		submit.addActionListener(new ButtonListener());
		
		yes = new JRadioButton("Yes", true);
		yes.addActionListener(new ButtonListener());
		yes.setBackground(Color.cyan);
		yes.setVisible(false);
		no = new JRadioButton("No");
		no.addActionListener(new ButtonListener());
		no.setBackground(Color.cyan);
		no.setVisible(false);

		ButtonGroup group = new ButtonGroup();
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
			int guessNum;
			boolean real;
			
			if(ae.getSource() == submit){
				guess = field.getText();
				
				
				if (map.get(guess) != null) {
					guessNum = (Integer) map.get(guess);
					if (guessNum != -1)
						error.setText("You guessed: " + guess);
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
						score.setText("Score: "+ ++scoreNum);
						info.setText("That is right! Congrats! Do you want to play again? Click Yes or No!");
						System.out.println("Do you wanna play again? Enter Yes or No!");

						
						yes.setVisible(true);
						no.setVisible(true);
						/*reset the game*/
					/*	if (choice.equals("No") | choice.equals("N")
								| choice.equals("no") | choice.equals("n")) {
							play = false;
							System.out.println("Thank you for playing!");
							break;
						} else {
							System.out.println("Starting a new game!");
							newGame(words);
						}*/
					} else {
						//error.setText("That is incorrect. Please guess Again!");

					}
						
					// update bounds
			/*		if (real) {
						updateBounds(guessNum, upper, lower, center);
					}
					System.out.println("The word is between " + uWord + " and "
							+ lWord);
				*/	
				

				
			}
		}
	}
}
