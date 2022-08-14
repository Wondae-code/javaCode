package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_5567 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] map = new ArrayList[N + 1];
        for (int i = 0; i < map.length; i++) {
            map[i] = new ArrayList<>();
        }
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            String[] raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);
            map[src].add(dst);
            map[dst].add(src);
        }

        BFS(map);
    }

    private static void BFS(ArrayList<Integer>[] map) {
        Queue<Integer> q = new LinkedList<>();
        int[] v = new int[map.length];
        q.offer(1);
        v[1] = 1;
        int cnt = 0;
        while (!q.isEmpty()) {
            int val = q.poll();
            if(v[val] > 3){
                for (int i = 2; i < v.length; i++) {
                    if(v[i] <= 3 && v[i] != 0){
                        cnt++;
                    }
                }
                break;
            }
            for (int dst : map[val]) {
                if (v[dst] == 0) {
                    v[dst] = v[val] + 1;
                    q.offer(dst);
                }
            }
        }

        System.out.println(cnt);
    }
}
