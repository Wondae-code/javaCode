package DailyCheck;

import java.util.Scanner;

public class powerSet {
    static int arr[], num;
    static boolean v[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        arr = new int[num];
        v = new boolean[num];

        for (int i = 0; i < num; i++) {
            arr[i] = i+1;
        }

        powerSet(0);
    }

    static void powerSet(int idx){
        if(idx == num){
            for (int i = 0; i < num; i++) {
                System.out.print(v[i] ? (arr[i] + " ") : "x ");
            }
            System.out.println();
            return;
        }

        // 집는 경우
        v[idx] = true;
        powerSet(idx+1);

        // 안 집는 경우
        v[idx] = false;
        powerSet(idx+1);

    }
}
