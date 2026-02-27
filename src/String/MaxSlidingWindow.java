package String;
import java.util.*;
public class MaxSlidingWindow {

// You are given an array of integers nums and an integer k. There is a sliding window of size k that starts at the left edge of the array. The window slides one position to the right until it reaches the right edge of the array.

// Return a list that contains the maximum element in the window at each step.

// Example 1:

// Input: nums = [1,2,1,0,4,2,6], k = 3

// Output: [2,2,4,4,6]

// Explanation:
// Window position            Max
// ---------------           -----
// [1  2  1] 0  4  2  6        2
//  1 [2  1  0] 4  2  6        2
//  1  2 [1  0  4] 2  6        4
//  1  2  1 [0  4  2] 6        4
//  1  2  1  0 [4  2  6]       6
// Constraints:

// 1 <= nums.length <= 100,000
// -10,000 <= nums[i] <= 10,000
// 1 <= k <= nums.length
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        PriorityQueue<int[]> max = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] > b[1]) return -1;
                return a[1] < b[1] ? 1 : 0;
            }
        });
        for (int i = 0; i < k; i++) {
            max.offer(new int[]{i, nums[i]});
        }
        res[0] = max.peek()[1];

        int index = 1;
        int left = 1; // left should be 1 cuz we already cal the first result.
        for (int right = k; right < nums.length; right++) {
            max.offer(new int[]{right, nums[right]});
            // cal res
            while (!max.isEmpty() && (max.peek()[0] < left || max.peek()[0] > right)) {
                max.poll();
            }
            res[index++] = max.peek()[1];
            left++;
        }
        return res;
    }
}
