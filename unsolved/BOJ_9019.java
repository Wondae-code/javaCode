import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 D: D 는 n을 두 배로 바꾼다. 결과 값이 9999 보다 큰 경우에는 10000 으로 나눈 나머지를 취한다. 그 결과 값(2n mod 10000)을 레지스터에 저장한다.
 S: S 는 n에서 1 을 뺀 결과 n-1을 레지스터에 저장한다. n이 0 이라면 9999 가 대신 레지스터에 저장된다.
 L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
 R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
 */

public class BOJ_9019 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            String[] inputs = br.readLine().split(" ");
            int A = Integer.parseInt(inputs[0]);
            int B = Integer.parseInt(inputs[1]);

            System.out.println(BFS(A, B));
        }

    }

    private static String BFS(int A, int B){
        Queue<Path> q = new LinkedList<>();
        q.offer(new Path(A, ""));
        boolean[] v = new boolean[10000];
        v[A] = true;

        while(true) {
            Path p = q.poll();
            if(p.val == B){
                return p.path;
            }
            if(D(p.val) < 10000 && !v[D(p.val)]) {
                q.offer(new Path(D(p.val), p.path + 'D'));
                v[D(p.val)] = true;
            }
            if(S(p.val) < 10000 && !v[S(p.val)]) {
                q.offer(new Path(S(p.val), p.path + 'S'));
                v[S(p.val)] = true;
            }
            if(L(p.val) < 10000 && !v[L(p.val)]) {
                q.offer(new Path(L(p.val), p.path + 'L'));
                v[L(p.val)] = true;
            }
            if(R(p.val) < 10000 && !v[R(p.val)]) {
                q.offer(new Path(R(p.val), p.path + 'R'));
                v[R(p.val)] = true;
            }
        }
    }

    private static class Path{
        int val;
        String path;

        public Path(int val, String path) {
            this.val = val;
            this.path = path;
        }
    }
    private static int D(int num){
        return (num * 2) % 10000;
    }

    private static int S(int num){
        return num - 1 == -1 ? 9999 : num - 1;
    }

    static int L(int num) {
        num = (num * 10) % 10000 + (num / 1000);
        return num;

    }

    static int R(int num) {
        num = (num / 10) + (num % 10) * 1000;
        return num;
    }
}
