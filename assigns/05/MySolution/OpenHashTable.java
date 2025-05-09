/*
 * OpenHashTable.java
 *
 * Computer Science 112, Boston University
 */

/*
 * A class that implements a hash table that employs open addressing
 * using either linear probing, quadratic probing, or double hashing.
 */
public class OpenHashTable<K, V> implements HashTable<K, V> {
    /* Private inner class for an entry in the hash table */
    private class Entry {
        private K key;
        private LLQueue<V> values;

        private Entry(K key, V value) {
            this.key = key;
            values = new LLQueue<>();
            values.insert(value);
        }
    }
    
    // possible types of probing
    public static final int LINEAR = 0;
    public static final int QUADRATIC = 1;
    public static final int DOUBLE_HASHING = 2;
    public static final int NUM_PROBE_TYPES = 3;
    
    private Entry[] table;             // the hash table itself
    private int probeType = LINEAR;    // the type of probing
    
    public OpenHashTable(int size, int probeType) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        if (probeType < 0 || probeType >= NUM_PROBE_TYPES) {
            throw new IllegalArgumentException("invalid probeType: " + probeType);
        }
        
        table = (Entry[]) new Object[size];
        this.probeType = probeType;
    }
    
    /*
     * Constructor for a hash table of the specified size that uses double hashing
     */ 
    public OpenHashTable(int size) {
        // call the other constructor to do the work
        this(size, DOUBLE_HASHING);
    }
    
    /* first hash function */
    private int h1(K key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /* second hash function */
    private int h2(K key) {
        return 5 - (key.hashCode() % 5);
    }
    
    /* 
     * probeIncr - returns the amount by which the current index
     * should be incremented to obtain the next element in the probe
     * sequence if we have already checked numChecked positions
     * and h2 is the value of the second hash function
     */
    private int probeIncr(int numChecked, int h2) {
        if (probeType == LINEAR) return 1;
        if (probeType == QUADRATIC) return numChecked * numChecked;
        return h2;
    }
    
    /*
     * probe - attempt to find a slot in the hash table for the specified key.
     *
     * If key is currently in the table, it returns the index of the entry.
     * If key isn't in the table, it returns the index of the first empty cell
     * in the table.
     * If overflow occurs, it returns -1.
     */
    private int probe(K key) {
        int i = h1(key);    // first hash function
        int h2 = h2(key);   // second hash function
        int numChecked = 0;
        
        // keep probing until we get an empty position or a match
        while (numChecked < table.length) {
            if (table[i] == null || table[i].key.equals(key)) return i;
            i = (i + probeIncr(numChecked, h2)) % table.length;
            numChecked++;
        }
        
        return -1;
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    @Override
    public boolean insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        
        int index = probe(key);
        if (index == -1) return false;
        if (table[index] == null) {
            table[index] = new Entry(key, value);
        } else {
            table[index].values.insert(value);
        }
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    @Override
    public Queue<V> search(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        
        int index = probe(key);
        if (index == -1 || table[index] == null) return null;
        return table[index].values;
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    @Override
    public Queue<V> remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
            
        int index = probe(key);
        if (index == -1 || table[index] == null) return null;
        LLQueue<V> removedValues = table[index].values;
        table[index] = null;
        return removedValues;
    }
}
