package dataCompression.runLengthEncoding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthEncoding {

    public static void main(String[] args) {
        System.out.println(encode("aaabbbcooe"));
        System.out.println(decode(encode("aaabbbcooe")));
    }

    public static String encode(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            int repeatedTimes = 1;
            while (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                repeatedTimes++;
                i++;
            }

            stringBuilder.append(repeatedTimes);
            stringBuilder.append(str.charAt(i));
        }

        return stringBuilder.toString();
    }

    public static String decode(String str) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                char ch = str.charAt(i + 1);
                int n = Integer.parseInt(String.valueOf(str.charAt(i)));
                for (int j = 1; j < n; j++) {
                    stringBuilder.append(ch);
                }
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }

        return stringBuilder.toString();
    }
}
