package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026 {
    // 일반, 색약
    static char[][] normal, amblyopia;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        normal = new char[N][N];
        amblyopia = new char[N][N];

        // 값 넣기
        for (int r = 0; r < N; r++) {
            String raw = br.readLine();
            for (int c = 0; c < N; c++) {
                normal[r][c] = raw.charAt(c);
                if(normal[r][c] == 'G'){
                    amblyopia[r][c] = 'R';
                }else{
                    amblyopia[r][c] = normal[r][c];
                }
            }
        }

        int normalCnt = 0;
        int amblyopiaCnt = 0;

        // 일반 탐색
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(normal[r][c] != 'x'){
                    normalCnt++;
                    dfs(normal, r, c, normal[r][c]);
                }

                if(amblyopia[r][c] != 'x'){
                    amblyopiaCnt++;
                    dfs(amblyopia, r, c, amblyopia[r][c]);
                }
            }
        }

        System.out.println(normalCnt + " " + amblyopiaCnt);
    }
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static void dfs(char[][] map, int r, int c, char val){
        if(r < 0 || r >= map.length || c < 0 || c >= map.length || map[r][c] != val){
            return;
        }

        map[r][c] = 'x';

        for (int d = 0; d < 4; d++) {
            dfs(map, r + dir[d][0], c + dir[d][1], val);
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
