import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class SWEA_7465 {
    static int[] djSet;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            // 값 채워넣기
            djSet = new int[V+1];
            for (int i = 1; i <= V; i++) {
                djSet[i] = i;
            }

            // 명령어 처리하기
            for (int i = 0; i < E; i++) {
                union(sc.nextInt(), sc.nextInt());
            }

            HashSet<Integer> h = new HashSet<>();
            // 그룹 수 찾기 -> how?
            for (int i = 1; i <= V; i++) {
                h.add(findSet(i));
            }

            System.out.printf("#%d %d\n", test_case, h.size());
        }
    }

    private static int findSet(int num){
        if(num == djSet[num]) return djSet[num];
        // path compression
        return djSet[num] = findSet(djSet[num]);
    }

    private static boolean union(int a, int b){
        int rootA = findSet(a);
        int rootB = findSet(b);
        if(rootA == rootB){
            return false;
        }
        djSet[rootB] = rootA;
        return true;
    }
}
