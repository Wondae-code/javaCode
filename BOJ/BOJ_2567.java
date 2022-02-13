package SSAFY;

import java.util.Scanner;

public class BOJ_2567 {
    static boolean[][] map = new boolean[102][102];
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int rMin = 100;
        int rMax = 0;
        int cMin = 100;
        int cMax = 0;

        int boundary = 0;

        for (int i = 0; i < num; i++) {
            int col = sc.nextInt();
            int row = sc.nextInt();
            // 나중에 탐색을 위한 min max값 찾기
            rMin = (rMin > row) ? row : rMin;
            rMax = (rMax < row) ? row : rMax;
            cMin = (cMin > col) ? col : cMin;
            cMax = (cMax < col) ? col : cMax;

            // 색 칠하기
            for (int r = row; r < row+10 & r < 100; r++) {
                for (int c = col; c < col+10 & c < 100; c++) {
                    if(map[r][c] == false){
                        map[r][c] = true;
                    }
                }
            }
        }
        //show(map, rMin,cMin,rMax,cMax);

        // 길이 측정하기
        for (int r = rMin; r < rMax+10 & r < 100; r++) {
            for (int c = cMin; c < cMax+10 & c < 100; c++) {
                if(map[r][c]) {
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dir[d][0];
                        int nc = c + dir[d][1];
                        if(!map[nr][nc]) {
                            boundary++;
                        }
                    }
                }
            }
        }
        System.out.println(boundary);
    }

    static void show(boolean[][] arr, int sr, int sc, int er, int ec){
        for (int i = sr; i < er+10; i++) {
            for (int j = sc; j < ec+10; j++) {
                if(arr[i][j]){
                    System.out.print("T ");
                }else{
                    System.out.print("F ");
                }
            }
            System.out.println();
        }
        System.out.printf("%d ~ %d, %d ~ %d 범위의 배열\n", sr,er+10,sc,ec+10);
    }
}
