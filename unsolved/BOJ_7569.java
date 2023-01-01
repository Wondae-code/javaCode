import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7569 {
    static int N, M, H;
    static String[] input;
    static int[][][] map;
    // 위 북 동 남 서 아래
    static int[][] dir = {{-1, 0, 0}, {1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    // 방문 배열
    static boolean[][][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);

        map = new int[H][M][N];
        v = new boolean[H][M][N];
        Queue<int[]> one = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int r = 0; r < M; r++) {
                input = br.readLine().split(" ");
                for (int c = 0; c < N; c++) {
                    map[h][r][c] = Integer.parseInt(input[c]);
                    if(map[h][r][c] == 1) one.add(new int[]{h,r,c});
                }
            }
        }

        BFS(one);

        System.out.println(check(map) ? Ans-1 : -1);
    }
    static int Ans = 0;
    private static boolean check(int[][][] a) {
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    Ans = Math.max(Ans, a[h][r][c]);
                    if (a[h][r][c] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void BFS(Queue<int[]> q) {
        while (!q.isEmpty()) {
            int[] p = q.poll();
            v[p[0]][p[1]][p[2]] = true;

            for (int d = 0; d < 6; d++) {
                int nh = p[0] + dir[d][0];
                int nr = p[1] + dir[d][1];
                int nc = p[2] + dir[d][2];

                if (nh >= 0 && nh < H && nr >= 0 && nr < M && nc >= 0 && nc < N && !v[nh][nr][nc] && (map[nh][nr][nc] == 0)) {
                    v[nh][nr][nc] = true;

                    map[nh][nr][nc] = map[p[0]][p[1]][p[2]] + 1;
                    q.offer(new int[]{nh, nr, nc});
                }
            }
        }


    }
}
