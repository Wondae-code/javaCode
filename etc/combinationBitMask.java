import java.util.Arrays;
import java.util.Scanner;

public class combinationBitMask {
    static int cnt, num, arr[], sel[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        cnt = sc.nextInt();

        arr = new int[num];
        sel = new int[cnt];

        for (int i = 0; i < num; i++) {
            arr[i] = i+1;
        }

        perBit(0, 0);
    }

    static void perBit(int k, int flag){ // flag : bitMask
        if(k == cnt){
            System.out.println(Arrays.toString(sel));
            return;
        }

        for (int i = 0; i < num; i++) {
            // 방문을 했으면
            if((flag & 1 << i) != 0) continue;
            sel[k] = arr[i];
            // 다음 수 뽑으러 가기
            perBit(k+1, flag | 1 << i);
        }
    }
}
