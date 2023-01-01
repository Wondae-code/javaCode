package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


public class BOJ_1620 {
    static String[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        HashMap<Integer, String> pokemon = new HashMap<>();
        HashMap<String, Integer> reverse = new HashMap<>();

        for (int i = 1; i <= N ; i++) {
            String str = br.readLine();
            pokemon.put(i, str);
            reverse.put(str, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            // 문자열과 숫자 구분 방법?
            String str = br.readLine();
            try{
                sb.append(pokemon.get(Integer.parseInt(str))).append('\n');
            } catch (NumberFormatException e){
                sb.append(reverse.get(str)).append('\n');
            }
        }
        System.out.println(sb);
    }
}
