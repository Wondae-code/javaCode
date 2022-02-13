package SSAFY;

// 첫번째 접근 : powerSet : 시간 초과
// 두번째 접근 : 반복문 두번 돌리기 => 정확히 2개 이므로

import java.util.Scanner;

public class SWEA_9229 {
    static int num, weight, max, snacks[];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            num = sc.nextInt();
            weight = sc.nextInt();
            snacks = new int[num];
            max = 0;

            // 값 넣기
            sc.nextLine();
            String[] raw = sc.nextLine().split(" ");
            for (int i = 0; i < num; i++) {
                snacks[i] = Integer.parseInt(raw[i]);
            }

            for (int i = 0; i < num-1; i++) {
                for (int j = i+1; j < num; j++) {
                    if(snacks[i] + snacks[j] <= weight){
                        max = Math.max(max, snacks[i] + snacks[j]);
                    }
                }
            }

            if(max == 0){
                max = -1;
            }

            // 출력
            System.out.printf("#%d %d\n", test_case, max);
        }
    }
}
