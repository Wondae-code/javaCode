/*
접근 방법
1. 전체를 돌아다니며 각 칸의 근처 칸 비용을 계산 후 배열에 담고 정렬 후 가장 작은 3값부터 검색

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14620 {
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 배열 만들고 값 넣기
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] raw = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(raw[j]);
            }
        }
        // 각 좌표들의 값을 넣기 위한 배열
        Point[] values = scan();

        Arrays.sort(values);
        print();
    }

    static class Point implements Comparable<Point>{
        int r, c, val;
        boolean[] avail;

        public Point(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
            avail = new boolean[4];
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", val=" + val +
                    '}';
        }

        @Override
        public int compareTo(Point o) {
            return this.val - o.val;
        }
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌
    private static Point[] scan(){
        Point[] temp = new Point[N*N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i*N + j] = new Point(i,j, 0);
                for (int d = 0; d < 4; d++) {
                    int nr = i + dir[d][0];
                    int nc = j + dir[d][1];
                    // 모서리 체크
                    if(nr >= 0 && nr < N && nc >= 0 && nc < N){
                        temp[i*N + j].val += map[nr][nc];
                        temp[i*N + j].avail[d] = true;
                    }
                }
            }
        }
        return temp;
    }

    private static void print(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
