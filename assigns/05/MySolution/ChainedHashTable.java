/*
 * ChainedHashTable.java
 *
 * Computer Science 392, Boston University
 * 
 * Modifications and additions by:
 *     name:
 *     email:
 */

import java.util.*;
// to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable<K, V> implements HashTable<K, V> {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private K key;
        private LLQueue<V> values;
        private Node next;

        private Node(K key, V value) {
            this.key = key;
            values = new LLQueue<>();
            values.insert(value);
            next = null;
        }
    }

    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table

    /* hash function */
    private int h1(K key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) h1 += table.length;
        return h1;
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    @Override
    public boolean insert(K key, V value) {
        int index = h1(key);
        Node trav = table[index];
        while (trav != null) {
            if (trav.key.equals(key)) {
                trav.values.insert(value);
                return true;
            }
            trav = trav.next;
        }
        Node newNode = new Node(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        numKeys++;
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    @Override
    public Queue<V> search(K key) {
        int index = h1(key);
        Node trav = table[index];
        while (trav != null) {
            if (trav.key.equals(key)) {
                return trav.values;
            }
            trav = trav.next;
        }
        return null;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    @Override
    public Queue<V> remove(K key) {
        int index = h1(key);
        Node trav = table[index];
        Node prev = null;
        while (trav != null) {
            if (trav.key.equals(key)) {
                if (prev == null) {
                    table[index] = trav.next;
                } else {
                    prev.next = trav.next;
                }
                numKeys--;
                return trav.values;
            }
            prev = trav;
            trav = trav.next;
        }
        return null;
    }
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /** Add your unit tests here **/
    }
}
