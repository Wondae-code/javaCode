package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17391 {
    static int[][] dir = {{0, 1}, {1, 0}};
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int Ans = BFS();

        System.out.println(Ans);
    }

    private static int BFS() {
        Queue<Point> q = new LinkedList<>();
        int[][] v = new int[N][M];
        q.offer(new Point(0, 0, map[0][0], 0));
        for (int r = 0; r < N; r++) {
            Arrays.fill(v[r], Integer.MAX_VALUE);
        }

        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.r == N - 1 && p.c == M - 1) {
                return p.step;
            }

            for (int i = p.booster; i >= 1; i--) {
                for (int d = 0; d < 2; d++) {
                    int nr = p.r + dir[d][0] * i;
                    int nc = p.c + dir[d][1] * i;
                    if (nr < N && nc < M) {
                        if (v[nr][nc] > p.step + 1) {
                            v[nr][nc] = p.step + 1;
                            q.offer(new Point(nr, nc, map[nr][nc], p.step + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static class Point {
        int r, c, booster, step;

        public Point(int r, int c, int booster, int step) {
            this.r = r;
            this.c = c;
            this.booster = booster;
            this.step = step;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", booster=" + booster +
                    ", step=" + step +
                    '}';
        }
    }
}
