package IM_practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IM_practice02 {
    static char[][] map;
    static int N;
    static int[][] dir = {{-1, -1},{-1, 0},{-1, 1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/Solution13.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            map = new char[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] = sc.next().charAt(0);
                }
            }
            int maxHeight = 2;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if(map[r][c] == 'B'){
                        int buildAvail = 0;
                        int buildArea = 0;
                        int tempHeight = 0;
                        for (int d = 0; d < 8; d++) {
                            int nr = r + dir[d][0];
                            int nc = c + dir[d][1];
                            // 조건 체크
                            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                                buildArea++;
                                if(map[nr][nc] == 'B') {
                                    buildAvail++;
                                }
                            }
                        }
                        // 주변이 모두 빌딩
                        if(buildAvail == buildArea){
                            for (int i = 0; i < N; i++) {
                                tempHeight = (map[r][i] == 'B') ? tempHeight+1 : tempHeight;
                            }
                            for (int i = 0; i < N; i++) {
                                tempHeight = (map[i][c] == 'B') ? tempHeight+1 : tempHeight;
                            }

                            // 중복된 값 제거
                            tempHeight -= 1;

                            maxHeight = Math.max(maxHeight, tempHeight);
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n", test_case, maxHeight);
        }

    }
}
