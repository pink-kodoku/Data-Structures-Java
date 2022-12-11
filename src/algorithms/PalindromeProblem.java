package algorithms;

public class PalindromeProblem {
    public static void main(String[] args) {
        System.out.println(isPalindrome("madam"));
        System.out.println(isPalindrome("radar"));
        System.out.println(isPalindrome("101"));
        System.out.println(isPalindrome("а роза упала на лапу азора"));
    }

    public static boolean isPalindrome(String word) {
        int lowIndex = 0;
        int highIndex = word.length() - 1;
        while (highIndex > lowIndex) {
            if (word.charAt(lowIndex) != word.charAt(highIndex)) {
                return false;
            }
            lowIndex++;
            highIndex--;
        }

        return true;
    }
}
