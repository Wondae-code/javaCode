import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 2와 6번째 값을 확인하면 될듯
// 자석이 회전할떄 자성이 같다 -> 안 움직임
// 자석이 회전할 때 자성이 다르다 -> 반대 방향으로 움직임

// 배열 회전하는 것 -> 시계 반시계


public class SWEA_4013 {
    static int[][] magnets;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            magnets = new int[4][8];
            int K = Integer.parseInt(br.readLine());
            for (int i = 0; i < 4; i++) {
                magnets[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            check = new boolean[3];
            for (int i = 0; i < K; i++) {
                String[] raw = br.readLine().split(" ");
                int idx = Integer.parseInt(raw[0]) - 1;
                int rDir = Integer.parseInt(raw[1]);
                operation(idx, rDir, new boolean[4]);
            }
            // 점수 계산 : 0 : N, 1 : S
            int sum = 0;
            int temp = 1;
            for (int i = 0; i < 4; i++) {
                if(magnets[i][0] == 1){
                    sum += temp;
                }
                temp <<= 1;
            }

            System.out.printf("#%d %d\n", test_case, sum);
        }
    }

    private static void operation(int idx, int rotateDir, boolean[] v) {
        //System.out.println(idx + " " + rotateDir);
        int beforeR = magnets[idx][2];
        int beforeL = magnets[idx][6];
        rotate(idx, rotateDir);
        int lIdx = idx-1;
        int rIdx = idx+1;
        v[idx] = true;
        if (lIdx >= 0 && !v[lIdx]){
            if(beforeL != magnets[lIdx][2]){
                operation(lIdx, rotateDir == 1 ? -1 : 1, v);
            }
        }
        if (rIdx < 4 && !v[rIdx]){
            if(beforeR != magnets[rIdx][6]){
                operation(rIdx, rotateDir == 1 ? -1 : 1, v);
            }
        }
    }

    static boolean[] check;

    private static void rotate(int idx, int rotateDir){
        // 시계 방향
        if(rotateDir == 1){
            int temp =  magnets[idx][7];
            for (int i = 7; i > 0; i--) {
                magnets[idx][i] = magnets[idx][i-1];
            }
            magnets[idx][0] = temp;
        }
        // 반 시계방향
        else{
            int temp = magnets[idx][0];
            for (int i = 0; i < 7; i++) {
                magnets[idx][i] = magnets[idx][i+1];
            }
            magnets[idx][7] = temp;
        }
    }
}
