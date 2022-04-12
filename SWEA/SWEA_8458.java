import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SWEA_8458 {

    static int N;
    static ArrayList<Integer> Ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            Ans = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String[] raw = br.readLine().split(" ");
                Ans.add(Math.abs(Integer.parseInt(raw[0])) + Math.abs(Integer.parseInt(raw[1])));
            }
            System.out.printf("#%d %d\n", test_case, cal());
        }

    }

    private static int cal(){
        int result = 0;
        int val = Ans.get(0)%2;
        for (int i : Ans) {
            if(val != i%2) return -1;
            int temp = 0;
            while(i > 0 || i%2 != 0){
                temp++;
                i -= temp;
            }

            result = Math.max(result, temp);
        }
        return result;
    }

}
