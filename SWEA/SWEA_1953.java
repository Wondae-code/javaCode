import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1953 {
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] map;
    static int N, M, R, C, L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            Ans = 0;
            String[] raw = br.readLine().split(" ");
            N = Integer.parseInt(raw[0]);
            M = Integer.parseInt(raw[1]);
            R = Integer.parseInt(raw[2]);
            C = Integer.parseInt(raw[3]);
            L = Integer.parseInt(raw[4]);

            map = new int[N][M];
            for (int r = 0; r < N; r++) {
                raw = br.readLine().split(" ");
                for (int c = 0; c < M; c++) {
                    map[r][c] = Integer.parseInt(raw[c]);
                }
            }

            BFS(R, C);
            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }

    static int Ans;

    // 지나갔던 곳을 또 지나가는 걸
    private static void BFS(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c, 0, setDirection(map[r][c])));
        boolean[][] v = new boolean[N][M];
        v[r][c] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.state < L) {
                //System.out.println(p);
                Ans++;
            }
            for (int d = 0; d < 4; d++) {
                if (p.able[d]) {
                    int nr = p.r + dir[d][0];
                    int nc = p.c + dir[d][1];
                    // 파이프끼리 연결 안 됐을 때 고려하기
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && map[nr][nc] != 0) {
                        if(isConnect(p.r, p.c, nr, nc, d)){
                            v[nr][nc] = true;
                            q.offer(new Point(nr, nc, p.state+1, setDirection(map[nr][nc])));
                        }
                    }
                }
            }
        }
    }

    private static boolean isConnect(int sr, int sc, int dr, int dc, int dir){
        boolean[] sAble = setDirection(map[sr][sc]);
        boolean[] dAble = setDirection(map[dr][dc]);

        switch(dir){
            // 위
            case 0:
                if(sAble[0] && dAble[2]){
                    return true;
                }
                break;
            // 오른쪽
            case 1:
                if(sAble[1] && dAble[3]){
                    return true;
                }
                break;
            // 아래
            case 2:
                if(sAble[2] && dAble[0]){
                    return true;
                }
                break;
            // 왼쪽
            case 3:
                if(sAble[3] && dAble[1]){
                    return true;
                }
                break;
        }
        return false;
    }

    // 1 : 상하좌우 2 : 상하 3 : 좌우 4 : 상우 5 : 하우 6 : 하좌 7 : 상좌
    private static boolean[] setDirection(int type) {
        boolean[] able = new boolean[4];
        switch (type) {
            case 1:
                able[0] = true;
                able[1] = true;
                able[2] = true;
                able[3] = true;
                break;
            case 2:
                able[0] = true;
                able[2] = true;
                break;
            case 3:
                able[1] = true;
                able[3] = true;
                break;
            case 4:
                able[0] = true;
                able[1] = true;
                break;
            case 5:
                able[1] = true;
                able[2] = true;
                break;
            case 6:
                able[2] = true;
                able[3] = true;
                break;
            case 7:
                able[0] = true;
                able[3] = true;
                break;
        }
        return able;
    }

    private static class Point {
        int r, c, state;
        boolean[] able;

        public Point(int r, int c, int state, boolean[] able) {
            this.r = r;
            this.c = c;
            this.state = state;
            this.able = able;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", state=" + state +
                    ", able=" + Arrays.toString(able) +
                    '}';
        }
    }

    private static void print(int[][] map) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
