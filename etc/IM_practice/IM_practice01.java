package IM_practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class IM_practice01 {
    static char[][] map;
    static int T, N;
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 오른쪽 왼쪽 위쪽 아래쪽

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/Solution11.txt"));
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt(); // number of testcase
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();  // size of map
            map = new char[N][N];
            int Ans = 0;
            ArrayList<Robot> robots = new ArrayList<>();
            // get map input from text file
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // S : 공백, W : 벽, A : 오른쪽 이동만 가능한 로봇, B : 좌우 이동만 가능한 로봇, C : 상하좌우로 이동 가능한 로봇
                    map[r][c] = sc.next().charAt(0);
                    if(map[r][c] == 'A' || map[r][c] == 'B' || map[r][c] == 'C'){
                        robots.add(new Robot(r, c, map[r][c]));
                    }
                }
            }

            //print();

            // 각 로봇별로 하나씩 검색
            for (Robot R : robots) {
                int nr, nc;
                switch(R.type){
                    // 오른쪽만 탐색
                    case 'A':
                        nr = R.r + dir[0][0];
                        nc = R.c + dir[0][1];
                        // 모서리 조건 처리
                        while(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 'S'){
                            Ans++;
                            nr += dir[0][0];
                            nc += dir[0][1];
                        }
                        break;
                    // 좌우 탐색
                    case 'B':
                        for (int d = 0; d < 2; d++) {
                            nr = R.r + dir[d][0];
                            nc = R.c + dir[d][1];
                            // 모서리 조건 처리
                            while(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 'S'){
                                Ans++;
                                nr += dir[d][0];
                                nc += dir[d][1];
                            }
                        }
                        break;
                    case 'C':
                        for (int d = 0; d < 4; d++) {
                            nr = R.r + dir[d][0];
                            nc = R.c + dir[d][1];
                            // 모서리 조건 처리
                            while(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 'S'){
                                Ans++;
                                nr += dir[d][0];
                                nc += dir[d][1];
                            }
                        }
                        break;
                }
            }
            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }

    private static class Robot{
        int r, c;
        char type;

        public Robot(int r, int c, char type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    private static void print() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
