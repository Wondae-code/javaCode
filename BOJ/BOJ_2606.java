package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2606 {
    static LinkedList<Integer>[] graph;
    static boolean[] check;
    static int V, E, Ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        
        graph = new LinkedList[V+1];
        for (int i = 0; i < E; i++) {
            String[] input = br.readLine().split(" ");
            int src = Integer.parseInt(input[0]);
            int dst = Integer.parseInt(input[1]);
            if(graph[src] == null){
                graph[src] = new LinkedList();
            }
            if(graph[dst] == null){
                graph[dst] = new LinkedList();
            }
            graph[src].add(dst);
            graph[dst].add(src);
        }
        if(graph[1] != null){
            BFS(1);
        }else{
            System.out.println(0);
            return;
        }


        System.out.println(Ans);
    }

    private static void BFS(int start){
        Queue<Integer> Q = new LinkedList<>();
        check = new boolean[V+1];
        Q.offer(start);
        check[start] = true;

        while(!Q.isEmpty()){
            int num = Q.poll();

            for (int val : graph[num]) {
                if(!check[val]){
                    Q.offer(val);
                    check[val] = true;
                    Ans++;
                }
            }
        }
    }
}
