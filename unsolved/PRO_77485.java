import java.util.Arrays;

public class PRO_77485 {
    static int[][] map;
    static int rows = 6, columns = 6;
    static int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};

    public static void main(String[] args) {
        map = new int[rows + 1][columns + 1];
        int[] answer = new int[queries.length];

        for (int r = 1, i = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++, i++) {
                map[r][c] = i;
            }
        }

        // 돌리기
        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }

        System.out.println(Arrays.toString(answer));
    }

    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static int rotate(int sr, int sc, int dr, int dc) {
        int range = (dr - sr) * 2 + (dc - sc) * 2;
        int d = 0;
        int val = map[sr][sc];
        int min = val;
        int nr = sr, nc = sc;
        for (int i = 0; i < range; i++) {
            // System.out.println(nr + " " + nc);
            nr += dir[d][0];
            nc += dir[d][1];
            min = Math.min(min, map[nr][nc]);
            // 값 바꾸기
            map[nr - dir[d][0]][nc - dir[d][1]] = map[nr][nc];

            // 모서리를 벗어나면 방향 바꾸기
            if ((nr == dr && nc == sc) || (nr == dr && nc == dc) || (nr == sr && nc == dc)){
                d++;
            }
        }
        // 마지막 값 넣기
        map[sr][sc+1] = val;
        return min;
    }
}
