package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2636 {
    static int[][] map;
    static int N, M;
    static int temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        int cnt = 0;

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            map[r] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // BFS 탐색 -> 모든 치즈가 없어 질 떄까지 -> 마지막의 수?
//        while(!check()){
//            temp = 0;
//            BFS();
//            //print();
//            cnt++;
//        }

        // DFS 탐색
        while(!check()){
            temp = 0;
            DFS(0, 0, new boolean[N][M]);
            //print();
            cnt++;
        }

        System.out.println(cnt);
        System.out.println(temp);
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static void DFS(int r, int c, boolean[][] verify) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if(nr >= 0 && nr < N && nc >= 0 && nc < M && !verify[nr][nc]){
                verify[nr][nc] = true;
                if(map[nr][nc] == 0) {
                    DFS(nr,nc, verify);
                }else{
                    temp++;
                    map[nr][nc] = 0;
                }
            }
        }
    }





    private static void BFS() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] verify = new boolean[N][M];
        // (0, 0) 부터 진행
        q.offer(new int[]{0, 0});
        verify[0][0] = true;

        while(!q.isEmpty()){
            int[] val = q.poll();
            // System.out.println(Arrays.toString(val));
            for (int d = 0; d < 4; d++) {
                int nr = val[0] + dir[d][0];
                int nc = val[1] + dir[d][1];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !verify[nr][nc]){
                    verify[nr][nc] = true;
                    if(map[nr][nc] == 0) {
                        q.offer(new int[]{nr, nc});
                    }else{
                        temp++;
                        map[nr][nc] = 0;
                    }
                }
            }
        }
    }

    private static boolean check(){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if(map[r][c] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    private static void print(){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
