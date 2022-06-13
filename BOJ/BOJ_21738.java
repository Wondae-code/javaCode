package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_21738 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        int N = Integer.parseInt(raw[0]);
        int S = Integer.parseInt(raw[1]);
        int P = Integer.parseInt(raw[2]);

        // 인접 리스트
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);
            graph[src].add(dst);
            graph[dst].add(src);
        }

        BFS(P, N, S, graph);
    }

    private static void BFS(int src, int N, int S, ArrayList<Integer>[] graph) {
        Queue<Integer> q = new LinkedList<>();
        int[] v = new int[N+1];
        v[src] = 1;
        q.offer(src);

        while (!q.isEmpty()) {
            int val = q.poll();
            for (int n : graph[val]) {
                if (v[n] == 0) {
                    v[n] = v[val] + 1;
                    q.offer(n);
                }
            }
        }
        // 최소 값 찾기
        int[] temp = Arrays.copyOfRange(v, 1, S+1);
        Arrays.sort(temp);
        System.out.println(N - temp[0] - temp[1] + 1);
    }
}
