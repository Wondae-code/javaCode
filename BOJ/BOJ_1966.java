package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
 * 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다. 그렇지 않다면 바로 인쇄를 한다.
 *
 * Queue에 4개의 문서(A B C D)가 있고, 중요도가 2 1 4 3 라면 C를 인쇄하고, 다음으로 D를 인쇄하고 A, B를 인쇄하게 된다.
 */

public class BOJ_1966 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] raw;
        Queue<Set> q;

        for (int tc = 0; tc < T; tc++) {
            // 변수 선언 & 입력 받기
            raw = br.readLine().split(" ");
            int N = Integer.parseInt(raw[0]);
            int targetIdx = Integer.parseInt(raw[1]);
            q = new LinkedList<>();
            raw = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                Set s = new Set(i, Integer.parseInt(raw[i]));
                q.offer(s);
            }


            
            // 계속 빼기 (1) 가장 단순한 로직
            int num = -1;
            int cnt = 0;
            while (num != targetIdx){
                // 맨 앞부터 비교한다.
                int head = q.peek().val;
                // 맨 뒤까지 순차 탐색
                if(isBiggerInteger(q, head)){
                    // 만약 더 큰 수가 있다면 맨 뒤로 보내기
                    q.offer(q.poll());
                    continue;
                }
                // 없다면 pop
                num = q.poll().order;
                cnt++;
            }

            System.out.println(cnt);
        }
    }

    private static boolean isBiggerInteger(Queue<Set> q, Integer num){
        Set[] tempArr = q.toArray(new Set[0]);
        for(Set o : tempArr){
            if(num < o.val){
                return true;
            }
        }

        return false;
    }

    private static class Set{
        int order;
        int val;

        public Set(int order, int val) {
            this.order = order;
            this.val = val;
        }
    }
}
