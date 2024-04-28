/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 25th April 2024
Project 6
Description: This class is ArrayListMap that maps a set of keys to specific values using an arrayList. 
             This extension contains constructors, getter and setter methods and a remove method


*/
import java.util.ArrayList;

public class ArrayListMap<K, V> implements MapSet<K, V> {

    private ArrayList<KeyValuePair<K, V>> arrayList;
    
    public ArrayListMap() {
        this.arrayList = new ArrayList<>();
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public boolean containsKey(K key) {
        for (KeyValuePair<K, V> pair : arrayList) {
            if (pair.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        for (KeyValuePair<K, V> pair : arrayList) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

  
    @Override
    public V put(K key, V value) {
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, value);
        // Check if the key already exists, update the value
        for (KeyValuePair<K, V> existingPair : arrayList) {
            if (existingPair.getKey().equals(key)) {
                V oldValue = existingPair.getValue();
                existingPair.setValue(value);
                return oldValue;
            }
        }
        arrayList.add(pair);
        return null; // No previous value
    }
   
    @Override
    public V remove(K key) {
        for (int i = 0; i < arrayList.size(); i++) {
            KeyValuePair<K, V> pair = arrayList.get(i);
            if (pair.getKey().equals(key)) {
                arrayList.remove(i);
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (KeyValuePair<K, V> pair : arrayList) {
            keys.add(pair.getKey());
        }
        return keys;
    }

    @Override
    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (KeyValuePair<K, V> pair : arrayList) {
            values.add(pair.getValue());
        }
        return values;
    }

    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        return new ArrayList<>(arrayList);
    }

    @Override
    public int maxDepth() {
        // Since ArrayList does not have "chains" like linear probing,
        // we can consider the max depth to always be 1.
        return 1;
    }
}

