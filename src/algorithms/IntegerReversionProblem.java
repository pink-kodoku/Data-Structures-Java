package algorithms;

public class IntegerReversionProblem {
    public static void main(String[] args) {
        System.out.println(reverse(2847824));
        System.out.println(reverse(259));
        System.out.println();
    }

    public static int reverse(int number) {
        int result = 0;
        int userNumber = number;

        int k = 1;
        while (userNumber / k > 0) {
            int div = (userNumber / k) % 10;
            result = (result * 10) + div;
            k *= 10;
        }

        return result;
    }
}
