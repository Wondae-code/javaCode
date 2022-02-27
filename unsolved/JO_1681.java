import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 간선리스트로 해볼까?
// 접근한 방식 : 주어진 것을 간선 리스트로 만들어서 가장 짧은 것을 시작 점으로 둔 후,
// 그 간선의 도착점이 시작점이 되고, 간선 값이 제일 작은 것을 뽑으면 최소값이 될 것이다라고 생각.
// 하지만 틀림..

public class JO_1681 {
    static ArrayList<Edge> map;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = br.readLine().charAt(0) - '0';
        map = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int[] raw = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < size; j++) {
                if(i == j) continue;
                if(raw[j] == 0){
                    continue;
                }else {
                    map.add(new Edge(i + 1, j + 1, raw[j]));
                }
            }
        }
        map.sort(Comparator.comparingInt(e -> e.val));


        int Ans = Cycle();

        System.out.println(Ans);
    }

    static int Cycle(){
        int val = map.get(0).val;
        boolean[] v = new boolean[size+1];
        v[map.get(0).src] = true;
        int dst = map.get(0).dst;


        for (int i = 0; i < size-1; i++) {
            if(i == size - 2){
                for (int idx = 0; idx < map.size(); idx++) {
                    if(map.get(idx).src == dst && map.get(idx).dst == map.get(0).src){
                        val += map.get(idx).val;
                    }
                }
            }else {
                for (int idx = 0; idx < map.size(); idx++) {
                    if (!v[map.get(idx).dst] && map.get(idx).src == dst) {
                        dst = map.get(idx).dst;
                        val += map.get(idx).val;
                        v[map.get(idx).src] = true;
                        break;
                    }
                }
            }
        }

        return val;
    }

    private static class Edge{
        int src, dst, val;

        public Edge(int src, int dst, int val) {
            this.src = src;
            this.dst = dst;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", dst=" + dst +
                    ", val=" + val +
                    '}';
        }
    }
}
