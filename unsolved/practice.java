// 4개 중 2개 2개씩 뽑는 경우

import java.util.Arrays;
import java.util.Scanner;

public class practice {
    static int[] arr, sel1, sel2;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        sel1 = new int[N/2];
        sel2 = new int[N/2];

        for (int i = 0; i < N; i++) {
            arr[i] = 2*i;
        }

        //combination(0, 0);
    }

    static int taste1, taste2;



}