package IM_practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IM_practice04 {
    static boolean[][] map;
    static int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/Solution22.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int size = sc.nextInt();
            map = new boolean[size][size];
            int num = sc.nextInt();
            int Ans = 0;
            int[][] striders = new int[num][3];
            for (int i = 0; i < num; i++) {
                // 0 : row, 1 : col, 2 : direction
                striders[i][0] = sc.nextInt();
                striders[i][1] = sc.nextInt();
                striders[i][2] = sc.nextInt();
            }

            for (int i = 0; i < num; i++) {
                // 뛰는 거 반복
                int nr = (striders[i][0]) + dir[striders[i][2]][0] * 6;
                int nc = (striders[i][1]) + dir[striders[i][2]][1] * 6;
                if(nr >= 0 && nr < size && nc >= 0 && nc < size && !map[nr][nc]){
                    map[nr][nc] = true;
                    Ans++;
                }
            }
            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }
}
