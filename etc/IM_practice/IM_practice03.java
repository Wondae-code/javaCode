package IM_practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IM_practice03 {
    static int[][] map, commands;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/Solution21.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int size = sc.nextInt();
            int numCom = sc.nextInt();
            map = new int[size][size];
            commands = new int[numCom+1][3]; // r, c, command
            int Ans = 0;
            // 값 받기
            for (int i = 1; i <= numCom; i++) {
                for (int c = 0; c < 3; c++) {
                    commands[i][c] = sc.nextInt();
                }
            }
            // 찾기
            Label : for (int n = 1; n <= commands.length; n++) {
                // 시작점이 뛰었던 자리인지 확인
                int stride = 3;
                if(map[commands[n][0]][commands[n][1]] != 0){
                    Ans = map[commands[n][0]][commands[n][1]];
                    break;
                }else{ // 뛴 자리가 아닌 경우
                    map[commands[n][0]][commands[n][1]] = n;
                    if(commands[n][2] == 1){ // 아래쪽
                        for (int i = 3; i > 0; i--) {
                            int nr = commands[n][0] + i;
                            // 모서리 체크
                            if(nr >= size){ // 넘는 경우
                                break;
                            }else{ // 안 넘는 경우
                                // 겹치는 경우
                                if(map[nr][commands[n][1]] != 0){
                                    Ans = map[nr][commands[n][1]];
                                    break Label;
                                }else{
                                    map[nr][commands[n][1]] = n;
                                }
                            }
                        }
                    }else{ // 오른쪽
                        for (int i = 3; i > 0; i--) {
                            int nc = commands[n][1] + i;
                            // 모서리 체크
                            if(nc >= size){ // 넘는 경우
                                break;
                            }else{ // 안 넘는 경우
                                // 겹치는 경우
                                if(map[commands[n][0]][nc] != 0){
                                    Ans = map[commands[n][0]][nc];
                                    break Label;
                                }else{
                                    map[commands[n][0]][nc] = n;
                                }
                            }
                        }
                    }
                }
            }

            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }
}
