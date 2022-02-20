package IM_practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IM_practice05 {
    static int[][] map;
    static int[][] dir = {{0,0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 동남서북

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/Solution32.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            // N : 세로 길이, M : 가로 길이, numPeople : 참가자의 수
            int N = sc.nextInt();
            int M = sc.nextInt();
            map = new int[N+1][M+1];
            int numPeople = sc.nextInt();
            // 지도 값 받기
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= M; c++) {
                    map[r][c] = sc.nextInt();
                }
            }

            // 사람 값 받기
            int[][] peoples = new int[numPeople][3];
            for (int cnt = 0; cnt < numPeople; cnt++) {
                peoples[cnt][0] = sc.nextInt();
                peoples[cnt][1] = sc.nextInt();
                peoples[cnt][2] = sc.nextInt();
            }

            // 함정 넣기
            int numTrap = sc.nextInt();
            for (int cnt = 0; cnt < numTrap; cnt++) {
                map[sc.nextInt()][sc.nextInt()] = 0;
            }

            // 값 찾기
            int total = 0;
            for (int cnt = 0; cnt < numPeople; cnt++) {
                int nr = peoples[cnt][0];
                int nc = peoples[cnt][1];
                total -= 1000;
                for (int i = 0; i < peoples[cnt][2]; i++) {
                    if(!(nr > 0 && nr <= N && nc > 0 && nc <= N && map[nr][nc] != 0)){
                        break;
                    }else{
                        int d = map[nr][nc] / 10;
                        int amount = map[nr][nc] % 10;
                        nr += dir[d][0] * amount;
                        nc += dir[d][1] * amount;
                    }
                    if(i == peoples[cnt][2] - 1){
                        total += map[nr][nc]*100;
                    }
                }
            }

            System.out.printf("#%d %d\n", test_case, total);
        }
    }
}
