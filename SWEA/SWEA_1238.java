import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1238 {
    static ArrayList<Integer>[] map;
    static int[] v;
    static int Ans;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/SWEA_1238.txt"));
        Scanner sc = new Scanner(System.in);
        int test_case = 1;
        while(sc.hasNext()){
            int size = sc.nextInt();
            int start = sc.nextInt();
            map = new ArrayList[101];
            v = new int[101];
            for (int i = 0; i < size/2; i++) {
                int src = sc.nextInt();
                int dst = sc.nextInt();
                if(map[src] == null){
                    map[src] = new ArrayList<>();
                }
                if(!map[src].contains(dst)) {
                    map[src].add(dst);
                }
            }
            Ans = 0;

            // 높이 기준으로 최대값 찾기
            int MaxDepth = BFS(start);
            for (int i = 0; i < map.length; i++) {
                if(v[i] == MaxDepth){
                    Ans = Math.max(Ans, i);
                }
            }

            // BFS로 해결 -> level은 노드의 차수로 구분
            System.out.printf("#%d %d\n", test_case, Ans);
            test_case++;
        }
    }

    private static int BFS(int start){
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(start);
        v[start] = 1;
        int MaxDepth = 0;

        while(!Q.isEmpty()){
            int src = Q.poll();
            if(map[src] != null) {
                for (int val : map[src]) {
                    if (v[val] == 0) {
                        v[val] = v[src] + 1;
                        Q.offer(val);
                    }
                }
            }
        }
        for (int depth : v){
            MaxDepth = Math.max(MaxDepth, depth);
        }

        return MaxDepth;
    }

    private static void print(ArrayList<Integer>[] map) {
        int index = 0;
        for(ArrayList<Integer> arr : map){
            if(arr != null){
                System.out.println(index + " : " + arr.toString());
            }
            index++;
        }
    }

}
