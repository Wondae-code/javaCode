package SSAFY;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2178 {
    static boolean[][] map;
    static int R, C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        // 값 넣기
        map = new boolean[R+1][C+1];
        for (int i = 1; i <= R; i++) {
            String raw = sc.next();
            for (int j = 1; j <= C; j++) {
                map[i][j] = (raw.charAt(j - 1) == '1');
            }
        }

        System.out.println(bfs(1,1));
    }

    public static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static Point[] dir = {new Point(-1, 0), new Point(0, 1), new Point(1, 0), new Point(0, -1)};

    private static int bfs(int r, int c){
        Queue<Point> Q = new LinkedList<>();
        int[][] v = new int[R+1][C+1];
        Q.offer(new Point(r, c));
        v[r][c] = 1;

        int depth = 1;

        while(!Q.isEmpty()){
            Point p = Q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d].r;
                int nc = p.c + dir[d].c;
                if(nr >= 1 && nr <= R && nc >= 1 && nc <= C && map[nr][nc] && v[nr][nc] == 0){
                    v[nr][nc] = v[p.r][p.c] + 1;
                    Q.offer(new Point(nr, nc));
                }
            }
        }

        return v[R][C];
    }
}
