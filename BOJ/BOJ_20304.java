package SSAFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_20304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = Integer.parseInt(br.readLine());
        // 왠지 멋져보여..
        int[] hacker = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 숫자 탐색
        String flag = Integer.toBinaryString(N); // 비트 마스킹을 위한 플래그
        int length = flag.length(); // 비트 길이 제한
        // 시도한 수들의 공통 부분 찾기
        int temp = 0;
        for (int i : hacker) {
            temp ^= i;
        }

        // 공통 부분은 냅둔 채로 자리수 하나씩 체크하기
        System.out.println(Integer.toBinaryString(~temp));
    }
}
