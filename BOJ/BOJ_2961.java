package SSAFY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BOJ_2961 {
    static ArrayList<Integer> sour, bitter;
    static ArrayList<int[]> tastes;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        sour = new ArrayList<>();
        bitter = new ArrayList<>();

        tastes = new ArrayList<>();

        for (int i = 0; i < N*2; i++) {
            if(i%2 == 0) sour.add(sc.nextInt());
            else bitter.add(sc.nextInt());
        }

        powerSet(0, new boolean[N]);

        // {1,0} 빼기 => 아무것도 안 집은 것은 마지막이므로 마지막 원소 제거
        tastes.remove(tastes.size() - 1);

        System.out.println(findMin(tastes));
    }

    static void powerSet(int idx, boolean[] v){
        // base part
        if(idx == N){
            int[] tempTaste = {1, 0};
            for (int i = 0; i < N; i++) {
                if(v[i]){
                    tempTaste[0] *= sour.get(i);
                    tempTaste[1] += bitter.get(i);
                }
            }
            tastes.add(tempTaste);
            return;
        }

        // inductive part
        v[idx] = true;
        powerSet(idx+1, v);

        v[idx] = false;
        powerSet(idx+1, v);
    }

    static int findMin(ArrayList<int[]> t){
        int totalMin = Integer.MAX_VALUE;
        for(int[] arr : t){
            int tempMin = Math.abs(arr[0] - arr[1]);
            totalMin = Math.min(totalMin, tempMin);
        }

        return totalMin;
    }
}
