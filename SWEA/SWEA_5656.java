import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_5656 {
    static int[][] map;
    static int N, W, H;
    static int Ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] raw = br.readLine().split(" ");
            N = Integer.parseInt(raw[0]);
            W = Integer.parseInt(raw[1]);
            H = Integer.parseInt(raw[2]);
            map = new int[H][W];
            for (int r = 0; r < H; r++) {
                raw = br.readLine().split(" ");
                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(raw[c]);
                }
            }

            // print(map);

            // 가능한 경우의 수 다 체크하기
            Recursive(map, 0);

            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }

    private static void Recursive(int[][] map, int cnt) {
        // 작업의 횟수를 base part에서 셈
        if (cnt == N) {
            // 남은 벽돌 수 세기
            int res = 0;
            for (int r = 0; r < H; r++) {
                for (int c = 0; c < W; c++) {
                    if(map[r][c] != 0) res++;
                }
            }

            Ans = Math.min(Ans, res);
            return;
        }

        // 구슬을 던져서 터트린다 (inductive part)
        for (int c = 0; c < W; c++) {
            // 여러번 시뮬레이션을 위해 원본을 복사해 놓음
            int[][] cpy = mapCpy(map);
            for (int r = 0; r < H; r++) {
                if(cpy[r][c] != 0){
                    shot(cpy, r, c);
                    break;
                }
            }

            gravity(cpy);
//            System.out.println("--------------------------------");
//            print(cpy);
            Recursive(cpy, cnt+1);
        }
    }

    private static void gravity(int[][] cpy) {
        for (int r = cpy.length-1; r >= 0 ; r--) {
            for (int c = cpy[0].length - 1 ; c >= 0; c--) {
                // 0을 만나면 0이 아닌 값까지 올라가서 내린다.
                if(cpy[r][c] == 0){
                    for (int k = r; k >= 0 ; k--) {
                        if(cpy[k][c] != 0){
                            cpy[r][c] = cpy[k][c];
                            cpy[k][c] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void shot(int[][] cpy, int r, int c) {
        // 지도 범위 안이며, cpy[r][c] 범위만큼 부수기.
        if(r >= 0 && r < H && c >= 0 && c < W){
            int range = cpy[r][c];
            cpy[r][c] = 0;
            for (int i = 1; i < range; i++) {
                shot(cpy, r+i, c);
                shot(cpy, r-i, c);
                shot(cpy, r, c+i);
                shot(cpy, r, c-i);
            }
        }
    }

    private static int[][] mapCpy(int[][] map) {
        int[][] cpy = new int[map.length][map[0].length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                cpy[r][c] = map[r][c];
            }
        }

        return cpy;
    }

    private static void print(int[][] map) {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
