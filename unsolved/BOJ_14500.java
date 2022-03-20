import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14500 {
    // 다 만들어서 하기..?
    // 이건 아닌거 같은데
    static int[][][] tetrimino = {
            // 1자 블럭
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            // 네모 블럭
            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            // L자 블럭
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 0}, {1, 0}, {0, 1}, {0, 2}},
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 2}},

            {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
            {{0, 0}, {-1, 0}, {0, 1}, {0, 2}},
            {{0, 0}, {0, -1}, {1, -1}, {2, -1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            // S자 블럭
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
            {{0, 0}, {0, 1}, {-1, 1}, {-1, 2}},
            {{0, 0}, {1, 0}, {1, -1}, {2, -1}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            // T자 블럭
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {-1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, -1}}
    };

    static int[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");

        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);
        map = new int[N][M];

        for (int r = 0; r < N; r++) {
            raw = br.readLine().split(" ");
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(raw[c]);
            }
        }

        int val = 0;
        // 지도 탐색
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                // 모든 블럭 체크
                outer : for (int[][] block: tetrimino) {
                    int temp = 0;
                    for (int d = 0; d < 4; d++) {

                        int nr = r + block[d][0];
                        int nc = c + block[d][1];
                        if(!check(nr,nc)){
                            continue outer;
                        }
                        temp += map[nr][nc];
                    }
                    if(val < temp){
                        val = temp;
//                        System.out.println(r + " " + c);
//                        for (int[] coord : block) {
//                            System.out.print(Arrays.toString(coord) + " ");
//                        }
//                        System.out.println(val);
//                        System.out.println();

                    }
                }
            }
        }

        System.out.println(val);
    }

    private static boolean check(int r, int c){
        if(r >= 0 && r < N && c >= 0 && c < M){
            return true;
        }
        return false;
    }
}
