package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_21610 {
    static int[][] map;
    static int[][] dir = {{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static ArrayList<int[]> cloud;
    static ArrayList<int[]> noCloud;
    static Movement[] movements;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        int size = Integer.parseInt(raw[0]);
        int move = Integer.parseInt(raw[1]);
        // 지도 기록
        map = new int[size + 1][size + 1];
        cloud = new ArrayList<>();
        cloud.add(new int[]{size, 1});
        cloud.add(new int[]{size, 2});
        cloud.add(new int[]{size-1, 1});
        cloud.add(new int[]{size-1, 2});
        noCloud = new ArrayList<>();
        for (int r = 1; r <= size; r++) {
            raw = br.readLine().split(" ");
            for (int c = 1; c <= size; c++) {
                map[r][c] = Integer.parseInt(raw[c - 1]);
            }
        }
        // 움직임 기록
        movements = new Movement[move];
        for (int i = 0; i < move; i++) {
            raw = br.readLine().split(" ");
            movements[i] = new Movement(dir[Integer.parseInt(raw[0])], Integer.parseInt(raw[1]));
        }

        // move번 사이클
        for (int i = 0; i < move; i++) {
            // 구름의 움직임
            for (int j = 0; j < cloud.size(); j++) {
                int nr = cloud.get(j)[0] + movements[i].d[0] * movements[i].dist;
                int nc = cloud.get(j)[1] + movements[i].d[1] * movements[i].dist;

                // 외곽으로 나갔을 경우 조절해줌
                while(nr < 1 || nr > size || nc < 1 || nc > size){
                    if(nr < 1 || nr > size) {
                        nr = nr > size ? nr - size : nr + size;
                    }
                    if(nc < 1 || nc > size) {
                        nc = nc > size ? nc - size : nc + size;
                    }
                }
                cloud.get(j)[0] = nr;
                cloud.get(j)[1] = nc;
            }
            // 비 내림
            for (int j = 0; j < cloud.size(); j++) {
                map[cloud.get(j)[0]][cloud.get(j)[1]]++;
                // 나중에 구름을 만들 수 없는 위치를 체크하기 위함.
                noCloud.add(new int[]{cloud.get(j)[0], cloud.get(j)[1]});
            }
            // 구름 사라짐
            cloud.clear();

            // 물복사 버그 -> 대각선의 물 양만큼 증가
            for (int[] p : noCloud) {
                int cnt = 0;
                for (int d = 1; d <= 4; d++) {
                    int nr = p[0] + dir[d*2][0];
                    int nc = p[1] + dir[d*2][1];
                    // 범위 체크
                    if(nr >= 1 && nr <= size && nc >= 1 && nc <= size && map[nr][nc] != 0){
                        cnt++;
                    }
                }
                map[p[0]][p[1]] += cnt;
            }

            // 새 구름 생성
            for (int r = 1; r <= size; r++) {
                for (int c = 1; c <= size; c++) {
                    // 물이 2 이상인 칸에서 구름 생성 후 물 2 감소, 원래 구름이 있던 자리면 안됨.
                    if(map[r][c] >= 2 && check(new int[]{r, c}, noCloud)){
                        cloud.add(new int[]{r, c});
                        map[r][c] -= 2;
                    }
                }
            }
            noCloud.clear();
        }

        // 물의 합 구하기
        int Ans = 0;
        for (int r = 1; r <= size; r++) {
            for (int c = 1; c <= size; c++) {
                Ans += map[r][c];
            }
        }
        System.out.println(Ans);
    }

    private static class Movement {
        int[] d;
        int dist;

        public Movement(int[] d, int dist) {
            this.d = d;
            this.dist = dist;
        }
    }

    private static boolean check(int[] p, ArrayList<int[]> noCloud){
        for (int[] arr : noCloud) {
            if(arr[0] == p[0] && arr[1] == p[1]){
                return false;
            }
        }

        return true;
    }
}
