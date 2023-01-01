package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_1978 {
    static final int MAX = 1000;
    static ArrayList<Integer> primeNums;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        primeNums = new ArrayList<>();
        // 소수 찾기 -> 시간이 걸리겠지만, 확실한 방법
        findPrime();

        int N = Integer.parseInt(br.readLine());
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int cnt = 0;
        for (int num : inputs) {
            if(primeNums.contains(num)){
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static void findPrime(){
        for (int i = 2; i <= MAX; i++) {
            boolean flag = false;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if(i % j == 0){
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                primeNums.add(i);
            }
        }
    }
}
