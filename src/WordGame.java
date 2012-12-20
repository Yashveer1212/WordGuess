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
	public static int lower = center + rand.nextInt(size - center + 1); // center to size
	public static String upperBound = "";
	public static String lowerBound = "";
	public static HashMap libHash = new HashMap(size);

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean play = true;

		// load library & create has
		String[] words = mlib.getmWords();
		System.out.println("There are " + size + " words available");
		int count = 0;
		for(int i = 0; i < size; i++){
			libHash.put(words[i], count);
			count++;
		}

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
			}

			if (guess.length() > 5) {
				System.out.println("You're guess is too long!");

			} 			
			if (guess.equals("quit")) {
				// reveal the answers if quit

				System.out.println("You Quit! The word is " + answer);
				break;

			}
			
			if (guess.equals(answer)) {
				System.out.println("That is right! Congrats!");
				System.out.println("Do you wanna play again? Enter Yes or No!");
				 String choice = scan.nextLine();

				if(choice.equals("No") |choice.equals("N")  | choice.equals("no") | choice.equals("n")){
					play = false;
					System.out.println("Thank you for playing!");
					break;
				}else{
					System.out.println("Starting a new game!");
					newGame(words);
				}
			}else
				System.out.println("That is incorrect. Please guess Again!");
				guess = scan.nextLine();
				System.out.println("You guessed:" + guess);

			}
	

		}


		
public static void newGame(String[] words){
	 center = rand.nextInt(size + 1); // choice location
	 
	 answer = words[center];
	//System.out.println("REMOVE ME!! The word is " + answer);


	// pick the random bounds
	 upper = rand.nextInt(center - 0 + 1); // 0 to center
	int lower = center + rand.nextInt(size - center + 1); // center to size
	 upperBound = words[upper];
	 lowerBound = words[lower];
	System.out.println("The word is between " + upperBound + " and "
			+ lowerBound);
	System.out.println("What is your guess?");	
}

public static void updateBound(String guess){
	
	if(guess.length() > 2){//fix this 
		setUpper(guess);
	}else{
		setLower(guess);
	}
	
}

public static void setUpper(String newUpper){
	
	upperBound = newUpper;
}

public static void setLower(String newLower){
	
	lowerBound = newLower;
}
}
