package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1041 {
    // A-F, B-E, C-D는 반대 편에 있음.
    // 1-6, 2-5, 3-4
    static long[] arr;
    static int[][] sel_2 = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {2, 3}, {4, 5}, {3, 5}, {2, 4}};
    static int[][] sel_3 = {{1, 2, 3}, {1, 2, 4}, {1, 5, 3}, {1, 5, 4}, {6, 2, 3}, {6, 2, 4}, {6, 5, 3}, {6, 5, 4}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());
        String[] raw = br.readLine().split(" ");
        // 두 짝으로 구분
        arr = new long[7];
        for (int i = 1; i <= 6; i++) {
            arr[i] = Integer.parseInt(raw[i - 1]);
        }

        // 최소값의 경우의 수 찾기
        // 1면이 보이는 곳
        long low_1 = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; i++) {
            low_1 = Math.min(low_1, arr[i]);
        }
        // 2면이 보이는 곳
        long low_2 = Integer.MAX_VALUE;
        for (int i = 0; i < sel_2.length; i++) {
            low_2 = Math.min(low_2, arr[sel_2[i][0]] + arr[sel_2[i][1]]);
        }
        // 3면이 보이는 곳
        long low_3 = Integer.MAX_VALUE;
        for (int i = 0; i < sel_3.length; i++) {
            low_3 = Math.min(low_3, arr[sel_3[i][0]] + arr[sel_3[i][1]] + arr[sel_3[i][2]]);
        }

        // System.out.printf("%d %d %d\n", low_1, low_2, low_3);

        long min_val = 0;
        if (N == 1) {
            min_val = Arrays.stream(arr).sum() - Arrays.stream(arr).max().getAsLong();
        } else if (N == 2) {
            min_val = low_3 * 4 + low_2 * 4;
        } else {
            min_val = (low_3 * 4) + (low_2 * (8 * N - 12)) + (low_1 * ((N - 2) * (5 * N - 6)));
        }

        System.out.println(min_val);
    }
}
