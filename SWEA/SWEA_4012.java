import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_4012 {
    static int[][] map; // 전체 시너지 지도
    static int[] arr, sel1, sel2; // 전체 경우의 수를 구하기 위한 집합
    static int N, Ans;
    
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/SWEA_4012.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            map = new int[N][N];
            Ans = Integer.MAX_VALUE;
            // 값 받기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    map[r][c] = sc.nextInt();
                }
            }
            // 조합을 위한 배열 만들기
            arr = new int[N];
            sel1 = new int[N/2];
            sel2 = new int[N/2];
            for (int i = 0; i < N; i++) {
                arr[i] = i;
            }
            combination(0,0);

            //print();
            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }

    private static void combination(int idx, int k){
        if(k == N/2){
            // 남은 것들을 sel2에 담기
            for (int i = 0, cnt = 0; i < N; i++) {
                if(!check(arr[i])){
                    sel2[cnt] = arr[i];
                    cnt++;
                }
            }
            // 조합 더하기
            int taste1 = 0, taste2 = 0;
            for (int i : sel1) {
                for(int j : sel1){
                    if(i == j) continue;
                    taste1 += map[i][j];
                }
            }

            for (int i : sel2) {
                for (int j : sel2) {
                    if(i == j) continue;
                    taste2 += map[i][j];
                }
            }

            Ans = Math.min(Ans, Math.abs(taste1 - taste2));
            return;
        }

        if(idx == N){
            return;
        }

        sel1[k] = arr[idx];

        // 집는 경우
        combination(idx+1, k+1);
        // 안 집는 경우
        combination(idx+1, k);
    }

    private static void miniCom(){

    }

    private static boolean check(int i){
        for (int num : sel1) {
            if (num == i) return true;
        }

        return false;
    }

    private static void print(){
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
