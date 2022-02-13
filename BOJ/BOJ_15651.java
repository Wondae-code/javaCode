package SSAFY.M_N;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651 {
    static int[] arr, sel;
    static int num, cnt;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        num = Integer.parseInt(st.nextToken());
        cnt = Integer.parseInt(st.nextToken());
        arr = new int[num];
        sel = new int[cnt];

        for (int i = 0; i < num; i++) {
            arr[i] = i+1;
        }

        permutation(0,0);
        System.out.println(sb);
    }

    static void permutation(int idx, int k){
        if(k == cnt){
            for (int i : sel) {
                sb.append(i + " ");
            }
            sb.append('\n');
            return;
        }

        if(idx == num){
            return;
        }

        sel[k] = arr[idx];

        // 집는 경우
        permutation(0,k+1);
        // 안집는 경우
        permutation(idx+1, k);
    }
}
