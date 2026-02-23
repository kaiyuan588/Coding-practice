import java.util.*;
public class SubarraySumEqualsK {
    // Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

    // A subarray is a contiguous non-empty sequence of elements within an array.



    // Example 1:
    // Input: nums = [1,1,1], k = 2
    // Output: 2

    // Example 2:
    // Input: nums = [1,2,3], k = 3
    // Output: 2

    // MOST BRUTE FORCE O(n^3)
    public int subarraySum1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int p = i; p <= j; p++) {
                    sum += nums[p];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
    // BRUTE FORCE O(n^2)
    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }
    // Example 1:
    // Input: nums = [1,1,1], k = 2
    // Output: 2

    // Example 2:
    // Input: nums = [1,2,3], k = 3
    // Output: 2

    // OPTIMAL SOLUTION
    public int subarraySumOptimal(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);
        int count = 0;

        int prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (prefixSumCount.containsKey(prefix - k)) {
                count += prefixSumCount.get(prefix - k);
            }
            prefixSumCount.put(prefix, prefixSumCount.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }   
}
