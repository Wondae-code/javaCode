import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12865 {
    static Thing[] things;
    static int[][] dp;
    static int N, MaxWeight; // 최대 무게, 물건의 개수
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] raw = br.readLine().split(" ");
        N = Integer.parseInt(raw[0]);
        MaxWeight = Integer.parseInt(raw[1]);

        things = new Thing[N];
        for (int i = 0; i < N; i++) {
            raw = br.readLine().split(" ");
            int weight = Integer.parseInt(raw[0]);
            int value = Integer.parseInt(raw[1]);
            things[i] = new Thing(weight, value);
        }
        // DP에 사용할 배열
        dp = new int[N][MaxWeight];
        // 처음부터 r개까지 모든 물건을 삺펴봄
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < MaxWeight; c++) {

            }
        }

    }

    private static class Thing{
        int weight, value;

        public Thing(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
