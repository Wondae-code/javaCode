package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 전체 리프 노드의 개수 - 해당 노드의 리프 개수

public class BOJ_1068 {
    static ArrayList<Integer>[] map;
    static int leaves;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int root = 0;
        map = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            map[i] = new ArrayList<>();
        }

        String[] raw = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(raw[i]);
            if(v == -1){
                root = i;
                continue;
            }
            map[v].add(i);
        }

        int del = Integer.parseInt(br.readLine());

        if(root == del){
            System.out.println(0);
            return;
        }

        for (ArrayList<Integer> arr: map) {
            if(arr.contains(del)){
                arr.remove(arr.indexOf(del));
            }
        }

        traverse(root);

        System.out.println(leaves);
    }

    private static void traverse(int cur){
        if(map[cur].size() == 0){
            leaves++;
            return;
        }

        for (int val: map[cur]) {
            traverse(val);
        }
    }
}
