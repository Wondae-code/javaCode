package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_20058 {
    static int[][] map;
    static boolean[][] v;
    static int N, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw;

        raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        Q = Integer.parseInt(raw[1]);

        map = new int[1 << N][1 << N];
        v = new boolean[1 << N][1 << N];

        for (int r = 0; r < map.length; r++) {
            raw = br.readLine().split(" ");
            for (int c = 0; c < map.length; c++) {
                map[r][c] = Integer.parseInt(raw[c]);
            }
        }

        raw = br.readLine().split(" ");
        for (int i = 0; i < Q; i++) {
            // 회전 함수

            int L = Integer.parseInt(raw[i]);
            //System.out.printf("%d try, L = %d\n", i+1, L);
            if (L != 0) section(N, L);
            //print();
            // 얼음 감소 함수
            ArrayList<int[]> melting = new ArrayList<>();
            for (int r = 0; r < 1 << N; r++) {
                for (int c = 0; c < 1 << N; c++) {
                    int[] melt = iceCheck(r, c);
                    if (melt != null) {
                        melting.add(melt);
                    }
                }
            }

            for (int[] arr : melting) {
                map[arr[0]][arr[1]]--;
                //System.out.printf("[%d, %d] melted! %d -> %d\n", arr[0], arr[1], map[arr[0]][arr[1]] + 1, map[arr[0]][arr[1]]);
            }
            //System.out.println("-------------------------------");
        }

        // 얼음 개수 세기
        int iceNum = totalIce();
        // 가장 넓은 덩어리 개수
        int Ans = Integer.MIN_VALUE;

        //print();

        for (int r = 0; r < 1 << N; r++) {
            for (int c = 0; c < 1 << N; c++) {
                if (!v[r][c] && map[r][c] != 0) {
                    Ans = Math.max(Ans, sizeIce(r, c));
                }
            }
        }

        System.out.println(iceNum);
        System.out.println(Ans == Integer.MIN_VALUE ? 0 : Ans);
    }

    private static void print() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map.length; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    private static void section(int N, int L) {
        for (int r = 0; r < (1 << N); r += (1 << L)) {
            for (int c = 0; c < (1 << N); c += (1 << L)) {
                rotate(r, c, L);
            }
        }
    }

    private static void rotate(int sr, int sc, int size) {
        int temp = (1 << size-1);
        for (int i = 0; i < (1 << size-1); i++) {
            innerRotate(sr++, sc++, temp--);
        }
    }

    static int[][] rotateDir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static void innerRotate(int cr, int cc, int size) {
        if (size == 1) {
            int br = cr, bc = cc;
            int save = map[br][bc];
            int d = 0;
            while (true) {
                if (d == 3) {
                    map[cr][cc] = save;
                    break;
                }
                int nr = cr + rotateDir[d][0];
                int nc = cc + rotateDir[d][1];
                if (nr >= br && nr < br + (1 << size) && nc >= bc && nc < bc + (1 << size)) {
                    map[cr][cc] = map[nr][nc];
                    cr = nr;
                    cc = nc;
                } else {
                    d++;
                }
            }
        } else {
            int br = cr, bc = cc;
            int[] save = Arrays.copyOfRange(map[br], bc, bc + ((2 * size)));
            for (int i = (2 * size) - 1; i >= 0; i--) {
                map[cr][cc + i] = map[cr + (2 * size) - 1 - i][cc];
            }
            for (int i = (2 * size) - 1; i >= 0; i--) { // 3 2 1 0
                map[cr + (2 * size) - 1 - i][cc] = map[cr + ((2 * size)) - 1][cc + ((2 * size)) - 1 - i];
            }
            for (int i = (2 * size) - 1; i >= 0; i--) { // 3 2 1 0
                map[cr + (2 * size) - 1][cc + ((2 * size)) - 1 - i] = map[cr + i][cc + ((2 * size)) - 1];
            }
            for (int i = (2 * size) - 1; i >= 0; i--) { // 3 2 1 0
                map[cr + i][cc + (2 * size) - 1] = save[i];
            }
        }
    }

    private static int[] iceCheck(int r, int c) {
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + rotateDir[d][0];
            int nc = c + rotateDir[d][1];
            if (nr >= 0 && nr < (1 << N) && nc >= 0 && nc < (1 << N) && map[nr][nc] != 0) {
                cnt++;
            }
        }

        if (cnt < 3 && map[r][c] > 0) {
            return new int[]{r, c};
        }

        return null;
    }

    private static int totalIce() {
        int total = 0;
        for (int r = 0; r < 1 << N; r++) {
            for (int c = 0; c < 1 << N; c++) {
                total += map[r][c];
            }
        }
        return total;
    }

    private static int sizeIce(int r, int c) {
        Queue<int[]> q = new LinkedList<int[]>();
        v[r][c] = true;
        q.offer(new int[]{r, c});

        int size = 1;

        while (!q.isEmpty()) {
            int[] point = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = point[0] + rotateDir[d][0];
                int nc = point[1] + rotateDir[d][1];
                if (nr >= 0 && nr < (1 << N) && nc >= 0 && nc < (1 << N) && map[nr][nc] != 0 && !v[nr][nc]) {
                    q.offer(new int[]{nr, nc});
                    v[nr][nc] = true;
                    size++;
                }
            }
        }
        return size;
    }
}