import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class BOJ_7662 {
    static String[] inputs;
    static TreeMap<Integer, Integer> map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());
            map = new TreeMap<>();
            for (int i = 0; i < K; i++) {
                inputs = br.readLine().split(" ");
                char flag = inputs[0].charAt(0);
                int val = Integer.parseInt(inputs[1]);

                if(flag == 'I'){
                    map.put(val, map.getOrDefault(val, 0) + 1);
                }else{
                    if(map.size() == 0) continue;

                    int num = val == 1 ? map.lastKey() : map.firstKey();

                    if(map.put(num, map.get(num) - 1) == 1) map.remove(num);
                }
            }
            System.out.println(map.size() == 0 ? "EMPTY" : map.lastKey() + " " + map.firstKey());
        }
    }
}
