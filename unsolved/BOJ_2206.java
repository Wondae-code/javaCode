import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2206 {
    static int[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            String t = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = t.charAt(c) - '0';
            }
        }
        //BFS
        BFS();
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0},{0, -1}};

    private static void BFS(){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0,0, 1, 0));
        boolean[][][] check = new boolean[N][M][2];
        int val = 1000000;
        check[0][0][0] = true;

        while(!q.isEmpty()){
            Point p = q.poll();
            if(p.r == N-1 && p.c == M-1){
                System.out.println(p);
                val = Math.min(val, p.dist);
            }


            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !check[nr][nc][p.k]){
                    // 빈칸
                    if(map[nr][nc] == 0){
                        check[nr][nc][p.k] = true;
                        q.offer(new Point(nr, nc, p.dist+1, p.k));
                    }// 벽을 부술 수 있는 경우
                    else if(map[nr][nc] == 1 && p.k == 0){
                        check[nr][nc][p.k] = true;
                        q.offer(new Point(nr, nc, p.dist+1, p.k+1));
                    }
                }
            }
        }
        System.out.println(val==1000000? -1 : val);

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
