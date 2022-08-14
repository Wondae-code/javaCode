package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1003 {
    static final int MAX_VAL = 41;
    static Fibo[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        dp = new Fibo[MAX_VAL];
        dp[0] = new Fibo(0, 1, 0);
        dp[1] = new Fibo(1, 0, 1);

        for (int t = 0; t < K; t++) {
            int N = Integer.parseInt(br.readLine());

            for (int i = 2; i <= N; i++) {
                if(dp[i] == null) {
                    dp[i] = new Fibo();
                    dp[i].fibonacci(dp[i - 1], dp[i - 2]);
                }
            }

            System.out.println(dp[N].cnt_0 + " " + dp[N].cnt_1);
        }
    }

    private static class Fibo{
        int val, cnt_0, cnt_1;

        public Fibo() {}

        public Fibo(int val, int cnt_0, int cnt_1) {
            this.val = val;
            this.cnt_0 = cnt_0;
            this.cnt_1 = cnt_1;
        }

        public void fibonacci(Fibo p1, Fibo p2){
            val = p1.val+ p2.val;
            cnt_0 = p1.cnt_0 + p2.cnt_0;
            cnt_1 = p1.cnt_1 + p2.cnt_1;
        }

        @Override
        public String toString() {
            return "Fibo{" +
                    "val=" + val +
                    ", cnt_0=" + cnt_0 +
                    ", cnt_1=" + cnt_1 +
                    '}';
        }
    }
}
