package SSAFY;// 프림 + 연결 리스트
// 솔직히 크루스칼 잘 기억 안남..

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class BOJ_1922 {
    static int N, M; // 노드의 수,
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numNode = Integer.parseInt(br.readLine());
        int numEdge = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Node>> map = new HashMap<>();

        for (int i = 0; i < numEdge; i++) {
            // 양방향이겠지?
            String[] raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);
            int val = Integer.parseInt(raw[2]);
            Node temp = new Node(dst, val);
            Node temp2 = new Node(src, val);
            map.computeIfAbsent(src, k -> new ArrayList<>());
            map.computeIfAbsent(dst, k -> new ArrayList<>());
            map.get(src).add(temp);
            map.get(dst).add(temp2);
        }

        // 프림 알고리즘 해보기
        System.out.println(prim(map));
    }

    private static int prim(HashMap<Integer, ArrayList<Node>> map){
        int dist = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        // 임의로 1번 노드를 넣기
        pq.add(new Node(1, 0));
        boolean[] v = new boolean[map.size()+1];
        while(!pq.isEmpty()){
            Node n = pq.poll();
            // 방문 체크
            if(!v[n.dst]){
                v[n.dst] = true;
                dist += n.val;

                for (Node edge: map.get(n.dst)) {
                    if(!v[edge.dst]){
                        pq.add(edge);
                    }
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
