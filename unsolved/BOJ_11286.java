import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_11286 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if(Math.abs(o1) == Math.abs(o2)){
                return o1 - o2;
            }
            return Math.abs(o1) - Math.abs(o2);
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0){
                if(pq.size() == 0){
                    sb.append(0).append('\n');
                }else {
                    sb.append(pq.poll()).append('\n');
                }
            }else{
                pq.add(num);
            }
        }

        System.out.println(sb);
    }
}
