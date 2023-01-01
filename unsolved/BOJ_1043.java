import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1043 {
    static HashSet<Integer>[] people;
    static HashSet<Integer> knows;
    static ArrayList<int[]> parties;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        // N : 사람 수, M : 파티 수
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        people = new HashSet[N+1];
        for (int i = 0; i < people.length; i++) {
            people[i] = new HashSet<>();
        }
        // 진실을 아는 사람 수, 그 번호 -> 진실 배열
        inputs = br.readLine().split(" ");
        knows = new HashSet<>();
        for (int i = 1; i < inputs.length; i++) {
            knows.add(Integer.parseInt(inputs[i]));
        }
        parties = new ArrayList<>();

        // 파티의 참가자 수, 사람 수 -> graph
        for (int p = 0; p < M; p++) {
            inputs = br.readLine().split(" ");
            int K = Integer.parseInt(inputs[0]);
            int[] arr = new int[K];
            for (int i = 1; i < inputs.length; i++) {
                arr[i-1] = Integer.parseInt(inputs[i]);
            }
            // 파티 추가
            parties.add(arr);
            // 가지 만들기
            ArrayList<int[]> edges = makeEdge(arr);
            // 그래프에 가지 추가하기
            for (int[] e: Objects.requireNonNull(edges)) {
                people[e[0]].add(e[1]);
                people[e[1]].add(e[0]);
            }
        }
        // 사실을 아는 사람 목록
        Integer[] temp = knows.toArray(new Integer[0]);
        for (int i : temp) {
            BFS(i);
        }

        // 최대값 찾기
        int cnt = 0;
        outer : for (int[] arr: parties) {
            for (int i: arr) {
                if(knows.contains(i)){
                    continue outer;
                }
            }
            cnt++;
        }

        System.out.println(cnt);
    }

    private static void BFS(int src){
        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[people.length];
        v[src] = true;
        q.offer(src);

        while(!q.isEmpty()){
            int p = q.poll();
            knows.add(p);

            for (int k: people[p]) {
                if(!v[k]){
                    v[k] = true;
                    q.offer(k);
                }
            }
        }

    }


    /**
     * arr을 통해 길이가 2인 부분 집합 만들기
     */
    private static ArrayList<int[]> makeEdge(int[] arr){
        ArrayList<int[]> edges = new ArrayList<>();
        boolean[] v = new boolean[arr.length];
        int[] e = new int[2];

        inner(edges, v, arr, e, 0);

        return edges;
    }

    private static void inner(ArrayList<int[]> edges, boolean[] v, int[] arr, int[] e, int k){
        if(k == 2){
            int[] ee = Arrays.copyOf(e, e.length);
            edges.add(ee);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(!v[i]){
                e[k] = arr[i];
                v[i] = true;
                inner(edges, v, arr, e, k+1);
                v[i] = false;
            }
        }
    }
}
