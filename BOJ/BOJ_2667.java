package SSAFY;
// 예전에 비슷한 문제를 재귀를 이용한 dfs로 불어본 것 같다.

import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class BOJ_2667 {
    static boolean[][] map;
    static Stack<Integer> cnt;
    static int size;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        cnt = new Stack<>();
        map = new boolean[size][size];
        // 값 설정
        for (int i = 0; i < size; i++) {
            String temp = sc.next();
            for (int j = 0; j < size; j++) {
                map[i][j] = ((temp.charAt(j) - '0') == 1) ? true : false;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(map[i][j]){
                    cnt.push(0);
                    dfs(i,j);
                }
            }
        }
        // 다음에도 쓸 수 있게 기억하기
        cnt.sort(Comparator.reverseOrder());
        // 출력
        System.out.println(cnt.size());
        while(!cnt.empty()){
            System.out.println(cnt.pop());
        }
    }

    static void dfs(int r, int c){
        if(r < 0 || r >= size || c < 0 || c >= size || !map[r][c]){
            return;
        }

        map[r][c] = false;
        cnt.set(cnt.size()-1,cnt.peek()+1);
        // 사방 탐색
        dfs(r-1, c);
        dfs(r+1, c);
        dfs(r, c+1);
        dfs(r, c-1);
    }
}
