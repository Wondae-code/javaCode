package level1;

import java.util.HashMap;

public class 숫자_문자열과_영단어 {
    static String s = "one4seveneight";
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        // 너무 불편한데
        map.put("zero", "0");
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        map.put("nine", "9");

        for (String numText :map.keySet()) {
            s = s.replaceAll(numText, map.get(numText));
        }
    }
}
