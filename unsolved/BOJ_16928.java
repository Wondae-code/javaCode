import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16928 {
    static HashMap<Integer,Integer> ladder, snake;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        ladder = new HashMap<>();
        snake = new HashMap<>();
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            ladder.put(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            snake.put(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }

        BFS();
    }

    private static void BFS(){
        Queue<Point> q = new LinkedList<>();
        int[] v = new int[101];
        Arrays.fill(v, Integer.MAX_VALUE);
        q.offer(new Point(1, 0));

        while(!q.isEmpty()){
            Point p = q.poll();
            if(p.val > 100){
                continue;
            }
            if(p.val == 100){
                System.out.println(p.state);
                return;
            }
            for (int i = 1; i <= 6 ; i++) {
                int nv = p.val + i;
                if(snake.containsKey(nv)){
                    nv = snake.get(nv);
                }else if(ladder.containsKey(nv)){
                    nv = ladder.get(nv);
                }
                if(nv <= 100 && v[nv] > p.state) {
                    q.add(new Point(nv, p.state + 1));
                    v[nv] = p.state + 1;
                }
            }
        }
    }

    private static class Point{
        int val, state;

        public Point(int val, int state) {
            this.val = val;
            this.state = state;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "val=" + val +
                    ", state=" + state +
                    '}';
        }
    }
}
