import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException{
		
		Scanner input = new Scanner(System.in);
		WordAnalysis list = new WordAnalysis();
		System.out.println("Please enter the text file name to perform the 7 operations");
		String f = input.next();
		list.readfromfile(f);
		System.out.println("Operation 1: " + list.documentLength());
		System.out.println("Operation 2: " + list.uniqueWords());
		System.out.println("Please enter the word you want to find the number of total occurances of");
		String t = input.next();
		System.out.println("Operation 3: " + list.totalOccurancesForWord(t));
		System.out.println("Please enter the length you want to find the total words for");
		int n = input.nextInt();
		System.out.println("Operation 4: " + list.totalWordsForLength(n));
		System.out.println("Operation 5:");
		list.displayUniqueWords();
		System.out.println("Please enter the word you want to find the list of occurrences for");
		String tt = input.next();
		System.out.println("Operation 6:");
		linkedList<WordOccurrence> b = list.occurrences(tt);
		b.findfirst();
		while(!b.last()){
			System.out.print("(" + b.retrieve().lineno + ", " + b.retrieve().position + ")");
			b.findnext();
		}
		System.out.println("(" + b.retrieve().lineno + ", " + b.retrieve().position + ")");
		System.out.println("Please enter the two words that you want to find out if they're adjacent");
		System.out.println("Word 1: ");
		String t1 = input.next();
		System.out.println("Word 2: ");
		String t2 = input.next();
		System.out.print("Operation 7: " + list.checkAdjacent(t1, t2));
		
	}
	}   
		