/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 25th April 2024
Project 6
Description: This class explored the timetaken to build a map for shakespeare as well as the maxdepth using 
            all the maps I implemeneted namely: BST,redblacktree,HashMap,HashMapHashFunc and ArrayListMap. 
            please double memory when running this program and also be patient for the ArrayListMap because it tends to take very long


*/
import java.util.ArrayList;
import java.util.HashMap;

public class ExploringmapTime {

    public static void main(String[] args) {
        // Run tests for each map individually
        
        // BST
        WordCounter wcBst = new WordCounter("BST");
        ArrayList<String> wordsBst = wcBst.readWords("CLEANED_shakespeare.txt");
        double avgTimeBst = 0;
        int maxDepthBst = 0;
        for (int i = 0; i < 5; i++) {
            avgTimeBst += wcBst.buildMap(wordsBst);
            maxDepthBst = Math.max(maxDepthBst, wcBst.getMaxDepth());
        }
        System.out.println("Average Time for BST: " + avgTimeBst / 5);
        System.out.println("Max Depth for BST: " + maxDepthBst);


        // RedBlackTree
        WordCounter wcRedBlackTree = new WordCounter("RedBlackTree");
        ArrayList<String> wordsRedBlackTree = wcRedBlackTree.readWords("CLEANED_shakespeare.txt");
        double avgTimeRedBlackTree = 0;
        int maxDepthRedBlackTree = 0;
        for (int i = 0; i < 5; i++) {
            avgTimeRedBlackTree += wcRedBlackTree.buildMap(wordsRedBlackTree);
            maxDepthRedBlackTree = Math.max(maxDepthRedBlackTree, wcRedBlackTree.getMaxDepth());
        }
        System.out.println("Average Time for RedBlackTree: " + avgTimeRedBlackTree / 5);
        System.out.println("Max Depth for RedBlackTree: " + maxDepthRedBlackTree);

        // HashMap
        WordCounter wcHashMap = new WordCounter("HashMap");
        ArrayList<String> wordsHashMap = wcHashMap.readWords("CLEANED_shakespeare.txt");
        double avgTimeHashMap = 0;
        int maxDepthHashMap = 0;
        for (int i = 0; i < 5; i++) {
            avgTimeHashMap += wcHashMap.buildMap(wordsHashMap);
            maxDepthHashMap = Math.max(maxDepthHashMap, wcHashMap.getMaxDepth());
        }
        System.out.println("Average Time for HashMap: " + avgTimeHashMap / 5);
        System.out.println("Max Depth for HashMap: " + maxDepthHashMap);

        
        // HashMapHashFunc
        WordCounter wcHashMapHashFunc = new WordCounter("HashMapHashFunc");
        ArrayList<String> wordsHashMapHashFunc = wcHashMapHashFunc.readWords("CLEANED_shakespeare.txt");
        double avgTimeHashMapHashFunc = 0;
        int maxDepthHashMapHashFunc = 0;
        for (int i = 0; i < 5; i++) {
            avgTimeHashMapHashFunc += wcHashMapHashFunc.buildMap(wordsHashMapHashFunc);
            maxDepthHashMapHashFunc = Math.max(maxDepthHashMapHashFunc, wcHashMapHashFunc.getMaxDepth());
        }
        System.out.println("Average Time for HashMapHashFunc: " + avgTimeHashMapHashFunc / 5);
        System.out.println("Max Depth for HashMapHashFunc: " + maxDepthHashMapHashFunc);

        // ArrayListMap
        WordCounter wcArrayListMap = new WordCounter("ArrayListMap");
        ArrayList<String> wordsArrayListMap = wcArrayListMap.readWords("CLEANED_shakespeare.txt");
        double avgTimeArrayListMap = 0;
        int maxDepthArrayListMap = 0;
        for (int i = 0; i < 5; i++) {
            avgTimeArrayListMap += wcArrayListMap.buildMap(wordsArrayListMap);
            maxDepthArrayListMap = Math.max(maxDepthArrayListMap, wcArrayListMap.getMaxDepth());
        }
        System.out.println("Average Time for ArrayListMap: " + avgTimeArrayListMap / 5);
        System.out.println("Max Depth for ArrayListMap: " + maxDepthArrayListMap);

        
    }
}
