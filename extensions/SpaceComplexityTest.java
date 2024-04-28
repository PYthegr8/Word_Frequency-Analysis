/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 25th April 2024
Project 6
Description: This class compares the memory used for A BST VS HASHMAP when processing the reddit file 


*/
import java.util.ArrayList;
import java.util.HashMap;


public class SpaceComplexityTest {

    public static void main(String[] args) {

        WordCounter bstTester = new WordCounter("BST");
        WordCounter hashMapTester = new WordCounter("HashMap");

   
        int numOfIterations = 5;


            long bstMemory = 0;
            long hashMapMemory = 0;

            for (int i = 0; i < numOfIterations; i++) {
                ArrayList<String> fileList = bstTester.readWords("CLEANED_reddit_comments_2015.txt");

                // Test BST Map
                bstTester.buildMap(fileList);
                long bstMemoryUsage = getMemoryUsage();
                bstMemory += bstMemoryUsage;

                // Test HashMap
                HashMap<String, Integer> hashMap = new HashMap<>();
                for (String word : fileList) {
                    hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
                }
                long hashMapMemoryUsage = getMemoryUsage();
                hashMapMemory += hashMapMemoryUsage;
            }

            
            System.out.println("AVERAGE MEMORY USAGE FOR BST MAP: " + bstMemory / numOfIterations);
            System.out.println("AVERAGE MEMORY USAGE FOR HASHMAP: " + hashMapMemory / numOfIterations);
        }
    

    private static long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        return totalMemory - freeMemory;
    }
}
