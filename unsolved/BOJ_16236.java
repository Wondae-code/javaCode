import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16236 {
    static int[][] map;
    static ArrayList<Fish> fish;
    static Fish shark;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        fish = new ArrayList<>();

        // 값 집어 넣기
        for (int r = 0; r < N; r++) {
            String[] inputs = br.readLine().split(" ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(inputs[c]);
                if(map[r][c] == 9){
                    shark = new Fish(r, c, 2);
                    map[r][c] = 0;
                }else {
                    if (map[r][c] != 0) {
                        fish.add(new Fish(r, c, map[r][c]));
                    }
                }
            }
        }

        boolean flag = true;

        int total_dist = 0;
        while(flag) {
            flag = false;
            scan();

            for (int i = fish.size() - 1; i >= 0; i--) {
                Fish f = fish.get(i);

                if (shark.size > f.size) {
                    if(f.dist == Integer.MAX_VALUE) continue;
                    total_dist += f.dist;
                    shark.r = f.r;
                    shark.c = f.c;
                    //System.out.println(f.toString());
                    map[f.r][f.c] = 0;
                    fish.remove(f);
                    shark.cnt++;
                    if (shark.size == shark.cnt) {
                        //System.out.println("Level up!");
                        shark.size++;
                        shark.cnt = 0;
                    }
                    flag = true;
                    //print();
                    break;
                }
            }
        }
        System.out.println(total_dist);
    }

    private static void print(){
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map.length; c++) {
                if(r == shark.r && c == shark.c){
                    System.out.print("S" + " ");
                }else {
                    System.out.print(map[r][c] + " ");
                }
            }
            System.out.println();
        }
    }

    private static void scan(){
        // 거리 업데이트
        for(Fish f : fish){
            f.dist = isMovable(f);
        }

        // 거리 별로 정렬 -> 위 왼쪽 고려하기
        fish.sort(new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                if(o2.dist == o1.dist){
                    if(o2.r == o1.r){
                        return o2.c - o1.c;
                    }
                    return o2.r - o1.r;
                }
                return o2.dist - o1.dist;
            }
        });
    }

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // BFS를 통한 탐색
    private static int isMovable(Fish target){
        // 갈 수 있는지 체크할 임시 물고기
        Fish temp = new Fish(shark.r, shark.c, shark.size);

        Queue<Fish> q = new LinkedList<>();
        int[][] v = new int[map.length][map.length];
        v[temp.r][temp.c] = 1;
        q.offer(temp);

        while(!q.isEmpty()){
            Fish p = q.poll();
            if(p.r == target.r && p.c == target.c){
                return v[p.r][p.c] -1;
            }

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dir[d][0];
                int nc = p.c + dir[d][1];

                if(nr >= 0 && nr < map.length && nc >= 0 && nc < map.length && v[nr][nc] == 0 && (map[nr][nc] == 0 || p.size >= map[nr][nc])){
                    v[nr][nc] = v[p.r][p.c] + 1;
                    q.offer(new Fish(nr, nc, p.size));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static class Fish{
        int r, c;
        int size;
        int cnt = 0;
        int dist = 0;

        public Fish(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "r=" + r +
                    ", c=" + c +
                    ", size=" + size +
                    ", cnt=" + cnt +
                    ", dist=" + dist +
                    '}';
        }
    }
}
