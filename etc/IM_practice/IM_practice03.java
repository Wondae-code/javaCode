package IM_practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IM_practice03 {
    static boolean[][] map;
    static int[][] dir = {{0, 0}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/Solution21.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        outer : for (int test_case = 1; test_case <= T; test_case++) {
            int size = sc.nextInt();
            int num = sc.nextInt();
            map = new boolean[size][size];
            // 0 : row, 1 : col, 2 : direction
            int[][] striders = new int[num][3];
            for (int i = 0; i < num; i++) {
                striders[i][0] = sc.nextInt();
                striders[i][1] = sc.nextInt();
                striders[i][2] = sc.nextInt();
            }

            for (int i = 0; i < num; i++) {
                int nr = striders[i][0];
                int nc = striders[i][1];
                int d = striders[i][2];

                if(map[nr][nc]){
                    System.out.printf("#%d %d\n", test_case, i+1);
                    continue outer;
                }


                for (int t = 3; t > 0; t--) {
                    nr += dir[d][0] * t;
                    nc += dir[d][1] * t;
                    if(nr >= 0 && nr < size && nc >= 0 && nc < size){
                        if(!map[nr][nc]){
                            map[nr][nc] = true;
                        }else{
                            System.out.printf("#%d %d\n", test_case, i+1);
                            continue outer;
                        }
                    }else{
                        break;
                    }
                }
            }
            System.out.printf("#%d %d\n", test_case, 0);
        }
    }
}
