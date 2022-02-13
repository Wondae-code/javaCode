package SSAFY;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1260 {
    static boolean[][] adjust;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int N = sc.nextInt();
        int start = sc.nextInt();

        adjust = new boolean[size][size];  // adjustment matrix
        visited = new boolean[size];  // 재방문을 막기 위한 배열

        // adjustment matrix
        for (int i = 0; i < N; i++) {
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;

            adjust[r][c] = true;
            adjust[c][r] = true;
        }

        dfs(start);
        System.out.println();

        // DFS를 위해 새롭게 false로 지정
        visited = new boolean[size];
        bfs(start);
        System.out.println();
    }

    static void bfs(int k){
        // bfs를 위한 큐
        Queue<Integer> order = new LinkedList<>();
        order.offer(k);
        visited[k-1] = true;
        while(!order.isEmpty()){
            int idx = order.poll();
            boolean[] temp = adjust[idx - 1];
            System.out.print(idx+ " ");
            for (int i = 0; i < temp.length; i++) {
                if(temp[i] & !visited[i]){
                    visited[i] = true;
                    order.offer(i+1);
                }
            }
        }

    }

    static void dfs(int k){
        boolean[] temp = adjust[k-1];
        System.out.print(k + " ");
        visited[k-1] = true;
        for (int i = 0; i < temp.length; i++) {
            if(temp[i] & !visited[i]){
                dfs(i+1);
            }
        }
    }
}
