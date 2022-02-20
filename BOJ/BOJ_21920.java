package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_21920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 값 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] raw = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(raw[i]);
        }
        int X = Integer.parseInt(br.readLine());

        // 서로소 찾기
        double cnt = 0;
        double sum = 0;
        // 중복 체크?
        for(int i : arr){
            if(euclid(i, X) == 1){
                cnt++;
                sum += i;
            }
        }

        // 값 출력
        System.out.println(sum/cnt);
    }

    // 유클리드 호제법을 통해 최대공약수 구하기 -> 최대공약수가 1이면 서로소
    // i >= j인 경우만
    private static int euclid(int i, int j){
        // i % j가 0이 될 때까지 진행
        while(j != 0){
            int r = i%j;
            i = j;
            j = r;
        }

        return i;
    }
}
