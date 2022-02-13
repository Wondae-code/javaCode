// https://www.acmicpc.net/problem/15649

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15649 {
    static int N;
    static int K;
    static int[] base;
    static ArrayList<Integer> result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        base = new int[N];
        result = new ArrayList<>();
        // 값 집어 넣기
        for (int i = 0; i < N; i++) {
            base[i] = i+1;
        }
        permutation(0);
    }

    static void permutation(int depth){
        if(depth == K){
            for (int i: result ) {
                System.out.print(i + " ");
            }
            System.out.println();
            result.remove(result.size() - 1);
            return;
        }

        for (int i: base) {
            if(result.size() != 0 && result.contains(i)){
                continue;
            }
            result.add(i);
            permutation(depth+1);
        }
        if(result.size() != 0) {
            result.remove(result.size() - 1);
        }
    }
}
