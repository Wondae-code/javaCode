import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11724 {
    static int N, M;
    static HashSet<Integer>[] graph;
    static boolean[] check;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        graph = new HashSet[N+1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new HashSet<>();
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int src = Integer.parseInt(inputs[0]);
            int dst = Integer.parseInt(inputs[1]);

            graph[src].add(dst);

            graph[dst].add(src);
        }

        check = new boolean[N+1];
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            if(!check[i]){
                BFS(i);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static void BFS(int start){
        Queue<Integer> q = new LinkedList<>();
        check[start] = true;
        q.offer(start);

        while (!q.isEmpty()){
            int k = q.poll();

            for (int i: graph[k]) {
                if(!check[i]){
                    check[i] = true;
                    q.offer(i);
                }
            }
        }
    }
}
