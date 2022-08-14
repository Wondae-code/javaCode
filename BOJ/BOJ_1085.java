package SSAFY;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1085 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] raw = br.readLine().split(" ");
        int x = Integer.parseInt(raw[0]);
        int y = Integer.parseInt(raw[1]);
        int w = Integer.parseInt(raw[2]);
        int h = Integer.parseInt(raw[3]);

        int min = Math.min(x, Math.min(y, Math.min(w-x, h-y)));

        System.out.println(min);
    }
}
