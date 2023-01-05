package level1;

import java.util.HashMap;

public class 키패드_누르기 {
    // 오른손 엄지는 #, 왼손 엄지는 *에서 시작
    // 1, 4, 7은 왼손, 3, 6, 9는 오른손, 2, 5, 8, 0은 왼손 오른손 중 가까운 쪽, 만약 거리가 같다면 주 손에 따름
    static int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
    static String hand = "left";
    public static void main(String[] args) {
        HashMap<Integer, int[]> keypad = new HashMap<>();
        int k = 1;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                keypad.put(k, new int[]{r, c});
                k++;
            }
        }
        keypad.put(0, new int[]{3, 1});


        int[] curR = {3, 2};
        int[] curL = {3, 0};

        String answer = "";

        for (int n: numbers) {
            switch(n){
                case 1:
                case 4:
                case 7:
                    curL[0] = n/3;
                    curL[1] = 0;
                    answer += "L";
                    break;
                case 3:
                case 6:
                case 9:
                    curR[0] = n/3 - 1;
                    curR[1] = 2;
                    answer += "R";
                    break;
                case 2:
                case 5:
                case 8:
                case 0:
                    // 가까운 손파악
                    int distL = Math.abs(keypad.get(n)[0] - curL[0]) + Math.abs(keypad.get(n)[1] - curL[1]);
                    int distR = Math.abs(keypad.get(n)[0] - curR[0]) + Math.abs(keypad.get(n)[1] - curR[1]);
                    if(distL == distR) {
                        if(hand.equals("right")){
                            answer += "R";
                            curR[0] = keypad.get(n)[0];
                            curR[1] = keypad.get(n)[1];
                        }else{
                            answer += "L";
                            curL[0] = keypad.get(n)[0];
                            curL[1] = keypad.get(n)[1];
                        }
                    }else{
                        if(distL > distR){
                            answer += "R";
                            curR[0] = keypad.get(n)[0];
                            curR[1] = keypad.get(n)[1];
                        }else{
                            answer += "L";
                            curL[0] = keypad.get(n)[0];
                            curL[1] = keypad.get(n)[1];
                        }
                    }
                    break;
            }
        }
        System.out.println(answer);
    }
}
