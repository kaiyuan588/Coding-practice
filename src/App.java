import java.util.*;

import String.NumberOfSubsequence;
public class App {
    public static void main(String[] args) throws Exception {
        String s = "dsahjpjauf";
        String[] words = new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"};

        System.out.print("ans: " + NumberOfSubsequence.numberOfSubsequence2(s, words));
    }
}
