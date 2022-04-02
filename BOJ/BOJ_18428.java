package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_18428 {
    // T로부터 시작해서 퍼진 다음에 S를 만나면 만나는 점에 만들기
    static char[][] map;
    static int N;
    static int obstacleNum = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        map = new char[N][N];

        for (int r = 0; r < N; r++) {
            raw = br.readLine().split(" ");
            for (int c = 0; c < N; c++) {
                map[r][c] = raw[c].charAt(0);
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if(map[r][c] == 'T'){
                    if(!watch(r,c)){
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }
        System.out.println("YES");

    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static boolean watch(int r, int c){
        for (int d = 0; d < 4; d++) {
            int nr = r+dir[d][0], nc = c+dir[d][1];
            while (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 'O'){
                if(map[nr][nc] == 'S' && obstacleNum != 0){
                    if(map[nr - dir[d][0]][nc - dir[d][1]] != 'T') {
                        map[nr - dir[d][0]][nc - dir[d][1]] = 'O';
                        obstacleNum--;
                        break;
                    }else{
                        return false;
                    }
                }else if (map[nr][nc] == 'S' && obstacleNum == 0){
                    return false;
                }
                nr += dir[d][0];
                nc += dir[d][1];
            }
        }
        return true;
    }

    private static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}
