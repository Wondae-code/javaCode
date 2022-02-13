package SSAFY.M_N;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15654 {
    static int[] arr, sel;
    static int num, cnt;
    static boolean[] v;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        cnt = sc.nextInt();
        arr = new int[num];
        sel = new int[cnt];
        v = new boolean[num];

        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }

        // 정렬하기
        Arrays.sort(arr);

        permutation(0);
    }

    static void permutation(int k){
        if(k == cnt){
            StringBuilder sb = new StringBuilder();
            for (int i : sel) {
                sb.append(i);
                sb.append(' ');
            }
            System.out.println(sb);
            return;
        }

        for (int i = 0; i < num; i++) {
            if(!v[i]){
                v[i] = true;
                sel[k] = arr[i];
                permutation(k+1);
                v[i] = false;
            }
        }
    }
}
