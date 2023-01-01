import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 임의의 두 점 사이의 거리가 가장 긴 것 구하기

public class BOJ_1167 {
    static ArrayList<Node>[] tree;
    static int Ans, Start;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs;
        int V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V+1];
        for (int i = 1; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        // 값 집어 넣기
        for (int i = 0; i < V; i++) {
            inputs = br.readLine().split(" ");
            int src = Integer.parseInt(inputs[0]);
            int idx = 1;
            while(true){
                int dst = Integer.parseInt(inputs[idx]);
                if(dst == -1){
                    break;
                }
                int val = Integer.parseInt(inputs[idx+1]);

                tree[src].add(new Node(dst, val));

                idx+=2;
            }
        }

        dfs(1, new boolean[V+1], 0);
        Ans = 0;
        dfs(Start, new boolean[V+1], 0);

        System.out.println(Ans);
    }

    private static void dfs(int root, boolean[] v, int dist){
        v[root] = true;

        for (Node n: tree[root]) {
            if(!v[n.num]){
                dfs(n.num, v, dist + n.val);
            }
        }

        if(dist > Ans){
            Ans = dist;
            Start = root;
        }
    }

    private static class Node{
        int num, val;

        public Node(int num, int val) {
            this.num = num;
            this.val = val;
        }
    }

}
