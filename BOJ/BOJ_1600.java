package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1600 {
    static int[][] map;
    static int N, M, K = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        String[] raw = br.readLine().split(" ");
        M = Integer.parseInt(raw[0]);
        N = Integer.parseInt(raw[1]);
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        BFS();
    }

    // 0~3 : 원숭이
    // 4~11 : 말
    static int[][] dir = {{-1, 0},{0, 1}, {1, 0}, {0, -1}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};

    private static void BFS(){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 0, K));
        boolean [][][] check = new boolean[N][M][K+1];
        int val = 40000;
        check[0][0][K] = true;

        while (!q.isEmpty()){
            Point p = q.poll();
            if(p.r == N-1 && p.c == M - 1){
                //System.out.println(p);
                //System.out.println();
                val = Math.min(val, p.dist);
            }

            for (int d = 0; d < (p.k==0? 4 : 12); d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                int nk = d < 4 ? p.k : p.k-1;
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !check[nr][nc][nk]){
                    if(map[nr][nc] == 0){
                        Point temp = new Point(nr, nc, p.dist+1, nk);
                        // System.out.println(temp);
                        check[nr][nc][nk] = true;
                        q.offer(temp);
                    }
                }
            }
        }
        System.out.println(val==40000?-1:val);
    }

    public static class Point{
        int r, c, dist, k;

        public Point(int r, int c, int dist, int k) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.k = k;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    ", k=" + k +
                    '}';
        }
    }
}
