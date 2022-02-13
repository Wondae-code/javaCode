package SSAFY;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2164 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        while (q.size() > 1){
            q.poll();
            q.offer(q.poll());
        }

        System.out.println(q.peek());
    }
}
