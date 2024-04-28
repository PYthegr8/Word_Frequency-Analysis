/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 25th April 2024
Project 6
Description: This class explored the timetaken to build a map for reddit and shakespeare as well as the maxdepth using 
            a redblacktree map


*/
import java.util.ArrayList;

public class ExploreBstRbt {

    public static void test1() {
        
        // RBT Reddit
			String filenameReddit = "CLEANED_reddit_comments_2015.txt";
			WordCounter wcReddit = new WordCounter("redblacktree");
			ArrayList<String> wordsReddit = wcReddit.readWords(filenameReddit);
		

			for (int i=0 ; i<5; ++i){	
				System.out.println("Trial "+ i + ": Time taken in ms RedBlackTree Reddit   ----" + wcReddit.buildMap(wordsReddit));
                System.out.println("              : MaxDepth ms RedBlackTree Reddit   ----" + wcReddit.getMaxDepth());
			}

			System.out.println();


			// RBT Shakespeare
			String filenameShakespeare = "CLEANED_shakespeare.txt";
			WordCounter wcShakespeare = new WordCounter("redblacktree");
			ArrayList<String> wordsShakespeare = wcShakespeare.readWords(filenameShakespeare);
			// wcShakespeare.buildMap(wordsShakespeare);
		
			for (int i=0 ; i<5; ++i){	
				System.out.println("Trial "+ i + ": Time taken in ms HashMap Shakespeare  ----" + wcShakespeare.buildMap(wordsShakespeare));
                System.out.println("              : MaxDepth ms RedBlackTree Shakespeare  ----" + wcShakespeare.getMaxDepth());
			}

			System.out.println();

    }


    
    public static void main(String[] args) {
        test1();
    }

}
