import java.util.*;

public class SWEA_7699 {
    static int R, C;
    static char[][] map;
    static int cnt;
    static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};
    static boolean[] v;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            R = sc.nextInt();
            C = sc.nextInt();
            map = new char[R][C];
            v = new boolean[26];
            cnt = 0;

            // 값 입력 받기
            for (int r = 0; r < R; r++) {
                String raw = sc.next();
                for (int c = 0; c < C; c++) {
                    map[r][c] = raw.charAt(c);
                }
            }

            // 첫 값 넣기
            v[map[0][0] - 'A'] = true;
            dfs(new Point(0,0), 1);
        }
    }

    private static void dfs(Point p, int tmp) {
        // base part
        cnt = Math.max(cnt, tmp);
        if(cnt == 26){
            return;
        }

        // inductive part
        for (int d = 0; d < 4; d++) {
            int nr = p.r + dir[d][0];
            int nc = p.c + dir[d][1];

            if(nr >= 0 && nr < R && nc >= 0 && nc < C && !v[map[nr][nc] - 'A']){
                v[map[nr][nc] - 'A'] = true;
                dfs(new Point(nr, nc), tmp+1);
                v[map[nr][nc] - 'A'] = false; // backtracking
            }
        }
    }

    static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
