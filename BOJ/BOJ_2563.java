// https://www.acmicpc.net/problem/2563

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2563 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int paperCnt = sc.nextInt();

        int[][] papers = new int[paperCnt][2];

        //  종이 위치 세기 & 맵 제작을 위한 최대 크기
        int row = 0, col = 0;
        for (int i = 0; i < paperCnt; i++) {
            papers[i][0] = sc.nextInt();
            papers[i][1] = sc.nextInt();
            row = Math.max(row, papers[i][0]);
            col = Math.max(col, papers[i][1]);
//            // 값 확인
//            System.out.println(Arrays.toString(papers[i]));
        }

        // 맵 제작 및 색칠 및 크기 세기
        int paperSize = 0;
        boolean[][] map = new boolean[row+10][col+10];
        for (int i = 0; i < paperCnt; i++) {
            for (int r = 0; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    if(!(map[papers[i][0] + r][papers[i][1] + c])) {
                        map[papers[i][0] + r][papers[i][1] + c] = true;
                        paperSize++;
                    }
                }
            }
        }

        System.out.println(paperSize);
    }
}
