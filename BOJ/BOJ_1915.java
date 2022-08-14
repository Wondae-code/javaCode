package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1915 {
    static int N, M;
    static boolean[][] map;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");

        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);

        map = new boolean[N+1][M+1];

        dp = new int[N+1][M+1];

        for (int r = 1; r <= N; r++) {
            String t = br.readLine();
            for (int c = 1; c <= M; c++) {
                map[r][c] = (t.charAt(c-1) == '1');
            }
        }

        int max = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                // 정사각형 크기 측정
                if(map[r][c]){
                    dp[r][c] = Math.min(dp[r-1][c-1], Math.min(dp[r-1][c], dp[r][c-1]))+1;
                    max = Math.max(max, dp[r][c]);
                }
            }
        }

        System.out.println(max*max);
    }

}
