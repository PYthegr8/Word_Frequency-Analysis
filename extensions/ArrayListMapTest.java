/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 25th April 2024
Project 6
Description: This class tests the method functionalities of the ArrayListMap


*/
public class ArrayListMapTest {

    public static void main(String[] args) {
        ArrayListMap<String, Integer> map = new ArrayListMap<>();

        // Putting values into the map
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);

        // Getting values from the map
        System.out.println("Get 'One': " + map.get("One")); // Should print 1
        System.out.println("Get 'Three': " + map.get("Three")); // Should print 3
        System.out.println("Get 'Five': " + map.get("Five")); // Should print null (key not found)

        // Removing a value from the map
        Integer removedValue = map.remove("Two");
        System.out.println("Removed value for 'Two': " + removedValue); // Should print 2
        System.out.println("Get 'Two' after removal: " + map.get("Two")); // Should print null (removed)

        // Size of the map
        System.out.println("Size of map: " + map.size()); // Should print 3

        // Key Set
        System.out.println("Key Set: " + map.keySet()); // Should print [One, Three, Four]

        // Values
        System.out.println("Values: " + map.values()); // Should print [1, 3, 4]

        // Entry Set
        System.out.println("Entry Set: " + map.entrySet()); // Should print [One=1, Three=3, Four=4]

        // Clear the map
        map.clear();
        System.out.println("Cleared map. Size: " + map.size()); // Should print 0
    }
}

