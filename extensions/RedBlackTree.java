/*

Name: Papa Yaw Owusu Nti
Class: CS231 B
Date: 25th April 2024
Project 6
Description: This class is ArrayListMap that maps a set of keys to specific values using an arrayList. 
             This extension contains constructors, getter and setter methods and a remove method


*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

// Define a class called RedBlackTree that implements the MapSet interface and can be iterated over
public class RedBlackTree<K extends Comparable<K>, V> implements MapSet<K, V>, Iterable<MapSet.KeyValuePair<K, V>> {
    
    // Define variables 
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node<K, V> extends KeyValuePair<K, V> {
        Node<K, V> left, right;
        boolean color;

        // constructor for the Node class
        public Node(K key, V value) {
            super(key, value);
            left = null;
            right = null;
            color = RED;
        }
    }

    // Define the size of the tree and its root
    private int size;
    private Node<K, V> root;
    
    // Define a constructor for the Red Black Tree class that sets the size and root to null
    public RedBlackTree() {
        size = 0;
        root = null;
    }

    // Helper methods
    // Define a method to check if a node is red
    private boolean isRed(Node<K, V> node) {
        if (node == null) {
            return BLACK;
        }
        return node.color == RED;
    }

    // Define a method to rotate a node to the left
    private Node<K, V> rotateLeft(Node<K, V> h) {
        Node<K, V> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // Define a method to rotate a node to the right
    private Node<K, V> rotateRight(Node<K, V> h) {
        Node<K, V> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // Define a method to flip the colors of a node
    private void flipColors(Node<K, V> h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    // Implement the put method from the MapSet interface
    @Override
    public V put(K key, V value) {
        // If tree is empty, create a new root node and set its color to black
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            root.color = BLACK;
            return null;
        } else {
            //recursively insert the node into the tree
            return put(key, value, root);
        }
    }

    // Define a recursive helper method for the put method
    private V put(K key, V value, Node<K, V> curNode) {
        // If the key is less than the current node's key, insert the node in the left subtree
        if (key.compareTo(curNode.getKey()) < 0) {
            // If the left child of the current node is null, create a new node and insert it
            if (curNode.left == null) {
                curNode.left = new Node<>(key, value);
                size++;
                return null;
            } else {
                // If there is already a node in the left subtree, recursively call put on that node
                return put(key, value, curNode.left);
            }
        } else if (key.compareTo(curNode.getKey()) > 0) {
        // If the key is greater than the current node's key, insert the node in the right subtree
            if (curNode.right == null) {
                curNode.right = new Node<>(key, value);
                size++;
                return null;
            } else {
            // If there is already a node in the right subtree, recursively call put on that node
                return put(key, value, curNode.right);
            }
        } else {
            // If the key already exists in the tree, update the node's value and return the old value
            V oldVal = curNode.getValue();
            curNode.setValue(value);
            return oldVal;
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }

        return containsKey(key, root);
    }

    private boolean containsKey(K key, Node<K, V> curNode) {
        if (key.compareTo(curNode.getKey()) > 0) {
            if (curNode.right != null) {
                return containsKey(key, curNode.right);
            } else {
                return false;
            }
        } else if (key.compareTo(curNode.getKey()) < 0) {
            if (curNode.left != null) {
                return containsKey(key, curNode.left);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        if (key == root.getKey()) {
            return root.getValue();
        } else {
            return get(key, root);
        }
    }


    private V get(K key, Node<K, V> curNode) {
        if (curNode == null) {
            return null;
        } else if (key.compareTo(curNode.getKey()) < 0) {
            return get(key, curNode.left);
        } else if (key.compareTo(curNode.getKey()) > 0) {
            return get(key, curNode.right);
        } else {
            return curNode.getValue();
        }
    }
    
    @Override
    public V remove(K key) {
        if (root == null) {
            return null;
        }
        V value = get(key); // get the value associated with the key
        root = remove(root, key); // remove the node with the key
        if (root != null) {
            root.color = BLACK; // ensure the root is black
        }
        if (value != null) {
            size--; // decrease the size of the tree
        }
        return value;
    }
    
    private Node<K, V> remove(Node<K, V> curNode, K key) {
        if (key.compareTo(curNode.getKey()) < 0) {
            if (!isRed(curNode.left) && !isRed(curNode.left.left)) {
                curNode = moveRedLeft(curNode); // ensure no 2 consecutive red nodes on the left
            }
            curNode.left = remove(curNode.left, key);
        } else {
            if (isRed(curNode.left)) {
                curNode = rotateRight(curNode); // ensure that the node to be removed is on the right of a red node
            }
            if (key.compareTo(curNode.getKey()) == 0 && curNode.right == null) {
                return null; // remove the node with the key
            }
            if (!isRed(curNode.right) && !isRed(curNode.right.left)) {
                curNode = moveRedRight(curNode); // ensure no 2 consecutive red nodes on the right
            }
            if (key.compareTo(curNode.getKey()) == 0) {
                Node<K, V> minNode = findMin(curNode.right); // find the minimum node in the right subtree
                put(minNode.getKey(), minNode.getValue(),minNode);
                curNode.setValue(minNode.getValue());
                curNode.right = deleteMin(curNode.right); // delete the minimum node from the right subtree
            } else {
                curNode.right = remove(curNode.right, key);
            }
        }
        return balance(curNode); // balance the tree
    }
    
    private Node<K, V> moveRedLeft(Node<K, V> node) {
        flipColors(node); // flip the colors
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right); // rotate to balance the tree
            node = rotateLeft(node);
            flipColors(node); // flip the colors again
        }
        return node;
    }
    
    private Node<K, V> moveRedRight(Node<K, V> node) {
        flipColors(node); // flip the colors
        if (isRed(node.left.left)) {
            node = rotateRight(node); // rotate to balance the tree
            flipColors(node); // flip the colors again
        }
        return node;
    }
    
    private Node<K, V> findMin(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    private Node<K, V> deleteMin(Node<K, V> h) {
        if (h.left == null) {
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node<K, V> balance(Node<K, V> h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        keySet(root, keys);
        return keys;
    }

    private void keySet(Node<K, V> curNode, ArrayList<K> keys) {
        if (curNode != null) {
            keySet(curNode.left, keys);
            keys.add(curNode.getKey());
            keySet(curNode.right, keys);
        }
    }

    @Override
    public ArrayList<V> values() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    @Override
    public ArrayList<KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> list = new ArrayList<>();
        entrySet(root, list);
        return list;
    }
    
    private void entrySet(Node<K, V> node, ArrayList<KeyValuePair<K, V>> list) {
        if (node != null) {
            entrySet(node.left, list);
            list.add(new KeyValuePair<>(node.getKey(), node.getValue()));
            entrySet(node.right, list);
        }
    }
    

    @Override
    public int size() {
        return size;
    }
    
    @Override
    public void clear() {
        size = 0;
        root = null;
    }
    
    @Override
    public int maxDepth() {
        return maxDepth(root);
    }
    
    private int maxDepth(Node<K, V> node) {
        if (node == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    @Override
    public Iterator<MapSet.KeyValuePair<K, V>> iterator() {
        ArrayList<MapSet.KeyValuePair<K, V>> list = new ArrayList<>();
        Stack<Node<K, V>> stack = new Stack<>();
        Node<K, V> curNode = root;
    
        while (curNode != null || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = stack.pop();
                list.add(curNode);
                curNode = curNode.right;
            }
        }
    
        return list.iterator();
    }

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    toString(root, sb, 0);
    return sb.toString();
}

private void toString(Node<K, V> node, StringBuilder sb, int depth) {
    if (node != null) {
        toString(node.right, sb, depth + 1);
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }
        sb.append(node.getKey()).append(": ").append(node.getValue()).append(" (").append(node.color ? "RED" : "BLACK").append(")").append("\n");
        toString(node.left, sb, depth + 1);
    }
}

    
    public static void main (String[] args) {
        RedBlackTree<Integer, String> testTree = new RedBlackTree<>();

        testTree.put(2, "Pa");
        testTree.put(3, "Pap");
        testTree.put(1, "Papa");
        testTree.put(5, "PapaYaw");

        System.out.println(testTree.toString());

        for (MapSet.KeyValuePair<Integer, String> curNode : testTree ) {
            System.out.println(curNode.getKey() + ": " + curNode.getValue());
        }

    }
}
