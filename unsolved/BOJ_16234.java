import java.util.Scanner;

public class BOJ_16234 {
    static int[][] map;
    static int N, L, R;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        // 값 넣기
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // open 함수를 통해 그래프를 만든다.

    }

    private static boolean open(){
        return false;
    }
}
