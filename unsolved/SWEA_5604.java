import java.io.BufferedReader;
import java.io.InputStreamReader;


public class SWEA_5604 {
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            arr = new long[10];
            String[] raw = br.readLine().split(" ");
            long src = Long.parseLong(raw[0]);
            long dst = Long.parseLong(raw[1]);
            long t = 1;

            if(src == 0) src = 1;

            while (src <= dst) {
                while (src % 10 != 0 && src <= dst) {
                    cal(src, t);
                    src++;
                }

                if(src > dst) break;

                while (dst % 10 != 9 && src <= dst) {
                    cal(dst, t);
                    dst--;
                }
                long val = (dst / 10) - (src / 10) + 1;
                for (int i = 0; i < 10; i++) {
                    arr[i] += t*val;
                }

                src /= 10;
                dst /= 10;
                t *= 10;
            }

            long sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i*arr[i];
            }

            System.out.printf("#%d %d\n", test_case, sum);
        }
    }

    private static void cal(long src, long t) {
        while (src != 0){
            int val = (int)(src%10);
            arr[val] += t;
            src/=10;
        }
    }
}
