import java.util.*;
import javax.swing.JFrame;



public class WordGame {
	
	public static void main(String[] args) {
		//load library
		Library mlib = new Library();
		int size = mlib.getmWords().length;
		Random rand = new Random(size);
		String[] words= mlib.getmWords();
		System.out.println("There are "+ size+" words available");
		
		
		//pick the work
		int center = rand.nextInt(size+1); //choice location
		int upper =rand.nextInt(size-center+1); //0 to center
		int lower =center + rand.nextInt(size-center+1); // center to size
		String answer = words[center];
		String upperBound = words[upper];
		String lowerBound = words[lower];
		System.out.println("The word is " + answer);
		System.out.println("the word is between " +upperBound +" and "+ lowerBound);
		//pick the random bounds
		
		JFrame main = new JFrame();
		
	}

}
