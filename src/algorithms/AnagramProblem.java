package algorithms;

import java.util.Arrays;
import java.util.Objects;

public class AnagramProblem {
    public static void main(String[] args) {
        System.out.println(isAnagram("cat", "tac"));
        System.out.println(isAnagram("restful", "fluster"));
    }

    public static boolean isAnagram(String word1, String word2) {
        char[] word1Chars = word1.toCharArray();
        char[] word2Chars = word2.toCharArray();
        Arrays.sort(word1Chars);
        Arrays.sort(word2Chars);

        for (int i = 0; i < word1Chars.length; i++) {
            if (word1Chars[i] != word2Chars[i]) {
                return false;
            }
        }

        return true;
    }
}
