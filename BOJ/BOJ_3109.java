package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3109 {
    static boolean[][] map;
    static int R, C, Ans;
    static int[][] dir = {{-1, 1}, {0, 1}, {1, 1}}; // 오른쪽 위, 오른쪽, 오른쪽 아래

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String raw = br.readLine();
        R = Integer.parseInt(raw.split(" ")[0]);
        C = Integer.parseInt(raw.split(" ")[1]);

        map = new boolean[R][C];
        // 값 받기
        for (int r = 0; r < R; r++) {
            raw = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = raw.charAt(c) == '.';
            }
        }

        print();

        map[0][0] = false;
        traverse();

        System.out.println(Ans);
    }

    static int curR;
    public static void traverse(){
        for (int i = 0; i < R; i++) {
            curR = 0;
            map[i][0] = false;
            traverseLine(i,0,1);
        }
    }

    public static void traverseLine(int r, int c, int cnt){
        curR = Math.max(cnt, curR);
        if(curR == C){
            Ans++;
            return;
        }

        for (int d = 0; d < 3; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] && curR != C){
                map[nr][nc] = false;
                traverseLine(nr,nc,cnt+1);
            }
        }
    }

    private static void print(){
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(map[r][c] ? 'O' : 'X');
            }
            System.out.println();
        }
    }
}
