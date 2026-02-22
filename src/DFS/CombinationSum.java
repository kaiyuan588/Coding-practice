import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
// Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target.
// You may return the combinations in any order.

// The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

// The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
// Example 1:

// Input: candidates = [2,3,6,7], target = 7
// Output: [[2,2,3],[7]]
// Explanation:
// 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
// 7 is a candidate, and 7 = 7.
// These are the only two combinations.
// Example 2:

// Input: candidates = [2,3,5], target = 8
// Output: [[2,2,2,2],[2,3,3],[3,5]]
// Example 3:

// Input: candidates = [2], target = 1
// Output: []
/*
    Clarify questions:
    1. All positive numbers?
    2. Are all candidate distinct numbers?
    3. Can ask or not: Can each number be used unlimited times?
    4. Should [2,2,3] and [3,2,2] be considered the same combination?
    5. Does the output need to be sorted?
    6. “Is the number of valid combinations bounded?”
    7. “If target is 0, should we return an empty combination [[]]?”
    
    Most important 3 questions:
    1. Are candidates distinct?
    2. Are all numbers positive?
    3. Can each number be reused unlimited times?
*/
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        dfs(candidates, 0, 0, target, new ArrayList<>(), res);
        return res;
    }
    public static void dfs(int[] candi, int index, int curSum, int target, List<Integer> path, List<List<Integer>> res) {
        if (curSum == target) {
            res.add(new ArrayList<>(path));
            return ;
        }
        for (int i = index; i < candi.length; i++) {
            if (curSum+candi[i] > target) {
                continue;
            }
            path.add(candi[i]);
            dfs(candi, i, curSum+candi[i], target, path, res);
            path.remove(path.size()-1);
        }
    }
}
