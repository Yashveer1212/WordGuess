import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	JTextArea background;
	JLabel instruct;
	JLabel bounds;
	JLabel upper;
	JLabel lower;
	JLabel score;
	JLabel error;
	JTextField field;
	JButton submit;

	public GamePanel(String upperWord, String lowerWord) {
		setBackground(Color.white);
		setPreferredSize(new Dimension(600, 210));
		setFont(new Font("Courier", Font.BOLD, 20));

		background = new JTextArea("Guess a 5 letter m-word!" + "\n"
				+ "The word will be between the given bounds." + "\n"
				+ "You may guess as many times as you like.");
		background.setFont(new Font("Courier", Font.PLAIN, 22));

		upper = new JLabel("Bounds Upper: " + upperWord);
		upper.setFont(new Font("Courier", Font.PLAIN, 20));

		lower = new JLabel("Lower: " + lowerWord);
		lower.setFont(new Font("Courier", Font.PLAIN, 20));

		instruct = new JLabel("What is your guess?");
		instruct.setFont(new Font("Courier", Font.BOLD, 20));

		error = new JLabel("");
		instruct.setFont(new Font("Courier", Font.BOLD, 20));

		field = new JTextField(5);

		submit = new JButton("Submit");

		add(background);
		add(upper);
		add(lower);
		add(instruct);

		add(field);
		add(submit);
		add(error);

	}

	@SuppressWarnings("unused")
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
		}
	}
}
