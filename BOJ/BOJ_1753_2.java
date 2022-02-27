package SSAFY;// 연결리스트를 사용하는 다익스트라 구현해보기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1753_2 {
    static ArrayList<Edge>[] graph;
    static int V, E, start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] raw = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        V = raw[0];
        E = raw[1];

        graph = new ArrayList[V+1];
        start = Integer.parseInt(br.readLine());
        // 그래프에 값 넣기
        for (int i = 0; i < E; i++) {
            raw = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(graph[raw[0]] == null){
                graph[raw[0]] = new ArrayList<>();
            }
            graph[raw[0]].add(new Edge(raw[1], raw[2]));
        }

        // 그래프 값 확인
        // print();

        // dijstra 알고리즘
        StringBuilder sb = new StringBuilder();
        int[] Ans = dijkstra(start);
        for (int i = 1; i <= V; i++) {
            sb.append((Ans[i] != Integer.MAX_VALUE) ? Ans[i] : "INF").append('\n');
        }

        System.out.println(sb.toString());
    }

    private static int[] dijkstra(int start){
        // 우선순위 큐 사용
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e->e.val));
        // 방문을 체크하는 배열 만들기
        boolean[] v = new boolean[V+1];
        // 시작점부터의 거리를 체크하는 배열
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 스타트값 넣기
        dist[start] = 0;
        pq.offer(new Edge(start, dist[start]));
        // 가장 가까운 점
        while (!pq.isEmpty()){
            // 최소 거리가 되는 값 찾기
            Edge cur = pq.poll();

            if(v[cur.dst]) continue;

            // 배열 접근 처리
            v[cur.dst] = true;

            // 시작점으로부터 최저거리 업데이트
            if(graph[cur.dst] != null) {
                for (Edge e : graph[cur.dst]) {
                    if (!v[e.dst] && dist[e.dst] > dist[cur.dst] + e.val) {
                        dist[e.dst] = dist[cur.dst] + e.val;
                        pq.offer(new Edge(e.dst, dist[e.dst]));
                    }
                }
            }
        }

        //System.out.println(Arrays.toString(dist));
        return dist;
    }

    private static void print(){
        for (int i = 1; i <= V; i++) {
            if(graph[i] != null){
                System.out.println("src : " + i + ", "  + graph[i].toString());
            }
        }
    }

    private static class Edge{
        int dst, val;

        public Edge(int dst, int val) {
            this.dst = dst;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "dst=" + dst +
                    ", val=" + val +
                    '}';
        }
    }
}
