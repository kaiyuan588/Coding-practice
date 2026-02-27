package String;

public class LongestRepeatingCharacterReplacement {
// You are given a string s consisting of only uppercase english characters and an integer k. You can choose up to k characters of the string and replace them with any other uppercase English character.

// After performing at most k replacements, return the length of the longest substring which contains only one distinct character.

// Example 1:

// Input: s = "XYYX", k = 2

// Output: 4
// Explanation: Either replace the 'X's with 'Y's, or replace the 'Y's with 'X's.

// Example 2:

// Input: s = "AAABABB", k = 1

// Output: 5
// Constraints:

// 1 <= s.length <= 1000
// 0 <= k <= s.length

    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] map = new int[26];
        int res = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            map[s.charAt(right) - 'A']++;
            int mostFreq = getMostFreq(map);
            int curWindowSize = right - left + 1;

            while (curWindowSize - mostFreq > k) {
                map[s.charAt(left) - 'A']--;
                left++;
                curWindowSize = right - left + 1;
                mostFreq = getMostFreq(map);
            }

            if (curWindowSize - mostFreq <= k) {
                // valid
                res = Math.max(res, curWindowSize);
            }
        }
        return res;
    }
    
    public int getMostFreq(int[] map) {
        int res = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            res = Math.max(res, map[c - 'A']);
        }
        return res;
    }

    public int characterReplacementOnTime(String s, int k) {
    int[] cnt = new int[26];
    int left = 0, maxCount = 0, res = 0;

    for (int right = 0; right < s.length(); right++) {
        int r = s.charAt(right) - 'A';
        cnt[r]++;
        maxCount = Math.max(maxCount, cnt[r]);

        // 如果需要替换的数量 > k，就收缩
        while ((right - left + 1) - maxCount > k) {
            cnt[s.charAt(left) - 'A']--;
            left++;
        }

        res = Math.max(res, right - left + 1);
    }
    return res;
}
}
