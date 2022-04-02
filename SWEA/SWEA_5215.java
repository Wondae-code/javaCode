import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT
public class SWEA_5215 {
    static int[] tastes, calories;
    static int num, cal;
    static int max;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            num = sc.nextInt(); // 재료의 수
            cal = sc.nextInt(); // 한계 칼로리
            tastes = new int[num];
            calories = new int[num];
            max = 0; // 최대값

            for (int i = 0; i < num; i++) {
                tastes[i] = sc.nextInt();
                calories[i] = sc.nextInt();
            }

            combination(0, 0, 0);

            System.out.printf("#%d %d\n", test_case, max);
        }
    }
    public static void combination(int cnt, int taste, int calorie){
        if(calorie > cal){
            return;
        }
        if(cnt == num){
            max = Math.max(max, taste);
            return;
        }
        // 집는 경우
        combination(cnt+1, taste + tastes[cnt], calorie + calories[cnt]);
        // 안 집는 경우
        combination(cnt+1, taste, calorie);
    }
}
