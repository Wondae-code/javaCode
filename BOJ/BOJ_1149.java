package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;

public class BOJ_1149 {
    static int[][] values;
    static int[][] DP;
    static int K;
    static OptionalInt Ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        values = new int[K+1][3];
        DP = new int[K+1][3];
        Ans = OptionalInt.of(Integer.MAX_VALUE);
        for (int i = 1; i <= K; i++) {
            values[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.arraycopy(values[1], 0, DP[1], 0, 3);

        Color();
        System.out.println(Ans.getAsInt());

    }

    private static void Color(){
        for (int level = 2; level <= K; level++) {
            for (int color = 0; color < 3; color++) {
                if(color == 0) DP[level][color] = values[level][color] + Math.min(DP[level-1][1], DP[level-1][2]);
                if(color == 1) DP[level][color] = values[level][color] + Math.min(DP[level-1][0], DP[level-1][2]);
                if(color == 2) DP[level][color] = values[level][color] + Math.min(DP[level-1][0], DP[level-1][1]);
            }
        }
        Ans = Arrays.stream(DP[K]).min();
    }
}