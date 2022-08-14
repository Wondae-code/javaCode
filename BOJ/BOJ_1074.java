package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1074 {
    static int[][] map;
    static int cnt;
    static int[][] dir = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String raw[] = br.readLine().split(" ");
        int N = Integer.parseInt(raw[0]);
        int r = Integer.parseInt(raw[1]);
        int c = Integer.parseInt(raw[2]);

        int size = (int) Math.pow(2, N);
        map = new int[size][size];


    }

    public static void outer(int size, int d){
        if(size == 2){
            inner(1,2);
        }else{
            for (int i = 0; i < 4; i++) {
                outer(size/2, i);
            }
        }
    }

    public static void inner(int curR, int curC) {
        for (int d = 0; d < 4; d++) {
            int nr = curR + dir[d][0];
            int nc = curC + dir[d][1];
            map[nr][nc] = cnt;
            cnt++;
        }
    }
}
