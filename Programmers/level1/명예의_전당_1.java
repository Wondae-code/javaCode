package level1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 명예의_전당_1 {
    static final int k = 3;
    static final int[] score = {10, 100, 20, 150, 1, 100, 200};
    public static void main(String[] args) {
        PriorityQueue<Integer> honor = new PriorityQueue<>(Comparator.naturalOrder());
        int[] answer = new int[score.length];
        int idx = 0;
        for (int s : score) {
            honor.add(s);
            if(honor.size() > k){
                honor.poll();
            }
            answer[idx] = honor.peek();
            idx++;
        }

        System.out.println(Arrays.toString(answer));
    }
}
