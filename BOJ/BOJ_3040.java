package SSAFY;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_3040 {
    static int[] arr, sel;
    static int num = 9, cnt = 7;
    static boolean check;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        arr = new int[num];
        sel = new int[cnt];

        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }

        permutation(0, 0);
    }

    static void permutation(int k, int flag){
        if(check){
            return;
        }

        if(k == cnt){
            if(Arrays.stream(sel).sum() == 100){
                for(int i : sel){
                    System.out.println(i);
                }
                check = true;
                return;
            }
            return;
        }

        for (int i = 0; i < num; i++) {
            if((flag & 1 << i) == 0){
                sel[k] = arr[i];
                permutation(k+1, flag | 1 << i);
            }
        }
    }
}
