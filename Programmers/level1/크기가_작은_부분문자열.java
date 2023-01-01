package level1;

import java.util.ArrayList;

public class 크기가_작은_부분문자열 {
    static final String t = "7";
    static final String p = "7";
    public static void main(String[] args) {
        int answer = 0;
        // 1. t에서 p와 길이가 같은 부분 문자열 구하기 => sliding window 사용
        int idxL = 0;
        int idxR = p.length();

        while(idxR <= t.length()){
            String subString = t.substring(idxL, idxR);
            if(Long.parseLong(p) >= Long.parseLong(subString)){
                answer++;
            }
            idxL++;
            idxR++;
        }
        System.out.println(answer);
    }
}
