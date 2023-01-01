import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1245 {
    static int R, C;
    static int[][] map;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1,1}};
    static boolean[][] v;
    static ArrayList<Node> peak;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);
        map = new int[R][C];
        // 2 : peak, 1 : not peak, 0: not discovered
        peak = new ArrayList<>();

        for (int r = 0; r < R; r++) {
            inputs = br.readLine().split(" ");
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(inputs[c]);
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(findPeak(r,c)){
                    peak.add(new Node(r, c, map[r][c]));
                }
            }
        }

        map = new int[R][C];

        // 골라진 peak들로 그루핑 하기
        for (Node n : peak) {
            map[n.r][n.c] = 1;
        }

        int count = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(map[r][c] == 1){
                    areas(r, c);
                    count++;
                }
            }
        }

        System.out.println(count);
    }
    
    private static void areas(int r, int c){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c, 0));

        while(!q.isEmpty()){
            Node n = q.poll();
            map[n.r][n.c] = 0;

            for (int d = 0; d < 8; d++) {
                int nr = n.r + dir[d][0];
                int nc = n.c + dir[d][1];
                if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == 1){
                    q.offer(new Node(nr, nc, 0));
                }
            }
        }
    }

    private static boolean findPeak(int r, int c){
        Queue<Node> q = new LinkedList<>();
        v = new boolean[R][C];
        int initVal = map[r][c];
        q.offer(new Node(r,c, map[r][c]));
        v[r][c] = true;

        while(!q.isEmpty()){
            Node N = q.poll();

            if(map[N.r][N.c] > initVal){
                return false;
            }

            for (int d = 0; d < 8; d++) {
                int nr = N.r + dir[d][0];
                int nc = N.c + dir[d][1];

                if(nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc]){
                    if(map[N.r][N.c] <= map[nr][nc]) {
                        v[nr][nc] = true;
                        q.offer(new Node(nr, nc, map[nr][nc]));
                    }
                }
            }
        }

        return true;
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
