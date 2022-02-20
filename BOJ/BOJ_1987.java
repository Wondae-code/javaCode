package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1987 {
    static int[][] map; // 알파벳의 위치를 표시할 2차원 배열
    static boolean[] check; // 알파벳의 중복을 판단하기 위한 배열
    static int R, C, cnt; // R : 세로 길이, C : 가로 길이, cnt : 최대 길이, 출력할 값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String raw = br.readLine();

        R = Integer.parseInt(raw.split(" ")[0]);
        C = Integer.parseInt(raw.split(" ")[1]);

        map = new int[R][C];
        check = new boolean[26];
        cnt = 0;

        // 값 넣기
        for (int r = 0; r < R; r++) {
            raw = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = raw.charAt(c);
            }
        }

        // 첫번째 값 넣기
        check[map[0][0] - 'A'] = true;

        dfs(0, 0, 1);

        System.out.println(cnt);
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서
    private static void dfs(int r, int c, int temp) {
        // base part
        cnt = Math.max(cnt, temp);
        // 길이가 26이상이면 의미 X
        if(temp == 26){
            return;
        }

        // inductive part
        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if(nr >= 0 && nr < R && nc >= 0 && nc < C && !check[map[nr][nc] - 'A']){
                check[map[nr][nc] - 'A'] = true;
                dfs(nr, nc, temp+1);
                // backtracking
                check[map[nr][nc] - 'A'] = false;
            }
        }
    }
}
