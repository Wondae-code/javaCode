import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7576 {
    static String[] inputs;
    static int N, M;
    static boolean[][] v;
    static int[][] map, dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputs = br.readLine().split(" ");
        M = Integer.parseInt(inputs[0]);
        N = Integer.parseInt(inputs[1]);

        map = new int[N][M];
        v = new boolean[N][M];
        Queue<Point> check = new LinkedList<>();

        for (int r = 0; r < N; r++) {
            inputs = br.readLine().split(" ");
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(inputs[c]);
                if(map[r][c] == 1){
                    check.offer(new Point(r, c));
                }
            }
        }

        BFS(check);

        System.out.println(isFull() ? findMax() - 1 : -1);
    }

    private static int findMax(){
        int max = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                max = Math.max(map[r][c], max);
            }
        }

        return max;
    }

    private static boolean isFull(){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(map[r][c] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    private static void BFS(Queue<Point> q){
        while(!q.isEmpty()){
            Point p = q.poll();
            v[p.r][p.c] = true;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && (map[nr][nc] == 0) ){
                    map[nr][nc] = map[p.r][p.c] + 1;
                    v[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
