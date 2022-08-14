package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1018 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        int N = Integer.parseInt(raw[0]);
        int M = Integer.parseInt(raw[1]);
        char[][] map = new char[N][M];
        for (int r = 0; r < N; r++) {
            String raw1 = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = raw1.charAt(c);
            }
        }

        // (0, 0) 부터 (N-8, M-8)까지 최저값 탐색
        int cnt = Integer.MAX_VALUE;
        for (int n = 0; n <= N-8; n++) {
            for (int m = 0; m <= M-8; m++) {
                // 각 칸의 색칠 해야 하는 곳 찾기
                char[] firstColors = {'W', 'B'};
                for(char prevColor : firstColors) {
                    int tempCnt = map[n][m] != prevColor ? 1 : 0;
                    for (int r = n; r < n + 8; r++) {
                        if(r != n) {
                            prevColor = prevColor == 'B' ? 'W' : 'B';
                        }
                        for (int c = m; c < m + 8; c++) {
                            if (r == n && c == m) continue;
                            if(map[r][c] == prevColor){
                                tempCnt++;
                            }
                            prevColor = prevColor == 'B' ? 'W' : 'B';
                        }
                    }
                    cnt = Math.min(cnt, tempCnt);
                }
            }
        }
        System.out.println(cnt);
    }
}
