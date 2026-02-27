import java.util.*;
public class FindMediumInDataStream {
// The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

// For example, for arr = [2,3,4], the median is 3.
// For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
// Implement the MedianFinder class:

// MedianFinder() initializes the MedianFinder object.
// void addNum(int num) adds the integer num from the data stream to the data structure.
// double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

// Example 1:

// Input
// ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
// [[], [1], [2], [], [3], []]
// Output
// [null, null, null, 1.5, null, 2.0]

// Explanation
// MedianFinder medianFinder = new MedianFinder();
// medianFinder.addNum(1);    // arr = [1]
// medianFinder.addNum(2);    // arr = [1, 2]
// medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
// medianFinder.addNum(3);    // arr[1, 2, 3]
// medianFinder.findMedian(); // return 2.0
    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;
    int total;
    public void MedianFinder() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (a > b) return -1;
                return a < b ? 1 : 0;
            }
        });
        total = 0;
    }
    
    public void addNum(int num) {
        if (min.size() - max.size() == 1) {
            max.offer(num);
        } else {
            min.offer(num);
        }
        total++;
    }
    
    public double findMedian() {
        if (total % 2 == 0) {
            return ((double)(min.peek() + max.peek())/2);
        } else {
            return min.peek();
        }
    }


    // 1. min heap with larger values
    // 2. max heap with smaller values
    // 3. maintain minheap size = maxheap size or maxheap size + 1
    // 4. if total size is odd, then return minheap peek
    // 5. if total size is even, then return min peek + max peek / 2

}
