package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_16637 {
    // 괄호 안 연산자는 하나뿐
    // 괄호를 넣음으로서 최대값을 구하기
    // 순열 조합 같은데?
    static char[] input;
    static boolean[] check;
    static int Ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = new char[Integer.parseInt(br.readLine())];
        check = new boolean[input.length + 1];
        String raw = br.readLine();
        for (int i = 0; i < input.length; i++) {
            input[i] = raw.charAt(i);
        }

        // 괄호가 없는 것을 초기 값으로 둔다
        Ans = cal(raw.toCharArray());

        permutation(0);

        System.out.println(Ans);
    }

    // src : 짝수, dst (src+3) == input.length까지 반복
    private static void permutation(int src) {
        // 탈출 조건 2. src가 input
        if (src > input.length - 3) {
            return;
        }
        // 탈출 조건 1. 괄호 사이에 들어가면 안됨
        if (check[src + 1]) {
            return;
        }

        // src, src+3을 집는다
        check[src] = true;
        check[src + 3] = true;
        // System.out.println(Arrays.toString(check));
        // 괄호 넣고 풀기
        Ans = Math.max(Ans, parse());
        permutation(src + 4);
        // src, src+3을 안 집는다
        check[src] = false;
        check[src + 3] = false;
        permutation(src + 2);
    }

    private static int parse() {
        boolean open = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < check.length + input.length; i++) {
            // 홀수의 경우 : input
            if (i % 2 == 1) {
                sb.append(input[i / 2]);
            }
            // 짝수의 경우 : check
            else {
                // 괄호가 있을 때
                if (check[i / 2]) {
                    // 여는 괄호
                    if (!open) {
                        sb.append('(');
                        open = true;
                    } else {
                        sb.append(')');
                        open = false;
                    }
                }
                // 없으면 넘어감
            }

        }
        //System.out.println(sb);

        return cal(sb.toString().toCharArray());
    }

    private static int cal(char[] s) {
        // 괄호 안을 먼저 계산하기
        for (int i = 0; i < s.length - 4 ; i++) {
            if (s[i] == '(') {
                // 괄호 안 계산
                switch (s[i + 2]) {
                    case '+':
                        s[i] = (char) (s[i + 1] + s[i + 3] - '0' + '0');
                        break;
                    case '-':
                        s[i] = (char) (s[i + 1] - s[i + 3] + '0' + '0');
                        break;
                    case '*':
                        s[i] = (char) ((s[i + 1] - '0') * (s[i + 3] - '0') + '0' + '0');
                        break;
                }
                // 계산 이후는 비움
                for (int j = 1; j <= 4; j++) {
                    s[i+j] = 0;
                }
                ArrayList<Character> temp = new ArrayList<>();
                for (char c : s) {
                    if(c == 0){
                        continue;
                    }
                    temp.add(c);
                }
                s = toCharArr(temp);
            }
        }
        
        // 실제 계산하기
        int val = 0;
        if(judge(s[0])) {
            val = s[0] - '0';
        }else{
            val = s[0] - '0' - '0';
        }
        for (int i = 0; i < s.length - 1; i++) {
            switch (s[i]) {
                case '+':
                    if(judge(s[i+1])){
                        val += s[i + 1] - '0';
                    }else{
                        val += s[i + 1] - '0' - '0';
                    }
                    break;
                case '-':
                    if(judge(s[i+1])){
                        val -= s[i + 1] - '0';
                    }else{
                        val -= s[i + 1] - '0' - '0';
                    }
                    break;
                case '*':
                    if(judge(s[i+1])){
                        val *= (s[i + 1] - '0');
                    }else{
                        val *= (s[i + 1] - '0' - '0');
                    }
                    break;
            }
        }
        //System.out.println(val);
        return val;
    }
    
    private static char[] toCharArr(ArrayList<Character> arr){
        char[] charArr = new char[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            charArr[i] = arr.get(i);
        }
        
        return charArr;
    }

    private static boolean judge(char c){
        return c >= '0' && c <= '9';
    }
}
