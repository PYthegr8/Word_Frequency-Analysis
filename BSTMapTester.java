/*
 * Papa Yaw Owusu Nti
 * April 16th, 2024
 * CS231 B
 * Project 6
 * 
 * Description: This class tests the functionalities of the BSTMap constructor and methods

*/
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class BSTMapTester {
    
    public static void test1(){
        System.out.println("-".repeat(30) + "\nTest1: ");

        String expectedResult = "\t\t<1 -> 1>\n\t<2 -> 2>\n\t\t<3 -> 3>\n<4 -> 4>\n\t\t<5 -> 5>\n\t<6 -> 6>\n\t\t<7 -> 7>";
        System.out.println("Expected result: \n" + expectedResult );
        System.out.println( "-".repeat(10) );

        MapSet<Integer, String> map = new BSTMap<>();
        for(int i : new int[] {4, 2, 6, 1, 3, 5, 7}){
            map.put(i, "" + i);
        }
        System.out.println("Your result: \n" + map );
        System.out.println("-".repeat(30));
    }

    public static void test2(){
        //Note: This one is implemented for you, but you need to figure out what it should look like!
        System.out.println("-".repeat(30) + "\nTest2: ");
        BSTMap<Integer, String> map = new BSTMap<>();
        for(int i : new int[] {1, 2, 3, 4, 5, 6, 7}){
            map.put(i, "" + i);
        } System.out.println(map);
        System.out.println("size: " + map.size() + " == " + 7);
        System.out.println("maxDepth: " + map.maxDepth() + " == " + 7);
        System.out.println("entrySet: " + map.entrySet());
        System.out.println("-".repeat(30));
    }

    public static void test3() {
        //This test should put a bunch of key/value pairs into the BSTMap, and remove them 
        //one by one--not in order, checking that the size of the BSTMap and its remaining 
        //Values stay correct.
        System.out.println("-".repeat(30) + "\nTest3: ");
    
        BSTMap<Integer, String> map = new BSTMap<>();
        for (int i : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            map.put(i, "" + i);
        }
    
        System.out.println("Initial Map:");
        System.out.println(map);
    
        LinkedList<Integer> keys = new LinkedList<>(map.keySet());
        Collections.shuffle(keys);
        System.out.println("Removing keys in random order:");
    
        for (int key : keys) {
            String removedValue = map.remove(key);
            System.out.println("Removed key " + key + ", Value: " + removedValue);
            System.out.println("Map size after removal: " + map.size());
            System.out.println("Map after removal:");
            System.out.println(map);
        }
    
        System.out.println("-".repeat(30));
    }
    
    public static void test4() {
        //This test should put a bunch of key/value pairs into the BSTMap, 
        //and check that the correct value gets returned when each key is removed.
        System.out.println("-".repeat(30) + "\nTest4: ");
    
        BSTMap<Integer, String> map = new BSTMap<>();
        for (int i : new int[]{1, 2, 3, 4, 5, 6, 7}) {
            map.put(i, "" + i);
        }
    
        System.out.println("Initial Map:");
        System.out.println(map);
    
        System.out.println("Removing keys and checking removed values:");
        for (int i = 1; i <= 7; i++) {
            String removedValue = map.remove(i);
            System.out.println("Removed key " + i + ", Removed Value: " + removedValue);
        }
    
        System.out.println("-".repeat(30));
    }
    

    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
    }

}
