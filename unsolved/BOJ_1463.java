import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1463 {
    static int[] DP;
    static int K;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt();

        DP = new int[K+1];
        if(K == 1){
            System.out.println("0");
        }else if(K <= 3){
            System.out.println("1");
        }else{
            DP[1] = 1;
            DP[2] = 1;
            DP[3] = 1;
            System.out.println(Cal());
        }
    }

    private static int Cal(){
        for (int i = 4; i <= K; i++) {
            // 아직 값을 모르는 상황
            ArrayList<Integer> temp = new ArrayList<>();
            if(i%3 == 0){
                temp.add(DP[i/3]);
            }
            if(i%2 == 0){
                temp.add(DP[i/2]);
            }
            temp.add(DP[i-1]);

            DP[i] = Collections.min(temp)+1;
        }
        return DP[K];
    }
}
