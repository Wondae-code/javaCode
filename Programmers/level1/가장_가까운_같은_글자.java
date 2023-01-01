package level1;

import java.util.Arrays;

public class 가장_가까운_같은_글자 {
    static final String s = "foobar";
    public static void main(String[] args) {
        // 정답 배열
        int[] answer = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 이전 글자 찾기
            int beforeIdx = i - 1;
            boolean flag = false;
            while(beforeIdx >= 0){
                if(s.charAt(beforeIdx) == c){
                    flag = true;
                    break;
                }
                beforeIdx--;
            }

            answer[i] = flag ? i - beforeIdx : -1;
        }

        System.out.println(Arrays.toString(answer));
    }
}
