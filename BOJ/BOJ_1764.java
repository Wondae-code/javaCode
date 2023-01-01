package SSAFY;// 교집합??
// 투 포인터??

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class BOJ_1764 {
    static String[] inputs;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        HashSet<String> noHear = new HashSet<>();
        HashSet<String> noSee = new HashSet<>();

        for (int i = 0; i < N; i++) {
            noHear.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            noSee.add(br.readLine());
        }

        noHear.retainAll(noSee);

        ArrayList<String> ans = new ArrayList<>(noHear);

        Collections.sort(ans);

        System.out.println(ans.size());
        for (String s : ans) {
            System.out.println(s);
        }
    }
}
