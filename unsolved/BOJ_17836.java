import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17836 {
    static int[][] map;
    static int N, M, T, Ans;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");

        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        T = Integer.parseInt(raw[2]);
        Ans = Integer.MAX_VALUE;

        map = new int[N][M];

        for (int r = 0; r < N; r++) {
            raw = br.readLine().split(" ");
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(raw[c]);
            }
        }

        BFS();

        System.out.println(Ans == Integer.MAX_VALUE ? "Fail" : Ans);
    }

    private static void BFS() {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] v = new boolean[N][M][2]; // 가로 세로
        v[0][0][0] = true;
        q.offer(new Point(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Point p = q.poll();
            //System.out.println(p);
            if(p.K > T){
                return;
            }

            if (p.r == N - 1 && p.c == M - 1) {
                Ans = Math.min(Ans, p.K);
                return;
            }


            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc][p.level]) {
                    v[nr][nc][p.level] = true;
                    // 칼을 집었을 때
                    if(p.level == 1){
                        q.offer(new Point(nr, nc, p.level, p.K+1));
                    }else if(p.level == 0){
                        // 벽을 만나지 않았을 때
                        if(map[nr][nc] == 0){
                            q.offer(new Point(nr, nc, p.level, p.K+1));
                        }else if(map[nr][nc] == 2){
                            q.offer(new Point(nr, nc, p.level+1, p.K+1));
                        }
                    }
                }
            }
        }
    }

    private static class Point {
        int r, c, level, K; // 가로, 세로, 검을 집은 경우 안 집은 경우, 이동거리

        public Point(int r, int c, int level, int k) {
            this.r = r;
            this.c = c;
            this.level = level;
            K = k;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", level=" + level +
                    ", K=" + K +
                    '}';
        }
    }
}
