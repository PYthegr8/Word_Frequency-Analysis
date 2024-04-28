/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 16th April 2024
Project 6
Description: This class is Binary Search Tree that maps a set of keys to specific values.
             It contains constructors, getter and setter methods and a remove method


*/

import java.util.Comparator;
import java.util.ArrayList;

public class BSTMap<K, V> implements MapSet<K, V> {

    private static class Node<K, V> extends KeyValuePair<K, V>{ 
        private Node<K, V> leftChild;
        private Node<K, V> rightChild;
        private V value;

        public Node(K key, V value){
            super(key, value);
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    
    private  Node<K, V> root;
    private int size;
    Comparator<K> comparator;

    // if comparator isn't null, saves it to the matching field. Otherwise (if it is null), creates a new Comparator<K> assuming that K is Comparable.
    public BSTMap(Comparator<K> comparator){
        if (comparator != null) {
            this.root = null;
            this.size = 0;
            this.comparator = comparator;
        }
        else{
            this.root = null;
            this.size = 0;
            this.comparator = new Comparator<K>() {
                @SuppressWarnings("unchecked")
                @Override
                public int compare(K o1, K o2) {
                    return ((Comparable <K>) o1).compareTo(o2);
                    
                }
            };
        }
        
           
    }
 
    // public BSTMap(): calls the first constructor with a null Comparator.
    public BSTMap(){
        this(null);
    }


 

    private String toString( Node<K, V> cur , int curDepth ) {
		if ( cur == null ){
			return "" ;
		} else{ //Ordering of these call matters for it to be sorted
			String output = this.toString( cur.leftChild , curDepth + 1 ) ;
			output += "\t".repeat(curDepth) + cur.toString() + "\n";
			output += this.toString( cur.rightChild , curDepth + 1 ) ;
			return output ;
		}
	}

	public String toString() {
		return this.toString( this.root , 0 );
	}

    // returns the size.
    public int size(){
        return this.size;
    }

    // resets fields to default values
    public void clear(){
        this.root = null;
        this.size = 0;
    }

    public V put(K key, V value) {
        if (value == null) {
            return null; // Does nothing if value is null
        }
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return null; // No previous value associated with key
        }
        return put(key, value, root);
    }
    
    private V put(K key, V value, Node<K, V> cur) {
        int cmp = comparator.compare(key, cur.getKey());
        if (cmp < 0) {
            if (cur.leftChild != null) {
                return put(key, value, cur.leftChild);
            } else {
                cur.leftChild = new Node<>(key, value);
                size++;
                return null; // No previous value associated with key
            }
        } else if (cmp > 0) {
            if (cur.rightChild != null) {
                return put(key, value, cur.rightChild);
            } else {
                cur.rightChild = new Node<>(key, value);
                size++;
                return null; // No previous value associated with key
            }
        } else { // cmp == 0, key already exists in the map
            V oldValue = cur.getValue();
            cur.setValue(value); // Replace the old value with the new one
            return oldValue;
        }
    }
    
    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node<K, V> cur) {

        if (cur == null) {
            return null; 
        }

        int comparatorVal = comparator.compare(key, cur.getKey());
        if (comparatorVal < 0) {
            return get(key, cur.leftChild); // left subtree
        } else if (comparatorVal > 0) {
            return get(key, cur.rightChild); // Search right subtree
        } else { // Key found
            return cur.getValue();
        }
    }



    /**
     * Returns {@code true} if this map contains a mapping for the
     * specified key to a value.
     *
     * @param key The key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     *         key to a value.
     */
    public boolean containsKey(K key){
        return get(key)!=null;
    }


    /**
     * Removes the mapping for a key from this map if it is present. More formally,
     * if this map contains a mapping from key {@code k} to value {@code v} such
     * that {@code key.equals(k)}, that mapping is removed. (The map can contain at
     * most one such mapping.)
     * 
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */

    public V remove(K key) {
        Node<K, V> removeNode = findNode(key, root);
        if (removeNode == null) {
            return null; // Key not found, return null or handle appropriately
        }
        V removedValue = removeNode.getValue();
        this.root = deleteNode(this.root, key);
        this.size--;
        return removedValue;
    }

    private Node<K, V> deleteNode(Node<K, V> root, K key) {
        // Base case: If the tree is empty
        if (root == null)
            return root;

        // If the key to be deleted is smaller than the root's key, then it lies in the left subtree
        int comparison = comparator.compare(key, root.getKey());
        if (comparison < 0 ) {
            root.leftChild = deleteNode(root.leftChild, key);
        }
        // If the key to be deleted is greater than the root's key, then it lies in the right subtree
        else if (comparison > 0) {
            root.rightChild = deleteNode(root.rightChild, key);
        }

        // If key is same as root's key, then this is the node to be deleted
        else {
            // Node with only one child or no child
            if (root.leftChild == null)
                return root.rightChild;
            else if (root.rightChild == null)
                return root.leftChild;

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            Node<K, V> successor = findSmallest(root.rightChild);

            // Copy the key and value from the successor to the current node
            root.setKey(successor.getKey());
            root.setValue(successor.getValue());

            // Recursively delete the successor node
            root.rightChild = deleteNode(root.rightChild, root.getKey());
        }
        return root;
    }


    private Node<K, V> findNode(K key, Node<K, V> cur) {
        if (cur == null) {
            return null; // Key not found
        }
    
        int comparison = comparator.compare(key, cur.getKey());
        if (comparison < 0) {
            return findNode(key, cur.leftChild); // Search left subtree
        } else if (comparison > 0) {
            return findNode(key, cur.rightChild); // Search right subtree
        } else {
            return cur; // Key found
        }
    }

    private Node<K, V> findSmallest(Node<K, V> node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    /**
     * Returns an ArrayList of all the keys in the map.
     * 
     * @return an ArrayList of all the keys in the map.
     */
    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<>();
        keySet(this.root, keys);
        return keys;
    }

    private void keySet(Node<K, V> cur, ArrayList<K> output){
        if (cur == null){
            return;
        }
        keySet(cur.leftChild, output); 
        output.add(cur.getKey()); 
        keySet(cur.rightChild, output); 
    }

    /**
     * Returns an ArrayList of all the values in the map in the same order as the
     * keys as returned by keySet().
     * 
     * @return an ArrayList of all the values in the map in the same order as the
     *         keys as returned by keySet().
     */
    public ArrayList<V> values(){
        ArrayList<V> valueList = new ArrayList<>();
        values(this.root, valueList);
        return valueList;
    }

    private void values(Node<K, V> cur, ArrayList<V> values_output){
        if (cur == null){
            return;
        }
        values(cur.leftChild, values_output); 
        values_output.add(cur.getValue()); 
        values(cur.rightChild, values_output); 
    }

    /**
     * Returns an ArrayList of each {@code KeyValuePair} in the map in the same
     * order as the keys as returned by keySet().
     * 
     * @return an ArrayList of each {@code KeyValuePair} in the map in the same
     *         order as the keys as returned by keySet().
     */
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K, V>> entry = new ArrayList<>();
        entrySet(this.root, entry);
        return entry;
    }

    private void entrySet(Node<K, V> cur, ArrayList<KeyValuePair<K, V>> values_output){
        if (cur == null){
            return;
        }
        entrySet(cur.leftChild, values_output); 
        values_output.add(cur); 
        entrySet(cur.rightChild, values_output); 
    }


 
    /**
     * Returns the maximal number of iterations to find any particular element of
     * the Map.
     * 
     * @return
     */
    public int maxDepth() {
        return maxDepth(this.root);
    }

    private int maxDepth(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = maxDepth(node.leftChild);
        int rightDepth = maxDepth(node.rightChild);
        return 1 + Math.max(leftDepth, rightDepth);
    }


}
