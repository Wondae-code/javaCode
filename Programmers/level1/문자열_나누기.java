package level1;

import java.util.ArrayList;

public class 문자열_나누기 {
    static String s = "aaabbaccccabba";
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        int charIdx = 0;
        while(true){
            if(charIdx == s.length()) break;
            char x = s.charAt(charIdx);
            int diffCnt = 0;
            int sameCnt = 0;
            boolean flag = false;

            for (int idx = 0; idx < s.length(); idx++) {
                if(x == s.charAt(idx)) sameCnt++;
                else diffCnt++;

                if(diffCnt == sameCnt){
                    // 문자열 분리
                    words.add(s.substring(charIdx, idx+1));
                    s = s.substring(idx+1);
                    charIdx = 0;
                    flag = true;
                    break;
                }
            }

            if(!flag){
                // 남은 문자열 분리
                words.add(s);
                s = "";
            }
        }

        System.out.println(words.size());
    }
}
