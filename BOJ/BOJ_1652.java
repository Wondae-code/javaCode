import java.util.Scanner;

public class BOJ_1652 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[][] map = new char[N][N];
        // 값 입력
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        show(map);
        // 가로 탐색 -> ..이 연속으로 2개가 나오면 X가 나오거나 모서리에 닿을때까지 패스한다.
        int horCnt = 0;
        boolean check = false;
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                // 뒤에 것이랑 비교하기 때문에 1부터 시작
                if(!check) {
                    if (map[i][j] == '.' & map[i][j] == map[i][j - 1]) {
                        check = true;
                        horCnt++;
                    }
                }
                if(map[i][j] == 'X'){
                    check = false;
                }
            }
            check = false;
        }
        // 세로 탐색
        int verCnt = 0;
        check = false;
        for (int j = 0; j < N; j++) {
            for (int i = 1; i < N; i++) {
                // 뒤에 것이랑 비교하기 때문에 1부터 시작
                if(!check) {
                    if (map[i][j] == '.' & map[i][j] == map[i-1][j]) {
                        check = true;
                        verCnt++;
                    }
                }
                if(map[i][j] == 'X'){
                    check = false;
                }
            }
            check = false;
        }
        System.out.println(horCnt + " " + verCnt);
    }

    static void show(char[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
