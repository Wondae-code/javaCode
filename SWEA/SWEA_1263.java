import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_1263 {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String[] raw = br.readLine().split(" ");
            int N = Integer.parseInt(raw[0]);
            map = new int[N][N];
            // 값 넣기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(raw[r*N + c + 1]);
                    if(map[r][c] == 0 && r != c){
                        map[r][c] = 10000;
                    }
                }
            }

            // 플로이드 워샬 해보기
            // 2차원 배열로 만든 후 값을 합치기..?
            for (int k = 0; k < N; k++) {
                for (int s = 0; s < N; s++) {
                    for (int d = 0; d < N; d++) {
                        map[s][d] = Math.min(map[s][d], map[s][k] + map[k][d]);
                    }
                }
            }

            // 최소 합 구하기
            int min = 10000;
            for (int r = 0; r < N; r++) {
                int sum = 0;
                for (int c = 0; c < N; c++) {
                    sum += map[r][c];
                }
                min = Math.min(min, sum);
            }

            System.out.printf("#%d %d\n", test_case, min);
        }
    }
}
