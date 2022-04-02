package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class BOJ_1697 {
    static int[] map = new int[200000];
    static boolean[] check = new boolean[200000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int src = Integer.parseInt(temp[0]);
        int dst = Integer.parseInt(temp[1]);

        BFS(src, dst);
    }

    private static void BFS(int src, int dst){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(src, 0));


        while(!q.isEmpty()){
            Point p = q.poll();

            if(p.r == dst){
                System.out.println(p.dist);
                break;
            }

            int nr = p.r*2;
            if(nr < map.length && nr >= 0 && !check[nr]){
                check[nr] = true;
                q.offer(new Point(nr, p.dist+1));
            }
            nr = p.r-1;
            if(nr < map.length && nr >= 0 && !check[nr]){
                check[nr] = true;
                q.offer(new Point(nr, p.dist+1));
            }
            nr = p.r+1;
            if(nr < map.length && nr >= 0 && !check[nr]){
                check[nr] = true;
                q.offer(new Point(nr, p.dist+1));
            }
        }
    }


    private static class Point{
        int r, dist;

        public Point(int r, int dist) {
            this.r = r;
            this.dist = dist;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", dist=" + dist +
                    '}';
        }
    }
}
