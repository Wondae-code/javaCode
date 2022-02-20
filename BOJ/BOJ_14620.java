package SSAFY;/*
접근 방법
1. 전체를 돌아다니며 각 칸의 근처 칸 비용을 계산 후 배열에 담고 정렬 후 가장 작은 3값부터 검색

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class BOJ_14620 {
    static int[][] map;
    static int N;
    static ArrayList<Point> values;

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
        // 꽃이 다 필 수 있는 칸들의 값들을 저장
        values = scan();
        // val 순서로 정렬
        Collections.sort(values);

        // 안 겹치면서 최소값 찾기
        int Ans = Integer.MAX_VALUE;
        for (int i = values.size() - 1; i >= 2; i--) {
            for (int j = i - 1; j >=  1; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if(check(i,j) && check(j, k) && check(k, i)){
                        Ans = Math.min(values.get(i).val + values.get(j).val + values.get(k).val, Ans);
                    }
                }
            }
        }

        System.out.println(Ans);
    }

    private static boolean check(int i, int j) {
        // BFS를 통해 겹치는게 있는지 확인
        Queue<int[]> Q = new LinkedList<>();
        boolean[][] v = new boolean[N][N];

        Q.offer(values.get(i).getCoord());
        v[values.get(i).r][values.get(i).c] = true;

        while(!Q.isEmpty()){
            int[] p = Q.poll();

            if((p[0] == values.get(j).r && p[1] == values.get(j).c)){
                return false;
            }

            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dir[d][0];
                int nc = p[1] + dir[d][1];
                // 모서리 체크 + 2칸 이내 조건 생각하기
                if(nr >= 0 && nr < N && nc >= 0 && nc <N
                        && Math.abs(nr - values.get(i).r) + Math.abs(nc - values.get(i).c) <= 2
                        && !v[nr][nc]){
                    v[nr][nc] = true;
                    Q.offer(new int[]{nr, nc});
                }
            }
        }


        return true;
    }

    // 사방 탐색 -> 북동남서 순서
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static ArrayList<Point> scan(){
        ArrayList<Point> points = new ArrayList<>();
        for (int r = 1; r < N - 1; r++) {
            for (int c = 1; c < N - 1; c++) {
                int price = map[r][c];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dir[d][0];
                    int nc = c + dir[d][1];
                    // 모서리 체크
                    if(nr >= 0 && nr < N && nc >= 0 && nc < N){
                        price += map[nr][nc];
                    }
                }
                points.add(new Point(r, c, price));
            }
        }
        return points;
    }



    static class Point implements Comparable<Point>{
        int r, c, val;

        public Point(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }

        public int[] getCoord(){
            return new int[]{this.r, this.c};
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", val=" + val +
                    '}';
        }

        // val 기준으로 정렬
        @Override
        public int compareTo(Point o) {
            return this.val - o.val;
        }
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

