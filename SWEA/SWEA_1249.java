import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1249 {
    // 단순히 가장 작은 값으로만 이동하면 되나?
    static int[][] map;
    static int N;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int Ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./inputs/SWEA_1249.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int r = 0; r < N; r++) {
                String temp = br.readLine();
                for (int c = 0; c < N; c++) {
                    map[r][c] = temp.charAt(c) - '0';
                }
            }
            //print();
            Ans = Integer.MAX_VALUE;
            BFS();

            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }
    // 0인곳을 최우선으로 넣고, 그 다음에 가장 작은 곳을 넣는다 -> 그냥 4 방향 중 최고로 적은 곳을 넣는다?

    private static void BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, map[0][0]));
        boolean[][] v = new boolean[N][N];
        v[0][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            System.out.println(p);
            if (p.r == N - 1 && p.c == N - 1) {
                Ans = Math.min(Ans, p.dist);
            }

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
                    v[nr][nc] = true;
                    q.offer(new Point(nr, nc, p.dist + map[nr][nc]));
                }
            }

        }
    }

    private static class Point {
        int r, c, dist;

        public Point(){}

        public Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dist=" + dist +
                    '}';
        }
    }

    private static void print() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
