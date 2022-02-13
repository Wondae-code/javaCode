package SSAFY;

import java.util.Scanner;

class JO_1707 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        if(size > 100 || size < 1){
            return;
        }
        int temp = size;
        int[][] arr = new int[size][size];
        int right = -1;
        int bottom = 0;
        int top = 1;
        int k =1;
        for(int i = size; i > 0; i--) {
            for(int j = 0; j < temp; j++) {
                right += top;
                arr[bottom][right] = k;
                k++;
            }
            temp--;
            for(int j = 0; j < temp; j++) {
                bottom += top;
                arr[bottom][right] = k;
                k++;
            }

            top = top * (-1);
        }
        // 출력 부분
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
