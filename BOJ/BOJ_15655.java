package SSAFY.M_N;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15655 {
    static int[] arr;
    static int[] sel;
    static int num, target;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        num = sc.nextInt();
        target = sc.nextInt();
        arr = new int[num];
        sel = new int[target];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        // 오름차순을 위해 정렬
        Arrays.sort(arr);

        combination(0,0);
    }

    static void combination(int idx, int k){
        if(k == target){
            // 출력
            for (int i = 0; i < k; i++) {
                System.out.print(sel[i] + " ");
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