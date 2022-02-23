package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_1197_2 {
    public static void main(String[] args) throws IOException {
        // Prim으로 풀어보기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String raw = br.readLine();
        int V = Integer.parseInt(raw.split(" ")[0]);
        int E = Integer.parseInt(raw.split(" ")[1]);

        ArrayList<int[]>[] arr = new ArrayList[V+1];
        for (int i = 0; i < E; i++) {
            // 입력 받기
            // [0] = src, [1] = dst, [2] = weight
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // 값 없으면 넣어주기
            // arr[src]가 없으면 만들어주기
            if(arr[temp[0]] == null){
                arr[temp[0]] = new ArrayList<>();
            }
            // arr[dst]가 없으면 만들어주기 -> 대칭이므로
            if(arr[temp[1]] == null){
                arr[temp[1]] = new ArrayList<>();
            }
            arr[temp[0]].add(new int[]{temp[1], temp[2]});
            arr[temp[1]].add(new int[]{temp[0], temp[2]});
        }

        // vertex 방문 배열
        boolean[] v = new boolean[V+1];
        // 비용값 저장 배열
        int[] weights = new int[V+1];
        Arrays.fill(weights, Integer.MAX_VALUE);

        // 1부터 시작
        weights[1] = 0;
        for (int idx = 1; idx <= V - 1; idx++) {
            int minW = Integer.MAX_VALUE;
            int minIdx = -1;

            // 최단 거리 인덱스 찾기
            for (int i = 1; i <= V; i++) {
                if(!v[i] && weights[i] < minW){
                    minW = weights[i];
                    minIdx = i;
                }
            }

            // minIdx = 시작점, arr[minIdx].get(i)[0] = 도착점
            v[minIdx] = true;
            for (int i = 0; i < arr[minIdx].size(); i++) {
                if(!v[arr[minIdx].get(i)[0]] && weights[arr[minIdx].get(i)[0]] > arr[minIdx].get(i)[1]){
                    weights[arr[minIdx].get(i)[0]] = arr[minIdx].get(i)[1];
                }
            }
        }
        int sum = 0;
        for (int i = 1; i < weights.length; i++) {
            sum += weights[i];
        }
        System.out.println(sum);
    }
}
