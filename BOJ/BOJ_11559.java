package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 같은색이 4개 연결되어 있으면 지워짐
//

public class BOJ_11559 {
    static char[][] map = new char[12][6];
    static int[][] dir = {{-1, 0},{0, 1},{1, 0},{0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력받기
        for (int r = 0; r < 12; r++) {
            String raw = br.readLine();
            for (int c = 0; c < 6; c++) {
                map[r][c] = raw.charAt(c);
            }
        }

        // 값 확인
        //print();

        // 값을 읽으며 연쇄가 존재하는지 확인
        int Ans = 0;
        boolean flag = false;
        while(true) {
            for (int r = 0; r < 12; r++) {
                for (int c = 0; c < 6; c++) {
                    if (map[r][c] != '.') {
                        int val = BFS(r, c);
                        if(val >= 4) flag = true;
                    }
                }
            }
            if (flag) {
                Ans++;
            }

            // 전체 내리기 -> 아래부터 스캔
            for (int r = 12 - 1; r >= 0; r--) {
                for (int c = 0; c < 6; c++) {
                    // 만약 현재 위치가 .이면 위로 올라가서 블록이 있는지 찾는다.
                    if (map[r][c] == '.') {
                        for (int k = r - 1; k >= 0; k--) {
                            if (map[k][c] != '.') {
                                map[r][c] = map[k][c];
                                map[k][c] = '.';
                                break;
                            }
                        }
                    }
                }
            }

            if(!flag){
                break;
            }else{
                flag = false;
            }
            //print();
        }
        System.out.println(Ans);
    }

    private static int BFS(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c, map[r][c]));
        boolean[][] v = new boolean[12][6];
        v[r][c] = true;
        // 4개 이상만 지우기 위해 리스트 하나 만듬
        ArrayList<Point> puyo = new ArrayList<>();
        puyo.add(q.peek());

        while(!q.isEmpty()){
            Point p = q.poll();
            char val = p.val;
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];
                if(nr >= 0 & nr < 12 && nc >= 0 && nc < 6 && !v[nr][nc] && map[nr][nc] == val){
                    q.offer(new Point(nr, nc, map[nr][nc]));
                    puyo.add(new Point(nr, nc, map[nr][nc]));
                    v[nr][nc] = true;
                }
            }
        }

        if(puyo.size() >= 4){
            for (Point p: puyo) {
                map[p.r][p.c] = '.';
            }
        }

        return puyo.size();
    }

    private static class Point{
        int r, c;
        char val;

        public Point(int r, int c, char val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    private static void print(){
        for (int r = 0; r < 12; r++) {
            for (int c = 0; c < 6; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}
