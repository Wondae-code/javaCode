package SSAFY;

import java.util.Scanner;

public class SWEA_2805 {
    //static int[] dr = {-1, 0, 1, 0};
    //static int[] dc = {0, 1, 0, -1};
    static int global_sum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            global_sum = 0;
            int size = sc.nextInt();

            int[][] grid = new int[size][size];
            // 값 입력 받기
            for (int i = 0; i < size; i++) {
                String temp = sc.next(); // 띄어쓰기가 없어서 직접 나눠주기.
                for (int j = 0; j < size; j++) {
                    grid[i][j] = temp.charAt(j) - '0';
                }
            }
            if(size == 1){ // 크기가 1인 경우
                global_sum = grid[0][0];
                System.out.printf("#%d %d\n", test_case, global_sum);
                continue;
            }
            // 탐색하기 -> 가운데부터 시작하는 재귀적 사방탐색..? -> 너무 오래 걸려..
            //recursiveTest(grid, size/2, size/2, size/2 - 1);
            // 그냥 마름모 모양 탐색하기

            for (int i = 0; i < size; i++) {
                if(i < size/2) {
                    for (int j = size / 2 - i; j < size/2 + i + 1; j++) {
                        global_sum += grid[i][j];
                    }
                }else{
                    for (int j = i-size/2; j < size*3/2 - i; j++) {
                        global_sum += grid[i][j];
                    }
                }
            }

            System.out.printf("#%d %d\n", test_case, global_sum);
        }
    }
//    static void recursiveTest(int[][] grid, int r, int c, int count){
//        if(count == 0){
//            return;
//        }
//
//        for (int d = 0; d < 4; d++) {
//            int nr = r + dr[d];
//            int nc = c + dc[d];
//            global_sum += grid[nr][nc];
//            grid[nr][nc] = 0;
//            recursiveTest(grid,nr,nc,count-1);
//        }
//    }
}
