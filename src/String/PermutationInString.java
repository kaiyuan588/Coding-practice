package String;

public class PermutationInString {
// You are given two strings s1 and s2.

// Return true if s2 contains a permutation of s1, or false otherwise. That means if a permutation of s1 exists as a substring of s2, then return true.

// Both strings only contain lowercase letters.

// Example 1:

// Input: s1 = "abc", s2 = "lecabee"

// Output: true
// Explanation: The substring "cab" is a permutation of "abc" and is present in "lecabee".

// Example 2:

// Input: s1 = "abc", s2 = "lecaabee"

// Output: false
// Constraints:

// 1 <= s1.length, s2.length <= 1000

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i)-'a']++;
        }

        int left = 0; 
        int right = 0;
        
        while (right < s2.length()) {
            int dis = right - left + 1;
            if (dis <= s1.length()) {
                // update first
                map[s2.charAt(right)-'a']--;
                right++;
                // find the res
                if (dis == s1.length() && isValid(map)) {
                    return true;
                }
            } else {
                map[s2.charAt(left)-'a']++;
                left++;
            }
        }
        return false;
    }
    public boolean isValid(int[] map) {
        for (int i: map) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
