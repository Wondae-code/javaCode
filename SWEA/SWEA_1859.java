import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1859 {
    static int[] map;
    static int T, N;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/SWEA_1859.txt"));
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            map = new int[N];
            // 값 넣기
            for (int i = 0; i < N; i++) {
                map[i] = sc.nextInt();
            }

            // 뒤에서 부터 값 찾기
            int max = 0;
            long cnt = 0;
            for (int i = N-1; i >= 0; i--) {
                max = Math.max(max, map[i]);
                cnt += max - map[i];
            }

            System.out.printf("#%d %d\n", test_case, cnt);
        }
    }
}
