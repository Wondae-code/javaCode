import java.util.Scanner;

public class SWEA_11315 {
    static boolean[][] map;
    // 어짜피 모든 것을 검사하므로 4방향만 체크
    static int[][] dir = {{0, 1}, {1, 1}, {1, 0}, {1, -1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int size = sc.nextInt();
            boolean check = false;

            // 값 넣기
            map = new boolean[size][size];
            for (int r = 0; r < size; r++) {
                String raw = sc.next();
                for (int c = 0; c < size; c++) {
                    map[r][c] = (raw.charAt(c) == 'o');
                }
            }

            // 체크하기
            outer : for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    // 값이 o일 때
                    if(map[r][c]){
                        for (int d = 0; d < 4; d++) {
                            int cnt = 1;
                            int nr = r + dir[d][0];
                            int nc = c + dir[d][1];
                            // 모서리 체크 + 방향 유효 체크
                            while(nr >= 0 && nr < size && nc >= 0 && nc < size && map[nr][nc]){
                                cnt++;
                                nr += dir[d][0];
                                nc += dir[d][1];
                            }

                            if(cnt >= 5){
                                check = true;
                                break outer;
                            }
                        }
                    }
                }
            }

            System.out.printf("#%d %s\n", test_case, (check) ? "YES" : "NO");
        }
    }
}
