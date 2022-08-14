package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11403 {
    static ArrayList<Integer>[] graph;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        String[] raw;
        for (int i = 0; i < N; i++) {
            raw = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(raw[j]);
                if (num == 1) {
                    graph[i].add(j);
                }
            }
        }

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            BFS(i, N);
        }
    }

    // ?
    public static void BFS(int src, int size){
        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[size];
        for (int num : graph[src]) {
           q.offer(num);
           v[num] = true;
        }

        while (!q.isEmpty()){
            int val = q.poll();

            for (int num: graph[val]) {
                if(!v[num]){
                    q.offer(num);
                    v[num] = true;
                }
            }
        }

        for (boolean b : v) {
            System.out.print(b ? 1 + " " : 0 + " ");
        }
        System.out.println();
    }
}
