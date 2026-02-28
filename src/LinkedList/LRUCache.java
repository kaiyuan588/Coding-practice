package LinkedList;
import java.util.*;
public class LRUCache {
// Implement the Least Recently Used (LRU) cache class LRUCache. The class should support the following operations
// LRUCache(int capacity) Initialize the LRU cache of size capacity.
// int get(int key) Return the value corresponding to the key if the key exists, otherwise return -1.
// void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the introduction of the new pair causes the cache to exceed its capacity, remove the least recently used key.
// A key is considered used if a get or a put operation is called on it.

// Ensure that get and put each run in 
// O
// (
// 1
// )
// O(1) average time complexity.

// Example 1:

// Input:
// ["LRUCache", [2], "put", [1, 10],  "get", [1], "put", [2, 20], "put", [3, 30], "get", [2], "get", [1]]

// Output:
// [null, null, 10, null, null, 20, -1]

// Explanation:
// LRUCache lRUCache = new LRUCache(2);
// lRUCache.put(1, 10);  // cache: {1=10}
// lRUCache.get(1);      // return 10
// lRUCache.put(2, 20);  // cache: {1=10, 2=20}
// lRUCache.put(3, 30);  // cache: {2=20, 3=30}, key=1 was evicted
// lRUCache.get(2);      // returns 20 
// lRUCache.get(1);      // return -1 (not found)
    int capacity;
    int curSize;
    DoubleLinkedList left;
    DoubleLinkedList right;
    Map<Integer, DoubleLinkedList> map;

    public LRUCache(int capacity) { 
        this.capacity = capacity;
        this.curSize = 0;
        this.left = new DoubleLinkedList(-1, -1);
        this.right = new DoubleLinkedList(-1, -1);
        left.next = right;
        right.prev = left;
        this.map = new HashMap<>();
    }
    public int get(int key) { 
        if (map.containsKey(key)) {
            DoubleLinkedList node = map.get(key);
            moveToTheMostRecent(node);
            return node.val;
        } else {
            return -1;
        }
    }
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoubleLinkedList node = map.get(key);
            node.val = value;
            moveToTheMostRecent(node);
            map.put(key, node);
        } else {
            // doesn't exist
            if (curSize < capacity) {
                DoubleLinkedList newNode = new DoubleLinkedList(key, value);
                map.put(key, newNode);
                addToTheMostRecent(newNode);
                curSize++;
            } else {
                DoubleLinkedList leastRecent = left.next;
                removeNode(leastRecent);
                map.remove(leastRecent.key);

                DoubleLinkedList newNode = new DoubleLinkedList(key, value);
                addToTheMostRecent(newNode);
                map.put(key, newNode);
            }
        }
    }
    public void addToTheMostRecent(DoubleLinkedList node) {
        DoubleLinkedList mostRecent = right.prev;
        mostRecent.next = node;
        node.prev = mostRecent;
        
        node.next = right;
        right.prev = node;
    }
    public void moveToTheMostRecent(DoubleLinkedList node) {
        removeNode(node);
        addToTheMostRecent(node);
    }
    public void removeNode(DoubleLinkedList node) {
        DoubleLinkedList prevNode = node.prev;
        DoubleLinkedList nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
    public class DoubleLinkedList {
        int key;
        int val;
        DoubleLinkedList prev;
        DoubleLinkedList next;
        public DoubleLinkedList(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}


