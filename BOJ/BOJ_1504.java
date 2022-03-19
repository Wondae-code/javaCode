package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 1 -> v0 -> v1 -> N
// 1 -> v1 -> v0 -> N

public class BOJ_1504 {
    // dst와 val 배열을 저장
    static ArrayList<int[]>[] graph;
    static int N, E;
    static int[] essential;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        E = Integer.parseInt(raw[1]);

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N+1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);
            int val = Integer.parseInt(raw[2]);

            graph[src].add(new int[]{dst, val});
            graph[dst].add(new int[]{src, val});
        }

        essential = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long val1 = dijkstra(1, essential[0]) + dijkstra(essential[0], essential[1]) + dijkstra(essential[1], N);
        long val2 = dijkstra(1, essential[1]) + dijkstra(essential[1], essential[0]) + dijkstra(essential[0], N);

        long bind = Math.min(val1, val2);

        if(E == 0){
            System.out.println(-1);
            return;
        }
        System.out.println(bind == Integer.MAX_VALUE ? -1 : bind);
    }

    private static int dijkstra(int start, int dest) {
        // 방문 처리 배열
        boolean[] check = new boolean[N + 1];
        // 거리 체크 배열
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        pq.offer(new int[]{start, 0});
        dist[start] = 0;
        while(!pq.isEmpty()){
            int[] curArr = pq.poll();
            int cur = curArr[0];

            if(!check[cur]) {
                check[cur] = true;
            }
            
            // 거리 업데이트
            for (int[] arr : graph[cur]) {
                if(!check[arr[0]] && dist[arr[0]] > dist[cur] + arr[1]){
                    dist[arr[0]] = dist[cur] + arr[1];
                    pq.offer(new int[]{arr[0], dist[arr[0]]});
                }
            }
        }

        return dist[dest];
    }
}
