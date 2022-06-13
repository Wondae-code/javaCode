package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 브루트포스로 모든 경우를 구한 다음 해보기?

public class BOJ_18188 {
    static char[][] map;
    static int H, W, N; // 높이, 너비, 최대 움직임 수
    static char[][] command;
    static String Ans;
    static Point src, dst;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] raw = br.readLine().split(" ");
        H = Integer.parseInt(raw[0]);
        W = Integer.parseInt(raw[1]);
        map = new char[H+1][W+1];
        // 지도 입력
        for (int r = 1; r <= H; r++) {
            String temp = br.readLine();
            for (int c = 1; c <= W; c++) {
                map[r][c] = temp.charAt(c-1);
                if(map[r][c] == 'D') src = new Point(r, c);

                if(map[r][c] == 'Z') dst = new Point(r, c);
            }
        }

        N = Integer.parseInt(br.readLine());
        command = new char[N][2];
        for (int i = 0; i < N; i++) {
            raw = br.readLine().split(" ");
            command[i][0] = raw[0].charAt(0);
            command[i][1] = raw[1].charAt(0);
        }


        //print();

        // 경우의 수 찾기
        arr = new char[N];
        numOfCases(0);

        // 최종값 출력
        if(Ans != null){
            System.out.println("YES");
            System.out.println(Ans);
        }else{
            System.out.println("NO");
        }
    }

    private static boolean isArrival(){
        Point cur = new Point(src.r, src.c);
        for (int i = 0; i < N; i++) {
            switch (arr[i]){
                case 'W':
                    cur.r -= 1;
                    break;
                case 'S':
                    cur.r += 1;
                    break;
                case 'A':
                    cur.c -= 1;
                    break;
                case 'D':
                    cur.c += 1;
                    break;
            }

            if(cur.r < 1 || cur.r > H || cur.c < 1 || cur.c > W || map[cur.r][cur.c] == '@'){
                break;
            }

            if(cur.r == dst.r && cur.c == dst.c){
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k <= i; k++) {
                    sb.append(arr[k]);
                }
                Ans = sb.toString();
                return true;
            }
        }

        return false;
    }

    private static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    static char[] arr;

    private static void numOfCases(int idx){
        if(idx == N){
            // 경우의 수를 구한 뒤 길 찾기 로직 실행
            isArrival();
            return;
        }

        // 첫번째 선택
        arr[idx] = command[idx][0];
        numOfCases(idx+1);
        // 두번째 선택
        arr[idx] = command[idx][1];
        numOfCases(idx+1);
    }

    private static void print(){
        for (int r = 1; r <= H; r++) {
            for (int c = 1; c <= W; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
