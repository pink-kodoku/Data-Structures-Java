package strings.boyerMooreSearch;

public class Main {
    public static void main(String[] args) {
        BoyerMooreSearch boyerMooreSearch = new BoyerMooreSearch("This is a test test and this is another test gagagagtest again", "test");
        System.out.println(boyerMooreSearch.search());
    }
}
