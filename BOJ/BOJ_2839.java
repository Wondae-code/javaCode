package SSAFY;

import java.util.Scanner;

public class BOJ_2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int val = sc.nextInt();

        int Ans;
        Ans = greedy(val);
        //Ans = DivideConquer(val);

        System.out.println(Ans);
    }

    static int greedy(int val){
        int count = 0;
        while(val > 0) {
            if (val <= 12) {
                if (val % 3 == 0) {
                    count += val / 3;
                    val = 0;
                } else {
                    count++;
                    val -= 5;
                }
            } else {
                count++;
                val -= 5;
            }
        }

        if(val == 0) return count;

        return -1;
    }


    // 자꾸 88%에서 에러가 나는데 어떤 케이스에서 에러가 나는지 모르곘습니다.
    static int DCcount = 1234657891;
    static int[] nums = {5, 3};
    static int DivideConquer(int val){
        int tempCnt = 0;
        while(val > 12){
            val -= 5;
            tempCnt++;
        }
        powerSet(0, val);

        return DCcount + tempCnt;
    }
    static void powerSet(int idx, int val){
        if(val == 0){
            DCcount = Math.min(DCcount, idx);
            return;
        }

        if(val < 0){
            return;
        }

        for (int num : nums) {
            powerSet(idx + 1, val - num);
        }
    }
}
