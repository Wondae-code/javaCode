package SSAFY;

import java.util.Scanner;

public class JO_1719 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        print2(n, m);
    }

    static void print2(int height, int type){
        if(height >= 100 || height <= 0 || type > 4 || type < 1 || height % 2 != 1){
            System.out.println("INPUT ERROR!");
            return;
        }
        switch (type){
            case 1:
                for (int i = 0; i < height; i++) {
                    if(i < height/2 + 1) {
                        for (int j = 0; j <= i; j++) {
                            System.out.print("*");
                        }
                    }else {
                        for (int j = 0; j < height - i; j++) {
                            System.out.print("*");
                        }
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int i = 0; i < height; i++) {
                    if(i < height/2 + 1) {
                        for (int j = 0; j < 1.0 * height/2 - i - 1; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j <= i; j++) {
                            System.out.print("*");
                        }
                    }else {
                        for (int j = 0; j < i - (float)height/2; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j < height - i; j++) {
                            System.out.print("*");
                        }
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int i = 0; i < height; i++) {
                    if(i < height/2 + 1) {
                        for (int j = 0; j < i; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j < height - 2*i; j++) {
                            System.out.print("*");
                        }
                        for (int j = 0; j < i; j++) {
                            System.out.print(" ");
                        }
                    }else {
                        for (int j = 0; j < height - i - 1; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j < 2*i - height + 2; j++) {
                            System.out.print("*");
                        }
                        for (int j = 0; j < height - i - 1; j++) {
                            System.out.print(" ");
                        }
                    }
                    System.out.println();
                }
                break;
            case 4:
                for (int i = 0; i < height; i++) {
                    if(i < height/2 + 1) {
                        for (int j = 0; j < i; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j < height/2 - i + 1; j++) {
                            System.out.print("*");
                        }
                    }else {
                        for (int j = 0; j < height/2; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 0; j < i - height/2+1; j++) {
                            System.out.print("*");
                        }
                    }
                    System.out.println();
                }
                break;
        }
    }
}
