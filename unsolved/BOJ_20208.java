
// 제일 가까운 것을 방문한 이후 집에 돌아갈 수 있는지 체크한다.
// 돌아 갈 수 있다면 다음으로 가까운 것을 방문하고 반복한다.
// 아니라면 종료한다.
// 같은 거리라면 백트래킹을 해야한다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_20208 {
    static int N, M, H, Ans; // 마을의 크기, 초기 체력, 민초 섭취시 증가하는 체력
    static Point start;
    static ArrayList<Point> points;
    static int[] arr, sel;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        H = Integer.parseInt(raw[2]);

        points = new ArrayList<>();
        start = null;

        for (int r = 0; r < N; r++) {
            raw = br.readLine().split(" ");
            for (int c = 0; c < N; c++) {
                if(raw[c].charAt(0) == '2'){
                    points.add(new Point(r, c));
                }else if(raw[c].charAt(0) == '1') {
                    start = new Point(r, c);
                }
            }
        }

        // 경우의 수 돌리기
        arr = new int[points.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        sel = new int[points.size()];

        combination(0, new boolean[arr.length]);

        System.out.println(Ans);
    }

    private static int getDist(Point p1, Point p2){
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }

    static void find() {
        int hp = M;
        Point cur = start;
        int cnt=0;
        for(int i=0; i<arr.length; i++) { //거리구하기
            int k = sel[i];
            int dist = getDist(cur, points.get(k));
            int toHome = getDist(points.get(k), start);
            if(hp >= dist) {//다음 민트 찾으러감
                cnt++;
                hp -= dist;//체력씀
                hp += H;//회복
                if(hp >= toHome) {//집 갈 체력이 됨
                    Ans = Math.max(Ans,cnt);
                }
                cur = points.get(k);
            } else {
                return;
            }
        }
    }

    private static void combination(int k, boolean[] v){
        if(k == arr.length){
            find();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!v[i]){
                v[i] = true;
                sel[k] = arr[i];
                combination(k+1, v);
                v[i] = false;
            }
        }

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
