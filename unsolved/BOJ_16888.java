
// 가장 큰 수를 빼고


import java.util.Scanner;

public class BOJ_16888 {
    static int[] dp = new int[1000001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 0 : koosaga, 1 : cubelover
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 0;

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int k = sc.nextInt();
            for (int j = 1; j <= k; j++) {

            }
        }
    }
}
