import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {
    // stack에 값을 넣으면서 앞의 글자를 비교하여 문자열이면 
    static char[] Input, bomb;
    static boolean checksum;
    static Stack<Character> s;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Input = br.readLine().toCharArray();
        bomb = br.readLine().toCharArray();
        s = new Stack<>();

        for (int i = 0; i < Input.length; i++) {
            s.push(Input[i]);
            if(i >= bomb.length-1){
                checksum = false;
                for (int j = s.size()-1, idx = bomb.length - 1; j > s.size()-1 - bomb.length ; j--, idx--) {
                    if(s.get(j) == bomb[idx]){
                        checksum = true;
                    }else{
                        checksum = false;
                        break;
                    }
                }
                // 폭탄을 찾으면
                if(checksum){
                    for (int j = 0; j < bomb.length; j++) {
                        s.pop();
                    }
                }
            }
        }

        System.out.println(s.size() != 0 ? toString(s): "FRULA");
    }

    private static String toString(Stack<Character> s){
        StringBuilder sb = new StringBuilder();
        for (char c: s) {
            sb.append(c);
        }
        return sb.toString();
    }
}
