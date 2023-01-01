import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_10282 {
    static ArrayList<Node>[] graph;
    static final int BIG_NUMBER = 100000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        String[] inputs = null;

        for (int t = 0; t < TC; t++) {
            inputs = br.readLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            int d = Integer.parseInt(inputs[1]);
            int c = Integer.parseInt(inputs[2]);

            graph = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                inputs = br.readLine().split(" ");
                int a = Integer.parseInt(inputs[0]);
                int b = Integer.parseInt(inputs[1]);
                int s = Integer.parseInt(inputs[2]);

                graph[b].add(new Node(a, s));
            }

            int[] dist = dijkstra(c, n);

            int cnt = 0;
            int max = 0;
            for (int i = 1; i < dist.length; i++) {
                if(dist[i] != BIG_NUMBER){
                    cnt++;
                    max = Math.max(max, dist[i]);
                }
            }
            System.out.println(cnt + " " + max);
        }
    }

    private static int[] dijkstra(int src, int size){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        int[] dist = new int[size+1];
        Arrays.fill(dist, 1, size+1, BIG_NUMBER);
        dist[src] = 0;
        pq.add(new Node(src, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for (Node node: graph[cur.dst]) {
                if(dist[node.dst] > dist[cur.dst] + node.val){
                    dist[node.dst] = dist[cur.dst] + node.val;
                    pq.add(new Node(node.dst, dist[node.dst]));
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
