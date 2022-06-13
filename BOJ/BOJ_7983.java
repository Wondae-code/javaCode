package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_7983 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Task[] tasks = new Task[n];
        String[] raw = null;
        for (int i = 0; i < n; i++) {
            raw = br.readLine().split(" ");
            int time = Integer.parseInt(raw[0]);
            int deadline = Integer.parseInt(raw[1]);
            tasks[i] = new Task(time, deadline);
        }

        // deadline 기준으로 역순으로 정렬
        Arrays.sort(tasks, Collections.reverseOrder(Comparator.comparingInt(t->t.deadline)));

        int prev = Integer.MAX_VALUE;
        for (Task t : tasks) {
            int val = Math.min(t.deadline, prev);
            prev = val - t.time;
        }

        System.out.println(prev);
    }

    private static class Task {
        int time, deadline; // 걸리는 시간, 마감시간

        public Task(int time, int deadline) {
            this.time = time;
            this.deadline = deadline;
        }
    }
}
