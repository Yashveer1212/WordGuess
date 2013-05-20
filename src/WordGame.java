import java.util.*;

import javax.swing.JFrame;

/**
 * @author Jasmine
 * 
 */
public class WordGame {

	public static Library mlib = new Library();
	public static int size = mlib.getmWords().length;
	public static Random rand = new Random(size);
	public static int center = rand.nextInt(size + 1); // choice location
	public static String answer = "";
	static String[] words;

	// pick the random bounds
	public static int upper = rand.nextInt(size - center + 1); // 0 to center
	public static int lower = center + rand.nextInt(size - center + 1); // center
																		// to
																		// size
	public static String upperBound = "";
	public static String lowerBound = "";
	static Map<String, Object> map = new HashMap<String, Object>();

	public static void main(String[] args) {
		System.out.println("Guess a 5 letter m-word!");
		System.out.println("The word will be between the given bounds.");
		System.out.println("You may guess as many times as you like.");
		System.out.println("Type \"quit\" to end the game.");

		Scanner scan = new Scanner(System.in);
		boolean play = true;
		boolean real = false;

		// load library & create has
		words = mlib.getmWords();
		// System.out.println("REMOVE ME There are " + size +
		// " words available");
		int count = 0;
		for (int i = 0; i < size; i++) {
			map.put(words[count], new Integer(count));// key value pair
			count++;
		}

		// pick the word
		answer = words[center];
		// System.out.println("REMOVE ME The word is " + answer+"("+center+")");
		// pick the random bounds
		upperBound = words[upper];
		lowerBound = words[lower];

		JFrame frame = new JFrame("Word Guess Game!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GamePanel panel = new GamePanel(upperBound, lowerBound, answer, map);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		System.out.println("What is your guess?");
		String guess = scan.nextLine();
		int guessNum = -1;
		if (map.get(guess) != null) {
			guessNum = (Integer) map.get(guess);
			if (guessNum != -1)
				System.out.println("You guessed: " + guess);
		} else {
			real = false;
		}
		while (play) {

			// error checking
			if (guess == null) {
				System.out.println("You have to guess something!");
			} else if (guess.length() > 5) {
				System.out.println("Your guess is too long!");

			} else if (guess.equals("quit")) {
				// reveal the answers if quit

				System.out.println("You Quit! The word is " + answer);
				break;
			}

			if (map.get(guess) == null) {
				System.out.println("That is not a  m-word");
				real = false;
			} else {
				real = true;
			}

			if (guess.equals(answer)) {
				System.out.println("That is right! Congrats!");
				System.out.println("Do you wanna play again? Enter Yes or No!");
				String choice = scan.nextLine();

				if (choice.equals("No") | choice.equals("N")
						| choice.equals("no") | choice.equals("n")) {
					play = false;
					System.out.println("Thank you for playing!");
					break;
				} else {
					System.out.println("Starting a new game!");
					newGame(words);
				}
			} else {
				System.out.println("That is incorrect. Please guess Again!");

			}
			// update bounds
			if (real) {
				updateBounds(guessNum, upper, lower, center);
			}
			System.out.println("The word is between " + upperBound + " and "
					+ lowerBound);
			guess = scan.nextLine();
			if (map.get(guess) != null) {
				guessNum = (Integer) map.get(guess);
			} else {
				guessNum = -1;
			}
			if (guessNum != -1)
				System.out.println("You guessed: " + guess);

		}

	}

	public static void newGame(String[] words) {
		center = rand.nextInt(size + 1); // choice location
		answer = words[center];

		// pick the random bounds
		upper = rand.nextInt(center - 0 + 1); // 0 to center
		lower = center + rand.nextInt(size - center + 1); // center to size
		upperBound = words[upper];
		lowerBound = words[lower];
		System.out.println("What is your guess?");
	}

	public static void updateBounds(int guess, int oldUpper, int oldLower,
			int answer) {

		// bounds should only update if the work makes the bounds smaller!

		if (guess < answer && guess > oldUpper) {
			setUpper(words[guess], guess);
		}

		if (guess > answer && guess < oldLower) {
			setLower(words[guess], guess);
		}

	}

	public static void setUpper(String newUpper, int guessedNum) {

		upperBound = newUpper;
		upper = guessedNum;
	}

	public static void setLower(String newLower, int guessedNum) {

		lowerBound = newLower;
		lower = guessedNum;
	}
}
