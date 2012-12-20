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
		String answer = words[rand.nextInt(size+1) ];
		System.out.println("The word is " + answer);
		JFrame main = new JFrame();
		
	}

}
