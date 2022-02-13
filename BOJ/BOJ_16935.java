package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16935 {
    static int [][] map;
    static int [][] result;
    static int [][][] subMap;
    static int N, M, K, command;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] settings = br.readLine().split(" ");
        N = Integer.parseInt(settings[0]);
        M = Integer.parseInt(settings[1]);
        K = Integer.parseInt(settings[2]);

        map = new int[N][M];

        // 값 입력 받기
        for (int r = 0; r < N; r++) {
            String[] inputs = br.readLine().split(" ");
            int idx = 0;
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(inputs[idx]);
                idx++;
            }
        }

        String[] commands = br.readLine().split(" ");

        for (int i = 0; i < K; i++) {
            command = Integer.parseInt(commands[i]);
            switch(command){
                case 1:
                    upsideDown();
                    break;
                case 2:
                    rightsideLeft();
                    break;
                case 3:
                    // N 과 M 스왑
                    result = new int[M][N];
                    rotateR90();
                    map = result;
                    N = map.length;
                    M = map[0].length;
                    break;
                case 4:
                    result = new int[M][N];
                    rotateL90();
                    map = result;
                    N = map.length;
                    M = map[0].length;
                    break;
                case 5:
                    subMap = new int[4][N/2][M/2];
                    subRotateR();
                    break;
                case 6:
                    subMap = new int[4][N/2][M/2];
                    subRotateL();
                    break;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void upsideDown(){
        int[] tempArr;
        for (int i = 0; i < map.length/2; i++) {
            tempArr = map[i];
            map[i] = map[map.length - (i+1)];
            map[map.length - (i+1)] = tempArr;
        }
    }
    static void rightsideLeft(){
        int temp;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length/2; j++) {
                temp = map[i][j];
                map[i][j] = map[i][map[0].length - (j+1)];
                map[i][map[0].length - (j+1)] = temp;
            }
        }
    }
    static void rotateR90(){
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = map[map.length - (j+1)][i];
            }
        }
    }
    static void rotateL90(){
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = map[j][map[0].length - (i+1)];
            }
        }
    }
    static void subRotateR(){
        // 배열들 저장해놓기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1번 배열의 경우
                if(i >= 0 && i < N/2 && j >= 0 && j< M/2){
                    subMap[0][i][j] = map[i][j];
                } // 2번 배열의 경우
                else if (i >= 0 && i < N/2 && j >= M/2 && j < M) {
                    subMap[1][i][j - map[0].length/2] = map[i][j];
                }// 3번 배열의 경우
                else if(i >= N/2 && i < N && j >= M/2 && j < M){
                    subMap[2][i - map.length/2][j - map[0].length/2] = map[i][j];
                }
                // 4번 배열의 경우
                else if(i >= N/2 && i < N && j >= 0 && j < M/2){
                    subMap[3][i - map.length/2][j] = map[i][j];
                }

            }
        }

        // 네가지 경우로 나누기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1번 배열의 경우 -> 4번 가져오기
                if(i >= 0 && i < N/2 && j >= 0 && j< M/2){
                    map[i][j] = subMap[3][i][j];
                } // 2번 배열의 경우 -> 1번 가져오기
                else if (i >= 0 && i < N/2 && j >= M/2 && j < M) {
                    map[i][j] = subMap[0][i][j - map[0].length/2];
                }// 3번 배열의 경우 -> 2번 가져오기
                else if(i >= N/2 && i < N && j >= M/2 && j < M){
                    map[i][j] = subMap[1][i - map.length/2][j - map[0].length/2];
                }
                // 4번 배열의 경우 -> 3번 가져오기
                else if(i >= N/2 && i < N && j >= 0 && j < M/2){
                    map[i][j] = subMap[2][i - map.length/2][j];
                }
            }
        }
    }
    static void subRotateL(){
        // 배열들 저장해놓기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1번 배열의 경우
                if(i >= 0 && i < N/2 && j >= 0 && j< M/2){
                    subMap[0][i][j] = map[i][j];
                } // 2번 배열의 경우
                else if (i >= 0 && i < N/2 && j >= M/2 && j < M) {
                    subMap[1][i][j - map[0].length/2] = map[i][j];
                }// 3번 배열의 경우
                else if(i >= N/2 && i < N && j >= M/2 && j < M){
                    subMap[2][i - map.length/2][j - map[0].length/2] = map[i][j];
                }
                // 4번 배열의 경우
                else if(i >= N/2 && i < N && j >= 0 && j < M/2){
                    subMap[3][i - map.length/2][j] = map[i][j];
                }
            }
        }

        // 배열 옮기기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1번 배열의 경우 -> 2번 가져오기
                if(i >= 0 && i < N/2 && j >= 0 && j< M/2){
                    map[i][j] = subMap[1][i][j];
                } // 2번 배열의 경우 -> 3번 가져오기
                else if (i >= 0 && i < N/2 && j >= M/2 && j < M) {
                    map[i][j] = subMap[2][i][j - map[0].length/2];
                }// 3번 배열의 경우 -> 4번 가져오기
                else if(i >= N/2 && i < N && j >= M/2 && j < M){
                    map[i][j] = subMap[3][i - map.length/2][j - map[0].length/2];
                }
                // 4번 배열의 경우 -> 1번 가져오기
                else if(i >= N/2 && i < N && j >= 0 && j < M/2){
                    map[i][j] = subMap[0][i - map.length/2][j];
                }
            }
        }
    }
}
