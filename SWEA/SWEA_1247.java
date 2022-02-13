package SSAFY;

import java.util.Arrays;
import java.util.Scanner;

class Coord{
    int x, y;

    Coord(){}

    Coord(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class SWEA_1247 {
    static int num, cnt;
    static Coord[] customer, order;
    static int Ans;
    static boolean[] v;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            num = sc.nextInt(); // 고객의 수
            cnt = num;
            Coord home = new Coord(sc.nextInt(), sc.nextInt()); // 집 좌표
            Coord office = new Coord(sc.nextInt(), sc.nextInt()); // 회사 좌표

            customer = new Coord[num];
            order = new Coord[num+2];
            order[0] = office;
            order[order.length - 1] = home;
            v = new boolean[num+1];
            Ans = Integer.MAX_VALUE;

            int[] len = new int[num];

            // 고객 값 입력
            for (int i = 0; i < num; i++) {
                customer[i] = new Coord(sc.nextInt(), sc.nextInt());
            }

            Permutation(1);
            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }
    static int getLength(Coord s, Coord d){
        return Math.abs(s.x - d.x) + Math.abs(s.y - d.y);
    }

    static void Permutation(int k){
        if(k == cnt+1){
            int temp = 0;
//            for (int i = 0; i < cnt; i++) {
//                System.out.print(order[i] + " ");
//            }
//            System.out.println();
            for (int i = 0; i < cnt+1; i++) {
                temp += getLength(order[i], order[i+1]);
            }
            Ans = Math.min(Ans, temp);
            return;
        }

        for (int i = 0; i < num; i++) {
            if(!v[i]){
                v[i] = true;
                order[k] = customer[i];
                Permutation(k+1);
                v[i] = false;
            }
        }
    }
}
