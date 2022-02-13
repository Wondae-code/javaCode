package SSAFY;

import java.util.Scanner;

public class BOJ_1592 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 총 사람 수
        int M = sc.nextInt(); // 목표 횟수
        int L = sc.nextInt(); // 홀수번 : 시계 방향으로 L, 짝수번 : 반시계 방향으로 L;
        int cnt = 0;

        // table 선언 및 0으로 초기화
        int[] table = new int[N];
        for (int i = 0; i < N; i++) {
            table[i] = 0;
        }

        // 공 던지기
        int idx = 0;
        while (true) {
            table[idx]++;
            if(table[idx] == M){
                break;
            }
            cnt++;
            if(table[idx]%2 == 0){ // 짝수 : 반시계
                if(idx - L < 0){
                    idx = N + idx - L;
                }else{
                    idx -= L;
                }
            }else{  // 홀수 : 시계
                if(idx + L >= N){
                    idx = idx + L - N;
                }else{
                    idx += L;
                }
            }

        }

        System.out.println(cnt);
    }
}
