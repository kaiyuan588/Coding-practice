package String;
import java.util.*;
public class MinimumWindowSubstring {
// Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

// The testcases will be generated such that the answer is unique.

 

// Example 1:

// Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"
// Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
// Example 2:

// Input: s = "a", t = "a"
// Output: "a"
// Explanation: The entire string s is the minimum window.
// Example 3:

// Input: s = "a", t = "aa"
// Output: ""
// Explanation: Both 'a's from t must be included in the window.
// Since the largest window of s only has one 'a', return empty string.

public String minWindow(String s, String t) {
        // every char inside t exits in s
        // Steps:
        // maintain a window with char count of t, tMap means how many this char we still need from s
        // looping through s, if s exits in the map, tMap.get(right)'s count-1, if count == 0, formed++
        // while formed == map.size, calculate the reuslt and shirnk the left pointer, update formed to formed-- only if tMap.get(left) == 1

        if (s == null || t == null) return "";
        int m = s.length();
        int n = t.length();
        if (n > m) return "";

        Map<Character, Integer> tMap = new HashMap<>(); 
        // tMap means how many this char we still need
        // 1. if tMap.get(c) < 0, means satisfied, and more, need to returnted some chars
        // 2. if tMap.get(c) == 0, means statified, formed++
        // 3. if tMap.get(c) > 0, means not satisfied, still need chars
        for (char c: t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int formed = 0;
        int left = 0;
        int shortest = Integer.MAX_VALUE;
        String res = "";
        for (int right = 0; right < m; right++) {
            char rightChar = s.charAt(right);
            if (tMap.containsKey(rightChar)) {
                tMap.put(rightChar, tMap.get(rightChar)-1);
                if (tMap.get(rightChar) == 0) {
                    formed++;
                }
            }
            while (formed == tMap.size()) {
                if (right - left + 1 < shortest) {
                    shortest = right - left + 1;
                    res = s.substring(left, right+1);
                }
                char leftChar = s.charAt(left);
                if (tMap.containsKey(leftChar)) {
                    tMap.put(leftChar, tMap.get(leftChar)+1);
                    if (tMap.get(leftChar) == 1) { // 4. That's why we need tMap.get(leftChar) == 1, because it's from 0 to 1, just about time to have formed--
                        formed--;
                    }
                }
                left++;
            }
        }
        return res;
    }
}
