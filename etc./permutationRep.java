package DailyCheck;

import java.util.Arrays;
import java.util.Scanner;

public class permutationRep{
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

        permutationRep(0);
    }

    static void permutationRep(int k){
        if(k == cnt){
            System.out.println(Arrays.toString(sel));
            return;
        }

        for (int i = 0; i < num; i++) {
            sel[k] = arr[i];
            permutationRep(k+1);
        }
    }
}
