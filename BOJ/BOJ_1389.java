package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1389 {
    static int N, M;
    static int[][] map;
    static String[] input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int src = Integer.parseInt(input[0]);
            int dst = Integer.parseInt(input[1]);

            map[src][dst] = 1;
            map[dst][src] = 1;
        }

        // BFS? floyd-warshall?
        int min = Integer.MAX_VALUE;
        int temp = 0;
        int Ans = -1;
        for (int i = 1; i <= N ; i++) {
            temp = bfs(i);
            if(min > temp){
                min = temp;
                Ans = i;
            }
        }

        System.out.println(Ans);
    }

    private static int bfs(int src){
        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        v[src] = true;
        dist[src] = 0;
        q.offer(src);


        while(!q.isEmpty()){
            int n = q.poll();

            for (int i = 1; i <= N; i++) {
                if(map[n][i] != 0 && !v[i]){
                    dist[i] = dist[n] + 1;
                    v[i] = true;
                    q.offer(i);
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += dist[i];
        }

        return sum;
    }
}
