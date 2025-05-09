/*
 * HashTable.java
 *
 * Computer Science 392, Boston University
 */

public interface HashTable<K, V> {
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    boolean insert(K key, V value);
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    Queue<V> search(K key);
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    Queue<V> remove(K key);
}
