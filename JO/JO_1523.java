package SSAFY;

// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=795&sca=2020

import java.util.Scanner;

public class JO_1523 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        printTriangle(n, m);
    }

    static void printTriangle(int height, int type){
        if(height >= 100 || height <= 0 || type > 3 || type < 1){
            System.out.println("INPUT ERROR!");
            return;
        }
        switch (type){
            case 1:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j <= i; j++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int i = 0; i < height; i++) {
                    for (int j = height - i; j > 0; j--) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < height - i - 1; j++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 2*i + 1; j++) {
                        System.out.print("*");
                    }
                    for (int j = 0; j < height - i - 1; j++) {
                        System.out.print(" ");
                    }
                    System.out.println();
                }
                break;
        }
    }
}
