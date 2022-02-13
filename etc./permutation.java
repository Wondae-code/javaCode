package DailyCheck;

import java.util.Arrays;
import java.util.Scanner;

public class permutation {
    static int[] arr;
    static int[] sel;
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

        permutation(0, new boolean[num]);
    }
    static void permutation(int k, boolean[] v){
        if(k == cnt){
            System.out.println(Arrays.toString(sel));
            return;
        }
        for (int i = 0; i < num; i++) {
            if(!v[i]){
                v[i] = true;
                sel[k] = arr[i];
                permutation(k+1, v);
                v[i] = false;
            }
        }
    }
}
