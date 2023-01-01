package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1107 {
    static int[] numbers, output;
    static int target;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        boolean[] allow = new boolean[10];
        int N = Integer.parseInt(br.readLine());
        if(N == 10){
            System.out.println(Math.abs(100 - target));
            return;
        }
        if(N != 0) {
            String[] input = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                allow[Integer.parseInt(input[i])] = true;
            }
        }
        numbers = new int[10-N];
        for (int i = 0, idx = 0; i < 10; i++) {
            if(!allow[i]){
                numbers[idx] = i;
                idx++;
            }
        }

        output = new int[6];

        comb(0);
        System.out.println(Ans);
        // 100부터 시작하는것과 비교
        System.out.println(compare100() ? AnsLen : len100);
    }

    static int gap = 500001;
    static int Ans;

    private static void comb(int idx){
        if(idx == 6){
            if(Math.abs(target - arrToInt(output)) < gap){
                gap = Math.abs(target - arrToInt(output));
                Ans = arrToInt(output);
            }
            return;
        }

        for (int k: numbers) {
            // 앞 숫자가 0이라면 0을 넣어도 되게 하기.
            if(idx == 0){
                output[idx] = 0;
                comb(idx+1);
            }else{
                if(output[idx - 1] == 0 && idx != 5){
                    output[idx] = 0;
                    comb(idx+1);
                }
            }
            output[idx] = k;
            comb(idx+1);
        }
    }

    static int AnsLen;
    static int len100;

    private static boolean compare100(){
        // Ans에서 target까지 숫자 세기
        // 1. Ans의 길이
        AnsLen = Integer.toString(Ans).toCharArray().length;
        // 2. Ans에서 target까지의 차이
        AnsLen += gap;

        // 100부터 target까지의 길이
        len100 = Math.abs(100 - target);

        return AnsLen < len100;
    }

    private static int arrToInt(int[] arr){
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i);
        }

        return Integer.parseInt(sb.toString());
    }
}
