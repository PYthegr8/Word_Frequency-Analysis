/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 25th April 2024
Project 6
Description: This class explored the memoryspace used to build a map for shakespeare using 
            all the maps I implemeneted namely: BST,redblacktree,HashMap,HashMapHashFunc and ArrayListMap. 
            please double memory when running this program and also be patient for the ArrayListMap because it tends to take very long


*/
import java.util.ArrayList;
import java.util.HashMap;

public class ExploringSpace {

    public static void main(String[] args) {
        // Test space for each map individually
        
        // BST
        WordCounter bstTester = new WordCounter("BST");
        ArrayList<String> wordsBst = bstTester.readWords("CLEANED_shakespeare.txt");
        long avgMemoryBst = 0;
        for (int i = 0; i < 5; i++) {
            bstTester.buildMap(wordsBst);
            avgMemoryBst += getMemoryUsage();
        }
        System.out.println("Average Memory Usage for BST: " + avgMemoryBst / 5);


        // RedBlackTree
        WordCounter rbtTester = new WordCounter("RedBlackTree");
        ArrayList<String> wordsRedBlackTree = rbtTester.readWords("CLEANED_shakespeare.txt");
        long avgMemoryRedBlackTree = 0;
        for (int i = 0; i < 5; i++) {
            rbtTester.buildMap(wordsRedBlackTree);
            avgMemoryRedBlackTree += getMemoryUsage();
        }
        System.out.println("Average Memory Usage for RedBlackTree: " + avgMemoryRedBlackTree / 5);


        // HashMap
        WordCounter hashMapTester = new WordCounter("HashMap");
        ArrayList<String> wordsHashMap = hashMapTester.readWords("CLEANED_shakespeare.txt");
        long avgMemoryHashMap = 0;
        for (int i = 0; i < 5; i++) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (String word : wordsHashMap) {
                hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
            }
            avgMemoryHashMap += getMemoryUsage();
        }
        System.out.println("Average Memory Usage for HashMap: " + avgMemoryHashMap / 5);

        
        // HashMapHashFunc
        WordCounter hashMapHashFuncTester = new WordCounter("HashMapHashFunc");
        ArrayList<String> wordsHashMapHashFunc = hashMapHashFuncTester.readWords("CLEANED_shakespeare.txt");
        long avgMemoryHashMapHashFunc = 0;
        for (int i = 0; i < 5; i++) {
            hashMapHashFuncTester.buildMap(wordsHashMapHashFunc);
            avgMemoryHashMapHashFunc += getMemoryUsage();
        }
        System.out.println("Average Memory Usage for HashMapHashFunc: " + avgMemoryHashMapHashFunc / 5);

        // ArrayListMap
        WordCounter arrayListMapTester = new WordCounter("ArrayListMap");
        ArrayList<String> wordsArrayListMap = arrayListMapTester.readWords("CLEANED_shakespeare.txt");
        long avgMemoryArrayListMap = 0;
        for (int i = 0; i < 5; i++) {
            arrayListMapTester.buildMap(wordsArrayListMap);
            avgMemoryArrayListMap += getMemoryUsage();
        }
        System.out.println("Average Memory Usage for ArrayListMap: " + avgMemoryArrayListMap / 5);

        
    }

    private static long getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        return totalMemory - freeMemory;
    }
}
