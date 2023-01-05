package level1;

import java.util.HashMap;

public class 성격_유형_검사하기 {
    // R <-> T, C <-> F, J <-> M, A <-> N
    static final String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
    // 7가지 선택 경우의 수 -> -3 -2 -1 0 1 2 3
    static final int[] choices = {5, 3, 2, 7, 5};

    public static void main(String[] args) {
        // 값 초기화
        HashMap<Character, Integer> type = new HashMap<>();
        type.put('R', 0);
        type.put('T', 0);
        type.put('A', 0);
        type.put('N', 0);
        type.put('C', 0);
        type.put('F', 0);
        type.put('M', 0);
        type.put('J', 0);

        // 문제 선택 반영
        for (int idx = 0; idx < survey.length; idx++) {
            String question = survey[idx];
            int choice = choices[idx];
            if(choice < 4){
                type.put(question.charAt(0), type.get(question.charAt(0)) + Math.abs(choice - 4));
            }else if(choice > 4){
                type.put(question.charAt(1), type.get(question.charAt(1)) + choice - 4);
            }
        }

        // 결과 파악
        StringBuilder sb = new StringBuilder();
        // R<->T
        if (type.get('R') >= type.get('T')) {
            sb.append('R');
        } else {
            sb.append('T');
        }

        // C <-> F
        if (type.get('C') >= type.get('F')) {
            sb.append('C');
        } else {
            sb.append('F');
        }

        // J <-> M
        if (type.get('J') >= type.get('M')) {
            sb.append('J');
        } else {
            sb.append('M');
        }

        // A <-> N
        if (type.get('A') >= type.get('N')) {
            sb.append('A');
        } else {
            sb.append('N');
        }

        System.out.println(sb.toString());
    }
}
