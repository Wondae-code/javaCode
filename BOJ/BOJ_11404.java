package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11404 {
    // 플로이드 워샬 연습
    static int[][] map, dist;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        map = new int[N+1][N+1];
        dist = new int[N+1][N+1];
        // 값 넣기
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            String[] raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);
            int val = Integer.parseInt(raw[2]);
            if(map[src][dst] == 0){
                map[src][dst] = val;
            }else{
                map[src][dst] = Math.min(map[src][dst], val);
            }
        }

        // dist 복사
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if(r == c) {
                    dist[r][c] = 0;
                }else if(map[r][c] != 0){
                    dist[r][c] = map[r][c];
                }else{
                    dist[r][c] = 100000000;
                }
            }
        }

        // 플로이드-워셜 적용
        for (int k = 1; k <= N; k++) {
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    dist[r][c] = Math.min(dist[r][c] , dist[r][k] + dist[k][c]);
                }
            }
        }

        print(dist);
    }

    private static void print(int[][] map) {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if(map[r][c] >= 100000000) map[r][c] = 0;
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }


}
