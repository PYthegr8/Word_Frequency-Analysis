/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 23rd April 2024
Project 6
Description: This class is HashMap that maps a set of keys to specific values.
             It contains constructors, getter and setter methods and a remove method


*/

import java.util.ArrayList;


public class HashMap<K, V> implements MapSet<K, V> {

        private static class Node<K, V> extends KeyValuePair<K, V>{ 
            
            private Node<K, V> next;
            private V value;
    
            public Node(K key, V value){
                super(key, value);
                this.next = null;
            }
        }

    int size;
    Node<K, V>[] arrayNodes;
    double maxLoadFactor;

    // calls the following constructor with a reasonably chosen starting capacity (Java uses 16).
    @SuppressWarnings("unchecked")
    public HashMap(){
        this(16);
    }

    @SuppressWarnings("unchecked")
    // : calls the following constructor with a reasonable load factor (Java uses .75).
    public HashMap(int capacity){
        this(capacity, .75);
    }

    @SuppressWarnings("unchecked")
    // : initializes the HashMap with the given capacity and stores the given loadFactor.
    public HashMap(int capacity, double loadFactor){
        this.size = 0;
        this.maxLoadFactor = loadFactor;
        arrayNodes = new Node[capacity];
        
    }

    // this method returns the length of the array 
    public int capacity(){
        return arrayNodes.length;
    }

    // this method should return the index of the underlying array in which the given key should be stored. 
    private int hash(K key){
        return Math.abs(key.hashCode() % capacity());
    } 

    public String toString() {
        String output = "" ;
        for ( int i = 0 ; i < this.capacity() ; i ++ ) {
           Node<K,V>  node = this.arrayNodes[ i ] ;
           output += "bin " + i + ": " ;
           while ( node != null ) {
               output += node.toString() + " | " ;
               node = node.next ;
           }
           output += "\n" ;
        }
       return output ;
   }
    // : returns the size.
    public int size(){
        return this.size;
    }

    // resets fields to default values
    @SuppressWarnings("unchecked")
    public void clear(){
        this.arrayNodes = new Node[capacity()];
        this.size = 0;
    }



    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced. Does nothing if {@code value} is {@code null}.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    public V put(K key, V value){
        int index = hash(key);
        Node<K, V> node = arrayNodes[index];

        while (node != null) {
            if (node.getKey().equals(key)) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
            node = node.next;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = arrayNodes[index];
        arrayNodes[index] = newNode;
        size++;
        
        if ((double)size / capacity() > maxLoadFactor) {
            resize(capacity() * 2);
        }
        return null;
    }

    // resizes an array to an optimal capacity
    @SuppressWarnings("unchecked")
    private void resize(int newcapacity){
        Node<K, V>[] oldNodes = arrayNodes;
        arrayNodes = new Node[newcapacity];

        for (Node<K, V> oldNode : oldNodes) {
            while (oldNode != null) {
                Node<K, V> next = oldNode.next;
                int index = hash(oldNode.getKey());
                oldNode.next = arrayNodes[index];
                arrayNodes[index] = oldNode;
                oldNode = next;
            }
        }
    }


     /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
    public V get(K key){
        // gonna get the index
        int index = hash(key);
        Node<K,V> expectedNode = arrayNodes[index];

        while (expectedNode !=null){
            if (expectedNode.getKey().equals(key)){
                return expectedNode.getValue();
            }
            expectedNode = expectedNode.next;
        }

        return null;

    }


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
    public V remove(K key){
        int index = hash(key);
        Node<K, V> prev = null;
        Node<K, V> curr = arrayNodes[index];

        while (curr != null) {
            if (curr.getKey().equals(key)) {
                if (prev == null) {
                    arrayNodes[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                 // Check if resizing is needed
                if (size < (capacity() * maxLoadFactor) / 4 ) {
                    resize(capacity() / 2);
                }
                return curr.getValue();
            }
            prev = curr;
            curr = curr.next;
        }

        return null; // Key not found
    }



    /**
     * Returns an ArrayList of all the keys in the map.
     * 
     * @return an ArrayList of all the keys in the map.
     */
    public ArrayList<K> keySet(){
        ArrayList<K> keys = new ArrayList<>();
        for (Node<K, V> node : arrayNodes) {
            while (node != null) {
                keys.add(node.getKey());
                node = node.next;
            }
        }
        return keys;
    }



    /**
     * Returns an ArrayList of all the values in the map in the same order as the
     * keys as returned by keySet().
     * 
     * @return an ArrayList of all the values in the map in the same order as the
     *         keys as returned by keySet().
     */
    public ArrayList<V> values(){
        ArrayList<V> values= new ArrayList<>();
        for (Node<K, V> node : arrayNodes) {
            while (node != null) {
                values.add(node.getValue());
                node = node.next;
            }
        }
        return values;
    }


    /**
     * Returns an ArrayList of each {@code KeyValuePair} in the map in the same
     * order as the keys as returned by keySet().
     * 
     * @return an ArrayList of each {@code KeyValuePair} in the map in the same
     *         order as the keys as returned by keySet().
     */
    public ArrayList<KeyValuePair<K, V>> entrySet(){
        ArrayList<KeyValuePair<K, V>> entries = new ArrayList<>();
        for (Node<K, V> node : arrayNodes) {
            while (node != null) {
                entries.add(node);
                node = node.next;
            }
        }
        return entries;
    }


    /**
     * Returns the maximal number of iterations to find any particular element of
     * the Map.
     * 
     * @return
     */
    public int maxDepth() {
        int maxDepth = 0;
        for (Node<K, V> node : arrayNodes) {
            int depth = 0;
            while (node != null) {
                depth++;
                node = node.next;
            }
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }
        return maxDepth;
    }
}

