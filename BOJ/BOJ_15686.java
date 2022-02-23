package SSAFY;// 현재의 치킨 거리를 구하고, K개의 치킨집에서 M개를 고르는 순열을 만들어서 다 비교하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class BOJ_15686 {
    static int[] arr, sel;
    static ArrayList<Point> chickenData, houseData;
    static int num, cnt, Ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        int size = Integer.parseInt(raw[0]);
        cnt = Integer.parseInt(raw[1]);

        chickenData = new ArrayList<>();
        houseData = new ArrayList<>();

        // 값 넣기
        for (int r = 0; r < size; r++) {
            raw = br.readLine().split(" ");
            for (int c = 0; c < size; c++) {
                if(Objects.equals(raw[c], "2")){
                    chickenData.add(new Point(r, c));
                }else if(Objects.equals(raw[c], "1")){
                    houseData.add(new Point(r, c));
                }
            }
        }

        num = chickenData.size();

        arr = new int[num];
        sel = new int[cnt];
        for (int i = 0; i < num; i++) {
            arr[i] = i;
        }
        // 순열을 통해 경우의 수 모두 체크하기
        permutation(0, 0);


        System.out.println(Ans);
    }

    private static void permutation(int idx, int k){
        if(k == cnt){
            // 각 경우마다 각 점마다 거리 구하기
            //System.out.println(Arrays.toString(sel));
            Ans = Math.min(Ans, size());
            return;
        }

        if(idx == num){
            return;
        }

        sel[k] = arr[idx];

        // 고르는 경우
        permutation(idx+1, k+1);
        // 안 고르는 경우
        permutation(idx+1, k);
    }

    private static int size(){
        int chickenDist = 0;
        for (Point house : houseData) {
            int dist = 500000;
            for (int i = 0; i < sel.length; i++) {
                dist = Math.min(dist, getDist(house, chickenData.get(sel[i])));
            }
            chickenDist += dist;
        }

        return chickenDist;
    }

    private static int getDist(Point p1, Point p2){
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }

    private static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
