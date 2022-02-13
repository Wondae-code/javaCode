package SSAFY;

// BFS로 풀어보기. 사실 문제 아래에 있는 댓글보고 어떻게 풀지 알았습니다.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1861 {
    static int[][] dir = {{-1,0}, {0, 1}, {1, 0}, {0, -1}}; // 북동남서 순서
    static int[][] map;
    static boolean[][] visited;
    static int maxLen = 0;
    static int maxVal;
    static int size = 0;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("SquareRoom.txt")); // 디버깅용
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // 테스트 케이스 수
        for (int test_case = 1; test_case <= T; test_case++) {
            size = sc.nextInt(); // 크기
            map = new int[size][size];
            maxVal = 0;
            maxLen = 0;
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    map[r][c] = sc.nextInt();
                }
            }
            //show(map, N);

            // 탐색하기
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    // BFS로 최대값 찾아보기
                    int tempLen = bfs(r,c);
                    if(maxLen < tempLen){
                        maxLen = tempLen;
                        maxVal = map[r][c];
                    }else if(maxLen == tempLen){
                        maxVal = Math.min(maxVal, map[r][c]);
                    }
                }
            }
            System.out.printf("#%d %d %d\n", test_case, maxVal, maxLen);
        }
    }

    static void show(int[][] map, int size){
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print(map[r][c] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int bfs(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r,c});
        int tempLen = 1; // 임시 길이
        while(!q.isEmpty()){
            int[] temp = q.poll();
            //System.out.println(Arrays.toString(temp));
            for (int d = 0; d < 4; d++) {
                int nr = temp[0] + dir[d][0];
                int nc = temp[1] + dir[d][1];
                if(nr >= 0 && nr < size && nc >= 0 && nc <size){
                    if(map[nr][nc] - map[temp[0]][temp[1]] == 1){
                        q.offer(new int[]{nr,nc});
                        tempLen++;
                    }
                }
            }
        }
        return tempLen;
    }
}
