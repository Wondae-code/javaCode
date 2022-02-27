import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// 1 : 아래로 이동, 2 : 위로 이동
// 빨강-파랑이 붙으면 교착

public class SWEA_1220 {
    static int[][] map;
    static int size;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./inputs/SWEA_1220.txt"));
        Scanner sc = new Scanner(System.in);
        int test_case = 1;
        while(sc.hasNext()){
            size = sc.nextInt();
            map = new int[size][size];
            // 값 넣기
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    map[r][c] = sc.nextInt();
                }
            }

            //print();
            // 탐색
            boolean open = false;
            int cnt = 0;
            for (int c = 0; c < size; c++) {
                for (int r = 0; r < size; r++) {
                    if(map[r][c] == 1){
                        open = true;
                    }
                    if(open && map[r][c] == 2){
                        cnt++;
                        open = false;
                    }
                }
                open = false;
            }

            System.out.printf("#%d %d\n", test_case, cnt);
            test_case++;
        }
    }

    private static void print(){
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
    }
}
