package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1654 {
    static int K, N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        K = Integer.parseInt(raw[0]);
        N = Integer.parseInt(raw[1]);
        arr = new int[K];
        long max = 0;
        long min = 1;

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        while(min <= max){
            long mid = (min + max) / 2;
            long count = 0;
            for (int i = 0; i < K; i++) {
                count += arr[i]/mid;
            }

            if(count < N){
                max = mid - 1;
            }else{
                min = mid + 1;
            }
        }

        System.out.println(max);
    }
}
