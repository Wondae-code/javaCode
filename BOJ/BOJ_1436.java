package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1436 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 원시적인 방법
        int totalCnt = 0;
        long[] arr = new long[10000];
        for (long i = 1; N - totalCnt >= 1 ; i++) {
            if(check(i)){
                arr[totalCnt] = i;
                totalCnt++;
            }
        }

        System.out.println(arr[N - 1]);
    }

    private static boolean check(long num){
        while(num > 0){
            if(num % 1000 == 666) return true;
            num /= 10;
        }
        return false;
    }
}
