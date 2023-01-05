package level1;

public class 신규_아이디_추천 {
    // 3 <= new_id.length() <= 15
    // new_id only conatains small alphabet, -, _, .
    // . can not be first and last position

    static String new_id = "abcdefghijklmn.p";
    public static void main(String[] args) {
        // 1. 대문자 -> 소문자
        while(true) {
            new_id = new_id.toLowerCase();
            // 2. 허가되지 않은 문자 제거 (_, -, . 제외)
            String regex = "[{}\\[\\]/:?,<>~!@#$%^&*()=+]";
            new_id = new_id.replaceAll(regex, "");
            // 3. ...과 ..을 .로 변경
            new_id = new_id.replaceAll("\\.{3}", ".");
            new_id = new_id.replaceAll("\\.{2}", ".");
            // 4. 문자열 맨 처음과 마지막에 .가 있다면 제거
            if (!new_id.equals("") && new_id.charAt(0) == '.') {
                new_id = new_id.replaceFirst(".", "");
                continue;
            }
            if (!new_id.equals("") && new_id.charAt(new_id.length() - 1) == '.') {
                new_id = new_id.substring(0, new_id.length() - 1);
                continue;
            }
            // 5. 빈 문자열이면 a로 바꿈
            if (new_id.equals("")) {
                new_id = "a";
                continue;
            }
            // 6. 16글자 이상이면 15글자까지만 자름
            if (new_id.length() > 15) {
                new_id = new_id.substring(0, 15);
                continue;
            }
            // 7. 3글자 미만이면 마지막 글자를 반복하여 3글자로 만듬
            while (new_id.length() < 3) {
                new_id += new_id.charAt(new_id.length() - 1);
                continue;
            }
            break;
        }

        System.out.println(new_id);
    }
}
