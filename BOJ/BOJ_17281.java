package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_17281 {
    static int N, Ans;
    static int[][] result;
    static int[] arr = {1, 2, 3, 4, 5, 6, 7, 8}, sel = new int[8], idx;
    ;
    static int num = 8, cnt = 8;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new int[N][9];

        for (int r = 0; r < N; r++) {
            String[] raw = br.readLine().split(" ");
            for (int c = 0; c < 9; c++) {
                result[r][c] = Integer.parseInt(raw[c]);
            }
        }

        // 순열
        permutation(0, new boolean[9]);
        //System.out.println(Arrays.toString(idx));
        System.out.println(Ans);
    }

    static void permutation(int k, boolean[] v) {
        if (k == cnt) {
            // 계산하기
            idx = new int[9];
            int t = 0;
            for (int i = 0; i < 9; i++) {
                if (i == 3) {
                    idx[i] = 0;
                } else {
                    idx[i] = sel[t];
                    t++;
                }
            }
            int temp = baseball();
            Ans = Math.max(Ans, temp);
            return;
        }
        for (int i = 0; i < num; i++) {
            if (!v[i]) {
                v[i] = true;
                sel[k] = arr[i];
                permutation(k + 1, v);
                v[i] = false;
            }
        }
    }

    // 1루타 2루타 3루타 홈런 아웃 구현
    static int score = 0;
    static boolean[] state;

    private static int baseball() {
        score = 0;
        int prev = 0;
        // 이닝 수
        for (int inning = 0; inning < N; inning++) {
            state = new boolean[3];
            int outCnt = 0;
            // 타자 순서
            for (int b = prev; b < 9; ) {
                if (result[inning][idx[b]] == 0) {
                    outCnt++;
                    if (outCnt == 3) {
                        prev = (b + 1)%9;
                        break;
                    }
                } else if (result[inning][idx[b]] == 1) {
                    hitted(inning, result[inning][idx[b]]);
                } else if (result[inning][idx[b]] == 2) {
                    hitted(inning, result[inning][idx[b]]);
                } else if (result[inning][idx[b]] == 3) {
                    hitted(inning, result[inning][idx[b]]);
                } else if (result[inning][idx[b]] == 4) {
                    // 홈런
                    int cnt = 1;
                    for (int i = 0; i < 3; i++) {
                        if (state[i]) {
                            cnt++;
                            state[i] = false;
                        }
                    }
                    score += cnt;
                }
                b = (b + 1) % 9;
            }
        }
        return score;
    }

    private static void hitted(int inning, int base) {
        // 기존 타자들 진루
        for (int i = 2; i >= 0; i--) {
            if (state[i]) {
                int hitted = i + base;
                if (hitted > 2) {
                    score++;
                } else {
                    state[hitted] = true;
                }
                state[i] = false;
            }
        }
        // 신규 타자 진루
        state[base - 1] = true;
    }
}
