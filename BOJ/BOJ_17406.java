package SSAFY;
// 첫번째로 순열 경우의 수를 구하고
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_17406 {
    static int[][] map;
    static int[][] mapTemp;
    static int[][] orders;
    static int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}}; // 남동북서
    static int[] sel;
    static int N, M, cnt, min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        cnt = sc.nextInt();

        // 값 넣기
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                map[r][c] = sc.nextInt();
            }
        }

        // 연산의 개수 만큼 배열 생성 후 연산 받기
        orders = new int [cnt][3];
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < 3; j++) {
                orders[i][j] = sc.nextInt();
            }
        }

        min = Integer.MAX_VALUE;
        // 고르는 배열 만들기
        sel = new int[cnt];

        permutation(0, new boolean[cnt]);

        System.out.println(min);
    }

    static void permutation(int k, boolean[] v){
        // basis part
        if(k == cnt){
            // 배열 복사
            mapTemp = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    mapTemp[i][j] = map[i][j];
                }
            }

            // 회전하기
            for (int i = 0; i < k; i++) { // 명령 개수
                int size = (orders[sel[i]][2]*2+1); // 정사각형이며, 한 변의 길이가 orders[i][2]*2+1이다
                // 회전 수
                for (int r = 0; r < (orders[sel[i]][2]*2+1)/2; r++, size-=2) {
                    rotate(orders[sel[i]][0] - orders[sel[i]][2] - 1 + r,
                            orders[sel[i]][1] - orders[sel[i]][2] - 1 + r,
                            size, size );
                }
            }

            // 배열의 값 구하기
            for (int i = 0; i < N; i++) {
                int temp = 0;
                for (int j = 0; j < M; j++) {
                    temp += mapTemp[i][j];
                }
                min = Math.min(min, temp);
            }
            return;
        }

        // inductive part
        for (int i = 0; i < cnt; i++) {
            if(!v[i]){
                v[i] = true;
                sel[k] = i;
                permutation(k+1, v);
                v[i] = false;
            }
        }
    }

    static void rotate(int sr, int sc, int row, int col){
        int sVal = mapTemp[sr][sc];
        int d = 0; // 돌리는 방향
        int nr, nc;
        for (int i = 0; i < 2*((row-2)+(col)) - 1; i++) { // 마지막 경우는 따로 값을 넣어준다
            // 방향 바꾸는 경우
            if((d == 0 || d == 2) && i%((row-2)+(col)) == col - 1){
                d++;
            }else if(d == 1 && i%((row-2)+(col))  == 0){
                d++;
            }
            nr = sr + dir[d][0];
            nc = sc + dir[d][1];
            mapTemp[sr][sc] = mapTemp[nr][nc];
            sr = nr;
            sc = nc;
        }
        mapTemp[sr][sc] = sVal;
    }
}
