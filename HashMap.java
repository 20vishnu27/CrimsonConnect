package DS_Mini;

import java.util.ArrayList;

//linked list to manage entries in the hash map


//HashMap implementation using the linked list
class HashMap<K, V> {
 private LinkedList<K, V>[] slots;
 private int size; // Number of slots
 private int entries; // Number of entries in the HashMap

 
 public HashMap(int capacity) {
     this.size = capacity;
     this.entries = 0;
     slots = new LinkedList[size];
     for (int i = 0; i < size; i++) {
         slots[i] = new LinkedList<>();
     }
 }

 private int hash(K key) {
     return Math.abs(key.hashCode()) % size; // Simple hash function
 }

 public void put(K key, V value) {
     int keyIndex = hash(key);
     LinkedList<K, V> singleSlot = slots[keyIndex];

     // Check if the key already exists and update its value
     if (singleSlot.get(key) != null) {
     	singleSlot.remove(key); // Remove old value
     }

     singleSlot.add(key, value); // Add new entry
     entries++;
 }

 public V get(K key) {
     int keyIndex = hash(key);
     LinkedList<K, V> singleSlot = slots[keyIndex];
     return singleSlot.get(key); // Get the value from the slot
 }

 public void remove(K key) {
     int keyIndex = hash(key);
     LinkedList<K, V> singleSlot = slots[keyIndex];

     if (singleSlot.remove(key)) {
         entries--; // Decrease size if removal was successful
     }
 }

 public int current_size() {
     return entries;
 }

 public boolean isEmpty() {
     return size == 0;
 }

	public boolean containsKey(K key) {
		int keyIndex = hash(key); // Get the index for the key
     LinkedList<K, V> singleSlot = slots[keyIndex]; // Access the corresponding slots
     return singleSlot.containsKey(key);
	}
	
	public ArrayList<K> keySet() {
     ArrayList<K> keys = new ArrayList<>();
     for (LinkedList<K, V> slot : slots) {
         keys.addAll(slot.getKeys()); // Gather keys from each slot
     }
     return keys; // Return the list of keys
 }
	
}
