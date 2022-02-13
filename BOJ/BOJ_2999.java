import java.util.Scanner;

public class BOJ_2999 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String raw = sc.next();

        //  R<=C이고, R*C=N인 R과 C를 찾는다.
        int len = raw.length();
        int R = 0, C = 0;
        for (int i = 1; i <= Math.sqrt(len); i++) {
            if(len%i == 0){
                R = i;
                C = len / i;
            }
        }

        char[][] result = new char[R][C];
        int index = 0;
        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++, index++) {
                result[j][i] = raw.charAt(index);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(result[i][j]);
            }
        }
    }

}
