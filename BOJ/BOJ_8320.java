package SSAFY;
// https://www.acmicpc.net/problem/8320

import java.util.Scanner;

public class BOJ_8320 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int result = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = i; j*i <= N; j++) {
                result++;
            }
        }
        System.out.println(result);
    }
}



