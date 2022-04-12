package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2146 {
    static int N, val = 1, Ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 섬의 번호 붙이기
        boolean[][] check = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(map[r][c] != 0 && !check[r][c]){
                    setIsland(r,c, check);
                    val++;
                }
            }
        }

        // 각 모서리(사방 탐색 중 한칸이라도 물이면)에서 섬간의 최소 거리 계산
        check = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(map[r][c] != 0){
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dir[d][0];
                        int nc = c + dir[d][1];
                        // 모서리 체크 + 하나라도 0이면 거리 계산
                        if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 0){
                            getDist(r, c);
                        }
                    }
                }
            }
        }
        //print();

        System.out.println(Ans);
    }

    // 섬 번호가 다른 곳에 갈 때까지 거리 계산
    private static void getDist(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r,c});
        int[][] check = new int[N][N];
        int val = map[r][c]; // 기준이 될 섬 번호

        while(!q.isEmpty()){
            int[] p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dir[d][0];
                int nc = p[1] + dir[d][1];
                // 섬번호가 같으면 넣지 않음
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != val && check[nr][nc] == 0){
                    if(map[nr][nc] == 0){
                        q.offer(new int[]{nr,nc});
                        check[nr][nc] = check[p[0]][p[1]] + 1;
                    }else{
                        Ans = Math.min(Ans, check[p[0]][p[1]]);
                        return;
                    }
                }
            }
        }
    }

    private static void setIsland(int r, int c, boolean[][] check){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r,c});
        check[r][c] = true;
        map[r][c] = val;

        while(!q.isEmpty()){
            int[] p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dir[d][0];
                int nc = p[1] + dir[d][1];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 0 && !check[nr][nc]){
                    check[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    map[nr][nc] = val;
                }
            }

        }
    }

    private static void print(){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
