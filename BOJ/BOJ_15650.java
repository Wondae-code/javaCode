package SSAFY.M_N;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15650 {
    static int[] arr, sel;
    static int num, cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        cnt = sc.nextInt();
        arr = new int[num];
        sel = new int[cnt];

        for (int i = 0; i < num; i++) {
            arr[i] = i+1;
        }

        combination(0,0);
    }

    static void combination(int idx, int k){
        if(k == cnt){
            for (int i : sel) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }
        if(idx == num){
            return;
        }

        sel[k] = arr[idx];

        // 집는 경우
        combination(idx+1, k+1);
        // 안 집는 경우
        combination(idx+1, k);
    }
}
