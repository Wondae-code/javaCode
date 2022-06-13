import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// BFS로 가장 가까운 곳을 찾고, 찾은곳에서 집에 갈 수 있는지 체크한다. 만약 된다면 반복, 아니면 방문한 수를 리턴.

public class BOJ_20208 {
    static int[][] map;
    static int N, M, H; // 마을의 크기, 초기 체력, 마실때 증가하는 체력
    static Point start;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        H = Integer.parseInt(raw[2]);

        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            raw = br.readLine().split(" ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(raw[c]);
                if (map[r][c] == 1) {
                    start = new Point(r, c, M);
                }
            }
        }

        BFS(start);
        if(!check){
            System.out.println(cnt);
        }
    }

    private static boolean isBack(Point p){
        return (Math.abs(p.r - start.r) + Math.abs(p.c - start.c) <= p.level);
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int cnt;
    static boolean check = false;

    private static void BFS(Point start) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] v = new boolean[N][N];
        v[start.r][start.c] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            Point p = q.poll();

            // 민초에 도달 했을때
            if(map[p.r][p.c] == 2){
                p.level += H;
                // 집에 돌아 갈 수 있는지 확인
                if(isBack(p)){
                    //System.out.println(p);
                    cnt++;
                    map[p.r][p.c] = 0;
                    BFS(p);
                }else{
                    check = true;
                    System.out.println(cnt);
                }
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]){
                    v[nr][nc] = true;
                    q.offer(new Point(nr, nc, p.level-1));
                }
            }
        }
    }

    private static class Point {
        int r, c, level;

        public Point(int r, int c, int level) {
            this.r = r;
            this.c = c;
            this.level = level;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", level=" + level +
                    '}';
        }
    }
}
