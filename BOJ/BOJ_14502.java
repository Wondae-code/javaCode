package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14502 {
    // 벽을 세운다 -> 바이러스를 퍼지게 한다 -> 0을 센다
    static int[][] map, dir = {{-1, 0}, {0, 1}, {1 , 0}, {0, -1}};
    static int[][] newMap;
    static boolean[][] v;
    static int N, M, Ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        map = new int[N][M];

        ArrayList<Point> zeros = new ArrayList<Point>();

        for (int r = 0; r < N; r++) {
            raw = br.readLine().split(" ");
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(raw[c]);

                if(map[r][c] == 0){
                    zeros.add(new Point(r, c));
                }
            }
        }

        for (int i = 0; i < zeros.size()-2; i++) {
            for (int j = i+1; j < zeros.size()-1; j++) {
                for (int k = j+1; k < zeros.size(); k++) {
                    // 지도 복사하기
                    newMap = new int[N][M];
                    v = new boolean[N][M];
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < M; c++) {
                            newMap[r][c] = map[r][c];
                        }
                    }
                    // 벽 세우기
                    newMap[zeros.get(i).r][zeros.get(i).c] = 1;
                    newMap[zeros.get(j).r][zeros.get(j).c] = 1;
                    newMap[zeros.get(k).r][zeros.get(k).c] = 1;
                    // 바이러스 퍼지게 하기
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < M; c++) {
                            if(newMap[r][c] == 2 && !v[r][c]){
                                BFS(r, c);
                            }
                        }
                    }
                    // 0인 부분 체크하기
                    int temp = 0;
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < M; c++) {
                            if(newMap[r][c] == 0){
                                temp++;
                            }
                        }
                    }
                    Ans = Math.max(Ans, temp);
                }
           }
        }
        System.out.println(Ans);
    }

    private static void BFS(int r, int c){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));
        v[r][c] = true;

        while (!q.isEmpty()){
            Point p = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc] && newMap[nr][nc] == 0){
                    v[nr][nc] = true;
                    newMap[nr][nc] = 2;
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
