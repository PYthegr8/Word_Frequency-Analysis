import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

public class HashMapTester {
    
    /** 
     * Testing put and resize 
     */
    public static void test1(){
        //Adds some values, makes sure they're in the Hashmap, makes sure it resizes
        System.out.println("-".repeat(30) + "\nTest1: ");
        String expected = "Hashmap after adding 5, 100, 3\nbin 0: <100 -> 2> | \nbin 1: <5 -> 1> | \nbin 2: <30 -> 3> | \nbin 3: \n\nHashmap after adding 12\nbin 0: \nbin 1: \nbin 2: \nbin 3: \nbin 4: <100 -> 2> | <12 -> 4> | \nbin 5: <5 -> 1> | \nbin 6: <30 -> 3> | \nbin 7: \n\nHashmap after adding 4, 6\nbin 0: \nbin 1: \nbin 2: \nbin 3: \nbin 4: <100 -> 2> | <12 -> 4> | <4 -> 5> | \nbin 5: <5 -> 1> | \nbin 6: <30 -> 3> | <6 -> 6> | \nbin 7: \n";
        System.out.println("Expected output: " + expected );
        System.out.println("-".repeat(15) + "\n");
        String actual = ""; 
        //Start it with 4 bins so it's small.
        MapSet<Integer, String> map = new HashMapHashFunc<>(4, 0.75);
        map.put( 5, "" + 1 );
        map.put( 100, "" + 2 );
        map.put( 30, "" + 3 );
        actual += "Hashmap after adding 5, 100, 3\n" + map ;
        map.put( 12, "" + 4 );
        actual += "\nHashmap after adding 12\n" + map ;
        map.put( 4, "" + 5 );
        map.put( 6, "" + 6 );
        actual += "\nHashmap after adding 4, 6\n" + map ; 
        System.out.println("Actual output: " + actual );
        System.out.println("maxdepth: " +  map.maxDepth());
        System.out.println("For test 1: expected and actual output are equal == " + actual.equals( expected ) );
    }

    /** 
     * Testing get 
     */
    public static void test2(){
        //Adds some values, gets them, and makes sure the key is correct
        System.out.println("-".repeat(30) + "\nTest2: ");
        MapSet<Integer, String> map = new HashMapHashFunc<>(4, 0.75);
        map.put( 5, "" + 1 );
        map.put( 100, "" + 2 );
        map.put( 30, "" + 3 );
        map.put( 12, "" + 4 );
        System.out.println("For test 2: the following values should be equal: " ); 
        System.out.println( "Get 5   --- Key is " + map.get( 5 ) + ", should be 1" );
        System.out.println( "Get 100 --- Key is " + map.get( 100 ) + ", should be 2" );
        System.out.println( "Get 30  --- Key is " + map.get( 30 ) + ", should be 3" );
        System.out.println( "Get 12  --- Key is " + map.get( 12 ) + ", should be 4" );
    }

    /** 
     * Testing that size, capacity entry set are correct after put and resize
     */
    public static void test3(){
        //TODO
        //Put some keys and values into the hashmap
        //Print out the map, size, capacity and entryset and validate that they are all correct
        System.out.println("-".repeat(30) + "\nTest3: ");
        MapSet<Integer, String> map = new HashMapHashFunc<>(4, 0.75);
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        System.out.println("Map after adding keys and values:");
        System.out.println(map);

        System.out.println("Size: " + map.size() + ", Expected: 3");
        System.out.println("Capacity: " + ((HashMap<Integer, String>) map).capacity() + ", Expected: 4");

        System.out.println("Entry Set:");
        System.out.println(map.entrySet());

        System.out.println("-".repeat(30));
    }

    /** 
     * Testing remove
     */
    public static void test4(){
        //TODO
        //Put around 20 values values into the hashmap
        //Remove a few of them at a time and print the size after
        //Make sure it resizes when it's supposed to
        System.out.println("-".repeat(30) + "\nTest4: ");
        MapSet<Integer, String> map = new HashMapHashFunc<>(4, 0.75);
        for (int i = 1; i <= 20; i++) {
            map.put(i, " " + i);
        }

        System.out.println("Map after putting 20 values:");
        System.out.println(map);
        System.out.println("Initial Capacity: " + ((HashMap<Integer, String>) map).capacity());

        for (int i = 1; i <= 10; i += 2) {
            String removed = map.remove(i);
            System.out.println("Removed key " + i + ", Value: " + removed);
        }

        System.out.println("Map after removing some keys:");
        System.out.println(map);

        System.out.println("Size: " + map.size() + ", Expected: 15");
        System.out.println("Final Capacity: " + ((HashMap<Integer, String>) map).capacity());

        System.out.println("-".repeat(30));
    }

    /** 
     * Testing remove at scale
     */
    public static void test5(){
        //TODO
        //Add a lot of key/value pairs (e.g. 1000).
        //Remove them one at a time
        //make sure the size stays between the bounds its supposed to
        //Make sure the associated values are correct
        System.out.println("-".repeat(30) + "\nTest5: ");
        MapSet<Integer, String> map = new HashMapHashFunc<>(16, 0.75);
    
        // Add 1000 key/value pairs
        for (int i = 1; i <= 1000; i++) {
            map.put(i, " " + i);
        }
    
        // Create a list of keys
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            keys.add(i);
        }
    
        // Shuffle the keys to remove them in random order
        Collections.shuffle(keys);
    
        // Remove the keys one at a time
        for (int key : keys) {
            String removedValue = map.remove(key);
            System.out.println("Removed key " + key + ", Value: " + removedValue);
            
            // Check if the removed value matches the expected value
            String expectedValue = " " + key;
            if (!expectedValue.equals(removedValue)) {
                System.out.println("ERROR: Removed value does not match expected value.");
            }
        }
    
        // Check the final size
        System.out.println("Final size: " + map.size() + ", Expected: 0");
    
        System.out.println("-".repeat(30));
    }

    public static void main(String[] args){
        test1();
        test2();
        // test3();
        // test4();
        // test5();
    }

}