import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_5607 {
    // 페르마의 소정리 응용
    static int N, R, mod = 1234567891;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] raw = br.readLine().split(" ");
            N = Integer.parseInt(raw[0]);
            R = Integer.parseInt(raw[1]);
            // factorial 계산
            long[] facto = new long[N + 1];
            facto[0] = 1;
            for (int i = 1; i <= N; i++) {
                facto[i] = facto[i - 1] * i % mod;
            }

            // 페르마의 소정리 응용
            long numerator = facto[N];
            long denominator = (facto[R] * facto[N-R]) % mod;
            denominator = fermat(denominator, mod - 2);


            System.out.printf("#%d %d\n", test_case, (numerator * denominator) % mod);

        }
    }

    private static long fermat(long base, long exp) {
        long result = 1L;

        base %= mod;

        while(exp > 0){
            if(exp % 2 == 1){
                result = (result*base) % mod;
            }
            exp >>= 1;
            base = (base * base) % mod;
        }

        return result;
    }
}
