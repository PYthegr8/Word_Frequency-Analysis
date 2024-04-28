import java.util.ArrayList;
import java.util.Collections;

public class ExpectedHeight {
    public static void main(String[] args) {
       
        BSTMap<Integer, Integer> bstMap = new BSTMap<>();

        int[] listSizes = {100, 500, 1000, 5000, 10000};
        // int[] listSizes = {15,25,40,79};
    
        for (int size : listSizes) {
            // sorted and unsorted lists
            ArrayList<Integer> sortedList = new ArrayList<>();
            ArrayList<Integer> unsortedList = new ArrayList<>();

            // Add numbers
            for (int i = 0; i < size; i++) {
                sortedList.add(i);
                unsortedList.add(i);
            }

            // Shuffle the unsorted list 
            Collections.shuffle(unsortedList);

            // Build BST from the sorted list and measure max depth
            for (Integer key : sortedList) {
                bstMap.put(key, key);
            }
            int sortedMaxDepth = bstMap.maxDepth();

      
            bstMap.clear();

            // Build BST from the unsorted list and measure max depth
            for (Integer key : unsortedList) {
                bstMap.put(key, key);
            }
            int unsortedMaxDepth = bstMap.maxDepth();

         
            System.out.println("List Size: " + size);
            System.out.println("Max Depth of BST from sorted list: " + sortedMaxDepth);
            System.out.println("Max Depth of BST from unsorted list: " + unsortedMaxDepth);
            System.out.println();
        }
    }
}
