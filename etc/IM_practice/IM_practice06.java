package IM_practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IM_practice06 {
    static int[][] map;
    static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/Solution42.txt"));
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int size = scan.nextInt();
            int sr = scan.nextInt();
            int sc = scan.nextInt();
            int jumperNum = scan.nextInt();

            map = new int[size+1][size+1];
            // 점퍼 표시
            for (int i = 0; i < jumperNum; i++) {
                map[scan.nextInt()][scan.nextInt()] = 'J';
            }
            int commandNum = scan.nextInt();
            for (int i = 0; i < commandNum; i++) {
                int dir = scan.nextInt() - 1;
                int amount = scan.nextInt();
                sr += direction[dir][0] * amount;
                sc += direction[dir][1] * amount;
                // 모서리 체크
                if(sr >= 1 && sr < size && sc >= 1 && sc < size && (map[sr][sc] != 'J')){
                    continue;
                }else{
                    sr = 0;
                    sc = 0;
                    break;
                }
            }

            System.out.printf("#%d %d %d\n", test_case, sr, sc);

        }
    }
}
