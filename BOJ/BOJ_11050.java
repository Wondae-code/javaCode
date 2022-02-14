package SSAFY;

import java.util.Scanner;

// 이항분포 테스트
public class BOJ_11050 {
    public static void main(String[] args) {
        // nCt = n!/((n-t)!*t!)
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();

        int result = 0;
        int mulA = 1;
        int mulB = 1;
        int mulC = 1;
        // n!
        for (int i = N; i > 0; i--) {
            mulA *= i;
        }
        // (n-t)!
        for (int i = N-T; i > 0; i--) {
            mulB *= i;
        }
        // t!
        for (int i = T; i > 0; i--) {
            mulC *= i;
        }
        // 최종 계산
        result = mulA/(mulB*mulC);
        System.out.println(result);
    }
}
