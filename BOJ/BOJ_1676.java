package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ_1676 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[501];
        BigInteger bi = new BigInteger("1");
        for (int i = 2; i <= N; i++) {
            bi = bi.multiply(new BigInteger(Integer.toString(i)));
            String s = bi.toString();

            boolean flag = false;
            int cnt = 0;
            if(s.charAt(s.length() - 1) == '0'){
                flag = true;
            }
            if(flag){
                int idx = s.length() - 1;
                while(s.charAt(idx) == '0'){
                    cnt++;
                    idx--;
                }
            }
            arr[i] = cnt;
        }
        //

        System.out.println(arr[N]);
    }
}
