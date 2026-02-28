package String;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class NumberOfSubsequence {
        // Given a string s and an array of string words, return the number of words[i] that is a subsequence of s
    // a subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
    // For example, "ace" is a subsequence of "abced".
    // Example.1:
    // input s = "abcde", words = ["a", "bb", "acd", "ace"]
    // output: 3
    
    // input s = "dsahjpjauf", words = ["ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"]
    // output: 2

    // brute force
    public static int numberOfSubsequence(String s, String[] words) {
        if (s == null || s.length() == 0 || words == null || words.length == 0) return 0;
        int count = 0;
        for (String t: words) {
            if (isSubsequence(s, t)) {
                count++;
            }
        }
        return count;
    }
    public static boolean isSubsequence(String s, String t) {

        int sIndex = 0;
        int tIndex = 0;

        for (sIndex = 0; sIndex < s.length(); sIndex++) {
            if (tIndex == t.length()) {
                break;
            }
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                tIndex++;
            }
        }
        return tIndex == t.length();
    }
    // input s = "abcde", words = ["a", "bb", "acd", "ace"]
    // output: 3

    // a: [a, acd, ace]
    // b: [bb]
    public static int numberOfSubsequence2(String s, String[] words) { 
        if (s == null || s.length() == 0 || words == null || words.length == 0) return 0;
        int count = 0;
        Map<Character, Deque<Deque<Character>>> bucket = new HashMap<>();

        for (char c = 'a'; c <= 'z'; c++) {
            bucket.put(c, new ArrayDeque<>());
        }
        for (String str: words) {
            if (str.length() == 0) {
                count++;
                continue;
            }
            Deque<Character> deque = new ArrayDeque<>();
            for (char c: str.toCharArray()) {
                deque.offerLast(c);
            }
            bucket.get(str.charAt(0)).offerLast(deque);
        }

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            Deque<Deque<Character>> queue = bucket.get(currentChar);
            if (queue == null) continue;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Deque<Character> cur = queue.pollFirst();
                cur.pollFirst();
                if (cur.isEmpty()) {
                    count++;
                } else {
                    bucket.get(cur.peekFirst()).add(cur);
                }
            }
        }
        return count;
    }
}
