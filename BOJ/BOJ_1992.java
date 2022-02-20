package SSAFY;

import java.util.Scanner;

public class BOJ_1992 {
    static boolean[][] map;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        map = new boolean[N][N];
        sb = new StringBuilder();

        // 값 받기
        for (int i = 0; i < N; i++) {
            String raw = sc.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = (raw.charAt(j) == '1');
            }
        }

        DC(0, 0, N);

        System.out.println(sb.toString());
    }

    public static void DC(int r, int c, int size){
        boolean val = map[r][c];
        // 전체 탐색
        for (int i = r; i < size + r; i++) {
            for (int j = c; j < size + c; j++) {
                // 값이 다르면
                if(val != map[i][j]){
                    // 나누는 파트 -> 4가지로 나눈다.
                    sb.append('(');
                    DC(r, c, size/2);
                    DC(r, c + size/2 , size/2);
                    DC(r + size/2, c, size/2);
                    DC(r + size/2, c + size/2, size/2);
                    sb.append(')');
                    return;
                }
            }
        }
        sb.append(val ? '1' : '0');
    }
}
