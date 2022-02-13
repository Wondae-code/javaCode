package SSAFY.M_N;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15656 {
    static int[] arr, sel;
    static int num, cnt;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        num = sc.nextInt();
        cnt = sc.nextInt();
        arr = new int[num];
        sel = new int[cnt];

        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        permutation(0,0);
        System.out.println(sb);
    }

    static void permutation(int idx, int k){
        if(k == cnt){
            for (int i : sel) {
                sb.append(i+ " ");
            }
            sb.append('\n');
            return;
        }

        if(idx == num){
            return;
        }

        sel[k] = arr[idx];

        // 집는 경우
        permutation(0,k+1);
        // 안집는 경우
        permutation(idx+1, k);
    }
}
