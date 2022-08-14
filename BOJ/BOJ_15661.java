package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_15661 {
    static int N, Ans = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            String[] raw = br.readLine().split(" ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(raw[c]);
            }
        }
        v = new boolean[N];
        powerSet(0);

        System.out.println(Ans);
    }

    private static void powerSet(int r) {
        if (r == N) {
            int cnt = 0;
            for (int i = 0; i < r; i++) {
                if (v[i]) cnt++;
            }
            if (cnt > 0 && cnt < r) {
                calculate(v);
            }
            return;
        }

        // 집는 경우
        v[r] = true;
        powerSet(r + 1);
        // 안 집는 경우
        v[r] = false;
        powerSet(r + 1);
    }

    private static void calculate(boolean[] v) {
        ArrayList<Integer> t1 = new ArrayList<>();
        ArrayList<Integer> t2 = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (v[i]) t1.add(i + 1);
            else t2.add(i + 1);
        }
        int s1 = t1.size() > 1 ? comb(t1) : 0;
        int s2 = t2.size() > 1 ? comb(t2) : 0;
        if (Ans > Math.abs(s1 - s2)) {
//            System.out.println("-----------------");
//            System.out.println(t1.toString());
//            System.out.println(t2.toString());
            Ans = Math.abs(s1 - s2);
        }
    }

    private static int comb(ArrayList<Integer> arr) {
        int sum = 0;
        for (int i : arr) {
            for (int j : arr) {
                if (i == j) continue;

                sum += map[i - 1][j - 1];
            }
        }

        return sum;
    }
}
