import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_4485 {
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] graph;
    static int SIZE = -1;
    static int[] nodeVal;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs;

        int cnt = 1;
        while(true){
            SIZE = Integer.parseInt(br.readLine());
            if(SIZE == 0) break;

            graph = new int[SIZE][SIZE];

            // 맵 입력 받음
            for (int r = 0; r < SIZE; r++) {
                inputs = br.readLine().split(" ");
                for (int c = 0; c < SIZE; c++) {
                    graph[r][c] = Integer.parseInt(inputs[c]);
                }
            }

            int sum = dijkstra();

            System.out.println(String.format("Problem %d: %d", cnt, sum));
            cnt++;
        }
    }

    // (1, 1) 부터 (size, size)까지 가기 떄문에 따로 입력을 주지 않음
    private static int dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        //boolean[][] v = new boolean[SIZE][SIZE];
        int[][] dist = new int[SIZE][SIZE];
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], 10000000);
        }
        dist[0][0] = graph[0][0];

        //v[0][0] = true;
        pq.add(new Node(0, 0, graph[0][0]));

        while(!pq.isEmpty()){
            Node n = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = n.r + dir[d][0];
                int nc = n.c + dir[d][1];

                if(nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE){
                    if(dist[nr][nc] > n.val + graph[nr][nc]){
                        dist[nr][nc] = n.val + graph[nr][nc];
                        pq.add(new Node(nr, nc, dist[nr][nc]));
                    }
                }
            }
        }

        return dist[SIZE -1][SIZE - 1];
    }

    private static class Node{
        int r, c, val;

        public Node(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }
}
