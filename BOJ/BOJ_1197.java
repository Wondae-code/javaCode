package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1197 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String raw = br.readLine();
        int V = Integer.parseInt(raw.split(" ")[0]);
        int E = Integer.parseInt(raw.split(" ")[1]);

        // 크루스칼로 풀어보기 -> 간선 리스트
        Edge[] edgeList = new Edge[E];
        for (int cmd = 0; cmd < E; cmd++) {
            // 너무 과한 거 아닌가?
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edgeList[cmd] = new Edge(temp[0], temp[1], temp[2]);
        }

        // 정렬하기
        Arrays.sort(edgeList, Comparator.comparingInt(e -> e.w));

        makeSet(V);

        // V-1개의 간선 뽑기
        int sum = 0, cnt = 0;
        ArrayList<Edge> sel = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            Edge e = edgeList[i];
            if(findSet(e.s) != findSet(e.d)){
                sum += e.w;
                sel.add(e);
                cnt++;
                unionSet(e.s, e.d);
            }

            if(cnt == V - 1){
                break;
            }
        }
        System.out.println(sum);
    }

    private static class Edge{
        // src, dst, weight
        int s, d, w;

        public Edge(int s, int d, int w) {
            this.s = s;
            this.d = d;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "s=" + s +
                    ", d=" + d +
                    ", w=" + w +
                    '}';
        }
    }

    private static void makeSet(int size){
        parents = new int[size+1];
        for (int i = 1; i <= size; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int num){
        if(num == parents[num]){
            return num;
        }
        return parents[num] = findSet(parents[num]);
    }

    private static boolean unionSet(int a, int b){
        int rootA = findSet(a);
        int rootB = findSet(b);
        if(rootA == rootB){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }
}
