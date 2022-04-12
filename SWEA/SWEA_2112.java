import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_2112 {
    // 초기에 통과하나 검사
    // 안된다면 최소 횟수로 약품을 넣어서 통과해야한다.
    // 부분집합?
    static int D, W, K, min;
    static int[][] map;
    static int[] drugA, drugB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] raw = br.readLine().split(" ");
            D = Integer.parseInt(raw[0]);
            W = Integer.parseInt(raw[1]);
            K = Integer.parseInt(raw[2]);
            map = new int[D][W];
            drugA = new int[W];
            drugB = new int[W];
            min = Integer.MAX_VALUE;
            for (int r = 0; r < D; r++) {
                raw = br.readLine().split(" ");
                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(raw[c]);
                }
            }

            Arrays.fill(drugA, 0);
            Arrays.fill(drugB, 1);

            process(0, 0);

            System.out.printf("#%d %d\n", test_case, min);
        }
    }
    // 보호필름 성능 검사
    private static boolean check(){
        // 열 고정 행 탐색 연속된 셀의 같은 특성이 K개 이상인지 검사
        for (int c = 0; c < W; c++) { // 열 고정
            int cnt = 1;
            int before = map[0][c];
            for (int r = 1; r < D; r++) {
                int current = map[r][c];
                if(before == current){
                    if(++cnt == K) break;
                }else{
                    before = current;
                    cnt = 1;
                }
            }// 열 고정 후 수직 검사
            if(cnt < K) return false;
        }
        return true;

    }

    // 약품 투여 상황
    private static boolean process(int row, int useCnt){
        if(row == D){
            if(check()){
                min = Math.min(min, useCnt);
                return min==0; // 약품을 하나도 쓰지 않으면 true, 사용했으면 false
            }
            return false;
        }

        if(useCnt >= min){
            return false;
        }

        // 현재 막의 상태 기억억
        int[] backup = map[row];
        // 약품 A 투여
        if(process(row+1, useCnt)) return true;
        // 약품 B 투여
        map[row] = drugA;
        process(row+1, useCnt+1);
        // 약품 미투여
        map[row] = drugB;
        process(row+1, useCnt+1);

        map[row] = backup;
        return false;
    }
}
