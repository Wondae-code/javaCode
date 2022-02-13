package SSAFY;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_1158 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int K = sc.nextInt();

        // 값 넣기
        LinkedList<Integer> arr = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            arr.add(i);
        }

        // k번째를 계속 빼기 + 값 넣기
        sb.append('<');
        for (int i = K; !arr.isEmpty(); i+=K) {
            if(arr.size() != N){
                sb.append(", ");
            }

            while(i > arr.size()){
                i -= arr.size();
            }
            sb.append(arr.get(i-1));
            arr.remove(i-1);
            i--;
        }
        sb.append('>');
        System.out.println(sb);
    }
}
