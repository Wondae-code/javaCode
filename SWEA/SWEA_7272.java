package SSAFY;
// 알파벳의 구멍 개수에 따라 같음/다름을 판단
// CEFGHIJKLMNSTUVWXYZ -> 구멍 X
// ADOPQR -> 구멍 1개
// B -> 구멍 2개

import java.util.Scanner;

public class SWEA_7272 {
    static String zero = "CEFGHIJKLMNSTUVWXYZ";
    static String one = "ADOPQR";
    static String two = "B";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        boolean[] diff = new boolean[T];
        for (int test_case = 0; test_case < T; test_case++) {
            String A = sc.next();
            String B = sc.next();
            //첫번째 조건 : 문자열의 길이가 다름
            if(A.length() != B.length()){
                diff[test_case] = true;
                continue;
            }
            // 두번째 조건 : 각 문자의 구멍의 개수가 다름
            for (int i = 0; i < A.length(); i++) {
                int belongA = -1; // 값은 구멍의 개수
                int belongB = -1;
                // A.charAt(i)와 B.charAt(i)의 구멍 개수 구하기.
                if(zero.indexOf(A.charAt(i)) != -1){
                    belongA = 0;
                }else if(one.indexOf(A.charAt(i)) != -1){
                    belongA = 1;
                }else{
                    belongA = 2;
                }
                if(zero.indexOf(B.charAt(i)) != -1){
                    belongB = 0;
                }else if(one.indexOf(B.charAt(i)) != -1){
                    belongB = 1;
                }else{
                    belongB = 2;
                }
                if(belongA != belongB){
                    diff[test_case] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < T; i++) {
            if(diff[i]){
                System.out.printf("#%d DIFF\n",i+1);
            }else{
                System.out.printf("#%d SAME\n",i+1);
            }
        }
    }
}
