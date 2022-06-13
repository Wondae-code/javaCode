import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_16938 {
    // 문제의 개수, 문제 난이도 합의 최소, 문제 난이도 합의 최대, 가장 어려운 난이도와 쉬운 난이도의 차
    static int N, L, R, X;
    static int[] arr, sel;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        L = Integer.parseInt(raw[1]);
        R = Integer.parseInt(raw[2]);
        X = Integer.parseInt(raw[3]);

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sel = new int[N];

        powerSet(0, 0, new boolean[N]);

        System.out.println(cnt);
    }

    static int cnt = 0;

    private static void powerSet(int idx, int k, boolean[] v){
        if(idx == N){
            // 2개 이상일 때
            if(k >= 2) {
                int sum = 0;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < N; i++) {
                    if(v[i]){
                        sum += arr[i];
                        min = Math.min(min, arr[i]);
                        max = Math.max(max, arr[i]);
                    }

                }
                if(sum >= L && sum <= R && (max - min) >= X){
                    cnt++;
                    // System.out.println(sum + " " + min + " " + max + " " + cnt);
                }
            }
            return;
        }

        // 집는 경우
        v[idx] = true;
        powerSet(idx+1, k+1,  v);

        // 안 집는 경우
        v[idx] = false;
        powerSet(idx+1, k,  v);

    }
}
