public class ProductOfArrayExceptItself {
// Given an integer array nums, 
// return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
// You must write an algorithm that runs in O(n) time and without using the division operation.

// Example 1:
// Input: nums = [1,2,3,4]
// Output: [24,12,8,6]

// Example 2:
// Input: nums = [-1,1,0,-3,3]
// Output: [0,0,9,0,0]
 

// Constraints:

// 2 <= nums.length <= 105
// -30 <= nums[i] <= 30
// The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 
// Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)

/*
    Clarify:
    1. “Am I allowed to use division?”
    2. “Should the solution use constant extra space?”
    3. “Can the input contain zeros?”
    4. “Can the product overflow 32-bit integers?”
    5. “What is the size range of the array?”
    6. “Can we modify the input array?”

    Must ask:
    1. Can I use division?
    2. Should I achieve O(1) extra space?
    3. Can the array contain zeros?
*/
    public static int[] productExceptSelf(int[] nums) {
// Example 1:
// Input: nums = [1,2,3,4]
// Output: [24,12,8,6]

// Example 2:
// Input: nums = [-1,1,0,-3,3]
// Output: [0,0,9,0,0]
 
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int n = nums.length;
        int[] prefixProd = new int[n];

        int prefix = 1;
        for (int i = 0; i < n; i++) {
            prefixProd[i] = prefix * nums[i];
            prefix = prefix * nums[i];
        }

        int suffix = 1;
        for (int j = n-1; j >= 0; j--) {
            if (j == 0) {
                prefixProd[j] = suffix;
                continue;
            }
            prefixProd[j] = prefixProd[j-1] * suffix;
            suffix = nums[j] * suffix;
        }
        return prefixProd;
    }
}
