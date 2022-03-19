package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// BFS로 풀었지만, Dijkstra로는 왜 안 풀렸지?

public class BOJ_18352 {
    static LinkedList<Integer>[] graph;
    static int N, M, K, X;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        // 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        K = Integer.parseInt(raw[2]);
        X = Integer.parseInt(raw[3]);

        graph = new LinkedList[N+1];
        // 연결 그래프 만들기
        for (int i = 0; i < M; i++) {
            raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);
            if(graph[src] == null){
                graph[src] = new LinkedList<>();
            }
            if(graph[dst] == null){
                graph[dst] = new LinkedList<>();
            }
            graph[src].add(dst);
        }

        dist = new int[N+1];
        BFS(X);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if(dist[i] == K){
                sb.append(i).append('\n');
            }
        }

        if(sb.length() == 0){
            System.out.println(-1);
        }else{
            System.out.print(sb);
        }
    }

    private static void BFS(int src){
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(src);
        Arrays.fill(dist, -1);
        dist[src] = 0;
        while(!Q.isEmpty()){
            int num = Q.poll();

            for (int val : graph[num]) {
                if(dist[val] == -1){
                    dist[val] = dist[num]+1;
                    Q.offer(val);
                }
            }
        }
    }
}
