import java.util.Scanner;

public class SWEA_1206 {
    static boolean[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_case = 1;
        while(sc.hasNext()){
            int N = sc.nextInt();
            map = new boolean[N][256];

            // 값 넣기
            for (int i = 0; i < N; i++) {
                int len = sc.nextInt();
                for (int j = 0; j < len; j++) {
                    map[i][j] = true;
                }
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                outer: for (int j = 0; map[i][j]; j++) {
                    //좌우로 두칸씩 검사
                    for (int k = i-2; k <= i+2 ; k++) {
                        if(k == i) continue;

                        if(map[k][j]){
                            continue outer;
                        }
                    }
                    cnt++;
                }
            }
            System.out.printf("#%d %d\n", test_case, cnt);
            test_case++;
        }
    }
}
