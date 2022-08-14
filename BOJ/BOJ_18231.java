package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BOJ_18231 {
    static int N, M;
    static HashMap<Integer, ArrayList<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        M = Integer.parseInt(raw[1]);

        graph = new HashMap<>();
        for (int i = 0; i < N; i++) {
            graph.put(i+1, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            raw = br.readLine().split(" ");
            int src = Integer.parseInt(raw[0]);
            int dst = Integer.parseInt(raw[1]);
            // 도로를 잇는 도로는 하나뿐이니 중복체크 X
            // 도로는 양방향
            graph.get(src).add(dst);
            graph.get(dst).add(src);
        }




    }

}
