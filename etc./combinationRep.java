package DailyCheck;

import java.util.Arrays;
import java.util.Scanner;

public class combinationRep {
    static int arr[], sel[], num, cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        cnt = sc.nextInt();

        arr = new int[num];
        sel = new int[cnt];

        for (int i = 0; i < num; i++) {
            arr[i] = i+1;
        }

        combinationRep(0,0);
    }

    static void combinationRep(int idx, int k){
        if(k == cnt){
            System.out.println(Arrays.toString(sel));
            return;
        }
        if(idx == num){
            return;
        }
        sel[k] = arr[idx];

        // 집는 경우
        combinationRep(idx, k+1);
        // 안 집는 경우
        combinationRep(idx + 1, k);
    }
}
