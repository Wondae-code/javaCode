package SSAFY;

import java.util.Scanner;
import java.util.ArrayList;

public class SWEA_1234 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 10; test_case++) {
            int length = sc.nextInt();
            // 정수 배열을 만들고, 문자열을 정수 배열로 저장
            ArrayList<Integer> arr = new ArrayList<Integer>();
            String raw = sc.next();
            for (int i = 0; i < length; i++) {
                arr.add(raw.charAt(i) - '0');
            }
            // 같은 번호 제거
            for (int i = 0; i < arr.size() - 1; i++) {
                if(arr.get(i) == arr.get(i+1)){
                    arr.remove(i);
                    arr.remove(i);
                    i = -1; // 인덱스 0부터 시작하기 위해
                }
            }
            // 비밀번호 출력
            System.out.print("#" + test_case + " ");
            for (int num : arr) {
                System.out.print(num);
            }
            System.out.println();
        }
    }
}
