package SSAFY;
// https://www.acmicpc.net/problem/17413
import java.util.ArrayList;
import java.util.Scanner;


public class BOJ_17413 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean sign = false;

        // 글자 입력 받기
        String raw = sc.nextLine();
        ArrayList<String> words = new ArrayList<String>();

        // 단어 자르기
        int start = 0;
        boolean brace = raw.charAt(0) == '<' ? true : false;
        boolean close = false;
        for (int i = 1; i < raw.length(); i++) {
            if(brace){
                if(raw.charAt(i) == '>'){
                    words.add(raw.substring(start,i+1));
                    start = i+1;
                    brace = false;
                }
            }else{
                if(raw.charAt(i) == '<'){
                    words.add(raw.substring(start,i));
                    start = i;
                    brace = true;
                }else if(raw.charAt(i) == ' '){
                    words.add(raw.substring(start,i));
                    start = i+1;
                }
            }
        }
        if(start != raw.length()){
            words.add(raw.substring(start,raw.length()));
        }
        
        // 단어 뒤집기
        for (String word: words) {
            if(word.equals("") || word.contains("<") || word.contains(">")) continue;

            char[] temp = word.toCharArray();
            for (int i = 0; i < temp.length/2; i++) {
                char ct = temp[temp.length - 1 - i];
                temp[temp.length - 1 - i] = temp[i];
                temp[i] = ct;
            }
            // 바꾼 것과 교체
            words.set(words.indexOf(word), new String(temp));
        }
        // 출력
        for (int i = 0; i < words.size(); i++) {
            if(words.get(i).equals("") || words.get(i).contains("<") || words.get(i).contains(">")){
                System.out.print(words.get(i));
            }else if((i != words.size() - 1) && !(words.get(i+1).contains("<"))){ // OOI?
                System.out.print(words.get(i) + " ");
            }else{
                System.out.print(words.get(i));
            }
        }
        System.out.println();
    }
}
