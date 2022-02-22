import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class JO_1863 {
    static int[] djSet;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N, M; // 학생의 수, 쌍의 수
        N = sc.nextInt();
        M = sc.nextInt();
        djSet = new int[N+1];

        for (int i = 1; i <= N; i++) {
            djSet[i] = i;
        }

        for (int i = 0; i < M; i++) {
            union(sc.nextInt(), sc.nextInt());
        }

        // 개수 세기 -> 중복값을 막기 위해 hashset으로 품. 더 좋은 방법이 있을까
        HashSet<Integer> h = new HashSet<>();
        for (int i = 1; i < N; i++) {
            h.add(findSet(i));
        }

        System.out.println(h.size());
    }

    private static int findSet(int num){
        if(num == djSet[num]) return num;

        return djSet[num] = findSet(djSet[num]); // path compression
    }


    private static boolean union(int a, int b){
        if(findSet(a) == findSet(b)){
            return false;
        }
        djSet[findSet(b)] = findSet(a);
        return true;
    }

}
