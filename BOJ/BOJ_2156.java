package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2156 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        if(N == 1){
            System.out.println(arr[0]);
        }else if(N == 2){
            System.out.println(arr[0] + arr[1]);
        }else {
            dp[0] = arr[0];
            dp[1] = arr[0] + arr[1];
            dp[2] = Math.max(dp[1], Math.max(arr[0] + arr[2], arr[1] + arr[2]));
            for (int i = 3; i < N; i++) {
                //System.out.println(dp[i-1] + " " + (dp[i-2] + arr[i]) + " " + (dp[i-3] + arr[i] + arr[i-1]));
                dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i] + arr[i-1]));
            }
        }

        System.out.println(dp[N-1]);
    }
}
