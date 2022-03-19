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
            // System.out.println(Arrays.toString(arr));
            // tempMap에 깊은 복사하기
            tempMap = new int[N + 2][M + 1];
            for (int r = 1; r <= N+1; r++) {
                for (int c = 1; c <= M; c++) {
                    tempMap[r][c] = map[r][c];
                }
            }
            Ans = Math.max(Ans, findMax());
            // System.out.println(Ans);
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

    static int[][] dir = {{-1, 0}, {0, 1}, {0, -1}}; // 위쪽, 오른쪽, 왼쪽

    private static int findMax() {
        int temp = 0;
        // map이 비지 않았으면
        while (!isEmpty()) {
            // 궁수 3명이 화살 발사
            // 중복 체크를 위한 배열
            boolean[] check = new boolean[M+1];
            int[] Row = new int[M+1];
            int tarR = 0, tarC = M;
            for (int i = 1; i <= M; i++) {
                // 궁수 위치 찾기
                if (tempMap[N + 1][i] == 1) {
                    // 일단 생 반복문 돌려보기
                    int tempDist = D;
                    tarR = 0;
                    tarC = M;
                    for (int r = 1; r <= N; r++) {
                        for (int c = 1; c <= M; c++) {
                            if(tempDist >= getDist(N+1, i, r, c) && tempMap[r][c] == 1){
                                // 거리가 같으면 왼쪽의 것 놓기
                                if(tempDist == getDist(N+1, i, r, c)) {
                                    if(c < tarC) {
                                        tempDist = getDist(N + 1, i, r, c);
                                        tarR = r;
                                        tarC = c;
                                    }
                                    continue;
                                }
                                tempDist = getDist(N + 1, i, r, c);
                                tarR = r;
                                tarC = c;
                            }
                        }
                    }
                    if(tarR != 0 && tarC != 0) {
                        check[tarC] = true;
                        Row[tarC] = tarR;
                    }
                }
            }
            // 한번에 제거
            for (int i = 1; i <= M; i++) {
                if(check[i]){
                    tempMap[Row[i]][i] = 0;
                    temp++;
                }
            }
            // 적의 위치를 한칸 내리기
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
