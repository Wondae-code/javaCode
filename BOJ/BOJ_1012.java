package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1012 {
    static int N, M, K;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            String[] raw = br.readLine().split(" ");
            M = Integer.parseInt(raw[0]);
            N = Integer.parseInt(raw[1]);
            K = Integer.parseInt(raw[2]);
            map = new boolean[N][M];
            for (int i = 0; i < K; i++) {
                raw = br.readLine().split(" ");
                int r = Integer.parseInt(raw[1]);
                int c = Integer.parseInt(raw[0]);
                map[r][c] = true;
            }

            int cnt = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if(map[r][c]){
                        cnt++;
                        BFS(r, c);
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void BFS(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        map[r][c] = false;

        while(!q.isEmpty()){
            int[] p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p[0]+dir[d][0];
                int nc = p[1]+dir[d][1];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc]){
                    map[nr][nc] = false;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
