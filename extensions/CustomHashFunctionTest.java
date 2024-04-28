/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 25th April 2024
Project 6
Description: This class compares the hashing process of the two hashfunctions implements and returns the indices. 


*/

public class CustomHashFunctionTest {

    public static void main(String[] args) {
        testCustomHashFunction();
        testHashCode();
    }

    public static void testCustomHashFunction() {
        System.out.println("Testing Custom Hash Function:");
        CustomHashFunction<Integer> customHashFunction = new CustomHashFunction<>(16);
        System.out.println("Hash for 10: " + customHashFunction.simpleHash(10));
        System.out.println("Hash for 20: " + customHashFunction.simpleHash(20));
        System.out.println("Hash for 30: " + customHashFunction.simpleHash(30));
        System.out.println();
    }

    public static void testHashCode() {
        System.out.println("Testing Java's hashCode():");
        CustomHashFunction<Integer> customHashFunction = new CustomHashFunction<>(16);
        System.out.println("Hash for 10: " + customHashFunction.hashCode(10));
        System.out.println("Hash for 20: " + customHashFunction.hashCode(20));
        System.out.println("Hash for 30: " + customHashFunction.hashCode(30));
        System.out.println();
    }

    static class CustomHashFunction<K> {
        private int capacity;

        public CustomHashFunction(int capacity) {
            this.capacity = capacity;
        }

        public int simpleHash(K key) {
            String strKey = key.toString();
            int hash = 0;
            for (int i = 0; i < strKey.length(); i++) {
                hash += strKey.charAt(i);
            }
            return hash % capacity;
        }

        public int hashCode(K key) {
            return Math.abs(key.hashCode() % capacity);
        }
    }
}

