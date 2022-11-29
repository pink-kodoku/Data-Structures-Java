package strings.bruteForceSearch;

public class BruteForceSearch {
    public static int search(String text, String pattern) {
        int count = 0;
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            int j;
            for (j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }

            if (j == pattern.length()) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String text = "Hello world my name is Hello world";
        String pattern = "my";
        System.out.println(search(text, pattern));
    }
}
