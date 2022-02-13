package SSAFY;

import java.util.Scanner;
import java.util.Arrays;

// 문제 설명 : https://www.acmicpc.net/problem/11399

public class BOJ_11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사람의 수
        // 값 입력
        int[] times = new int[N];
        for (int i = 0; i < times.length; i++) {
            times[i] = sc.nextInt();
        }

        // 가장 작은 값 뽑기
        Arrays.sort(times);
        int sum = 0;
        for (int i = 0; i < times.length; i++) {
            sum += times[i] * (N - i);
        }
        System.out.println(sum);
    }
}
