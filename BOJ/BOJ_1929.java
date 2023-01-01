package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1929 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        int M = Integer.parseInt(raw[0]);
        int N = Integer.parseInt(raw[1]);

        // 배열 만들기
        int[] arr = new int[N - M + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = M + i;
        }

        // 1 제거
        if(arr[0] == 1){
            arr[0] = 0;
        }

        // 에라토스테네스의 체
        // 2부터 sqrt(N)까지의 수 지우기
        for (int i = 2; i <= Math.sqrt(N); i++) {
            for (int k = 0; k < arr.length; k++) {
                if(arr[k] == 0) continue;

                if(arr[k] != i && arr[k] % i == 0){
                    arr[k] = 0;
                }
            }
        }

        for (int num: arr) {
            if(num != 0){
                System.out.println(num);
            }
        }
    }
}
