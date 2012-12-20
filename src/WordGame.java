import java.util.*;

import javax.swing.JFrame;

public class WordGame {
	
	public static Library mlib = new Library();
	public static int size = mlib.getmWords().length;
	public static Random rand = new Random(size);
	public static int center = rand.nextInt(size + 1); // choice location
	public static String answer = "";

	// pick the random bounds
	public static int upper = rand.nextInt(size - center + 1); // 0 to center
	public static int lower = center + rand.nextInt(size - center + 1); // center
																		// to
																		// size
	public static String upperBound = "";
	public static String lowerBound = "";
	static Map<String, Object> map = new HashMap<String, Object>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean play = true;
		boolean real = false;

		// load library & create has
		String[] words = mlib.getmWords();
		System.out.println("There are " + size + " words available");
		int count = 0;
		for (int i = 0; i < size; i++) {
			map.put(words[i],new Integer(count));// key value pair
			count++;
		}
		
		//System.out.println("Testing that "+ map.get("maker") + " exits but " + map.get("dfafdad")+ " doesnt");

		// pick the work
		center = rand.nextInt(size + 1); // choice location
		answer = words[center];
		 //System.out.println("REMOVE ME The word is " + answer);

		// pick the random bounds
		int upper = rand.nextInt(center - 0 + 1); // 0 to center
		int lower = center + rand.nextInt(size - center + 1); // center to size
		upperBound = words[upper];
		lowerBound = words[lower];
		System.out.println("The word is between " + upperBound + " and "
				+ lowerBound);
		System.out.println("What is your guess?");
		String guess = scan.nextLine();

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
			
			if(map.get(guess)== null){
				System.out.println("That is not a real word");
				real =false;
			}else{
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
			} else
				System.out.println("That is incorrect. Please guess Again!");
			if(real){
				updateBounds(guess);
			}
			guess = scan.nextLine();
			System.out.println("You guessed: " + guess);

		}

	}

	public static void newGame(String[] words) {
		center = rand.nextInt(size + 1); // choice location

		answer = words[center];
		// System.out.println("REMOVE ME!! The word is " + answer);

		// pick the random bounds
		upper = rand.nextInt(center - 0 + 1); // 0 to center
		int lower = center + rand.nextInt(size - center + 1); // center to size
		upperBound = words[upper];
		lowerBound = words[lower];
		System.out.println("The word is between " + upperBound + " and "
				+ lowerBound);
		System.out.println("What is your guess?");
	}

	public static void updateBounds(String guess) {
		int their_guess = (Integer)map.get(guess);
		int the_answer = (Integer)map.get(answer);
		
	//	System.out.println("the upper"+ upper + "the lower"+ lower);

//		System.out.println("their guess"+ their_guess + "the answer"+ the_answer);
		
		if ( their_guess< the_answer && their_guess >= upper) {// fix this
			setUpper(guess);
		}  
		
		if(their_guess> the_answer && their_guess <= lower){
			setLower(guess);
		}
		System.out.println("The word is between " + upperBound + " and "
				+ lowerBound);

	}

	public static void setUpper(String newUpper) {

		upperBound = newUpper;
	}

	public static void setLower(String newLower) {

		lowerBound = newLower;
	}
}
