import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1932 {
    static int[][] map;
    static int size, Ans = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs;

        size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(map[i], -1);
        }

        for (int r = 0; r < size; r++) {
            inputs = br.readLine().split(" ");
            for (int c = 0; c < inputs.length; c++) {
                map[r][c] = Integer.parseInt(inputs[c]);
            }
        }

        for (int r = size - 1; r > 0; r--) {
            for (int c = 0; c < r; c++) {
                map[r-1][c] += Math.max(map[r][c], map[r][c+1]);
            }
        }

        System.out.println(map[0][0]);
    }


}
