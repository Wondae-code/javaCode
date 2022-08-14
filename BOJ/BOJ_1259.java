package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1259 {
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        while(true){
            char[] input = br.readLine().toCharArray();
            if(input[0] == '0'){
                break;
            }
            checkPalin(input);
        }
        System.out.print(sb);
    }

    private static void checkPalin(char[] input) {
        boolean palin = true;
        for (int i = 0, j = input.length - 1; ; i++, j--) {
            if(input.length % 2 == 0){
               // 길이가 짝수 일 때
               if(j < i) break;
            }else if (i == j) break;

            if(input[i] != input[j]){
                palin = false;
                break;
            }
        }

        sb.append(palin ? "yes" : "no").append('\n');
    }


}
