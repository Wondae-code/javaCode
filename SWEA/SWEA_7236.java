import java.util.Scanner;
// boolean 배열 map에서 true는 물, false는 땅

public class SWEA_7236 {
    static boolean[][] map;
    static int size;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int size = sc.nextInt();
            map = new boolean[size][size];
            // 값 받기
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    map[r][c] = sc.next().equals("W");
                }
            }

            // 최대값 찾기
            int Ans = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    if(map[r][c]) {
                        int depth = 0;
                        for (int d = 0; d < 8; d++) {
                            int nr = r + dir[d][0];
                            int nc = c + dir[d][1];
                            if (nr >= 0 && nr < size && nc >= 0 && nc < size){
                                // 근처가 w일때
                                if(map[nr][nc]){
                                    depth++;
                                }
                            }
                        }
                        if(depth == 0) depth = 1;

                        Ans = Math.max(Ans, depth);
                    }
                }
            }

            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }
}
