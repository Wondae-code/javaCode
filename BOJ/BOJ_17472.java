package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17472 {
    static int N, M, val = 1, Ans = 0;
    static int[][] map, adjMatrix;
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw= br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 섬의 번호 붙이기
        boolean[][] check = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != 0 && !check[r][c]) {
                    setIsland(r, c, check);
                    val++;
                }
            }
        }
        int V= val -1;
        // 섬의 시작점과 도착점, 거리로 간선 리스트를 만들어 MST 사용하기
        // Prim -> 인접 행렬
        // 인접 행렬 만들기
        adjMatrix = new int[val-1][val-1];
        for (int r = 0; r < adjMatrix.length; r++) {
            Arrays.fill(adjMatrix[r], Integer.MAX_VALUE);
        }
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                if(map[r][c] != 0){
                    setAdj(r,c);
                }
            }
        }
        for (int r = 0; r < adjMatrix.length; r++) {
            for (int c = 0; c < adjMatrix.length; c++) {
                if(adjMatrix[r][c] == Integer.MAX_VALUE){
                    adjMatrix[r][c] = 0;
                }
            }
        }

        int[] node = new int[V];
        boolean[] v = new boolean[V];
        Arrays.fill(node, Integer.MAX_VALUE);
        node[0]=0;
        for (int l = 0; l < V-1; l++) {
            // 최소노드값 구하기
            int minIdx=-1;
            int minD=Integer.MAX_VALUE;
            for (int i = 0; i < V; i++) {
                if(node[i]<minD&&!v[i]) {
                    minIdx = i;
                    minD = node[i];
                }
            }
            if(minIdx==-1) break;

            v[minIdx] = true;

            for (int i = 0; i < V; i++) {
                if(adjMatrix[minIdx][i]>0&&!v[i]&&adjMatrix[minIdx][i]<node[i]) {
                    node[i]=adjMatrix[minIdx][i];
                }
            }
        }
        //System.out.println(Arrays.toString(node));

        for (int i = 0; i < node.length; i++) {
            if(node[i]==Integer.MAX_VALUE) {
                Ans = -1;
                break;
            }
            Ans += node[i];
        }
        System.out.println(Ans);
    }

    private static void setAdj(int r, int c) {
        // 다른 섬을 만날때까지 전진
        int cnt = 0;
        int src = map[r][c];
        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            cnt = 0;
            while(nr >= 0 && nr < N && nc >= 0 && nc < M){
                if(map[nr][nc] != 0 && map[nr][nc] != val){
                    int dst = map[nr][nc];
                    if(cnt >= 2 && adjMatrix[src-1][dst-1] > cnt){
                        adjMatrix[src-1][dst-1] = cnt;
                        adjMatrix[dst-1][src-1] = cnt;
                    }
                }
                nr += dir[d][0];
                nc += dir[d][1];
                cnt++;
            }
        }
    }

    private static void setIsland(int r, int c, boolean[][] check){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r,c});
        check[r][c] = true;
        map[r][c] = val;

        while(!q.isEmpty()){
            int[] p = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dir[d][0];
                int nc = p[1] + dir[d][1];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && !check[nr][nc]){
                    check[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                    map[nr][nc] = val;
                }
            }

        }
    }

    private static void print(){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
