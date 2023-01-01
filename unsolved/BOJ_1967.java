import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 트리의 지름 : 어느 한 점에서 가장 먼 점을 구하고, 그 점에서 가장 먼 점의 거리
// 다익스트라 두번 돌리기

public class BOJ_1967 {
    static ArrayList<Node>[] tree;
    static int N;
    static final int MAX_NUMBER = 100000000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            tree[i] = new ArrayList<>();
        }


        String[] inputs;
        for (int i = 0; i < N-1 ; i++) {
            inputs = br.readLine().split(" ");
            int src = Integer.parseInt(inputs[0]);
            int dst = Integer.parseInt(inputs[1]);
            int val = Integer.parseInt(inputs[2]);

            tree[src].add(new Node(dst, val));
            tree[dst].add(new Node(src, val));
        }

        // 노드가 2개 이하 -> 그냥 하나 출력
        if(N == 1){
            System.out.println(0);
            return;
        }else if(N == 2){
            System.out.println(tree[1].get(0).val);
            return;
        }

        // 가지가 2개 이상인 노드에서 시작
        int startIdx = 0;
        for (int i = 1; i < tree.length; i++) {
            if(tree[i].size() >= 2){
                startIdx = i;
                break;
            }
        }
        int[] e1 = dijkstra(startIdx);
        // 가장 먼 가지 찾기
        int maxVal = -1, maxIndex = 0;
        for (int i = 1; i < e1.length; i++) {
            if(maxVal < e1[i]){
                maxVal = e1[i];
                maxIndex = i;
            }
        }
        int[] e2 = dijkstra(maxIndex);
        for (int i = 1; i < e2.length; i++) {
            if(maxVal < e2[i]){
                maxVal = e2[i];
                maxIndex = i;
            }
        }
        System.out.println(e2[maxIndex]);
    }

    private static int[] dijkstra(int src){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.val));
        int[] dist = new int[N+1];
        Arrays.fill(dist, MAX_NUMBER);
        dist[src] = 0;
        pq.add(new Node(src, dist[src]));


        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for (Node edge: tree[cur.dst]) {
                if(dist[edge.dst] > dist[cur.dst] + edge.val){
                    dist[edge.dst] = dist[cur.dst] + edge.val;
                    pq.add(new Node(edge.dst, dist[edge.dst]));
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
