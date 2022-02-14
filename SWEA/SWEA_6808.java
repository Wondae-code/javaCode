import java.util.Arrays;
import java.util.Scanner;

public class SWEA_6808 {
    static int[] Gyu, In;
    static int GyuWin, InWin;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            Gyu = new int[9];
            In = new int[9];
            GyuWin = 0;
            InWin = 0;

            // 입력 받기
            for (int i = 0; i < 9; i++) {
                Gyu[i] = sc.nextInt();
            }

            // 입력 받은 값 기준으로 flag 채우기
            int flag = 0;
            for (int val : Gyu) {
                flag = flag | (1 << val);
            }
            permutation(0, flag);

            System.out.println("#" + test_case + " " + GyuWin + " " + InWin);
        }
    }

    static void permutation(int k, int flag){
        if(k == 9){
            // 9개를 다 집은 경우
            int GyuScore = 0, InScore = 0;
            for (int i = 0; i < 9; i++) {
                if(Gyu[i] > In[i]){
                    GyuScore += Gyu[i] + In[i];
                }else{
                    InScore += Gyu[i] + In[i];
                }
            }
            if(GyuScore > InScore){
                GyuWin++;
            }else{
                InWin++;
            }

            return;
        }

        for (int i = 1; i <= 18; i++) {
            if((flag & 1 << i) != 0) continue;
            In[k] = i;
            permutation(k+1, flag | 1 << i);
        }
    }
}
