package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_17143 {
    // 오른쪽 한칸 이동 -> 땅에 가장 가까운 상어 잡음 -> 상어 이동
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int R, C, M;
    static ArrayList<Shark> sharks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        R = Integer.parseInt(raw[0]);
        C = Integer.parseInt(raw[1]);
        M = Integer.parseInt(raw[2]);
        sharks = new ArrayList<>();
        //  상어 값 넣기
        for (int i = 0; i < M; i++) {
            raw = br.readLine().split(" ");
            int r = Integer.parseInt(raw[0]);
            int c = Integer.parseInt(raw[1]);
            int s = Integer.parseInt(raw[2]);
            int d = Integer.parseInt(raw[3]);
            int z = Integer.parseInt(raw[4]);
            sharks.add(new Shark(r, c, s, d - 1, z));
        }

        long Ans = 0;
        for (int t = 1; t <= C; t++) {
            // 상어 잡기
            //System.out.println("-------------------");
            int minIdx = 101;
            Shark min = null;
            for (Shark s : sharks) {
                if (s.c == t) {
                    if (s.r < minIdx) {
                        minIdx = s.r;
                        min = s;
                    }
                }
            }
            // 상어가 있다면 잡음
            if (min != null) {
                Ans += min.size;
                sharks.remove(min);
            }

            // 상어 이동
            for (Shark s : sharks) {
                for (int m = 0; m < s.speed; m++) {
                    int nr = s.r + dir[s.d][0];
                    int nc = s.c + dir[s.d][1];
                    // 모서리 체크
                    if (nc == 1 && s.d == 3) {
                        s.d = 2;
                    } else if (nc == C && s.d == 2) {
                        s.d = 3;
                    } else if (nr == 1 && s.d == 0) {
                        s.d = 1;
                    } else if (nr == R && s.d == 1) {
                        s.d = 0;
                    }
                    s.r = nr;
                    s.c = nc;
                }
                //System.out.println(s);
            }

            // 겹치는 상어 처리
            for (int i = 0; i < sharks.size(); i++) {
                ArrayList<Shark> same = getSame(i);
                if(same.size() > 1) {
                    Shark max = Collections.max(same, Comparator.comparingInt(Shark::getSize));


                    for (int j = 1; j < same.size(); j++) {
                        sharks.remove(same.get(j));
                    }
                    sharks.get(i).size = max.size;
                    sharks.get(i).d = max.d;
                    sharks.get(i).speed = max.speed;
                }
                same.clear();
            }
        }
        System.out.println(Ans);
    }

    // 같은 위치의 상어 체크
    private static ArrayList<Shark> getSame(int i) {
        int r = sharks.get(i).r;
        int c = sharks.get(i).c;
        ArrayList<Shark> temp = new ArrayList<>();

        for (Shark s : sharks) {
            if (s.r == r && s.c == c) {
                temp.add(s);
            }
        }

        return temp;
    }


    private static class Shark {
        int r, c, speed, d, size;

        public Shark(int r, int c, int speed, int d, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.d = d;
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", speed=" + speed +
                    ", d=" + d +
                    ", size=" + size +
                    '}';
        }
    }
}
