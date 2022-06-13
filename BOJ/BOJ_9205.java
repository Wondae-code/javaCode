package SSAFY;// 맥주 마시면서 걸어가기
// 50미터를 가기 위해선 맥주 한병
// 중간에 편의점에서 빈 병 -> 새 병 교체 가능


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BOJ_9205 {
    static boolean[][] map;
    static final int MAX_DIST = 1000000;
    static int convN;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            // 위치의 수
            convN = Integer.parseInt(br.readLine());
            Place[] convs = new Place[convN+2];
            map = new boolean[convN+2][convN+2];
            for (int i = 0; i < convN+2; i++) {
                String[] raw = br.readLine().split(" ");
                convs[i] = new Place(Integer.parseInt(raw[0]), Integer.parseInt(raw[1]));
            }

            for(int r=0; r<convN+1; r++) {
                for(int c=r+1; c<convN+2; c++) {
                    if(getDist(convs[r], convs[c]) <= 1000) {
                        map[r][c] = true;
                        map[c][r] = true;
                    }
                }
            }

            for (int k = 0; k < convN + 2; k++) {
                for (int r = 0; r < convN + 2; r++) {
                    for (int c = 0; c < convN + 2; c++) {
                        if(r == c) continue;
                        if(map[r][k] && map[k][c]){
                            map[r][c] = true;
                        }
                    }
                }
            }

            System.out.println(map[0][convN+1] ? "happy" : "sad");
        }
    }


    private static int getDist(Place p1, Place p2){
        return Math.abs(p1.r - p2.r) + Math.abs(p1.c - p2.c);
    }


    private static class Place {
        int r, c;

        public Place(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Place{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
}

