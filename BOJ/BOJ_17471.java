package SSAFY;
// 연결 리스트?
// 필요한것 : 인구수 저장 배열, 연결된 구조 그래프

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17471 {
    static int N, Ans = Integer.MAX_VALUE;
    static int[] population;
    static int [][] graph;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // 지역구 수 입력
        N = sc.nextInt();
        // 지역구 인구 입력
        population = new int[N];
        for (int i = 0; i < N; i++) {
            population[i] = sc.nextInt();
        }
        // 연결 그래프 입력
        graph = new int[N][];
        for (int i = 0; i < N; i++) {
            graph[i] = new int[sc.nextInt()];
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j] = sc.nextInt() - 1;
            }
        }

        //print();

        // powerSet 관련
        powerSet(0, 0, new boolean[N]);

        System.out.println(Ans == Integer.MAX_VALUE ? -1 : Ans);
    }
    static ArrayList<Integer> subGraph1 = new ArrayList<>();
    static ArrayList<Integer> subGraph2 = new ArrayList<>();

    private static void powerSet(int idx, int k, boolean[] v){
        if(idx == N){
            // 부분 그래프들에 값 넣기
            subGraph1.clear();
            subGraph2.clear();
            for (int i = 0; i < N; i++) {
                if(v[i]) subGraph1.add(i);
                else subGraph2.add(i);
            }
            // 전부 다 true, false인 경우 빼기
            if(subGraph1.size() == 0 || subGraph2.size() == 0){
                return;
            }

            // 전부를 방문하는 경로가 있는지 체크
            int sum1 = 0, sum2 = 0;
            if(pathFind(subGraph1) && pathFind(subGraph2)){
                for (int i : subGraph1) {
                    sum1 += population[i];
                }
                for (int i : subGraph2) {
                    sum2 += population[i];
                }
                // 최소 값 구하기
                Ans = Math.min(Ans, Math.abs(sum1 - sum2));
            }
            return;
        }

        // 집는 경우
        v[idx] = true;
        powerSet(idx+1, k+1, v);
        // 안 집는 경우
        v[idx] = false;
        powerSet(idx+1, k+1, v);
    }

    // BFS?
    private static boolean pathFind(ArrayList<Integer> g){
        // 중복 접근하지 않게 할 boolean 배열
        boolean[] check = new boolean[N];
        // BFS를 위한 큐
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(g.get(0));
        check[g.get(0)] = true;
        while(!Q.isEmpty()){
            Integer src = Q.poll();
            //logic
            //System.out.print(src + " ");
            for (int dst : graph[src]) {
                if(!check[dst] & g.contains(dst)){
                    Q.offer(dst);
                    check[dst] = true;
                }
            }
        }
        // 경로가 이어지지 않은 경우
        for (int vertex : g) {
            if(!check[vertex]){
                return false;
            }
        }

        return true;
    }

    private static void print(){
        System.out.println("----------각 vertex의 크기-----------");
        for (int i = 0; i < N; i++) {
            System.out.print(population[i] + " ");
        }
        System.out.println();
        System.out.println("-------------연결 그래프-------------");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
