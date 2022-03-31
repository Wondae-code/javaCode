package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 궁수 : 3명


public class BOJ_17135 {
    static int N, M, D, Ans;
    static int[][] map, tempMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");

        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        D = Integer.parseInt(raw[2]);
        map = new int[N + 2][M + 1]; // 추후에 궁수 놓을 공간까지 생각
        Ans = 0;

        for (int r = 1; r <= N; r++) {
            raw = br.readLine().split(" ");
            for (int c = 1; c <= M; c++) {
                map[r][c] = Integer.parseInt(raw[c - 1]);
            }
        }

        // 5C3 위치 찾기
        combination(map[N + 1], 1, 0);

        System.out.println(Ans);
    }

    private static void combination(int[] arr, int idx, int k) {
        // 3개를 다 집었을 경우
        if (k == 3) {
            // 실제 거리 체크
            // cnt : 각 경우의 수 별 지울 칸
            //System.out.println("------------------------");
            //System.out.println(Arrays.toString(arr));
            // tempMap에 깊은 복사하기
            tempMap = new int[N + 2][M + 1];
            for (int r = 1; r <= N+1; r++) {
                for (int c = 1; c <= M; c++) {
                    tempMap[r][c] = map[r][c];
                }
            }
            Ans = Math.max(Ans, archerShoot());
            //System.out.println(Ans);
            return;
        }

        // 인덱스가 M보다 커지는 것을 방지
        if (idx == M + 1) {
            return;
        }

        // 집는 경우
        arr[idx] = 1;
        combination(arr, idx + 1, k + 1);

        // 안 집는 경우
        arr[idx] = 0;
        combination(arr, idx + 1, k);
    }

    private static int archerShoot() {
        int temp = 0;
        // map이 빌 때 까지 반복
        while (!isEmpty()) {
            // 동시 선택을 위해 coords 배열 만들기
            // 좌표를 담아두기 -> 궁수가 3명이므로 3*2 배열
            int[][] coords = new int[3][2];
            // 궁수는 3명
            int coordCnt = 0;
            for (int i = 1; i <= M; i++) {
                if(tempMap[N+1][i] == 1){
                    int curDist = D;
                    int dstR = 0;
                    int dstC = M+1;
                    for (int r = 1; r <= N; r++) {
                        for (int c = 1; c <= M; c++) {
                            if(curDist >= getDist(N+1, i, r, c) && tempMap[r][c] == 1){
                                // 거리가 같은 경우 -> c의 좌표 비교 -> 더 왼쪽이면 교체
                                if(curDist == getDist(N+1, i, r, c)){
                                    // dstC보다 c가 왼쪽인 경우 값 교체
                                    if(dstC > c){
                                        dstR = r;
                                        dstC = c;
                                    }
                                    // 아닌경우 그냥 넘어감
                                } // 거리가 작을 경우
                                else{
                                    curDist = getDist(N+1, i, r, c);
                                    dstR = r;
                                    dstC = c;
                                }
                            }
                        }
                    }
                    // 찾은 값을 배열에 넣어 놓기
                    if(dstR != 0 && dstC != 0) {
                        coords[coordCnt][0] = dstR;
                        coords[coordCnt][1] = dstC;
                        coordCnt++;
                    }
                }
            }
            // 찾은 값을 한번에 제거
            for (int i = 0; i < 3; i++) {
                // 이미 값이 0인 경우는 패스 -> 동시 접근 표현
                if(tempMap[coords[i][0]][coords[i][1]] == 0){
                    continue;
                }
                tempMap[coords[i][0]][coords[i][1]] = 0;
                //System.out.printf("(%d, %d) deleted\n", coords[i][0], coords[i][1]);
                temp++;
            }
            // 한칸씩 밑으로 내리기
            for (int r = N; r >= 1; r--) {
                for (int c = 1; c <= M; c++) {
                    tempMap[r][c] = tempMap[r-1][c];
                }
            }
        }
        return temp;
    }

    private static int getDist(int srcR, int srcC, int tarR, int tarC){
        return Math.abs(srcR - tarR) + Math.abs(srcC - tarC);
    }

    // map이 비었는지 확인
    private static boolean isEmpty() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (tempMap[r][c] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
