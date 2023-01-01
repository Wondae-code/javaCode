package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1541 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        ArrayList<String> words = new ArrayList<>();

        String part = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '+' || c == '-'){
                words.add(part);
                words.add(String.valueOf(c));
                part = "";
            }else{
                part += c;
            }
        }
        words.add(part);

        ArrayList<Integer> mid = new ArrayList<>();

        int sum = 0;
        for (String s: words) {
            if(s.equals("-")){
                mid.add(sum);
                sum = 0;
            }else if(s.equals("+")){
                continue;
            }else{
                sum += Integer.parseInt(s);
            }
        }
        mid.add(sum);

        sum = mid.get(0);
        for (int i = 1; i < mid.size(); i++) {
            sum -= mid.get(i);
        }

        System.out.println(sum);
    }
}
