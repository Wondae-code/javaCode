import java.util.Scanner;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWQmA4uK8ygDFAXj
public class SWEA_4615 {
    static int[][] initialPoint = {{-1, -1}, {-1, 0}, {0, 0}, {0, -1}};
    static int[][] EaD = {{-1,-1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();


        for (int test_case = 1; test_case <= T; test_case++) {
            // initial setting
            int size = sc.nextInt();
            int t = sc.nextInt();
            int[][] board = new int[size][size];

            int b = 0, w = 0;

            for (int i = 0; i < 4; i++) {
                board[size/2 + initialPoint[i][0]][size/2 + initialPoint[i][1]] = (i%2 == 0) ? 2 : 1;
            }

            // 수 놓기
            for (int i = 0; i < t; i++) {
                int r = sc.nextInt() - 1;
                int c = sc.nextInt() - 1;
                int color = sc.nextInt(); // 2: white, 1: black

                board[r][c] = color;
                int otherColor = (int)(1.0/color * 2); // 1->2, 2->1
                // 팔방 탐색을 통한 바꾸는 범위 체크
                for (int d = 0; d < 8; d++) {
                    int k = 1;
                    while(check(r + EaD[d][0] * k, c + EaD[d][1] * k, size) && board[r + EaD[d][0] * k][c + EaD[d][1] * k] == otherColor){
                        k++;
                    }
                    if(check(r + EaD[d][0] * k, c + EaD[d][1] * k, size) && board[r + EaD[d][0] * k][c + EaD[d][1] * k] == color){
                        for (int j = 0; j < k; j++) {
                            board[r + EaD[d][0] * j][c + EaD[d][1] * j] = color;
                        }
                    }
                }

                //Show(board);
                //System.out.println();
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(board[i][j] == 2) w++;
                    else if(board[i][j] == 1) b++;
                }
            }
            System.out.printf("#%d %d %d\n", test_case, b, w);
        }
    }
    static void Show(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean check(int r, int c, int size){ // 모서리 체크
        if(r >= 0 && r < size && c >= 0 && c < size) {
            return true;
        }
        return false;
    }
}