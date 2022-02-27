import java.util.ArrayList;
import java.util.Scanner;

// 입력 예시 : TXY (T : 카드 타입, XY : 카드 넘버)


public class SWEA_4047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            // 카드 값 입력 받기
            String raw = sc.next();
            // 3글자마다 나누기
            ArrayList<String> cards = new ArrayList<>();
            for (int idx = 1; idx <= raw.length(); idx++) {
                if(idx%3 == 0){
                    cards.add(raw.substring(idx - 3, idx));
                }
            }

            // 카드 분석
            boolean[][] check = new boolean[4][13];
            boolean flag = false; // 중복 체크 플래그
            for (String card : cards) {
                int type = -1;
                switch (card.charAt(0)){
                    case 'S':
                        type = 0;
                        break;
                    case 'D':
                        type = 1;
                        break;
                    case 'H':
                        type = 2;
                        break;
                    case 'C':
                        type = 3;
                        break;
                }
                int num = Integer.parseInt(card.substring(1,card.length()));

                if(!check[type][num-1]){
                    check[type][num-1] = true;
                }else{
                    flag = true;
                    break;
                }
            }

            if(flag){
                System.out.printf("#%d ERROR\n", test_case);
            }else{
                StringBuilder sb = new StringBuilder();
                for (boolean[] booleans : check) {
                    int cnt = 0;
                    for (int j = 0; j < check[0].length; j++) {
                        if (!booleans[j]) cnt++;
                    }
                    sb.append(" ").append(cnt);
                }

                System.out.printf("#%d%s\n", test_case, sb);
            }
        }
   }
}
