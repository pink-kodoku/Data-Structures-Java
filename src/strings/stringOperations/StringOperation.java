package strings.stringOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringOperation {

    public static void main(String[] args) {
        System.out.println(reverse("Hello world"));
        System.out.println(getSuffixes("Hello"));
        System.out.println(getPrefixes("Hello"));
        System.out.println(getLongestCommonPrefix("Hello", "Hello world"));
        System.out.println(longestRepeatedSubstring("abcabc"));
        System.out.println();
    }

    // O(n)
    public static String reverse(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }

        return stringBuilder.toString();
    }

    public static List<String> getSuffixes(String str) {
        int length = str.length();
        List<String> suffixesList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            suffixesList.add(str.substring(i, length));
        }

        return suffixesList;
    }

    public static List<String> getPrefixes(String str) {
        int length = str.length();
        List<String> prefixes = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            prefixes.add(str.substring(0, i + 1));
        }

        return prefixes;
    }

    public static String getLongestCommonPrefix(String str1, String str2) {
        int commonLength = Math.min(str1.length(), str2.length());

        for (int i = 0; i < commonLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }

        return str1.substring(0, commonLength);
    }

    public static String longestRepeatedSubstring(String str) {
        int length = str.length();
        List<String> suffixes = getSuffixes(str);

        Collections.sort(suffixes);

        System.out.println(suffixes);

        String longestSubstring = "";

        for (int i = 0; i < length - 1; i++) {
            String tempString = getLongestCommonPrefix(suffixes.get(i), suffixes.get(i + 1));
            if (tempString.length() > longestSubstring.length()) {
                longestSubstring = tempString;
            }
        }

        return longestSubstring;
    }
}
