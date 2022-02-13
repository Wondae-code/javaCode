import java.util.Scanner;

public class BOJ_2991 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int danger1 = sc.nextInt(); // dog 1 dangerous
        int safe1 = sc.nextInt(); // dog 1 safe
        int danger2 = sc.nextInt(); // dog 2 dangerous
        int safe2 = sc.nextInt(); // dog 2 safe

        int[] arr = new int[3];

        for (int i = 0; i < 3; i++) {
            // dog 1의 한 사이클 : danger1 + safe1
            // dog 2의 한 사이클 : danger2 + safe2
            // 값을 사이클로 나눴을 때 나머지 값을 기준으로 생각 해 봄.
            arr[i] = sc.nextInt();
            int count = 0;
            count = (arr[i] % (danger1 + safe1) >= 1 && arr[i] % (danger1 + safe1) <= danger1) ? count + 1 : count;
            count = (arr[i] % (danger2 + safe2) >= 1 && arr[i] % (danger2 + safe2) <= danger2) ? count + 1 : count;

            System.out.println(count);
        }
    }
}
