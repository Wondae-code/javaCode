// 각 CCTV별 방향 경우의 수
// 1 : 4 (상, 하, 좌, 우)
// 2 : 2 (상하, 좌우)
// 3 : 4 (상우, 우하, 하좌, 좌상)
// 4 : 4 (좌상우, 상우하, 우하좌, 하좌상)
// 5 : 1 (상하좌우)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ_15683 {
    static int[][] map;
    static ArrayList<Camera> cameras = new ArrayList<>();
    static int R, C, cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new int[R][C];

        // 값 넣기기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] > 0 && map[i][j] < 6){
                    cameras.add(new Camera(i,j,map[i][j]));
                }
            }
        }

        recursive(0, map);

        System.out.println(cnt);
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] camType = {{},{1},{1, 3}, {0,1}, {0, 1, 3}, {0, 1, 2, 3}};

    private static void recursive(int idx, int[][] map){
        // base part
        if(idx == cameras.size()){
            // 사각지대 체크
            print();
            return;
        }

        // logic part
        Camera cam = cameras.get(idx);
        // inductive part
        // 4번 회전
        for (int d = 0; d < 4; d++) {
            // 복사
            int[][] tmpMap = mapCopy(map);
            // 카메라의 종류에 따라 모든 방향을 감시
            for (int i = 0; i < camType[cam.t].length; i++) {
                int nr = cam.r;
                int nc = cam.c;
                int nd = (camType[cam.t][i] + d) % 4;
                // 벽이 나올때까지 탐색
                while(true){
                    nr += dir[nd][0];
                    nc += dir[nd][1];
                    // 지도 안에 있을 때
                    if(check(nr, nc) || tmpMap[nr][nc] != 6){
                        break;
                    }
                    tmpMap[nr][nc] = 9;
                }
            }
            recursive(idx+1, tmpMap);
        }
    }
    private static boolean check(int r, int c){
        if(r >= 0 && r < R && c >= 0 && c < C){
            return true;
        }
        return false;
    }


    private static int[][] mapCopy(int[][] map) {
        int[][] temp = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                temp[i][j] = map[i][j];
            }
        }

        return temp;
    }

    static class Camera{
        int r, c, t;
        Camera(int r, int c, int t){
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    private static void print(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
