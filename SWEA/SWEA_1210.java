package SSAFY;


// 아래부터 올라가는 방법
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_1210 {
    static int[][] map;
    static int[] dir = {1, -1}; // 오른쪽 왼쪽 순서
    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("ladder.txt"));
        Scanner sc = new Scanner(System.in);
        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            sc.nextInt(); // 테스트케이스 값 버리기.
            // 값 넣기
            map = new int[100][100];
            for (int r = 0; r < 100; r++) {
                for (int c = 0; c < 100; c++) {
                    map[r][c] = sc.nextInt();
                }
            }
            // 아래에서 부터 시작해서 시작 좌표 찾기
            int startR = 99;
            int startC = 0;
            for (int i = 0; i < map.length; i++) {
                if(map[99][i] == 2){
                    startC = i;
                    break;
                }
            }
            //시작 좌표부터 탐색하기
            int direction = 0; // -1 : 좌우 방향 없음, 0 : 오른쪽, 1 : 왼쪽
            outer : while(startR > 0){
                // 좌우 탐색
                if(direction == -1){ // 좌우 방향이 없으면 양쪽을 체크
                    for (int d = 0; d < 2; d++) {
                        int nc = startC + dir[d];
                        if(nc >= 0 && nc < 100 && map[startR][nc] == 1){
                            direction = d;
                            continue outer;
                        }
                    }
                }else { // 좌우 방향이 있다 -> 값 더해주기
                    int nc = startC + dir[direction];
                    if(nc >= 0 && nc < 100 && map[startR][nc] == 1){
                        startC = nc;
                        continue outer;
                    }else{
                        direction = -1;
                    }
                }
                // 좌우로 움직이지 않는다면 위로 움직임
                startR--;
            }

            // 찾은 출발점의 x 좌표 출력
            System.out.printf("#%d %d\n", test_case, startC);

        }
   }
}
