package SSAFY;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_1225 {
    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("Encrypt.txt"));
        Scanner sc = new Scanner(System.in);
        Queue<Integer> q = new LinkedList<Integer>();

        while (sc.hasNext()){
            int test_case = sc.nextInt();
            sc.nextLine();
            String[] raw = sc.nextLine().split(" ");
            for (int i = 0; i < 8; i++) {
                q.offer(Integer.parseInt(raw[i]));
            }

            encrypt :while(true){
                for (int i = 1; i <= 5; i++) {
                    int temp = q.poll();
                    if(temp - i <= 0){
                        q.offer(0);
                        break encrypt;
                    }
                    q.offer(temp - i);

                }
            }

            System.out.print("#"+test_case);
            for(int i = 0; i < 8; i++){
                System.out.print(" " + q.poll());
            }
            System.out.println();
        }
    }

}
