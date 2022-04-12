import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_1708 {
    static Point[] points;
    static Point minP;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];
        for (int i = 0; i < N; i++) {
            String[] raw = br.readLine().split(" ");
            points[i] = new Point(Integer.parseInt(raw[0]), Integer.parseInt(raw[1]));
        }

        // 선택할 점 중 가장 Y와 X가 작은 점을 구한다.
        minP = getMin();

        // 반시계방향으로 정렬한다.
        Arrays.sort(points);
        System.out.println(Arrays.toString(points));


        // graham scan을 통해 값을 구한다.
        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);
        for (int i = 1; i < N; i++) {
            Point pop = stack.pop();

        }

        System.out.println(stack.size());
    }

    private static Point getMin(){
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if(minY > points[i].y){
                minY = points[i].y;
                minX = points[i].x;
            }else if(minY == points[i].y){
                if(minX > points[i].x){
                    minX = points[i].x;
                }
            }
        }

        return new Point(minX, minY);
    }


    private static int CCW(Point a, Point b, Point c){
        // 외적을 통한 계산
        long val = (long) (b.x - a.x)*(c.y - a.y) - (long) (c.x-a.x) *(b.y-a.y);

        if(val > 0) return 1;
        else if (val < 0) return -1;
        else return 0;
    }


    private static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            // 각도 비교후 거리
            if(CCW(minP, this, o) == 0){
                return this.x - o.x;
            }

            return this.y - o.y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
