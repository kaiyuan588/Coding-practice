import java.util.*;

public class LetterCombinationOfPhone {
// Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
// A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
// Example 1:
// Input: digits = "23"
// Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// Example 2:

// Input: digits = "2"
// Output: ["a","b","c"]

/*
    clarify
    1.“Can the input contain digits outside 2–9, such as 0 or 1?”
    2. “What should we return if the input string is empty?”
    3. “What is the maximum length of digits?”
    4. “Does the result need to follow lexicographical order?”
    5. “Should duplicate combinations ever appear?”
    6. “Should we assume the standard phone keypad mapping?”
    
    Best questions:
    1. Can digits contain 0 or 1?
    2. What should we return for an empty input?
    3. Should we use the standard phone keypad mapping?
*/
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        dfs(digits, 0, new StringBuilder(), res, map);
        return res;
    }
    public static void dfs(String digits, int index, StringBuilder sb, List<String> res, Map<Character, String> map) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return ;
        }
        String val = map.get(digits.charAt(index));

        for (int i = 0; i < val.length(); i++) {
            sb.append(val.charAt(i));
            dfs(digits, index+1, sb, res, map);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}