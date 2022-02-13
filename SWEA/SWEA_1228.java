package SSAFY;


import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_1228 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = 1;
        while(sc.hasNext()){
            // 값 저장
            int passNum = sc.nextInt();
            sc.nextLine();
            String[] passwordRaw = sc.nextLine().split(" ");
            ArrayList<Integer> password = new ArrayList<>();
            for (int i = 0; i < passNum; i++) {
                password.add(Integer.parseInt(passwordRaw[i]));
            }
            int comNum = sc.nextInt();
            sc.nextLine();
            String[] commands = sc.nextLine().split("I");

            // 명령어 해석 및 실행
            for (int i = 1; i < comNum+1; i++) {
                // 명령어 해석
                String[] temp = commands[i].split(" ");
                ArrayList<Integer> command = new ArrayList<>();
                for (int j = 1; j < temp.length; j++) {
                    command.add(Integer.parseInt(temp[j]));
                }

                // 명령어 실행
                password.addAll(command.get(0), command.subList(2, command.size()));
            }

            // 출력
            System.out.print("#" + cnt);
            for (int i = 0; i < 10; i++) {
                System.out.print(" " + password.get(i));
            }
            System.out.println();
            cnt++;
        }
    }
}
