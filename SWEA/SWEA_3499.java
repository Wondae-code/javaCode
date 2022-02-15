import java.util.*;

public class SWEA_3499 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        Queue<String> base1 = new LinkedList<>();
        Queue<String> base2 = new LinkedList<>();
        for (int test_case = 1; test_case <= T; test_case++) {

            int num = sc.nextInt();
            sc.nextLine();
            // 값 넣고 카드 나누기
            String[] before = sc.nextLine().split(" ");
            // 길이가 홀수 짝수 일때 케이스 나눠서 나눔 -> 조금 더 효율적인 방법은 없을까?
            if(num%2 == 0){
                for (int i = 0; i < before.length/2; i++) {
                    base1.offer(before[i]);
                }
                for (int i = before.length/2; i < before.length; i++) {
                    base2.offer(before[i]);
                }
            }else{
                for (int i = 0; i < before.length/2+1; i++) {
                    base1.offer(before[i]);
                }
                for (int i = before.length/2+1; i < before.length; i++) {
                    base2.offer(before[i]);
                }
            }
            // 카드 놓기 + 바로 출력
            System.out.print("#" + test_case);
            for (int i = 0; i < num; i++) {
                if(i%2 == 0){
                    System.out.print(" " + base1.poll());
                }
                else{
                    System.out.print(" " + base2.poll());
                }
            }
            System.out.println();
            base1.clear();
            base2.clear();
        }
    }
}
