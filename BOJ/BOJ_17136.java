package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_17136 {
    static int[][] map;
    static int[] papers = {0, 5, 5, 5, 5, 5}; // 1*1, 2*2, 3*3, 4*4, 5*5
    static int Ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        map = new int[10][10];
        for (int r = 0; r < 10; r++) {
            map[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        solve(0);
        System.out.println(Ans == Integer.MAX_VALUE ? -1 : Ans);
    }


    private static void solve(int cnt){
        // 위치 탐색
        int sr = -1, sc = -1;
        outer : for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if(map[r][c] == 1){
                    sr = r;
                    sc = c;
                    break outer;
                }
            }
        }

        // 만약 색종이를 다 붙였다면
        if(sr == -1 && sc == -1){
            Ans = Math.min(Ans, cnt);
            return;
        }

        int size = getPaperSize(sr,sc);

        // 가능한 크기를 다 붙여봄
        for (int i = 1; i <= size; i++) {
            // 붙일 색종이가 남으면 붙여라
            if(papers[i] > 0){
                papers[i]--;
                for (int r = 0; r < i; r++) {
                    for (int c = 0; c < i; c++) {
                        map[sr + r][sc + c] = 0;
                    }
                }
                solve(cnt+1);
                // backtracking
                papers[i]++;
                for (int r = 0; r < i; r++) {
                    for (int c = 0; c < i; c++) {
                        map[sr + r][sc + c] = 1;
                    }
                }
            }
        }
    }

    private static int getPaperSize(int sr, int sc){
        // 최대 paper size
        int size = 5;
        while (size > 1){
            boolean flag = true;
            outer: for (int r = sr; r < sr + size; r++) {
                for (int c = sc; c < sc + size; c++) {
                    if (r < 0 || r >= 10 || c < 0 || c >= 10 || map[r][c] != 1) {
                        flag = false;
                        break outer;
                    }
                }
            }
            if(flag){
                return size;
            }
            size--;
        }
        return size;
    }
}
