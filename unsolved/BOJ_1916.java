import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1916 {
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            String[] raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);
            int val = Integer.parseInt(raw[2]);

            // 중복 값이 있는가?
            graph[src].add(new Node(dst, val));
            // graph[dst].add(new Node(src, val));
        }
        String[] raw = br.readLine().split(" ");

        int start = Integer.parseInt(raw[0]);
        int end = Integer.parseInt(raw[1]);

        dijkstra(start, end, N);
    }

    private static void dijkstra(int start, int end, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (dist[node.dst] < node.val) continue;

            for (Node n : graph[node.dst]) {
                int distance = dist[node.dst] + n.val;

                if (dist[n.dst] > distance) {
                    dist[n.dst] = distance;

                    pq.offer(new Node(n.dst, distance));
                }
            }
        }

        System.out.println(dist[end]);
    }

    private static class Node {
        int dst, val;

        public Node(int dst, int val) {
            this.dst = dst;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "dst=" + dst +
                    ", val=" + val +
                    '}';
        }
    }
}
