package DS_Mini;

import java.util.ArrayList;


class LinkedList<K, V> {
	class Node<K, V> {
	    K key;
	    V value;
	    Node<K, V> next;

	    Node(K key, V value) {
	        this.key = key;
	        this.value = value;
	        this.next = null;
	    }
	}
	
	
	
    private Node<K, V> head;

    // Add a new entry to the linked list
    public void add(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        head = newNode;
    }

    // Get the value by key
    public V get(K key) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Return null if not found
    }

    // Remove an entry by key
    public boolean remove(K key) {
        if (head == null) return false; // List is empty

        if (head.key.equals(key)) {
            head = head.next; // Remove head
            return true;
        }

        Node<K, V> current = head;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                current.next = current.next.next; // Remove the node
                return true;
            }
            current = current.next;
        }
        return false; // Key not found
    }
    
    
    public boolean containsKey(K key) {
        Node<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                return true; // Key found
            }
            current = current.next;
        }
        return false; // Key not found
    }
    
    
    public ArrayList<K> getKeys() {
        ArrayList<K> keys = new ArrayList<>();
        Node<K, V> current = head;
        while (current != null) {
            keys.add(current.key); // Add key to the list
            current = current.next;
        }
        return keys; // Return the list of keys
    }
    
    
}