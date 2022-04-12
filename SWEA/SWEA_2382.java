import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SWEA_2382 {
    static int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static ArrayList<Point> list;
    static int N, M, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String[] raw = br.readLine().split(" ");
            N = Integer.parseInt(raw[0]); // map의 크기
            M = Integer.parseInt(raw[1]); // 시간
            K = Integer.parseInt(raw[2]); // 군집의 수
            list = new ArrayList<>();
            // 값 넣기
            for (int i = 0; i < K; i++) {
                raw = br.readLine().split(" ");
                int r = Integer.parseInt(raw[0]);
                int c = Integer.parseInt(raw[1]);
                int num = Integer.parseInt(raw[2]);
                int d = Integer.parseInt(raw[3]);
                list.add(new Point(r,c,num,d));
            }

            // 움직이기
            for (int t = 0; t < M; t++) {
                for (Point point : list) {
                    int nr = point.r + dir[point.d][0];
                    int nc = point.c + dir[point.d][1];
                    // 모서리에 갔을 때
                    if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
                        point.num /= 2;
                        point.d = point.d % 2 == 1 ? point.d + 1 : point.d - 1;
                    }
                    point.r = nr;
                    point.c = nc;
                }

                //겹침 처리하기
                for (int i = 0; i < list.size(); i++) {
                    ArrayList<Point> same = getSame(i);
                    // 겹치는 것이 있을 때
                    if(same.size() > 1) {
                        // d는 가장 큰 값의 방향, num은 총합
                        int sum = 0;
                        int tempNum = same.get(0).num, tempIdx = 0;
                        for (Point p : same) {
                            if (p.num > tempNum) {
                                tempNum = p.num;
                                tempIdx = same.indexOf(p);
                            }
                            sum += p.num;
                        }

                        list.get(i).d = same.get(tempIdx).d;
                        list.get(i).num = sum;

                        // 나머지 값 다 지우기
                        for (int j = 1; j < same.size(); j++) {
                            list.remove(same.get(j));
                        }
                    }
                }
            }

            // 총 개수 구하기
            int Ans = 0;
            for (Point p : list) {
                Ans += p.num;
            }
            System.out.printf("#%d %d\n", test_case, Ans);
        }
    }

    private static ArrayList<Point> getSame(int idx) {
        ArrayList<Point> temp = new ArrayList<>();
        for (Point p : list) {
            if(p.r == list.get(idx).r && p.c == list.get(idx).c){
                temp.add(p);
            }
        }
        return temp;
    }

    private static class Point {
        int r, c, num, d;

        public Point(int r, int c, int num, int d) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.d = d;
        }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        public int getNum() {
            return num;
        }

        public int getD() {
            return d;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", num=" + num +
                    ", d=" + d +
                    '}';
        }
    }
}
