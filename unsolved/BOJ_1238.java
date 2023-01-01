import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1238 {
    static ArrayList<Node>[] forward, backward;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        int X = Integer.parseInt(inputs[2]);

        forward = new ArrayList[N+1];
        backward = new ArrayList[N+1];
        for (int i = 1; i < forward.length; i++) {
            forward[i] = new ArrayList<>();
            backward[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int src = Integer.parseInt(inputs[0]);
            int dst = Integer.parseInt(inputs[1]);
            int val = Integer.parseInt(inputs[2]);
            forward[src].add(new Node(dst, val));
            backward[dst].add(new Node(src, val));
        }

        // 다익스트라 짜보기
        int[] dist_forward = dijkstra(forward, X, N);
        int[] dist_backward = dijkstra(backward, X, N);

        int Ans = 0;
        for (int i = 1; i < N+1; i++) {
            Ans = Math.max(Ans, dist_backward[i] + dist_forward[i]);
        }

        System.out.println(Ans);
    }

    private static int[] dijkstra(ArrayList<Node>[] graph, int X, int N){
        // 가장 거리가 가까운 노드를 뽑기 위해 pq 사용
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        int[] dist = new int[N+1];
        boolean[] v = new boolean[N+1];

        Arrays.fill(dist, 100000000);
        pq.add(new Node(X, 0));
        dist[X] = 0;

        while (!pq.isEmpty()){
            Node node = pq.poll();
            v[node.num] = true;
            // 방문 하지 않은 노드 집어넣기
            for (Node n: graph[node.num]) {
                if(!v[n.num]){
                    if(dist[n.num] > dist[node.num] + n.dist){
                        dist[n.num] = dist[node.num] + n.dist;
                        pq.add(new Node(n.num, dist[n.num]));
                    }
                }
            }
        }
        return dist;
    }
    private static class Node{
        int num, dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }
}
