package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17086 {
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    static int[][] map, check;
    static int Ans, N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        map = new int[N + 1][M + 1];
        check = new int[N+1][M+1];
        for (int r = 1; r <= N; r++) {
            raw = br.readLine().split(" ");
            for (int c = 1; c <= M; c++) {
                map[r][c] = Integer.parseInt(raw[c - 1]);
            }
        }

        // print(map);

        // BFS
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                // BFS
                check[r][c] = BFS(r, c);
                Ans = Math.max(check[r][c], Ans);
            }
        }

        System.out.println(Ans);
    }

    private static int BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        int[][] v = new int[N+1][M+1];
        q.offer(new int[]{x, y});
        v[x][y] = 1;
        int val = 0;

        while (!q.isEmpty()){
            int[] p = q.poll();
            val = v[p[0]][p[1]];
            if(map[p[0]][p[1]] == 1){
                return val - 1;
            }

            for (int d = 0; d < 8; d++) {
                int nr = p[0] + dir[d][0];
                int nc = p[1] + dir[d][1];
                if(nr >= 1 && nr <= N && nc >= 1 && nc <= M && v[nr][nc] == 0){
                    q.offer(new int[]{nr, nc});
                    v[nr][nc] = v[p[0]][p[1]] + 1;
                }
            }
        }

        return val;
    }


    private static void print(int[][] map) {
        for (int r = 1; r < map.length; r++) {
            for (int c = 1; c < map[0].length; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
