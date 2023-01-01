package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
산술평균 : N개의 수들의 합을 N으로 나눈 값
중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
최빈값 : N개의 수들 중 가장 많이 나타나는 값
범위 : N개의 수들 중 최댓값과 최솟값의 차이
 */

public class BOJ_2108 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        int max = -5000;
        int min = 5000;
        int sum = 0;
        // 최빈값
        ArrayList<Node> freq = new ArrayList<>();


        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            // 산술 평균을 위한 값 더하기
            sum += arr[i];
            // 최대값, 최소값을 위한 값 비교
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
            // 빈도수 체크
            boolean isIn = false;
            for (Node n: freq) {
                if(n.val == arr[i]){
                    n.cnt++;
                    isIn = true;
                    break;
                }
            }
            if(!isIn){
                freq.add(new Node(arr[i], 1));
            }
        }

        // 산술 평균
        System.out.println(Math.round((double) sum / size));
        // 중간값
        Arrays.sort(arr);
        System.out.println(arr[size/2]);
        //최빈값
        Collections.sort(freq);
        // 같은 값 빼네기
        Node n = freq.get(0);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Node nn: freq) {
            if(n.cnt == nn.cnt){
                pq.add(nn.val);
            }
        }

        if(pq.size() >= 2){
            pq.poll();
        }
        System.out.println(pq.poll());


        //범위
        System.out.println(max - min);

    }

    private static class Node implements Comparable<Node>{
        int val;
        int cnt;

        public Node(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return o.cnt - this.cnt;
        }
    }
}
