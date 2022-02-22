import java.util.Scanner;

public class SWEA_3234 {
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            StringBuilder sb = new StringBuilder();
            arr = new int[V+1];
            for (int n = 0; n <= V; n++) {
                arr[n] = n;
            }

            for (int c = 0; c < E; c++) {
                int command = sc.nextInt();
                if(command == 1){
                    sb.append(unionCheck(sc.nextInt(), sc.nextInt()) ? 1 : 0);
                }else{
                    union(sc.nextInt(), sc.nextInt());
                }
            }

            System.out.printf("#%d %s\n", test_case, sb);
        }
    }

    private static int findSet(int num){
        if(num == arr[num]) return num;
        return arr[num] = findSet(arr[num]);
    }

    private static void union(int a, int b){
        int rootA = findSet(a);
        int rootB = findSet(b);
        if(unionCheck(a, b)){
            return;
        }

        arr[rootB] = rootA;
    }

    private static boolean unionCheck(int a, int b){
        return findSet(a) == findSet(b);
    }
}
