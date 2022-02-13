package SSAFY;

import java.util.Scanner;

public class JO_1329 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        print3(n);
    }

    static void print3(int height){
        if(height >= 100 || height <= 0 || height%2 != 1){
            System.out.println("INPUT ERROR!");
            return;
        }
        for (int i = 0; i < height; i++) {
            if(i < height/2 + 1) { // 절반
                for (int j = 0; j < i; j++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < i*2+1; j++) {
                    System.out.print("*");
                }
            }else{
                for (int j = 0; j < height-i-1; j++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < (height - i)*2 - 1; j++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
