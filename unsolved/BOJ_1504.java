import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1504 {
    static int N, E;
    static int must1, must2;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        E = Integer.parseInt(inputs[1]);

        // 그래프 생성
        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 값 넣기
        for (int i = 0; i < E; i++) {
            inputs = br.readLine().split(" ");
            int src = Integer.parseInt(inputs[0]);
            int dst = Integer.parseInt(inputs[1]);
            int val = Integer.parseInt(inputs[2]);

            graph[src].add(new Node(dst, val));
            graph[dst].add(new Node(src, val));
        }

        // 꼭 방문 해야할 점 찾기
        inputs = br.readLine().split(" ");
        must1 = Integer.parseInt(inputs[0]);
        must2 = Integer.parseInt(inputs[1]);

        int sum1 = 0;
        int sum2 = 0;
        // 1 -> must1
        // 1 -> must2
        int[] dist1 = dijkstra(1);

        // must1 -> must2
        // must2 -> must1
        int[] dist2 = dijkstra(must1);

        // must2 -> N
        // must1 -> N
        int[] dist3 = dijkstra(must2);

        sum1 += dist1[must1];
        sum2 += dist1[must2];

        sum1 += dist2[must2];
        sum2 += dist2[N];

        sum1 += dist3[N];
        sum2 += dist3[must1];

        if(sum1 >= 100000000 && sum2 >= 100000000) System.out.println(-1);
        else System.out.println(Math.min(sum1, sum2));
    }

    // 평범한 다익스트라
    // 1번부터 N번까지
    private static int[] dijkstra(int src){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        int[] dist = new int[N+1];
        Arrays.fill(dist, 1, dist.length, 100000000);
        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            for (Node n: graph[cur.dst]) {
                if(dist[n.dst] > dist[cur.dst] + n.val){
                    dist[n.dst] = dist[cur.dst] + n.val;
                    pq.offer(new Node(n.dst, dist[n.dst]));
                }
            }
        }

        return dist;
    }

    private static class Node{
        int dst, val;

        public Node(int dst, int val) {
            this.dst = dst;
            this.val = val;
        }
    }
}
