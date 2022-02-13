package SSAFY;
// 값을 주는것 보다 값을 가져오는게 쉬우니 시계방향으로 접근하는게 편할지도?
// 사방탐색으로 풀기


import java.util.Scanner;

public class BOJ_16926 {
    static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // 오른쪽 아래 왼쪽 위
    static int[][] map;
    static int R, C;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        int rotateNum = sc.nextInt();
        map = new int[R][C];

        // 값 넣기
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = sc.nextInt();
            }
        }

        for (int i = 0; i < rotateNum; i++) {// 돌리는 횟수
            R = map.length;
            C = map[0].length;
            for (int j = 0; j < Math.min(map.length, map[0].length)/2; j++, R-=2, C -= 2) {
                rotate(j,j, R, C);
            }
        }
        // 출력
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                sb.append(map[r][c] + " ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void rotate(int sr, int sc, int row, int col){
        int sVal = map[sr][sc];
        int d = 0; // 돌리는 방향
        int br = sr, bc = sc;
        int nr, nc;
        for (int i = 0; i < 2*((row-2)+(col)) - 1; i++) { // 마지막 경우는 따로 값을 넣어준다
            // 방향 바꾸는 경우
            if((d == 0 || d == 2) && i%((row-2)+(col)) == col - 1){
                d++;
            }else if(d == 1 && i%((row-2)+(col))  == 0){
                d++;
            }
            nr = br + dir[d][0];
            nc = bc + dir[d][1];
            map[br][bc] = map[nr][nc];
            br = nr;
            bc = nc;
        }
        map[br][bc] = sVal;
    }
}
