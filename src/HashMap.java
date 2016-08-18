/**
 * Created by jeff.miller on 8/17/16.
 * HashMap implementation based on hashing keys to distribute into buckets
 * each bucket is a LinkedList to handle key collisions
 */
public class HashMap<K, V> {

    Entry<K, V>[] entries; //Our array of linkedlists
    int bucketSize = 2;  // More buckets means less collisions but more memory overhead

    public HashMap() {
        entries = new Entry[bucketSize];
    }

    /**
     * Adds a key / value to the HashMap
     */
    public void put(K key, V value) {
        int bucketLoc = key.hashCode() % bucketSize;
        System.out.println("Adding to bucket:" + bucketLoc);

        if (entries[bucketLoc] == null) {
            entries[bucketLoc] = new Entry(key, value);
        } else {
            Entry entry = entries[bucketLoc];
            entry.addEntry(key, value);
        }
    }

    /**
     * Takes a key and looks up its value
     */
    public Object get(K key) {
        int bucketLoc = key.hashCode() % bucketSize;
        Entry currentEntry = entries[bucketLoc];
        while (currentEntry != null) {
            if (currentEntry.key.equals(key)) {
                return currentEntry.value;
            } else {
                currentEntry = currentEntry.next;
            }
        }
        return null; //not found
    }

    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (Entry entry : entries) {
            Entry currentNode = entry;
            while (currentNode != null) {
                toReturn.append("K:V(" + currentNode.key + ":" + currentNode.value + ")");
                currentNode = currentNode.next;
            }

        }
        return toReturn.toString();
    }


    public static void main(String[] args) {
        HashMap<String, String> hashTest = new HashMap<>();
        hashTest.put("key1", "val1");
        hashTest.put("key2", "val2");
        hashTest.put("key3", "val3");
        hashTest.put("key4", "val1");
        hashTest.put("key5", "val2");
        hashTest.put("key6", "val3");
        System.out.println(hashTest.toString());
        System.out.println(hashTest.get("key1"));
        System.out.println(hashTest.get("key2"));
    }
}

class Entry<K, V> {
    K key = null;
    V value = null;
    Entry next = null;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Adds an entry to the end of the list
     */
    public void addEntry(K key, V value) {

        Entry currentEntry = this;
        do {
            if (currentEntry.next == null) {
                currentEntry.next = new Entry(key, value);
                return;
            }
            currentEntry = currentEntry.next;
        } while (currentEntry != null);
    }
}



