package linearStructures.hashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        map.put("1", "John");
        map.put("2", "Lua");
        map.put("3", "Python");
        map.put("4", "Java");
        map.put("5", "C++");
        map.put("6", "Go");
        map.put("7", "JavaScript");
        map.put("8", "C#");
        map.put("4", "length");
        System.out.println("");
        System.out.println(map.get("6"));
    }
}
