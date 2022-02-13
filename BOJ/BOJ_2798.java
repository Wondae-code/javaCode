package SSAFY;

import java.util.Scanner;

// 문제 설명 https://www.acmicpc.net/problem/2798
public class BOJ_2798 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 카드 개수
        int M = sc.nextInt(); // 목표 숫자


        // 카드 값 입력 받기
        int[] cards = new int[N];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = sc.nextInt();
        }

        // M 근처의 값 찾기
        // 첫번째 아이디어 : 모든 조합을 찾고 거기서 가장 비슷한 값 찾기 -> 너무 비효율적이야
        // 두번째 아이디어 : 삼중 포문을 돌려 M과 가장 오차가 적은 값을 찾기 -> 위에 것 보다는 효율적..?
        int gap = M; // M과 tempSum 사이의 값
        int tempSum;
        int sum = 0;
        for (int i = 0; i < N-2; i++) {
            for (int j = i+1; j < N-1; j++) {
                for (int k = j+1; k < N; k++) {
                    tempSum = cards[i] + cards[j] + cards[k];
                    if((gap > M - tempSum) && (M - tempSum >= 0)){
                        gap = M - tempSum;
                        sum = tempSum;
                        if(gap == 0){
                            System.out.println(sum);
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(sum);
    }
}